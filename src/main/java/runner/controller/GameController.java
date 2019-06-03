package runner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.google.gson.Gson;
import com.rabbitmq.client.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import runner.data.Counter;
import runner.data.ParameterMetida;
import runner.jsonObject.FrameJson;
import runner.jsonObject.PreloadFinalJson;
import runner.jsonObject.PreloadJson;
import runner.jsonObjectUI.GameSnapshot;
import runner.jsonObjectUI.StrategyJson;
import runner.models.*;
import runner.repository.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    ParametersRoomRepository parametersRoomRepository;

    @Autowired
    StrategiesRepository strategiesRepository;

    @Autowired
    UsersRoomRepository usersRoomRepository;

    private final static String QUEUE_NAME = "snapshots";

    @Autowired
    Counter counter;

    @Autowired
    SnapshotsHelperRepository snapshotsHelperRepository;

    @Autowired
    SnapshotsRepository snapshotsRepository;

    @Autowired
    RoomRepository roomRepository;

    ConnectionFactory factory = new ConnectionFactory();

    ThreadQueue threadQueue;

    @RequestMapping(value = "/{idOfRoom}/game/start", method = RequestMethod.GET)
    public void startMatida(@PathVariable("idOfRoom") int idOfRoom) {
        DefaultDockerClientConfig config
                = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withRegistryEmail("vorotnikov_dmitry@mail.ru")
                .withRegistryPassword("b0lx9fqg")
                .withRegistryUsername("vorotnikovdmitry")
                .withDockerHost("tcp://85.119.150.240:2550").build();

        DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

        List<Image> list = dockerClient.listImagesCmd().exec();

        for (Image image : list) {
            LOGGER.info(image.getId());
        }

        String id = dockerClient.createContainerCmd("metida:latest")
                .withEnv("RUNNER_URL=http://85.119.150.240:8085/" + idOfRoom)
                .exec().getId();

        LOGGER.info("Id container {}", id);
        dockerClient.startContainerCmd(id).exec();

        threadQueue = new ThreadQueue(idOfRoom);
        threadQueue.start();
    }

    @RequestMapping(value = "{idRoom}/game/parameters", method = RequestMethod.GET)
    public ParameterMetida setParameters(@PathVariable int idRoom) {
        ParametersRoom parametersRoom = parametersRoomRepository.getByIdIdRoom(idRoom);
        JSONObject json = new JSONObject(parametersRoom.getValue());

        List<UsersRoom> usersRoomList = usersRoomRepository.getByIdIdRoom(idRoom);
        List<StrategyJson> strategyJsonList = new ArrayList<>();
        for (UsersRoom us : usersRoomList) {
            LOGGER.info(String.valueOf(us.getId().getIdUser()));
            String strategy = strategiesRepository.getById(us.getIdStrategy()).getDescription();
            String name = us.getChosenTank();
            StrategyJson strategyJson = new StrategyJson(us.getIdStrategy(), name, strategy);
            strategyJsonList.add(strategyJson);
        }

        ParameterMetida parameterMetida = new ParameterMetida(json.getInt("countOfPlayers"),
                json.getInt("heightOfMapForGame"),
                json.getInt("widthOfMapForGame"),
                strategyJsonList);
        return parameterMetida;
    }

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public GameSnapshot getSnapshots(@RequestHeader("Authorization") String request,
                                     @RequestParam("page") int page,
                                     @RequestParam("size") int size,
                                     @RequestParam("idRoom") int idRoom) throws Exception {

        PreloadJson preloadFinalJson = null;
        List<FrameJson> listS = null;

        List<Snapshots> snapshotsList = snapshotsRepository.findAll(idRoom, page, size);

        if (snapshotsList.size() == size) {
            listS = new ArrayList<>();
            for (Snapshots snapshots : snapshotsList) {

                Gson gson = new Gson();
                FrameJson frameJson = gson.fromJson(snapshots.getSnapshot(), FrameJson.class);

                listS.add(frameJson);
            }

            Gson gson = new Gson();
            SnapshotsHelper snapshotsHelper = snapshotsHelperRepository.getByIdIdRoom(idRoom);
            preloadFinalJson = gson.fromJson(snapshotsHelper.getValue(), PreloadJson.class);
        }

        GameSnapshot gameSnapshot = new GameSnapshot();
        gameSnapshot.setPreload(preloadFinalJson);
        gameSnapshot.setFrames(listS);

        return gameSnapshot;

    }

    @RequestMapping(value = "{idRoom}/game/preload", method = RequestMethod.POST)
    public void getPreload(@RequestBody PreloadJson preloadJson,
                           @PathVariable(name = "idRoom") int idRoom) {

        PreloadJson preloadJson1 = new PreloadJson();
        preloadJson1.setBlocks(preloadJson.getBlocks());
        PreloadFinalJson preloadFinalJson1 = new PreloadFinalJson();
        preloadFinalJson1.setPreload(preloadJson1);

// PreloadForDB preloadForDB = new PreloadForDB();
// preloadForDB.setIdGame(idGame);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonPreload = null;

        try {
            jsonPreload = mapper.writeValueAsString(preloadFinalJson1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

// preloadForDB.setValue(jsonPreload);

        Gson gson = new Gson();
        String json = gson.toJson(preloadJson);

        SnapshotsHelper snapshotsHelper = new SnapshotsHelper(idRoom, "preload");
        snapshotsHelper.setValue(json);
        snapshotsHelperRepository.save(snapshotsHelper);
    }

    @RequestMapping(value = "{idRoom}/game/animation")
    public void getFrameJson(@RequestBody FrameJson frameJson,
                             @PathVariable(name = "idRoom") int idRoom) throws Exception {
        Gson gson = new Gson();
        String json = gson.toJson(frameJson);

        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, json.getBytes("UTF-8"));
            LOGGER.info(" [x] Sent '" + json + "'");
            counter.increaseNumber();

            synchronized (threadQueue) {
                threadQueue.notify();
            }

        }

        LOGGER.info("Counter {}", counter.getNumber());
    }

    @RequestMapping(value = "/game/strategy", method = RequestMethod.GET)
    public String getStrategy(@RequestParam("idOfChosenStrategy") int id) {
        String str = strategiesRepository.getById(id).getDescription();
        return str;
    }

    class ThreadQueue extends Thread {

        private int idRoom;

        private boolean bool = false;

        public ThreadQueue(int idRoom) {
            this.idRoom = idRoom;
        }

        @Override
        public void run() {
            try {

                while (true) {
                    if (counter.getNumber() == 30) {
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
                            LOGGER.info("ROOOOOOOOOOOOOOOOOOOOOOM TRUE");
                            Room room = roomRepository.findById(idRoom);
                            room.setStarted(true);
                            roomRepository.save(room);
                            bool = true;
                        }
                        counter.resetNumber();
                    }
                    synchronized (this) {
                        this.wait();
                    }
                }

            } catch (IOException e) {
                LOGGER.info("IOException = " + e);
            } catch (TimeoutException e) {
                LOGGER.info("TimeoutException = " + e);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
