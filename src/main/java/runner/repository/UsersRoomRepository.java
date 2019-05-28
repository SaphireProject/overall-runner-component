package runner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import runner.models.UsersRoom;
import runner.models.UsersRoomId;

import java.util.List;

@Repository
public interface UsersRoomRepository extends CrudRepository<UsersRoom, Integer> {
    List<UsersRoom> findAll();

    List<UsersRoom> findByIdIdUser(int id);

    List<UsersRoom> getByIdIdRoom(int id);

    UsersRoom removeByIdIdRoomAndIdIdUser(int idRoom, int idUser);

    UsersRoom findById(UsersRoomId id);

    UsersRoom deleteById(UsersRoomId id);

    UsersRoom getByIdIdUser(int id);
}