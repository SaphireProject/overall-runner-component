package runner.jsonObjectUI;

import java.util.List;

public class UsersRoomJson {

    List<UserRoomJson> users;

    public UsersRoomJson(List<UserRoomJson> users) {
        this.users = users;
    }

    public List<UserRoomJson> getUsers() {
        return users;
    }

    public void setUsers(List<UserRoomJson> users) {
        this.users = users;
    }
}
