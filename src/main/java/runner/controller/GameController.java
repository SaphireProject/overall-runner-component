package runner.controller;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import runner.config.JwtGenerator;
import runner.data.Counter;
import runner.data.ParameterMetida;
import runner.data.SetMetida;
import runner.jsonObject.*;
import runner.jsonObjectUI.GameSnapshot;
import runner.jsonObjectUI.StrategyJson;
import runner.models.ParametersRoom;
import runner.models.Snapshots;
import runner.models.SnapshotsHelper;
import runner.models.UsersRoom;
import runner.repository.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/game")
public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    ParametersRoomRepository parametersRoomRepository;

    @Autowired
    StrategiesRepository strategiesRepository;

    @Autowired
    UsersRoomRepository usersRoomRepository;

    private static int idGame = 22;

    private final static String QUEUE_NAME = "snapshots";

    @Autowired
    Counter counter;

    @Autowired
    SnapshotsHelperRepository snapshotsHelperRepository;

    @Autowired
    SnapshotsRepository snapshotsRepository;

    @Autowired
    JwtGenerator jwtGenerator;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public void startMatida() {

    }

    @RequestMapping(value = "/parameters", method = RequestMethod.POST)
    public ParameterMetida setParameters(@RequestBody SetMetida requestBody) {
        ParametersRoom parametersRoom = parametersRoomRepository.getByIdIdRoom(requestBody.getId());
        JSONObject json = new JSONObject(parametersRoom.getValue());

        List<UsersRoom> usersRoomList = usersRoomRepository.getByIdIdRoom(requestBody.getId());
        List<StrategyJson> strategyJsonList = new ArrayList<>();
        for (UsersRoom us : usersRoomList) {
            LOGGER.info(String.valueOf(us.getId().getIdUser()));
            String strategy = strategiesRepository.getById(us.getIdStrategy()).getDescription();
            String name = strategiesRepository.getById(us.getIdStrategy()).getName();
            StrategyJson strategyJson = new StrategyJson(us.getIdStrategy(), name, strategy);
            strategyJsonList.add(strategyJson);
        }

        ParameterMetida parameterMetida = new ParameterMetida(json.getInt("countOfPlayers"), json.getInt("heightOfMapForGame"),
                json.getInt("widthOfMapForGame"), strategyJsonList);
        return parameterMetida;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public GameSnapshot getSnapshots(@RequestHeader("Authorization") String request,
                                     @RequestParam("page") int page,
                                     @RequestParam("size") int size) throws Exception {

        int id = jwtGenerator.decodeNew(request).getUserData().getId().intValue();
        UsersRoom us = usersRoomRepository.getByIdIdUser(id);
        if (us.getId().getIdRoom() == idGame) {
            GameSnapshot gameSnapshot = new GameSnapshot();
            List<FrameJson> listS = new ArrayList<>();
            List<Snapshots> snapshotsList = snapshotsRepository.findAll(idGame, page, size);
            for (Snapshots snapshots : snapshotsList) {

                Gson gson = new Gson();
                FrameJson frameJson = gson.fromJson(snapshots.getSnapshot(), FrameJson.class);

                listS.add(frameJson);
            }

            Gson gson = new Gson();
            SnapshotsHelper snapshotsHelper = snapshotsHelperRepository.getByIdIdRoom(idGame);
            PreloadJson preloadFinalJson = gson.fromJson(snapshotsHelper.getValue(), PreloadJson.class);
            gameSnapshot.setPreload(preloadFinalJson);
            gameSnapshot.setFrames(listS);

            return gameSnapshot;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room was not found.");
        }

    }

    @RequestMapping(value = "/preload", method = RequestMethod.POST)
    public void getPreload(@RequestBody PreloadFinalJson preloadFinalJson) {
        PreloadFinalJson preloadFinalJson1 = new PreloadFinalJson();
        preloadFinalJson1.setPreload(preloadFinalJson.getPreload());

//        PreloadForDB preloadForDB = new PreloadForDB();
//        preloadForDB.setIdGame(idGame);
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//        String jsonPreload = null;
//
//        try {
//            jsonPreload = mapper.writeValueAsString(preloadFinalJson1);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        Gson gson = new Gson();
        String json = gson.toJson(preloadFinalJson.getPreload());

        SnapshotsHelper snapshotsHelper = new SnapshotsHelper(idGame, "preload");
        snapshotsHelper.setValue(json);
        snapshotsHelperRepository.save(snapshotsHelper);
    }

    @RequestMapping(value = "/animation")
    public void getFrameJson(@RequestBody FrameJson frameJson) throws Exception {
//        FrameJson frameJson1 = new FrameJson();
//        frameJson1.setSnapshotNumber(frameJson.getSnapshotNumber());
//        frameJson1.setAnimations(frameJson.getAnimations());
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
//
//        String jsonFrame = null;
//
//        try {
//            jsonFrame = mapper.writeValueAsString(frameJson);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        FrameForDB frameForDB = new FrameForDB();
//        frameForDB.setId(frameJson.getSnapshotNumber());
//        frameForDB.setValue(jsonFrame);

        //куда то сохранять

        Gson gson = new Gson();
        String json = gson.toJson(frameJson);

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, json.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + json + "'");

            counter.increaseNumber();
        }

        LOGGER.info(String.valueOf(counter.getNumber()));

        if (counter.getNumber() == 10) {
            factory = new ConnectionFactory();
            factory.setHost("localhost");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");

                Snapshots snapshots = new Snapshots();
                snapshots.setSnapshot(message);
                snapshots.setIdRoom(idGame);

                snapshotsRepository.save(snapshots);
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            });

            counter.resetNumber();
        }

    }

    @RequestMapping(value = "/strategy", method = RequestMethod.GET)
    public String getStrategy(@RequestParam("idOfChosenStrategy") int id) {
        String str = strategiesRepository.getById(id).getDescription();
        return str;
    }


}

// для Миши сделать ответ стратегией обычной

// в общем про работу раннера, проблемы его, 3 слайда, на одном схема, и короткая инфа
// про реббит основная инфа