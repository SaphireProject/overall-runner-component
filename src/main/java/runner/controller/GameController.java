package runner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @RequestMapping(value = "/game/set-snapshots", method = RequestMethod.GET)
    public String gameIsStarted(@RequestBody String requestBody) {

        return "";
    }
}
