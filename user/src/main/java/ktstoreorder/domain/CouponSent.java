package ktstoreorder.domain;

import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class CouponSent extends AbstractEvent {

    private Long id;
    private String couponName;
    private String content;
}
