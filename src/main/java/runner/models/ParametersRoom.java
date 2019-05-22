package runner.models;

import javax.persistence.*;

@Entity
@Table(name = "parameters_room")
public class ParametersRoom {
    @EmbeddedId
    private ParametersRoomId id;

    //параметры игры
    private String value;

    public ParametersRoom(String name, int idroom, String value) {
        ParametersRoomId parametersRoomId = new ParametersRoomId(idroom, name);
        this.id = parametersRoomId;
        this.value = value;
    }

    public ParametersRoom() {
    }

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
