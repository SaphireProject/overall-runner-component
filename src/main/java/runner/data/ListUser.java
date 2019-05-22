package runner.data;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ListUser {
    private String userName;

    @Autowired
    private Map<String, InviteUsers> listUser = new HashMap<String, InviteUsers>();

    public void addList(String userName, InviteUsers inviteUsers) {
        listUser.put(userName, inviteUsers);
    }

    public Map<String, InviteUsers> getList() {
        return listUser;
    }

    public int getSize() {
        return listUser.size();
    }
}
