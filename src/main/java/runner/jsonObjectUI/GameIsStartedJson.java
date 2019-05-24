package runner.jsonObjectUI;

public class GameIsStartedJson {
    private boolean status;
    private int idOfRoom;

    public GameIsStartedJson(boolean status, int idOfRoom) {
        this.status = status;
        this.idOfRoom = idOfRoom;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdOfRoom() {
        return idOfRoom;
    }

    public void setIdOfRoom(int idOfRoom) {
        this.idOfRoom = idOfRoom;
    }
}
