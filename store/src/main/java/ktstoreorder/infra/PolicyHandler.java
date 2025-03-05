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
    MenuRepository menuRepository;

    @Autowired
    StoreRepository storeRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderPlaced'"
    )
    public void wheneverOrderPlaced_AcceptOrder(
        @Payload OrderPlaced orderPlaced
    ) {
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener AcceptOrder : " + orderPlaced + "\n\n"
        );

        // Sample Logic //
        Store.acceptOrder(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='PromotionRegistered'"
    )
    public void wheneverPromotionRegistered_ReceiveMarketingInfo(
        @Payload PromotionRegistered promotionRegistered
    ) {
        PromotionRegistered event = promotionRegistered;
        System.out.println(
            "\n\n##### listener ReceiveMarketingInfo : " +
            promotionRegistered +
            "\n\n"
        );

        // Sample Logic //
        Store.receiveMarketingInfo(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
