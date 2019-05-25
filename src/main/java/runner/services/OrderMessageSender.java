package runner.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runner.config.RabbitConfig;

import java.io.Serializable;

@Service
public class OrderMessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(Order order) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_ORDERS, order);

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public class Order implements Serializable {
        private String orderNumber;
        private String productId;
        private double amount;
    }
}

