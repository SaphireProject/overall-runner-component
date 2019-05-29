package runner.jsonObjectUI;

public class UserJson {
    private int idOfUser;
    private String username;
    private  String email;
    private int readyToPlay;
    private String chosenTank;
    private int idOfRoom;

    public UserJson(int idOfUser, String username, String email, int readyToPlay, String chosenTank, int idOfRoom) {
        this.idOfUser = idOfUser;
        this.username = username;
        this.email = email;
        this.readyToPlay = readyToPlay;
        this.chosenTank = chosenTank;
        this.idOfRoom = idOfRoom;
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

    public int getReadyToPlay() {
        return readyToPlay;
    }

    public void setReadyToPlay(int readyToPlay) {
        this.readyToPlay = readyToPlay;
    }

    public String getChosenTank() {
        return chosenTank;
    }

    public void setChosenTank(String chosenTank) {
        this.chosenTank = chosenTank;
    }

    public int getIdOfRoom() {
        return idOfRoom;
    }

    public void setIdOfRoom(int idOfRoom) {
        this.idOfRoom = idOfRoom;
    }
}
