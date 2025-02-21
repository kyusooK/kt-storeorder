package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PromotionRegistered extends AbstractEvent {

    private Long id;

    public PromotionRegistered(Promotion aggregate) {
        super(aggregate);
    }

    public PromotionRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
