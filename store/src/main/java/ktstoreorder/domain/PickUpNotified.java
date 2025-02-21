package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PickUpNotified extends AbstractEvent {

    private Long id;
    private FoodStatus foodStatus;

    public PickUpNotified(Store aggregate) {
        super(aggregate);
    }

    public PickUpNotified() {
        super();
    }
}
//>>> DDD / Domain Event
