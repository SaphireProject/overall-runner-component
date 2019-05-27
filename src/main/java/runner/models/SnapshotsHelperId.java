package runner.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SnapshotsHelperId implements Serializable {

    @Column(name = "idroom")
    private int idRoom;

    private String type;

    public SnapshotsHelperId() {
    }

    public SnapshotsHelperId(int idRoom, String type) {
        this.idRoom = idRoom;
        this.type = type;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
