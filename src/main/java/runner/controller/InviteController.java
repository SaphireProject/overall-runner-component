package runner.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import runner.config.JwtGenerator;
import runner.models.ParametersRoom;
import runner.models.ParametersRoomId;
import runner.models.UsersRoom;
import runner.models.UsersRoomId;
import runner.repository.ParametersRoomRepository;
import runner.repository.UsersRoomRepository;
import runner.services.UsersRoomService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InviteController {

    @Autowired
    JwtGenerator jwtGenerator;

    @Autowired
    ParametersRoomRepository parametersRoomRepository;

    @Autowired
    UsersRoomRepository usersRoomRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(InviteController.class);
    static final String URL_USER_INFO = "http://localhost:8084/user/info";

    @RequestMapping(value = "/invite-user", method = RequestMethod.PUT)
    public Map<String, String> inviteUser(@RequestHeader("Authorization") String requestHeader, @RequestBody String requestBody) {
        JSONObject jsonObject = new JSONObject(requestBody);
        String name = jsonObject.getString("username");
        Integer idOfRoom = jsonObject.getInt("idOfRoom");

        Map<String, String> body = new HashMap<>();
        body.put("username", name);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

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
        String email = jsonObject.getString("email");

        UsersRoomService usersRoomService = new UsersRoomService();
        UsersRoom usersRoom = new UsersRoom(idOfRoom, id);
        usersRoomService.saveUsersRoom(usersRoom);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", String.valueOf(id));
        responseData.put("email", email);
        responseData.put("username", name);
        responseData.put("idOfRoom", String.valueOf(idOfRoom));
        return responseData;
    }

    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    public Map<String, String> notification(@RequestHeader("Authorization") String request) {

        Long id = jwtGenerator.decodeNew(request).getUserData().getId();

        //usersRoomRepository.findByIdUser(id);

        Map<String, String> responseData = new HashMap<>();
        return responseData;
    }

    //TODO do it
    @RequestMapping(value = "/invite", method = RequestMethod.DELETE)
    public Map<String, String> deleteInvite(@RequestBody String request) {
        JSONObject jsonObject = new JSONObject(request);
        String username = jsonObject.getString("username");
        Long idOfInvite = jsonObject.getLong("idOfInvite");

        Map<String, String> responseData = new HashMap<>();
        responseData.put("status", "true");
        return responseData;
    }

    @RequestMapping(value = "/invite/accept", method = RequestMethod.PUT)
    public Map<String, String> inviteAccept(@RequestBody String request) {
        JSONObject jsonObject = new JSONObject(request);
        String username = jsonObject.getString("username");
        Integer idOfInvite = jsonObject.getInt("idOfInvite");

        Integer idL = 3;

        UsersRoomId id = new UsersRoomId(idL, idL);
        UsersRoom usersRoom = usersRoomRepository.findById(id);
        usersRoom.setStatus(1);
        usersRoom.setCheckInvite(true);
        usersRoomRepository.save(usersRoom);

        ParametersRoomId paramRoomId = new ParametersRoomId(idL, "dima room");
        ParametersRoom parametersRoom = parametersRoomRepository.getById(paramRoomId);

        String nameOfRoom = parametersRoom.getId().getName();

        JSONObject jsonParam = new JSONObject(parametersRoom.getValue());
        String idOfAdmin = jsonParam.getString("idOfAdmin");
        String usernameOfAdmin = jsonParam.getString("usernameOfAdmin");
        String countOfPlayers = jsonParam.getString("countOfPlayers");
        String heightOfMapForGame = jsonParam.getString("heightOfMapForGame");
        String widthOfMapForGame = jsonParam.getString("widthOfMapForGame");

        Map<String, String> responseData = new HashMap<>();
        responseData.put("idOfInvite", String.valueOf(id.hashCode()));
        responseData.put("idOfRoom", String.valueOf(id.getIdRoom()));
        responseData.put("nameOfRoom", nameOfRoom);
        responseData.put("idOfAdmin", idOfAdmin);
        responseData.put("usernameOfAdmin", usernameOfAdmin);
        responseData.put("countOfPlayers", countOfPlayers);
        responseData.put("heightOfMapForGame", heightOfMapForGame);
        responseData.put("widthOfMapForGame", widthOfMapForGame);
        return responseData;
    }

}
