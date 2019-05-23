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
import runner.models.Strategies;
import runner.models.UsersRoom;
import runner.repository.StrategiesRepository;
import runner.repository.UsersRoomRepository;
import runner.services.UsersRoomService;

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
    public Map<String, String> strategies(@RequestHeader("Authorization") String request, @RequestParam("game") String game) {

        int id = jwtGenerator.decodeNew(request).getUserData().getId().intValue();

        JSONArray responseArr = new JSONArray();

        List<Strategies> strategiesList = strategiesRepository.getByIdUserAndTypeGame(id, game);
        for (Strategies strategies : strategiesList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", strategies.getId());
            jsonObject.put("name", strategies.getName());
            responseArr.put(jsonObject);
        }

        Map<String, String> response = new HashMap<>();
        response.put("strategies", String.valueOf(responseArr));
        return response;
    }

    @RequestMapping(value = "/user/strategy", method = RequestMethod.GET)
    public Map<String, String> strategy(@RequestParam("idOfChosenStrategy") int idOfChosenStrategy) {


        Map<String, String> response = null;
        try {
            Strategies strategies = strategiesRepository.getById(idOfChosenStrategy);

            response = new HashMap<>();
            response.put("id", String.valueOf(strategies.getId()));
            response.put("name", strategies.getName());
            response.put("description", strategies.getDescription());
        } catch (Exception e) {
        }
        return response;
    }

    static final String URL_USER_INFO = "http://localhost:8084/user/info";

    @RequestMapping(value = "/user-ready", method = RequestMethod.POST)
    public Map<String, String> userReady(@RequestBody String requestBody) {
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

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("status", String.valueOf(usersRoom.getStatus()));
        responseMap.put("username", username);
        responseMap.put("idOfChosenStrategy", String.valueOf(usersRoom.getIdStrategy()));
        responseMap.put("chosenTank", usersRoom.getChosenTank());
        responseMap.put("idOfRoom", String.valueOf(idOfRoom));
        return responseMap;
    }

    @Autowired
    JwtGenerator jwtGenerator;

    @RequestMapping(value = "/notification-new", method = RequestMethod.GET)
    public Map<String, String> notification(@RequestHeader("Authorization") String request) {

        int id = jwtGenerator.decodeNew(request).getUserData().getId().intValue();

        List<UsersRoom> usersRoomList = usersRoomRepository.findByIdIdUser(id);

        int countOfNotifications = 0;
        for (UsersRoom us : usersRoomList) {
            if (!us.isCheckInvite()) {
                countOfNotifications++;
            }
        }

        Map<String, String> responseData = new HashMap<>();
        responseData.put("countOfNotifications", String.valueOf(countOfNotifications));
        return responseData;
    }


}