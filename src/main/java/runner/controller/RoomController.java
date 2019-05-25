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
import runner.data.ParameterMetida;
import runner.jsonObjectUI.*;
import runner.models.ParametersRoom;
import runner.models.Room;
import runner.models.UsersRoom;
import runner.models.UsersRoomId;
import runner.repository.ParametersRoomRepository;
import runner.repository.RoomRepository;
import runner.repository.UsersRoomRepository;
import runner.services.ParameterService;
import runner.services.RoomService;
import runner.services.UsersRoomService;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Autowired
    ParametersRoomRepository parametersRoomRepository;

    @RequestMapping(value = "/room", method = RequestMethod.POST)
    public RoomJson roomCreate(@RequestBody String response) {
        JSONObject jsonObject = new JSONObject(response);
        LOGGER.info(jsonObject.toString());
        Integer idOfAdmin = jsonObject.getInt("idOfAdmin");
        String usernameOfAdmin = jsonObject.getString("usernameOfAdmin");
        String nameOfRoom = jsonObject.getString("nameOfRoom");
        Integer countOfPlayers = jsonObject.getInt("countOfPlayers");
        Integer heightOfMapForGame = jsonObject.getInt("heightOfMapForGame");
        Integer widthOfMapForGame = jsonObject.getInt("widthOfMapForGame");

        LocalDate localDate = LocalDate.now();
        Room room = new Room(idOfAdmin, false, localDate, 0, 1);
        roomRepository.save(room);

        ParameterMetida parameterMetida = new ParameterMetida(countOfPlayers, heightOfMapForGame, widthOfMapForGame, null);
        JSONObject map = new JSONObject();
        map.put("countOfPlayers", String.valueOf(countOfPlayers));
        map.put("heightOfMapForGame", String.valueOf(heightOfMapForGame));
        map.put("widthOfMapForGame", String.valueOf(widthOfMapForGame));

        ParametersRoom parametersRoom = new ParametersRoom(nameOfRoom, room.getId(), map.toString());
        parametersRoomRepository.save(parametersRoom);

        UsersRoom us = new UsersRoom(room.getId(), idOfAdmin);
        us.setCheckInvite(true);
        us.setStatus(1);
        usersRoomService.saveUsersRoom(us);

        RoomJson responceRoomJson = new RoomJson(room.getId(), nameOfRoom,
                idOfAdmin, usernameOfAdmin,
                countOfPlayers, heightOfMapForGame,
                widthOfMapForGame);
        return responceRoomJson;
    }

    static final String URL_USER_ID = "http://localhost:8084/user/";

    @RequestMapping(value = "/game-status", method = RequestMethod.GET)
    public GameStatusJson gameStatus(@RequestParam("idOfRoom") int idOfRoom) {

        List<UsersRoom> usersRoomList = usersRoomRepository.getByIdIdRoom(idOfRoom);

        int idOfAdmin = roomRepository.findById(idOfRoom).getIdOwner();

        List<UserJson> usersJsonList = new ArrayList<>();

        for (UsersRoom us : usersRoomList) {
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

            UserJson userJson = new UserJson(us.getId().getIdUser(), json.getString("username"),
                    json.getString("email"), us.getStatus(), us.getChosenTank(), idOfRoom);

            usersJsonList.add(userJson);
        }

        GameStatusJson gameStatusJson = new GameStatusJson(usersJsonList, idOfAdmin);
        return gameStatusJson;
    }

    @RequestMapping(value = "/game/users", method = RequestMethod.GET)
    public UsersRoomJson gameUsers(@RequestParam("idOfRoom") int idOfRoom) {

        List<UsersRoom> usersRoomList = usersRoomRepository.getByIdIdRoom(idOfRoom);

        List<UserRoomJson> list = new ArrayList<>();
        for (UsersRoom us : usersRoomList) {
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

            UserRoomJson userRoomJson = new UserRoomJson(idOfUser, username, email, choosenTank, readyToPlay, idOfRoom);
            list.add(userRoomJson);
        }

        UsersRoomJson usersRoomJson = new UsersRoomJson(list);
        return usersRoomJson;
    }

    @Autowired
    RoomRepository roomRepository;

    @RequestMapping(value = "/game/is-started", method = RequestMethod.GET)
    public GameIsStartedJson gameIsStarted(@RequestParam("idOfRoom") int idOfRoom) {

        GameIsStartedJson gameIsStartedJson = null;
        try {
            Room room = roomRepository.findById(idOfRoom);
            boolean status = room.isStarted();

            gameIsStartedJson = new GameIsStartedJson(status, idOfRoom);
        } catch (Exception e) {
        }

        return gameIsStartedJson;
    }

    static final String URL_USER_INFO = "http://localhost:8084/user/info";

    @Autowired
    UsersRoomService usersRoomService;

    @RequestMapping(value = "/invite-user", method = RequestMethod.DELETE)
    public InviteUserDeleteJson deleteInviteUser(@RequestParam("idOfInvite") String idOfInvite) {

        int index = idOfInvite.indexOf("_");
        int idRoom = Integer.parseInt(idOfInvite.substring(0, index));
        int idUser = Integer.parseInt(idOfInvite.substring(index + 1));

        UsersRoom us = new UsersRoom(idRoom, idUser);
        usersRoomService.deleteUsersRoom(us);
        InviteUserDeleteJson inviteUserDeleteJson = new InviteUserDeleteJson(true, 0, 0);

        return inviteUserDeleteJson;
    }

    @RequestMapping(value = "/invite-admin", method = RequestMethod.DELETE)
    public InviteUserDeleteJson deleteInviteAdmin(@RequestParam("idOfUser") int idOfUser, @RequestParam("idOfRoom") int idOfRoom) {

        UsersRoom us = new UsersRoom(idOfRoom, idOfUser);
        usersRoomService.deleteUsersRoom(us);

        InviteUserDeleteJson inviteUserDeleteJson = new InviteUserDeleteJson(true, idOfUser, idOfRoom);
        return inviteUserDeleteJson;
    }

    //TODO удаление
    @RequestMapping(value = "/game/exit", method = RequestMethod.POST)
    public InviteUserDeleteJson gameExit(@RequestBody String request) {
        JSONObject jsonObject = new JSONObject(request);
        int idOfUser = jsonObject.getInt("idOfUser");
        int idOfRoom = jsonObject.getInt("idOfRoom");
        String username = jsonObject.getString("username");

        UsersRoom us = new UsersRoom(idOfRoom, idOfUser);
        usersRoomService.deleteUsersRoom(us);

        InviteUserDeleteJson inviteUserDeleteJson = new InviteUserDeleteJson(true, 0, idOfRoom);
        return inviteUserDeleteJson;
    }
}
