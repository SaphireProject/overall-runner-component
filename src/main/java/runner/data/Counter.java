package runner.data;

import org.springframework.stereotype.Component;

@Component
public class Counter {

    private int number;

    public Counter() {
        this.number = 0;
    }

    public int getNumber() {
        return number;
    }

    public void increaseNumber() {
        this.number = number + 1;
    }

    public void resetNumber() {
        this.number = 0;
    }
}
