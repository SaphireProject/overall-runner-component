package runner.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import runner.config.RabbitConfig;
import runner.services.OrderMessageSender;

@Component
public class OrderMessageListener {
    static final Logger logger = LoggerFactory.getLogger(OrderMessageListener.class);

    @RabbitListener(queues = RabbitConfig.QUEUE_ORDERS)
    public void process(OrderMessageSender.Order order){
        logger.info("Received order");
        logger.info(order.toString());
    }

}

