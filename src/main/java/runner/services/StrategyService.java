package runner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runner.dao.StrategyDAO;
import runner.models.Strategies;

public class StrategyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyService.class);
    private StrategyDAO strategyDAO = new StrategyDAO();

    public StrategyService() {
    }

    public void saveStrategy(Strategies strategy) {
        LOGGER.info("save");
        strategyDAO.save(strategy);
    }

    public void deleteStrategy(Strategies strategy) {
        LOGGER.info("delete");
        strategyDAO.delete(strategy);
    }

    public void updateStrategy(Strategies strategy) {
        LOGGER.info("upd");
        strategyDAO.update(strategy);
    }

    public void findStrategy(int id) {
        LOGGER.info("find");
        strategyDAO.findStrategyById(id);
    }
}
