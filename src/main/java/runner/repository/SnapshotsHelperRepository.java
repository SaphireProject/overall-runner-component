package runner.repository;

import org.springframework.data.repository.CrudRepository;
import runner.models.SnapshotsHelper;

public interface SnapshotsHelperRepository extends CrudRepository<SnapshotsHelper, Integer> {
    SnapshotsHelper getByIdIdRoom(Integer idRoom);
}
