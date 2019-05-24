package runner.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import runner.jsonObject.*;

public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @RequestMapping(value = "/game/animation", method = RequestMethod.POST)
    public String setAnimation(@RequestBody AnimationJson requestBody) {

        return "";
    }

    @RequestMapping(value = "/game/bullet", method = RequestMethod.POST)
    public String setBullet(@RequestBody BulletJson requestBody) {

        return "";
    }

    @RequestMapping(value = "/game/end-game", method = RequestMethod.POST)
    public String end(@RequestBody EndGame requestBody) {

        return "";
    }

    @RequestMapping(value = "/game/frame", method = RequestMethod.POST)
    public String setFrame(@RequestBody FrameJson requestBody) {

        return "";
    }


    @RequestMapping(value = "/game/preload", method = RequestMethod.POST)
    public String setPreload(@RequestBody PreloadJson requestBody) {

        return "";
    }

    @RequestMapping(value = "/game/tank", method = RequestMethod.POST)
    public String setTank(@RequestBody TankJson requestBody) {

        return "";
    }

    @RequestMapping(value = "/game/type-end", method = RequestMethod.POST)
    public String setTypeEnd(@RequestBody TypeEnd requestBody) {

        return "";
    }

    @RequestMapping(value = "/game/winner", method = RequestMethod.POST)
    public String setWinner(@RequestBody Winner requestBody) {

        return "";
    }
}


// написать контракт, и пост для параметров

