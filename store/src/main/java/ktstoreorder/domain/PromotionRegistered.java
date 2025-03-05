package ktstoreorder.domain;

import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class PromotionRegistered extends AbstractEvent {

    private Long id;
    private String promotionName;
    private String content;
    private Date startAt;
    private Date endAt;
    private Object storeId;
}
