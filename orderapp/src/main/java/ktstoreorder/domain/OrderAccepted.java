package ktstoreorder.domain;

import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class OrderAccepted extends AbstractEvent {

    private Long id;
    private Object menuId;
    private String orderInfo;
    private String requestInfo;
    private Object orderId;
    private Object foodStatus;
}
