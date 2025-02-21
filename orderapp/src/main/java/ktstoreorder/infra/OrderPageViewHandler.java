package ktstoreorder.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import ktstoreorder.config.kafka.KafkaProcessor;
import ktstoreorder.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderPageViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private OrderPageRepository orderPageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderAccepted_then_CREATE_1(
        @Payload OrderAccepted orderAccepted
    ) {
        try {
            if (!orderAccepted.validate()) return;

            // view 객체 생성
            OrderPage orderPage = new OrderPage();
            // view 객체에 이벤트의 Value 를 set 함
            orderPage.setOrderInfo(orderAccepted.getOrderInfo());
            orderPage.setRequestInfo(orderAccepted.getRequestInfo());
            orderPage.setOrderStatus(
                String.valueOf(orderAccepted.getFoodStatus())
            );
            // view 레파지 토리에 save
            orderPageRepository.save(orderPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
