package ktstoreorder.domain;

import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class PickUpNotified extends AbstractEvent {

    private Long id;
    private Object foodStatus;
    private Object orderId;
}
