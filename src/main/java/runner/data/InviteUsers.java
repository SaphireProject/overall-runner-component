package runner.data;

public class InviteUsers {
    private int id;
    private String userName;
    private String email;
    private boolean status;

    public InviteUsers(String userName, String email, int id) {
        this.userName = userName;
        this.email = email;
        this.id = id;
        status = false;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
