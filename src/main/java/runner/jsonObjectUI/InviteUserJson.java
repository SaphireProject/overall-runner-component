package runner.jsonObjectUI;

public class InviteUserJson {
    private int id;
    private String username;
    private String email;
    private int idOfRoom;

    public InviteUserJson(int id, String username, String email, int idOfRoom) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.idOfRoom = idOfRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIdOfRoom() {
        return idOfRoom;
    }

    public void setIdOfRoom(int idOfRoom) {
        this.idOfRoom = idOfRoom;
    }
}
