package runner.jsonObjectUI;

import runner.models.Strategies;

public class InviteJson {
    private String idOfInvite;
    private int idOfRoom;
    private String nameOfRoom;
    private int idOfAdmin;
    private String usernameOfAdmin;
    private String emailOfAdmin;
    private int countOfPlayers;
    private int heightOfMapForGame;
    private int widthOfMapForGame;

    public InviteJson(String idOfInvite, int idOfRoom, String nameOfRoom, int idOfAdmin, String usernameOfAdmin, String emailOfAdmin, int countOfPlayers, int heightOfMapForGame, int widthOfMapForGame) {
        this.idOfInvite = idOfInvite;
        this.idOfRoom = idOfRoom;
        this.nameOfRoom = nameOfRoom;
        this.idOfAdmin = idOfAdmin;
        this.usernameOfAdmin = usernameOfAdmin;
        this.emailOfAdmin = emailOfAdmin;
        this.countOfPlayers = countOfPlayers;
        this.heightOfMapForGame = heightOfMapForGame;
        this.widthOfMapForGame = widthOfMapForGame;
    }

    public String getIdOfInvite() {
        return idOfInvite;
    }

    public void setIdOfInvite(String idOfInvite) {
        this.idOfInvite = idOfInvite;
    }

    public int getIdOfRoom() {
        return idOfRoom;
    }

    public void setIdOfRoom(int idOfRoom) {
        this.idOfRoom = idOfRoom;
    }

    public String getNameOfRoom() {
        return nameOfRoom;
    }

    public void setNameOfRoom(String nameOfRoom) {
        this.nameOfRoom = nameOfRoom;
    }

    public int getIdOfAdmin() {
        return idOfAdmin;
    }

    public void setIdOfAdmin(int idOfAdmin) {
        this.idOfAdmin = idOfAdmin;
    }

    public String getUsernameOfAdmin() {
        return usernameOfAdmin;
    }

    public void setUsernameOfAdmin(String usernameOfAdmin) {
        this.usernameOfAdmin = usernameOfAdmin;
    }

    public String getEmailOfAdmin() {
        return emailOfAdmin;
    }

    public void setEmailOfAdmin(String emailOfAdmin) {
        this.emailOfAdmin = emailOfAdmin;
    }

    public int getCountOfPlayers() {
        return countOfPlayers;
    }

    public void setCountOfPlayers(int countOfPlayers) {
        this.countOfPlayers = countOfPlayers;
    }

    public int getHeightOfMapForGame() {
        return heightOfMapForGame;
    }

    public void setHeightOfMapForGame(int heightOfMapForGame) {
        this.heightOfMapForGame = heightOfMapForGame;
    }

    public int getWidthOfMapForGame() {
        return widthOfMapForGame;
    }

    public void setWidthOfMapForGame(int widthOfMapForGame) {
        this.widthOfMapForGame = widthOfMapForGame;
    }
}
