package runner.jsonObjectUI;

import runner.models.Strategies;

public class UserReadyJson {

    private boolean status;
    private String username;
    private int idOfChosenStrategy;
    private int idOfRoom;
    private String chosenTank;

    public UserReadyJson(boolean status, String username, int idOfChosenStrategy, int idOfRoom, String chosenTank) {
        this.status = status;
        this.username = username;
        this.idOfChosenStrategy = idOfChosenStrategy;
        this.idOfRoom = idOfRoom;
        this.chosenTank = chosenTank;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdOfChosenStrategy() {
        return idOfChosenStrategy;
    }

    public void setIdOfChosenStrategy(int idOfChosenStrategy) {
        this.idOfChosenStrategy = idOfChosenStrategy;
    }

    public int getIdOfRoom() {
        return idOfRoom;
    }

    public void setIdOfRoom(int idOfRoom) {
        this.idOfRoom = idOfRoom;
    }

    public String getChosenTank() {
        return chosenTank;
    }

    public void setChosenTank(String chosenTank) {
        this.chosenTank = chosenTank;
    }
}
