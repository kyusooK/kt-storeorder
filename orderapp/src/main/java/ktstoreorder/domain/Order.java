package ktstoreorder.domain;

import ktstoreorder.domain.OrderPlaced;
import ktstoreorder.domain.OrderStatusUpdated;
import ktstoreorder.OrderappApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
@Table(name="Order_table")
@Data

//<<< DDD / Aggregate Root
public class Order  {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String requestInfo;
    
    private String price;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    private String paymentId;
    
    private String paymentStatus;
    
    @Embedded
    private UserId userId;
    
    @Embedded
    private StroeId stroeId;
    
    @ElementCollection
    private List<MenuId> menuId;

    @PostPersist
    public void onPostPersist(){
        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> menuMap = mapper.convertValue(getMenuId(), Map.class);
        List<Menu> menu = OrderApplication.applicationContext
        .getBean(ktstoreorder.external.MenuService.class)
        .getMenu((Long)menuMap.get("id"));


        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();



        OrderStatusUpdated orderStatusUpdated = new OrderStatusUpdated(this);
        orderStatusUpdated.publishAfterCommit();

    
    }

    public static OrderRepository repository(){
        OrderRepository orderRepository = OrderappApplication.applicationContext.getBean(OrderRepository.class);
        return orderRepository;
    }




//<<< Clean Arch / Port Method
    public static void updateOrderStatus(OrderAccepted orderAccepted){
        
        //implement business logic here:
        
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderStatusUpdated orderStatusUpdated = new OrderStatusUpdated(order);
        orderStatusUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if orderAccepted.menuIdorderId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> stroeMap = mapper.convertValue(orderAccepted.getMenuId(), Map.class);
        // Map<Long, Object> stroeMap = mapper.convertValue(orderAccepted.getOrderId(), Map.class);

        repository().findById(orderAccepted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderStatusUpdated orderStatusUpdated = new OrderStatusUpdated(order);
            orderStatusUpdated.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void updateOrderStatus(Cooked cooked){
        
        //implement business logic here:
        
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderStatusUpdated orderStatusUpdated = new OrderStatusUpdated(order);
        orderStatusUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if cooked.menuIdorderId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> stroeMap = mapper.convertValue(cooked.getMenuId(), Map.class);
        // Map<Long, Object> stroeMap = mapper.convertValue(cooked.getOrderId(), Map.class);

        repository().findById(cooked.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderStatusUpdated orderStatusUpdated = new OrderStatusUpdated(order);
            orderStatusUpdated.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void updateOrderStatus(PickUpNotified pickUpNotified){
        
        //implement business logic here:
        
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        OrderStatusUpdated orderStatusUpdated = new OrderStatusUpdated(order);
        orderStatusUpdated.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if pickUpNotified.menuIdorderId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> stroeMap = mapper.convertValue(pickUpNotified.getMenuId(), Map.class);
        // Map<Long, Object> stroeMap = mapper.convertValue(pickUpNotified.getOrderId(), Map.class);

        repository().findById(pickUpNotified.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);

            OrderStatusUpdated orderStatusUpdated = new OrderStatusUpdated(order);
            orderStatusUpdated.publishAfterCommit();

         });
        */

        
    }
//>>> Clean Arch / Port Method
//<<< Clean Arch / Port Method
    public static void updatePaymentInfo(RequstPaymentCompleted requstPaymentCompleted){
        
        //implement business logic here:
        
        /** Example 1:  new item 
        Order order = new Order();
        repository().save(order);

        */

        /** Example 2:  finding and process
        

        repository().findById(requstPaymentCompleted.get???()).ifPresent(order->{
            
            order // do something
            repository().save(order);


         });
        */

        
    }
//>>> Clean Arch / Port Method


}
//>>> DDD / Aggregate Root
