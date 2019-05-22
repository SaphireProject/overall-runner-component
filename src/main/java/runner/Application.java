package runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@ComponentScan
@Configuration
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        //StrategyService strategyService = new StrategyService();
        //Strategies strategies = new Strategies(1, "C://path");
        //strategyService.saveStrategy(strategies);
        //strategies.setPath("C://");
        //strategyService.updateStrategy(strategies);
        //strategyService.deleteStrategy(strategies);
        //strategyService.findStrategy(1);

    }
}

// стартовать игру
// docker java, метиду в докер джава
// запустить пробный контейнер с игрой,
// гет запрос РАН, без парам
// раннер через доекр клиент иниц запуск контейнер метиды
// метиде сообщается что-то на локалхост/ран
// метида когда приходит по пути кот сообщили, отдаю конфиг из файла
// снепшот уник ид,
// пост для получения снепшотов
// идеально = где-то в поминальничке(абстр каталог), в метиде принадлежит твик шаблон,