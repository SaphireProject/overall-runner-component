package runner.jsonObjectUI;

public class InviteUserDeleteJson {
    private boolean status;
    private int idOfDeletedUser;
    private int idOfRoom;

    public InviteUserDeleteJson(boolean status, int idOfDeletedUser, int idOfRoom) {
        this.status = status;
        this.idOfDeletedUser = idOfDeletedUser;
        this.idOfRoom = idOfRoom;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getIdOfDeletedUser() {
        return idOfDeletedUser;
    }

    public void setIdOfDeletedUser(int idOfDeletedUser) {
        this.idOfDeletedUser = idOfDeletedUser;
    }

    public int getIdOfRoom() {
        return idOfRoom;
    }

    public void setIdOfRoom(int idOfRoom) {
        this.idOfRoom = idOfRoom;
    }
}
