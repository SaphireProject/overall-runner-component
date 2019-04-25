package runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import runner.services.StrategyService;

@SpringBootApplication
@ComponentScan
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        StrategyService strategyService = new StrategyService();
        //Strategies strategies = new Strategies(1, "C://path");
        //strategyService.saveStrategy(strategies);
        //strategies.setPath("C://");
        //strategyService.updateStrategy(strategies);
        //strategyService.deleteStrategy(strategies);

        strategyService.findStrategy(1);
    }
}
