package runner.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ParametersRoomId implements Serializable {

    @Column(name = "idroom")
    private int idRoom;

    @Column(name = "name")
    private String name;

    public ParametersRoomId() {

    }

    public ParametersRoomId(int idRoom, String name) {
        this.idRoom = idRoom;
        this.name = name;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return idRoom + "_" + name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((name == null) ? 0 : name.hashCode());
        result = prime * result + idRoom;
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
        if (idRoom != other.idRoom)
            return false;
        return true;
    }

}