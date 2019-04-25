package runner.models;

import javax.persistence.*;

@Entity
@Table(name ="parameters_room")
public class ParametersRoom {
    @EmbeddedId
    private ParametersRoomId id;

    //параметры игры
    private String value;

    public ParametersRoomId getId() {
        return id;
    }

    public void setId(ParametersRoomId id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
