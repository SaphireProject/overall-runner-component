package runner.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import runner.config.JwtGenerator;
import runner.jsonObjectUI.StrategiesJson;
import runner.jsonObjectUI.StrategyJson;
import runner.jsonObjectUI.UserReadyJson;
import runner.models.Strategies;
import runner.models.UsersRoom;
import runner.repository.StrategiesRepository;
import runner.repository.UsersRoomRepository;
import runner.services.UsersRoomService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UsersRoomRepository usersRoomRepository;

    @Autowired
    UsersRoomService usersRoomService;

    @Autowired
    StrategiesRepository strategiesRepository;

    @RequestMapping(value = "/user/strategies", method = RequestMethod.GET)
    public StrategiesJson strategies(@RequestHeader("Authorization") String request, @RequestParam("game") String game) {

        int id = jwtGenerator.decodeNew(request).getUserData().getId().intValue();

        List<StrategyJson> list = new ArrayList<>();

        List<Strategies> strategiesList = strategiesRepository.getByIdUserAndTypeGame(id, game);
        for (Strategies strategies : strategiesList) {
            StrategyJson strategyJson = new StrategyJson(strategies.getId(), strategies.getName(), "");
            list.add(strategyJson);
        }

        StrategiesJson strategyJson = new StrategiesJson(list);
        return strategyJson;
    }

    @RequestMapping(value = "/user/strategy", method = RequestMethod.GET)
    public StrategyJson strategy(@RequestParam("idOfChosenStrategy") int idOfChosenStrategy) {
        StrategyJson strategyJson = null;
        try {
            Strategies strategies = strategiesRepository.getById(idOfChosenStrategy);
            strategyJson = new StrategyJson(strategies.getId(), strategies.getName(), strategies.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strategyJson;
    }

    static final String URL_USER_INFO = "http://localhost:8084/user/info";

    @RequestMapping(value = "/user-ready", method = RequestMethod.POST)
    public UserReadyJson userReady(@RequestBody String requestBody) {
        JSONObject jsonObject = new JSONObject(requestBody);

        String username = jsonObject.getString("username");
        int idOfRoom = jsonObject.getInt("idOfRoom");
        int idOfChosenStrategy = jsonObject.getInt("idOfChosenStrategy");
        String chosenTank = jsonObject.getString("chosenTank");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        Map<String, String> body = new HashMap<>();
        body.put("username", username);

        HttpEntity<Map> entity = new HttpEntity<>(body, headers);

        //TODO к Мише переделать запрос под GET
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseUser = restTemplate.exchange(
                URL_USER_INFO,
                HttpMethod.POST,
                entity,
                String.class);

        jsonObject = new JSONObject(responseUser.getBody());
        Integer id = jsonObject.getInt("id");

        UsersRoom usersRoom = usersRoomRepository.findById(new UsersRoom(idOfRoom, id).getId());

        boolean selectColor = false;

        /*
        List<UsersRoom> usersRoomList = usersRoomRepository.getByIdIdRoom(idOfRoom);
        for (UsersRoom us : usersRoomList) {
            if (us.getChosenTank().equals(chosenTank) && us.getStatus() == 2) {
                //цет такой выбран
                selectColor = true;
            }
        }
        */

        if (!selectColor) {
            usersRoom.setChosenTank(chosenTank);
            usersRoom.setIdStrategy(idOfChosenStrategy);
            usersRoom.setStatus(2);
            usersRoom.setCheckInvite(true);
            usersRoomService.updateUsersRoom(usersRoom);
        }

        UserReadyJson userReadyJson = new UserReadyJson(true, username,
                usersRoom.getIdStrategy(), idOfRoom,
                usersRoom.getChosenTank());

        return userReadyJson;
    }

    @Autowired
    JwtGenerator jwtGenerator;

    @RequestMapping(value = "/notification-new", method = RequestMethod.GET)
    public Map<String, Integer> notification(@RequestHeader("Authorization") String request) {

        int id = jwtGenerator.decodeNew(request).getUserData().getId().intValue();

        List<UsersRoom> usersRoomList = usersRoomRepository.findByIdIdUser(id);

        int countOfNotifications = 0;
        for (UsersRoom us : usersRoomList) {
            if (!us.isCheckInvite()) {
                countOfNotifications++;
            }
        }

        Map<String, Integer> responseData = new HashMap<>();
        responseData.put("countOfNotifications", countOfNotifications);
        return responseData;
    }


}