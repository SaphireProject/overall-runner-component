package runner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import runner.dao.RoomDAO;
import runner.models.Room;

@Service
public class RoomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomService.class);
    private RoomDAO roomDAO = new RoomDAO();

    public RoomService() {
    }

    public void saveRoom(Room room) {
        roomDAO.save(room);
    }

    public void deleteRoom(Room room) {
        roomDAO.delete(room);
    }

    public void updateRoom(Room room) {
        roomDAO.update(room);
    }

    public void findRoom(int id) {
        roomDAO.findRoomById(id);
    }

}
