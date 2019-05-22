package runner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import runner.dao.ParameterRoomDAO;
import runner.models.ParametersRoom;

@Service
public class ParameterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterService.class);
    private ParameterRoomDAO parameterRoomDAO = new ParameterRoomDAO();

    public ParameterService() {
    }

    public void saveParametersRoom(ParametersRoom parametersRoom) {
        LOGGER.info("save");
        parameterRoomDAO.save(parametersRoom);
    }

    public void deleteParametersRoom(ParametersRoom parametersRoom) {
        LOGGER.info("delete");
        parameterRoomDAO.delete(parametersRoom);
    }

    public void updateParametersRoom(ParametersRoom parametersRoom) {
        LOGGER.info("upd");
        parameterRoomDAO.update(parametersRoom);
    }

    public void findParametersRoom(int id) {
        LOGGER.info("find");
        parameterRoomDAO.findParametersRoomById(id);
    }

}
