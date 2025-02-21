package ktstoreorder.domain;

import java.time.LocalDate;
import java.util.*;
import ktstoreorder.domain.*;
import ktstoreorder.infra.AbstractEvent;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class CouponSent extends AbstractEvent {

    private Long id;
    private String couponName;
    private String content;

    public CouponSent(Coupon aggregate) {
        super(aggregate);
    }

    public CouponSent() {
        super();
    }
}
//>>> DDD / Domain Event
