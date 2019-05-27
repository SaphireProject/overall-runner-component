package runner.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import runner.models.Snapshots;

import java.util.List;

@Repository
public interface SnapshotsRepository extends CrudRepository<Snapshots, Integer> {
    @Query(value = "Select c.* from Snapshots c where c.idRoom = ?1 order by c.id asc offset ?2 limit ?3 ", nativeQuery = true)
    List<Snapshots> findAll(Integer idRoom, int offset, int limit);
}