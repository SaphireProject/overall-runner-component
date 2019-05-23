package runner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import runner.models.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    Room findById(int id);
}
