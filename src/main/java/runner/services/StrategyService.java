package runner.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import runner.dao.StrategyDAO;
import runner.models.Strategies;

import java.util.List;

@Service
public class StrategyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StrategyService.class);
    private StrategyDAO strategyDAO = new StrategyDAO();

    public StrategyService() {
    }

    public void saveStrategy(Strategies strategy) {
        strategyDAO.save(strategy);
    }

    public void deleteStrategy(Strategies strategy) {
        strategyDAO.delete(strategy);
    }

    public void updateStrategy(Strategies strategy) {
        strategyDAO.update(strategy);
    }

    public void findStrategy(int id) {
        strategyDAO.findStrategyById(id);
    }

    public List findAll(){
        return strategyDAO.findAll();
    }
}
