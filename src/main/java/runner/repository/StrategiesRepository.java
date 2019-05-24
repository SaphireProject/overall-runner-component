package runner.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import runner.models.Strategies;

import java.util.List;

@Repository
public interface StrategiesRepository extends CrudRepository<Strategies, Integer> {
    List<Strategies> getByIdUserAndTypeGame(Integer id, String type);
    Strategies getById(Integer id);

}
