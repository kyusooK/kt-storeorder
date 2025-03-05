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
    
    
    
    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus;
    
    
    
    
    private String reviewId;
    
    
    
    @Embedded
    private MenuId menuId;
    
    
    
    
    private String marketInfo;
    
    
    
    
    private String storeLocation;
    
    
    
    @Embedded
    private OrderId orderId;


    public static StoreRepository repository(){
        StoreRepository storeRepository = StoreApplication.applicationContext.getBean(StoreRepository.class);
        return storeRepository;
    }



//<<< Clean Arch / Port Method
    public void cook(CookCommand cookCommand){
        
        //implement business logic here:
        


        Cooked cooked = new Cooked(this);
        cooked.publishAfterCommit();
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void notifyPickup(NotifyPickupCommand notifyPickupCommand){
        
        //implement business logic here:
        
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        ktstoreorder.external.Reservation reservation = new ktstoreorder.external.Reservation();
        // mappings goes here
        StoreApplication.applicationContext.getBean(ktstoreorder.external.ReservationService.class)
            .createReservation(reservation);


        ktstoreorder.external.MenuQuery menuQuery = new ktstoreorder.external.MenuQuery();
        // menuQuery.set??()        
          = StoreApplication.applicationContext
            .getBean(ktstoreorder.external.Service.class)
            .menu(menuQuery);

        PickUpNotified pickUpNotified = new PickUpNotified(this);
        pickUpNotified.publishAfterCommit();
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public void approveStore(){
        
        //implement business logic here:
        


        StoreApproved storeApproved = new StoreApproved(this);
        storeApproved.publishAfterCommit();
    }
//>>> Clean Arch / Port Method

//<<< Clean Arch / Port Method
    public static void acceptOrder(OrderPlaced orderPlaced){
        
        //implement business logic here:
        
        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        OrderAccepted orderAccepted = new OrderAccepted(store);
        orderAccepted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if orderPlaced.userIdmenuIdstoreId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> orderMap = mapper.convertValue(orderPlaced.getUserId(), Map.class);
        // Map<Long, Object> orderMap = mapper.convertValue(orderPlaced.getMenuId(), Map.class);
        // Map<Long, Object> orderMap = mapper.convertValue(orderPlaced.getStoreId(), Map.class);

        repository().findById(orderPlaced.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);

            OrderAccepted orderAccepted = new OrderAccepted(store);
            orderAccepted.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void receiveMarketingInfo(PromotionRegistered promotionRegistered){
        
        //implement business logic here:
        
        /** Example 1:  new item 
        Store store = new Store();
        repository().save(store);

        */

        /** Example 2:  finding and process
        
        // if promotionRegistered.storeId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> promotionMap = mapper.convertValue(promotionRegistered.getStoreId(), Map.class);

        repository().findById(promotionRegistered.get???()).ifPresent(store->{
            
            store // do something
            repository().save(store);


         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
