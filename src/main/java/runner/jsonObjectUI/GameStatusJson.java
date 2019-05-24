package runner.jsonObjectUI;

import java.util.List;

public class GameStatusJson {

    private List<UserJson> users;

    private int idOfAdmin;

    public GameStatusJson(List<UserJson> users, int idOfAdmin) {
        this.users = users;
        this.idOfAdmin = idOfAdmin;
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
}
