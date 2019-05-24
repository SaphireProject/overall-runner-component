package runner.jsonObjectUI;

import runner.models.Strategies;

public class UserRoomJson {
    private int idOfUser;
    private String username;
    private String email;
    private String chosenTank;
    private int readyToPlay;
    private int idOfRomm;

    public UserRoomJson(int idOfUser, String username, String email, String chosenTank, int readyToPlay, int idOfRomm) {
        this.idOfUser = idOfUser;
        this.username = username;
        this.email = email;
        this.chosenTank = chosenTank;
        this.readyToPlay = readyToPlay;
        this.idOfRomm = idOfRomm;
    }

    public int getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(int idOfUser) {
        this.idOfUser = idOfUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChosenTank() {
        return chosenTank;
    }

    public void setChosenTank(String chosenTank) {
        this.chosenTank = chosenTank;
    }

    public int getReadyToPlay() {
        return readyToPlay;
    }

    public void setReadyToPlay(int readyToPlay) {
        this.readyToPlay = readyToPlay;
    }

    public int getIdOfRomm() {
        return idOfRomm;
    }

    public void setIdOfRomm(int idOfRomm) {
        this.idOfRomm = idOfRomm;
    }
}
