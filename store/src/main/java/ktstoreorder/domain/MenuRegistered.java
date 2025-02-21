package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class MenuRegistered extends AbstractEvent {

    private Long id;
    private String menuName;
    private Long price;
    private String menuInfo;
    private String ingredients;

    public MenuRegistered(Menu aggregate) {
        super(aggregate);
    }

    public MenuRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
