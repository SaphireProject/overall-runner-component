package runner.controller;

import org.apache.catalina.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import runner.config.JwtGenerator;
import runner.models.ParametersRoom;
import runner.models.Room;
import runner.models.UsersRoom;
import runner.models.UsersRoomId;
import runner.repository.RoomRepository;
import runner.repository.UsersRoomRepository;
import runner.services.ParameterService;
import runner.services.RoomService;
import runner.services.UsersRoomService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    JwtGenerator jwtGenerator;

    @Autowired
    UsersRoomRepository usersRoomRepository;

    @RequestMapping(value = "/room", method = RequestMethod.POST)
    public Map<String, String> roomCreate(@RequestBody String response) {
        JSONObject jsonObject = new JSONObject(response);
        LOGGER.info(jsonObject.toString());
        Integer idOfAdmin = jsonObject.getInt("idOfAdmin");
        String usernameOfAdmin = jsonObject.getString("usernameOfAdmin");
        String nameOfRoom = jsonObject.getString("nameOfRoom");
        Integer countOfPlayers = jsonObject.getInt("countOfPlayers");
        Integer heightOfMapForGame = jsonObject.getInt("heightOfMapForGame");
        Integer widthOfMapForGame = jsonObject.getInt("widthOfMapForGame");

        LocalDate localDate = LocalDate.now();
        RoomService roomService = new RoomService();
        Room room = new Room(idOfAdmin, false, localDate, 0, 1);
        roomService.saveRoom(room);

        Map<String, String> map = new HashMap<>();
        map.put("countOfPlayers", String.valueOf(countOfPlayers));
        map.put("heightOfMapForGame", String.valueOf(heightOfMapForGame));
        map.put("widthOfMapForGame", String.valueOf(widthOfMapForGame));

        ParameterService parameterService = new ParameterService();
        ParametersRoom parametersRoom = new ParametersRoom(nameOfRoom, room.getId(), map.toString());
        parameterService.saveParametersRoom(parametersRoom);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("idOfRoom", String.valueOf(room.getId()));
        responseData.put("nameOfRoom", nameOfRoom);
        responseData.put("idOfAdmin", String.valueOf(idOfAdmin));
        responseData.put("usernameOfAdmin", usernameOfAdmin);
        responseData.put("countOfPlayers", String.valueOf(countOfPlayers));
        responseData.put("heightOfMapForGame", String.valueOf(heightOfMapForGame));
        responseData.put("widthOfMapForGame", String.valueOf(widthOfMapForGame));
        return responseData;
    }

    static final String URL_USER_ID = "http://localhost:8084/user/";

    @RequestMapping(value = "/game-status", method = RequestMethod.GET)
    public Map<String, String> gameStatus(@RequestParam("idOfRoom") int idOfRoom) {

        List<UsersRoom> usersRoomList = usersRoomRepository.getByIdIdRoom(idOfRoom);

        JSONArray responseArray = new JSONArray();

        for (UsersRoom us : usersRoomList) {
            Map<String, String> responseData = new HashMap<>();
            responseData.put("idOfUser", String.valueOf(us.getId().getIdUser()));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<String> entity = new HttpEntity<>("", headers);

            //TODO к Мише переделать запрос под GET
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseUser = restTemplate.exchange(
                    URL_USER_ID + us.getId().getIdUser(),
                    HttpMethod.GET,
                    entity,
                    String.class);

            JSONObject json = new JSONObject(responseUser.getBody());
            LOGGER.info(json.toString());

            responseData.put("username", json.getString("username"));
            responseData.put("email", json.getString("email"));

            responseData.put("readyToPlay", String.valueOf(us.getStatus()));
            responseData.put("chosenTank", us.getChosenTank());
            responseData.put("idOfRoom", String.valueOf(idOfRoom));

            responseArray.put(responseData);
        }

        Map<String, String> response = new HashMap<>();
        response.put("users", responseArray.toString());
        return response;
    }

    @RequestMapping(value = "/game/users", method = RequestMethod.GET)
    public Map<String, String> gameUsers(@RequestParam("idOfRoom") int idOfRoom) {

        List<UsersRoom> usersRoomList = usersRoomRepository.getByIdIdRoom(idOfRoom);

        JSONArray responseArray = new JSONArray();
        for (UsersRoom us : usersRoomList) {
            JSONObject jsonObject = new JSONObject();

            int idOfUser = us.getId().getIdUser();
            String choosenTank = us.getChosenTank();
            int readyToPlay = us.getStatus();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<String> entity = new HttpEntity<>("", headers);

            //TODO к Мише переделать запрос под GET
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseUser = restTemplate.exchange(
                    URL_USER_ID + idOfUser,
                    HttpMethod.GET,
                    entity,
                    String.class);

            JSONObject json = new JSONObject(responseUser.getBody());
            String username = json.getString("username");
            String email = json.getString("email");

            jsonObject.put("idOfUser", idOfUser);
            jsonObject.put("username", username);
            jsonObject.put("email", email);
            jsonObject.put("choosenTank", choosenTank);
            jsonObject.put("readyToPlay", readyToPlay);
            jsonObject.put("idOfRoom", idOfRoom);

            responseArray.put(jsonObject);
        }

        Map<String, String> responseData = new HashMap<>();
        responseData.put("users", responseArray.toString());
        return responseData;
    }

    @Autowired
    RoomRepository roomRepository;

    @RequestMapping(value = "/game/is-started", method = RequestMethod.GET)
    public Map<String, String> gameIsStarted(@RequestParam("idOfRoom") int idOfRoom) {

        Map<String, String> responseData = null;
        try {
            Room room = roomRepository.findById(idOfRoom);

            boolean status = room.isStarted();

            responseData = new HashMap<>();
            responseData.put("status", String.valueOf(status));
            responseData.put("idOfRoom", String.valueOf(idOfRoom));
        } catch (Exception e) {
        }

        return responseData;
    }

    static final String URL_USER_INFO = "http://localhost:8084/user/info";

    @Autowired
    UsersRoomService usersRoomService;

    @RequestMapping(value = "/invite-user", method = RequestMethod.DELETE)
    public Map<String, String> deleteInviteUser(@RequestParam("idOfInvite") String idOfInvite) {

        int index = idOfInvite.indexOf("-");
        int idRoom = Integer.parseInt(idOfInvite.substring(0, index));
        int idUser = Integer.parseInt(idOfInvite.substring(index + 1));

        UsersRoom us = new UsersRoom(idRoom, idUser);
        usersRoomService.deleteUsersRoom(us);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("status", "true");
        return responseData;
    }

    @RequestMapping(value = "/invite-admin", method = RequestMethod.DELETE)
    public Map<String, String> deleteInviteAdmin(@RequestParam("idOfUser") int idOfUser, @RequestParam("idOfRoom") int idOfRoom) {

        UsersRoom us = new UsersRoom(idOfRoom, idOfUser);
        usersRoomService.deleteUsersRoom(us);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("status", "true");
        responseData.put("idOfDeletedUser", String.valueOf(idOfUser));
        responseData.put("idOfRoom", String.valueOf(idOfRoom));
        return responseData;
    }

    //TODO удаление
    @RequestMapping(value = "/game/exit", method = RequestMethod.POST)
    public Map<String, String> gameExit(@RequestBody String request) {
        JSONObject jsonObject = new JSONObject(request);
        int idOfUser = jsonObject.getInt("idOfUser");
        int idOfRoom = jsonObject.getInt("idOfRoom");
        String username = jsonObject.getString("username");

        UsersRoom us = new UsersRoom(idOfRoom, idOfUser);
        usersRoomService.deleteUsersRoom(us);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("status", "true");
        responseData.put("idOfRoom", String.valueOf(idOfRoom));
        return responseData;
    }
}
