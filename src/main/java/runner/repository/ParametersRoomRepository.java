package runner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import runner.models.ParametersRoom;
import runner.models.ParametersRoomId;

@Repository
public interface ParametersRoomRepository extends CrudRepository<ParametersRoom, Integer> {
    ParametersRoom getById(ParametersRoomId id);

    ParametersRoom getByIdIdRoom(int id);
}
