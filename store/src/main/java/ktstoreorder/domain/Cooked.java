package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class Cooked extends AbstractEvent {

    private Long id;
    private FoodStatus foodStatus;

    public Cooked(Stroe aggregate) {
        super(aggregate);
    }

    public Cooked() {
        super();
    }
}
//>>> DDD / Domain Event
