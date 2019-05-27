package runner.models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "snapshotshelper")
public class SnapshotsHelper {

    @EmbeddedId
    private SnapshotsHelperId id;

    private String value;

    public SnapshotsHelper() {
    }

    public SnapshotsHelper(int idRoom, String type) {
        SnapshotsHelperId snapshotsHelperId = new SnapshotsHelperId(idRoom, type);
        this.id = snapshotsHelperId;
    }

    public SnapshotsHelperId getId() {
        return id;
    }

    public void setId(SnapshotsHelperId id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
