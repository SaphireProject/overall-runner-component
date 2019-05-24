package runner.jsonObjectUI;

import java.util.List;

public class StrategiesJson {

    private List<StrategyJson> strategies;

    public StrategiesJson(List<StrategyJson> strategies) {
        this.strategies = strategies;
    }

    public List<StrategyJson> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<StrategyJson> strategies) {
        this.strategies = strategies;
    }
}
