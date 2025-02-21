package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String requestInfo;
    private OrderStatus orderStatus;
    private UserId userId;
    private Long price;
    private List<MenuIds> menuIds;
    private String orderInfo;
    private StoreId storeId;

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
//>>> DDD / Domain Event
