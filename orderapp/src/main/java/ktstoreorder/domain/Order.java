package ktstoreorder.domain;

import ktstoreorder.domain.OrderPlaced;
import ktstoreorder.domain.OrderStatusUpdated;
import ktstoreorder.external.Menu;
import ktstoreorder.OrderappApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

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
    
    private Long price;
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    
    private String paymentId;
    
    private String paymentStatus;

    private String orderInfo;
    
    @Embedded
    private UserId userId;
    
    @Embedded
    private StroeId stroeId;
    
    @ElementCollection
    private List<MenuId> menuIds;

    @PostPersist
    public void onPostPersist(){
        ObjectMapper mapper = new ObjectMapper();
        Map<Long, Object> menuMap = mapper.convertValue(getMenuIds(), Map.class);
        List<Long> menuIds = (List<Long>) menuMap.get("id");
        List<Menu> menus = OrderappApplication.applicationContext
        .getBean(ktstoreorder.external.MenuService.class)
        .getMenu(menuIds);

        Long totalPrice = menus.stream()
        .map(Menu::getPrice)
        .reduce(0L, Long::sum);

        String MenuInfo = "주문한 음식: " + menus.stream()
        .map(Menu::getMenuName)
        .collect(Collectors.joining(", "));

        this.setPrice(totalPrice);
        this.setOrderInfo(MenuInfo);


        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();
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
