package runner.data;

import runner.models.Strategies;

public class SetMetida {
    private int id;
    private String type;

    public SetMetida(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public SetMetida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
