package runner.thread;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runner.data.Counter;
import runner.models.Snapshots;
import runner.repository.SnapshotsRepository;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class ThreadQueue implements Runnable {

    private int idRoom;

    @Autowired
    SnapshotsRepository snapshotsRepository;

    ConnectionFactory factory = new ConnectionFactory();

    private final static String QUEUE_NAME = "snapshots";

    @Autowired
    Counter counter;

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadQueue.class);

    public ThreadQueue() {
    }

    public void setidRoom(int idRoom) {
        this.idRoom = idRoom;
        run();
    }

    @Override
    public void run() {
        try {
            if (counter.getNumber() == 2) {
                factory = new ConnectionFactory();
                factory.setHost("localhost");
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();

                channel.queueDeclare(QUEUE_NAME, false, false, false, null);

                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");

                    Snapshots snapshots = new Snapshots();
                    snapshots.setSnapshot(message);
                    snapshots.setIdRoom(idRoom);
                    snapshots.setCounter(1);
                    LOGGER.info("Save {}", message);
                    try {
                        snapshotsRepository.save(snapshots);
                    } catch (Exception e) {
                        LOGGER.info(String.valueOf(e));
                    }
                };
                channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
                });

                counter.resetNumber();
            }

        } catch (IOException e) {
            LOGGER.info("IOException = " + e);
        } catch (TimeoutException e) {
            LOGGER.info("TimeoutException = " + e);
        }
    }
}
