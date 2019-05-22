package runner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import runner.dao.UsersRoomDAO;
import runner.models.UsersRoom;
import runner.models.UsersRoomId;
import runner.repository.UsersRoomRepository;

@Component
public class UsersRoomService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersRoomService.class);
    private UsersRoomDAO usersRoomDAO = new UsersRoomDAO();

    public UsersRoomService() {
    }

    public void saveUsersRoom(UsersRoom usersRoom) {
        usersRoomDAO.save(usersRoom);
    }

    public void deleteUsersRoom(UsersRoom usersRoom) {
        usersRoomDAO.delete(usersRoom);
    }

    public void updateUsersRoom(UsersRoom usersRoom) {
        usersRoomDAO.update(usersRoom);
    }

    public void findUsersRoom(UsersRoomId id) {
        usersRoomDAO.findUsersRoomById(id);
    }

    public UsersRoom find(Long id) {
        return null;
    }
}