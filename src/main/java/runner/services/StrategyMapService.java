package runner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.dao.StrategyMapDAO;
import runner.models.StrategiesMap;

public class StrategyMapService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyMapService.class);
    private StrategyMapDAO strategyDAO = new StrategyMapDAO();

    public StrategyMapService() {
    }

    public void saveStrategyMap(StrategiesMap strategiesMap) {
        LOGGER.info("save");
        strategyDAO.save(strategiesMap);
    }

    public void deleteStrategyMap(StrategiesMap strategiesMap) {
        LOGGER.info("delete");
        strategyDAO.delete(strategiesMap);
    }

    public void updateStrategyMap(StrategiesMap strategiesMap) {
        LOGGER.info("upd");
        strategyDAO.update(strategiesMap);
    }

    public void findStrategyMap(int id) {
        LOGGER.info("find");
        strategyDAO.findStrategiesMapById(id);
    }
}
