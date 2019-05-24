package runner.jsonObjectUI;

import java.util.List;

public class GameStatusJson {

    private List<UserJson> users;

    public GameStatusJson(List<UserJson> users) {
        this.users = users;
    }

    public List<UserJson> getListUser() {
        return users;
    }

    public void setListUser(List<UserJson> users) {
        this.users = users;
    }
}
