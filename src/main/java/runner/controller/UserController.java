package runner.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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
    public Map<String, String> strategy(@RequestParam("idOfRoom") int idOfRoom) {

        List<UsersRoom> usersRoomList = usersRoomRepository.getByIdIdRoom(idOfRoom);

        for (UsersRoom us : usersRoomList) {
            List<Strategies> strategiesList = strategiesRepository.getByIdUser(us.getId().getIdUser());

        }


        /*JSONArray responseArr = new JSONArray();
        for (Strategies strategies : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", strategies.getId());
            jsonObject.put("name", strategies.getName());
            responseArr.put(jsonObject);
        }
*/
        Map<String, String> response = new HashMap<>();

        return response;
    }

    static final String URL_USER_INFO = "http://localhost:8084/user/info";

    @RequestMapping(value = "/user-ready", method = RequestMethod.PUT)
    public Map<String, String> userReady(@RequestBody String requestBody) {
        JSONObject jsonObject = new JSONObject(requestBody);

        String username = jsonObject.getString("username");
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

        UsersRoom usersRoom = usersRoomRepository.findByIdIdUser(id);

        usersRoom.setChosenTank(chosenTank);
        usersRoom.setIdStrategy(idOfChosenStrategy);
        usersRoom.setStatus(2);
        usersRoom.setCheckInvite(true);

        usersRoomService.updateUsersRoom(usersRoom);
        //TODO проверка на цвет танка

        Map<String, String> responseMap = new HashMap<>();

        responseMap.put("status", String.valueOf(usersRoom.getStatus()));
        responseMap.put("username", username);
        responseMap.put("idOfChosenStrategy", String.valueOf(usersRoom.getIdStrategy()));
        responseMap.put("chosenTank", usersRoom.getChosenTank());
        return responseMap;
    }

}