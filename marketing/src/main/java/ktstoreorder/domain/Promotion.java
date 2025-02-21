package ktstoreorder.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import ktstoreorder.MarketingApplication;
import ktstoreorder.domain.PromotionRegistered;
import lombok.Data;

@Entity
@Table(name = "Promotion_table")
@Data
//<<< DDD / Aggregate Root
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String promotionName;

    private String content;

    private Date startAt;

    private Date endAt;

    @Embedded
    private StoreId storeId;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        ktstoreorder.external.Reservation reservation = new ktstoreorder.external.Reservation();
        reservation.setTaskId(this.getId().toString());
            reservation.setTitle(this.getPromotionName());
            // reservation.setTargetUserIds();
            reservation.setDescription("기간: " + this.getStartAt().toString() + " ~ " + this.getEndAt().toString() + "프로모션 내용: "  +  this.getContent());
            reservation.setNow(true);
        MarketingApplication.applicationContext
            .getBean(ktstoreorder.external.ReservationService.class)
            .createReservation(reservation);

        PromotionRegistered promotionRegistered = new PromotionRegistered(this);
        promotionRegistered.publishAfterCommit();
    }

    public static PromotionRepository repository() {
        PromotionRepository promotionRepository = MarketingApplication.applicationContext.getBean(
            PromotionRepository.class
        );
        return promotionRepository;
    }
}
//>>> DDD / Aggregate Root
