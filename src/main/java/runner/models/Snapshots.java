package runner.models;

import javax.persistence.*;

@Entity
@Table(name = "snapshots")
public class Snapshots {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int idroom;

    private String snapshot;

    private int type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdRoom() {
        return idroom;
    }

    public void setIdRoom(int idroom) {
        this.idroom = idroom;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}