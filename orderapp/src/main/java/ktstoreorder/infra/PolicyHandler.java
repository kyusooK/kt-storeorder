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
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderAccepted'"
    )
    public void wheneverOrderAccepted_UpdateOrderStatus(
        @Payload OrderAccepted orderAccepted
    ) {
        OrderAccepted event = orderAccepted;
        System.out.println(
            "\n\n##### listener UpdateOrderStatus : " + orderAccepted + "\n\n"
        );

        // Sample Logic //
        Order.updateOrderStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Cooked'"
    )
    public void wheneverCooked_UpdateOrderStatus(@Payload Cooked cooked) {
        Cooked event = cooked;
        System.out.println(
            "\n\n##### listener UpdateOrderStatus : " + cooked + "\n\n"
        );

        // Sample Logic //
        Order.updateOrderStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PickUpNotified'"
    )
    public void wheneverPickUpNotified_UpdateOrderStatus(
        @Payload PickUpNotified pickUpNotified
    ) {
        PickUpNotified event = pickUpNotified;
        System.out.println(
            "\n\n##### listener UpdateOrderStatus : " + pickUpNotified + "\n\n"
        );

        // Sample Logic //
        Order.updateOrderStatus(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='RequstPaymentCompleted'"
    )
    public void wheneverRequstPaymentCompleted_UpdatePaymentInfo(
        @Payload RequestPaymentCompleted requestPaymentCompleted
    ) {
        RequestPaymentCompleted event = requestPaymentCompleted;
        System.out.println(
            "\n\n##### listener UpdatePaymentInfo : " +
            requestPaymentCompleted +
            "\n\n"
        );

        // Sample Logic //
        Order.updatePaymentInfo(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
