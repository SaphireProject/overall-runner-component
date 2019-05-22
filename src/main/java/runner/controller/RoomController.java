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
import runner.models.ParametersRoom;
import runner.models.Room;
import runner.models.UsersRoom;
import runner.repository.UsersRoomRepository;
import runner.services.ParameterService;
import runner.services.RoomService;

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
        map.put("idOfAdmin", String.valueOf(idOfAdmin));
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

/*13. Удалить админом игрока из лобби

DELETE /invite-user
Тело запроса
{
  idOfUser: number;
}

Тело ответа
{
  status: boolean;
  idOfDeletedUser: number;
}
*/

    //TODO удаление
    @RequestMapping(value = "/invite-user", method = RequestMethod.DELETE)
    public Map<String, String> inviteUser(@RequestHeader("Authorization") String requestHeader, @RequestBody String requestBody) {
        Long idAdmin = jwtGenerator.decodeNew(requestHeader).getUserData().getId();
        LOGGER.info(String.valueOf(idAdmin));

        JSONObject json = new JSONObject(requestBody);
        Long idUser = json.getLong("idOfUser");


        Map<String, String> responseData = new HashMap<>();
        return responseData;
    }


    // 14 GET
    //
    //Тело ответа
    //{
    //  status: boolean;
    //}

    //TODO удаление
    @RequestMapping(value = "/game/exit", method = RequestMethod.GET)
    public Map<String, String> gameExit(@RequestHeader("Authorization") String request) {

        Long id = jwtGenerator.decodeNew(request).getUserData().getId();

        Map<String, String> responseData = new HashMap<>();
        return responseData;
    }
}
