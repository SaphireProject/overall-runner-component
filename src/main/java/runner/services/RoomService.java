package runner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.dao.RoomDAO;
import runner.models.Room;

public class RoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomService.class);
    private RoomDAO roomDAO = new RoomDAO();

    public RoomService() {
    }

    public void saveRoom(Room Room) {
        LOGGER.info("save");
        roomDAO.save(Room);
    }

    public void deleteRoom(Room Room) {
        LOGGER.info("delete");
        roomDAO.delete(Room);
    }

    public void updateRoom(Room Room) {
        LOGGER.info("upd");
        roomDAO.update(Room);
    }

    public void findRoom(int id) {
        LOGGER.info("find");
        roomDAO.findRoomById(id);
    }

}
