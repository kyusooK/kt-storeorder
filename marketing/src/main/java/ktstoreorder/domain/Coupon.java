package ktstoreorder.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import ktstoreorder.MarketingApplication;
import ktstoreorder.domain.CouponSent;
import lombok.Data;

@Entity
@Table(name = "Coupon_table")
@Data
//<<< DDD / Aggregate Root
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String couponName;

    private String content;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        ktstoreorder.external.Reservation reservation = new ktstoreorder.external.Reservation();
        reservation.setTaskId(this.getId().toString());
        reservation.setTitle(this.getCouponName());
        // reservation.setTargetUserIds();
        reservation.setDescription("쿠폰 내용: " + this.getContent());
        reservation.setNow(true);
        MarketingApplication.applicationContext
            .getBean(ktstoreorder.external.ReservationService.class)
            .createReservation(reservation);

        CouponSent couponSent = new CouponSent(this);
        couponSent.publishAfterCommit();
    }

    public static CouponRepository repository() {
        CouponRepository couponRepository = MarketingApplication.applicationContext.getBean(
            CouponRepository.class
        );
        return couponRepository;
    }
}
//>>> DDD / Aggregate Root
