package runner.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import runner.config.JwtGenerator;
import runner.dao.ParameterRoomDAO;
import runner.data.InviteUsers;
import runner.jsonObjectUI.InviteJson;
import runner.jsonObjectUI.InviteUserJson;
import runner.jsonObjectUI.InvitesJson;
import runner.models.*;
import runner.repository.ParametersRoomRepository;
import runner.repository.RoomRepository;
import runner.repository.UsersRoomRepository;
import runner.services.UsersRoomService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/invite-user", method = RequestMethod.POST)
    public InviteUserJson inviteUser(@RequestHeader("Authorization") String requestHeader, @RequestBody String requestBody) {
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

        InviteUserJson inviteUserJson = new InviteUserJson(id, email, name, idOfRoom);

        return inviteUserJson;
    }

    @Autowired
    RoomRepository roomRepository;

    static final String URL_USER_ID = "http://localhost:8084/user/";

    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    public InvitesJson notification(@RequestHeader("Authorization") String request) {

        int id = jwtGenerator.decodeNew(request).getUserData().getId().intValue();

        LOGGER.info(String.valueOf(id));

        List<UsersRoom> usersRoomList = usersRoomRepository.findByIdIdUser(id);

        List<InviteJson> list = new ArrayList<>();

        JSONArray responseArray = new JSONArray();
        for (UsersRoom us : usersRoomList) {

            String idOfInvite = us.getId().getId();

            int idOfRoom = us.getId().getIdRoom();

            ParametersRoom parametersRoom = parametersRoomRepository.getByIdIdRoom(idOfRoom);

            int idOfAdmin = roomRepository.findById(idOfRoom).getIdOwner();

            String nameOfRoom = parametersRoom.getId().getName();

            JSONObject param = new JSONObject(parametersRoom.getValue());

            int countOfPlayers = param.getInt("countOfPlayers");
            int heightOfMapForGame = param.getInt("heightOfMapForGame");
            int widthOfMapForGame = param.getInt("widthOfMapForGame");

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<String> entity = new HttpEntity<>("", headers);

            //TODO к Мише переделать запрос под GET
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseUser = restTemplate.exchange(
                    URL_USER_ID + idOfAdmin,
                    HttpMethod.GET,
                    entity,
                    String.class);

            JSONObject json = new JSONObject(responseUser.getBody());
            String usernameOfAdmin = json.getString("username");
            String emailOfAdmin = json.getString("email");

            InviteJson inviteJson = new InviteJson(idOfInvite, idOfRoom, nameOfRoom, idOfAdmin, usernameOfAdmin,
                    emailOfAdmin, countOfPlayers, heightOfMapForGame, widthOfMapForGame);

            list.add(inviteJson);

            us.setCheckInvite(true);
            usersRoomService.updateUsersRoom(us);
        }

        InvitesJson invitesJson = new InvitesJson(list);
        return invitesJson;
    }

    //TODO do it
    @RequestMapping(value = "/invite", method = RequestMethod.DELETE)
    public Map<String, String> deleteInvite(@RequestBody String request) {
        JSONObject jsonObject = new JSONObject(request);
        String username = jsonObject.getString("username");
        String idOfInvite = jsonObject.getString("idOfInvite");

        Map<String, String> responseData = new HashMap<>();
        responseData.put("status", "true");
        return responseData;
    }

    @RequestMapping(value = "/invite/accept", method = RequestMethod.POST)
    public Map<String, String> inviteAccept(@RequestBody String request) {
        JSONObject jsonObject = new JSONObject(request);
        String username = jsonObject.getString("username");
        String idOfInvite = jsonObject.getString("idOfInvite");

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
        responseData.put("idOfInvite", id.getId());
        responseData.put("idOfRoom", String.valueOf(id.getIdRoom()));
        responseData.put("nameOfRoom", nameOfRoom);
        responseData.put("idOfAdmin", idOfAdmin);
        responseData.put("usernameOfAdmin", usernameOfAdmin);
        responseData.put("countOfPlayers", countOfPlayers);
        responseData.put("heightOfMapForGame", heightOfMapForGame);
        responseData.put("widthOfMapForGame", widthOfMapForGame);
        return responseData;
    }

    @Autowired
    UsersRoomService usersRoomService;

    @RequestMapping(value = "/accept-invite", method = RequestMethod.POST)
    public InviteJson acceptInvite(@RequestBody String request) {
        JSONObject requestJson = new JSONObject(request);
        String username = requestJson.getString("username");
        String idOfInvite = requestJson.getString("idOfInvite");

        LOGGER.info(idOfInvite);
        int index = idOfInvite.indexOf("_");
        int idRoom = Integer.parseInt(idOfInvite.substring(0, index));
        int idUser = Integer.parseInt(idOfInvite.substring(index + 1));

        ParametersRoom pr = parametersRoomRepository.getByIdIdRoom(idRoom);
        String nameOfRoom = pr.getId().getName();

        JSONObject jsonObject = new JSONObject(pr.getValue());
        int countOfPlayers = jsonObject.getInt("countOfPlayers");
        int heightOfMapForGame = jsonObject.getInt("heightOfMapForGame");
        int widthOfMapForGame = jsonObject.getInt("widthOfMapForGame");

        Room room = roomRepository.findById(idRoom);
        int idOfAdmin = room.getIdOwner();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        //TODO к Мише переделать запрос под GET
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseUser = restTemplate.exchange(
                URL_USER_ID + idOfAdmin,
                HttpMethod.GET,
                entity,
                String.class);
        JSONObject json = new JSONObject(responseUser.getBody());
        String usernameOfAdmin = json.getString("username");

        UsersRoom us = new UsersRoom(idRoom, idUser);
        us.setCheckInvite(true);
        us.setStatus(1);
        usersRoomService.updateUsersRoom(us);

        InviteJson inviteJson = new InviteJson(idOfInvite, idRoom, nameOfRoom, idOfAdmin, usernameOfAdmin, "", countOfPlayers,
                heightOfMapForGame, widthOfMapForGame);
        return inviteJson;
    }
}
