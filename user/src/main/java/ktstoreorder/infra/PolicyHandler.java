package ktstoreorder.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import ktstoreorder.config.kafka.KafkaProcessor;
import ktstoreorder.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    UserRepository userRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CouponSent'"
    )
    public void wheneverCouponSent_SendCoupon(@Payload CouponSent couponSent) {
        CouponSent event = couponSent;
        System.out.println(
            "\n\n##### listener SendCoupon : " + couponSent + "\n\n"
        );

        // Sample Logic //
        User.sendCoupon(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
