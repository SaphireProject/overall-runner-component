package runner.jsonObject;

public class PreloadForDB {
    private int idGame;
    private String value;

    public PreloadForDB(int idGame , String value) {
        this.idGame = idGame;
        this.value = value;
    }

    public PreloadForDB() {
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
