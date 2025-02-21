package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class StoreApproved extends AbstractEvent {

    private Long id;
    private Boolean storeApproval;

    public StoreApproved(Store aggregate) {
        super(aggregate);
    }

    public StoreApproved() {
        super();
    }
}
//>>> DDD / Domain Event
