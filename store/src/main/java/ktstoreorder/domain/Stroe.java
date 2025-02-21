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
@Table(name="Stroe_table")
@Data

//<<< DDD / Aggregate Root
public class Stroe  {


    
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
    
    
    
    @Embedded
    private OrderId orderId;
    
    
    
    
    private String marketInfo;


    public static StroeRepository repository(){
        StroeRepository stroeRepository = StoreApplication.applicationContext.getBean(StroeRepository.class);
        return stroeRepository;
    }

    public void 조리(){
        //
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
          = StroeApplication.applicationContext
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
        Stroe stroe = new Stroe();
        repository().save(stroe);

        OrderAccepted orderAccepted = new OrderAccepted(stroe);
        orderAccepted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if orderPlaced.userIdstroeIdmenuId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> orderMap = mapper.convertValue(orderPlaced.getUserId(), Map.class);
        // Map<Long, Object> orderMap = mapper.convertValue(orderPlaced.getStroeId(), Map.class);
        // Map<Long, Object> orderMap = mapper.convertValue(orderPlaced.getMenuId(), Map.class);

        repository().findById(orderPlaced.get???()).ifPresent(stroe->{
            
            stroe // do something
            repository().save(stroe);

            OrderAccepted orderAccepted = new OrderAccepted(stroe);
            orderAccepted.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void receiveMarketingInfo(ReservationCreated reservationCreated){
        
        //implement business logic here:
        
        /** Example 1:  new item 
        Stroe stroe = new Stroe();
        repository().save(stroe);

        */

        /** Example 2:  finding and process
        

        repository().findById(reservationCreated.get???()).ifPresent(stroe->{
            
            stroe // do something
            repository().save(stroe);


         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
