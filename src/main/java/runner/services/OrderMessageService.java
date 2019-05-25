package runner.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import runner.config.RabbitConfig;

@Service
public class OrderMessageService {
    private final RabbitTemplate rabbitTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public OrderMessageService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrder(OrderMessageSender.Order order) throws Exception{
        String orderJson = objectMapper.writeValueAsString(order);
        Message message = MessageBuilder
                .withBody(orderJson.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .build();

        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_ORDERS, order);
    }
}

