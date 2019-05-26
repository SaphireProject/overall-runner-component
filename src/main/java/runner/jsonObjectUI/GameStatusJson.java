package runner.jsonObjectUI;

import java.util.List;

public class GameStatusJson {

    private List<UserJson> users;

    private int idOfAdmin;

    private int countOfPlayers;

    public GameStatusJson(List<UserJson> users, int idOfAdmin, int countOfPlayers) {
        this.users = users;
        this.idOfAdmin = idOfAdmin;
        this.countOfPlayers = countOfPlayers;
    }

    public List<UserJson> getUsers() {
        return users;
    }

    public void setUsers(List<UserJson> users) {
        this.users = users;
    }

    public int getIdOfAdmin() {
        return idOfAdmin;
    }

    public void setIdOfAdmin(int idOfAdmin) {
        this.idOfAdmin = idOfAdmin;
    }

    public int getCountOfUsers() {
        return countOfPlayers;
    }

    public void setCountOfUsers(int countOfPlayers) {
        this.countOfPlayers = countOfPlayers;
    }
}
