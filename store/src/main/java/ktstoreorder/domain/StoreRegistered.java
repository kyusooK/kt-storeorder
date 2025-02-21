package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class StoreRegistered extends AbstractEvent {

    private Long id;
    private String healthCertificate;
    private String storeInfo;
    private String storeLocation;

    public StoreRegistered(Store aggregate) {
        super(aggregate);
    }

    public StoreRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
