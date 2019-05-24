package runner.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import runner.data.ParameterMetida;
import runner.data.SetMetida;
import runner.jsonObject.*;
import runner.jsonObjectUI.StrategyJson;
import runner.models.ParametersRoom;
import runner.models.UsersRoom;
import runner.repository.ParametersRoomRepository;
import runner.repository.StrategiesRepository;
import runner.repository.UsersRoomRepository;

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

    @RequestMapping(value = "/parameters", method = RequestMethod.POST)
    public ParameterMetida setParameters(@RequestBody SetMetida requestBody) {
        ParametersRoom parametersRoom = parametersRoomRepository.getByIdIdRoom(requestBody.getId());
        JSONObject json = new JSONObject(parametersRoom.getValue());

        List<UsersRoom> usersRoomList = usersRoomRepository.getByIdIdRoom(requestBody.getId());
        List<StrategyJson> strategyJsonList = new ArrayList<>();
        for (UsersRoom us : usersRoomList) {
            String strategy = strategiesRepository.getById(us.getIdStrategy()).getDescription();
            String name = strategiesRepository.getById(us.getIdStrategy()).getName();
            StrategyJson strategyJson = new StrategyJson(us.getIdStrategy(), name, strategy);
            strategyJsonList.add(strategyJson);
        }

        ParameterMetida parameterMetida = new ParameterMetida(json.getInt("countOfPlayers"), json.getInt("heightOfMapForGame"),
                json.getInt("widthOfMapForGame"), strategyJsonList);
        return parameterMetida;
    }

    private static int idGame = 1;

    @RequestMapping(value = "/set-snapshots", method = RequestMethod.POST)
    public ObjectAnswerUI gameIsStarted(@RequestBody String requestBody) {


        return new ObjectAnswerUI();
    }


    @RequestMapping(value = "/preload", method = RequestMethod.POST)
    public void getPreload(@RequestBody PreloadFinalJson preloadFinalJson) {
        PreloadFinalJson preloadFinalJson1 = new PreloadFinalJson();
        preloadFinalJson1.setPreload(preloadFinalJson.getPreload());

        PreloadForDB preloadForDB = new PreloadForDB();
        preloadForDB.setIdGame(idGame);
        idGame++;

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonPreload = null;

        try {
            jsonPreload = mapper.writeValueAsString(preloadFinalJson1);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        preloadForDB.setValue(jsonPreload);

        //куда то сохранять

    }

    @RequestMapping(value = "/animation")
    public void getFrameJson(@RequestBody FrameJson frameJson) {
        FrameJson frameJson1 = new FrameJson();
        frameJson1.setSnapshotNumber(frameJson.getSnapshotNumber());
        frameJson1.setAnimations(frameJson.getAnimations());

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String jsonFrame = null;

        try {
            jsonFrame = mapper.writeValueAsString(frameJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        FrameForDB frameForDB = new FrameForDB();
        frameForDB.setId(frameJson.getSnapshotNumber());
        frameForDB.setValue(jsonFrame);

        //куда то сохранять

    }

}

