package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderStatusUpdated extends AbstractEvent {

    private Long id;
    private OrderStatus orderStatus;

    public OrderStatusUpdated(Order aggregate) {
        super(aggregate);
    }

    public OrderStatusUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
