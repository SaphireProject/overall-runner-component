package runner.thread;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import runner.data.Counter;
import runner.models.Room;
import runner.models.Snapshots;
import runner.repository.RoomRepository;
import runner.repository.SnapshotsRepository;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ThreadQueue implements Runnable {

    private int idRoom;

    Counter counter;

    @Autowired
    SnapshotsRepository snapshotsRepository;

    @Autowired
    RoomRepository roomRepository;

    ConnectionFactory factory = new ConnectionFactory();

    private final static String QUEUE_NAME = "snapshots";


    private boolean bool = false;

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadQueue.class);

    public ThreadQueue(int idRoom, Counter counter) {
        this.idRoom = idRoom;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (counter.getNumber() > 3) {
                    LOGGER.info(String.valueOf(counter.getNumber()));

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

                    if (!bool) {
                        Room room = roomRepository.findById(idRoom);
                        room.setStarted(true);
                        roomRepository.save(room);
                        bool = true;
                    }

                    counter.resetNumber();
                }
            }

        } catch (IOException e) {
            LOGGER.info("IOException = " + e);
        } catch (TimeoutException e) {
            LOGGER.info("TimeoutException = " + e);
        }
    }
}
