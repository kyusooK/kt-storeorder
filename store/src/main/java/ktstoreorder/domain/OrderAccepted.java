package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderAccepted extends AbstractEvent {

    private Long id;
    private List<MenuId> menuId;
    private String orderInfo;
    private String requestInfo;
    private FoodStatus foodStatus;

    public OrderAccepted(Store aggregate) {
        super(aggregate);
    }

    public OrderAccepted() {
        super();
    }
}
//>>> DDD / Domain Event
