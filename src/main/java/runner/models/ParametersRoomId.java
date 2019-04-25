package runner.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ParametersRoomId implements Serializable {

    private int id_room;
    private String name;

    public ParametersRoomId() {

    }

    public ParametersRoomId(int id_room, String name) {
        this.id_room = id_room;
        this.name = name;
    }

    public int getId_room() {
        return id_room;
    }

    public void setId_room(int id_room) {
        this.id_room = id_room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((name == null) ? 0 : name.hashCode());
        result = prime * result + id_room;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ParametersRoomId other = (ParametersRoomId) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (id_room != other.id_room)
            return false;
        return true;
    }

}