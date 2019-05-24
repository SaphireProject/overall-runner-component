package runner.jsonObject;

import java.util.List;

public class EndGame {
    private TypeEnd type;
    private List<Winner> idOfWinner;

    public EndGame(TypeEnd type , List<Winner> idOfWinner) {
        this.type = type;
        this.idOfWinner = idOfWinner;
    }

    public EndGame() {
    }

    public TypeEnd getType() {
        return type;
    }

    public void setType(TypeEnd type) {
        this.type = type;
    }

    public List<Winner> getIdOfWinner() {
        return idOfWinner;
    }

    public void setIdOfWinner(List<Winner> idOfWinner) {
        this.idOfWinner = idOfWinner;
    }
}
