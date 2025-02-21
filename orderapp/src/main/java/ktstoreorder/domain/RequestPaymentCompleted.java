package ktstoreorder.domain;

import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class RequestPaymentCompleted extends AbstractEvent {

    private Long id;
    private Long itemId;
    private String paymentId;
    private String status;
    private String reason;
}
