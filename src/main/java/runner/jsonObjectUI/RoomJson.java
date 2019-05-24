package runner.jsonObjectUI;

public class RoomJson {
    private int idOfRoom;
    private String nameOfRoom;
    private int idOfAdmin;
    private String usernameOfAdmin;
    private int countOfPlayers;
    private int heightOfMapForGame;
    private int widthOfMapForGame;

    public RoomJson(int idOfRoom, String nameOfRoom, int idOfAdmin, String usernameOfAdmin, int countOfPlayers, int heightOfMapForGame, int widthOfMapForGame) {
        this.idOfRoom = idOfRoom;
        this.nameOfRoom = nameOfRoom;
        this.idOfAdmin = idOfAdmin;
        this.usernameOfAdmin = usernameOfAdmin;
        this.countOfPlayers = countOfPlayers;
        this.heightOfMapForGame = heightOfMapForGame;
        this.widthOfMapForGame = widthOfMapForGame;
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
