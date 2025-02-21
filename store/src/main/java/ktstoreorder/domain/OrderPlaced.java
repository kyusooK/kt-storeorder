package ktstoreorder.domain;

import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String requestInfo;
    private Object orderStatus;
    private Object userId;
    private Long price;
    private Object stroeId;
    private Object menuIds;
    private String orderInfo;
}
