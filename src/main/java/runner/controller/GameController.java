package runner.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import runner.data.ParameterMetida;
import runner.data.SetMetida;
import runner.jsonObjectUI.StrategyJson;
import runner.models.ParametersRoom;
import runner.models.Strategies;
import runner.models.UsersRoom;
import runner.repository.ParametersRoomRepository;
import runner.repository.RoomRepository;
import runner.repository.StrategiesRepository;
import runner.repository.UsersRoomRepository;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    ParametersRoomRepository parametersRoomRepository;

    @Autowired
    StrategiesRepository strategiesRepository;

    @Autowired
    UsersRoomRepository usersRoomRepository;

    @RequestMapping(value = "/game/parameters", method = RequestMethod.POST)
    public ParameterMetida setParameters(@RequestBody SetMetida requestBody) {
        /*ParametersRoom parametersRoom = parametersRoomRepository.getByIdIdRoom(requestBody.getId());
        JSONObject json = new JSONObject(parametersRoom.getValue());

        List<UsersRoom> usersRoomList = usersRoomRepository.getByIdIdRoom(requestBody.getId());
        List<StrategyJson> strategyJsonList = new ArrayList<>();
        for (UsersRoom us: usersRoomList){
            Strategies strategy = strategiesRepository.getByIdUserAndTypeGame(us.getId().getIdUser(), requestBody.getType());
            StrategyJson strategyJson = new StrategyJson();
        }

        ParameterMetida parameterMetida = new ParameterMetida(json.getInt(""),json.getInt(""),json.getInt(""))

        ParameterMetida parameterMetida = new ParameterMetida();*/
        return null;
    }

}

// 2 таблички в бд
// написать контракт, и пост для параметров

