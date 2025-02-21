package ktstoreorder.domain;

import ktstoreorder.domain.OrderAccepted;
import ktstoreorder.domain.StoreRegistered;
import ktstoreorder.StoreApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
@Table(name="Store_table")
@Data

//<<< DDD / Aggregate Root
public class Store  {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String storeInfo;
    
    private String healthCertificate;
    
    private Boolean storeApproval;
    
    private String orderInfo;
    
    private String requestInfo;

    private String storeLocation;
    
    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus;
    
    private String reviewId;
    
    @Embedded
    private List<MenuId> menuIds;
    
    @Embedded
    private OrderId orderId;
    
    private String marketInfo;

    public static StoreRepository repository(){
        StoreRepository stroeRepository = StoreApplication.applicationContext.getBean(StoreRepository.class);
        return stroeRepository;
    }

//<<< Clean Arch / Port Method
    public void approveStore(){
        
        repository().findById(this.getId()).ifPresent(store ->{
            if(this.getHealthCertificate() != null){
                this.setStoreApproval(true);

                StoreApproved storeApproved = new StoreApproved(this);
                storeApproved.publishAfterCommit();
            }else{

                this.setStoreApproval(false);
            }
        });
    }

    public static void acceptOrder(OrderPlaced orderPlaced){
        
        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> orderMap = mapper.convertValue(orderPlaced.getStroeId(), Map.class);

        repository().findById(Long.valueOf(orderMap.get("id").toString())).ifPresent(store->{
            
            store.setOrderInfo(orderPlaced.getOrderInfo());
            store.setFoodStatus(FoodStatus.ACCEPTED);
            store.setMenuIds(null);
            repository().save(store);

            OrderAccepted orderAccepted = new OrderAccepted(store);
            orderAccepted.publishAfterCommit();

         });
        
    }
//>>> Clean Arch / Port Method

//<<< Clean Arch / Port Method
    public void cook(CookCommand cookCommand){
        
        repository().findById(this.getId()).ifPresent(store -> {
            this.setFoodStatus(FoodStatus.COOKED);

            Cooked cooked = new Cooked(this);
            cooked.publishAfterCommit();
        });
        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void notifyPickup(NotifyPickupCommand notifyPickupCommand){
        
        repository().findById(this.getId()).ifPresent(store -> {
            
            ktstoreorder.external.Reservation reservation = new ktstoreorder.external.Reservation();

            reservation.setTaskId(store.getId().toString());
            reservation.setTitle("픽업 안내");
            // reservation.setTargetUserIds();
            reservation.setDescription(store.getOrderInfo() + " 에 대한 준비가 완료되었습니다. 픽업장소: " + store.getStoreLocation());
            reservation.setNow(true);
            
            StoreApplication.applicationContext.getBean(ktstoreorder.external.ReservationService.class)
                .createReservation(reservation);

            store.setFoodStatus(notifyPickupCommand.getFoodStatus());

            PickUpNotified pickUpNotified = new PickUpNotified(this);
            pickUpNotified.publishAfterCommit();
        });

    }
//>>> Clean Arch / Port Method

//<<< Clean Arch / Port Method
    public static void receiveMarketingInfo(ReservationCreated reservationCreated){
        // repository().findById(reservationCreated.getTargetUserIds()).ifPresent(store->{
        //  store.setMarketingInfo(reservationCreated.getDescription());
        // });
    }


}
//>>> DDD / Aggregate Root
