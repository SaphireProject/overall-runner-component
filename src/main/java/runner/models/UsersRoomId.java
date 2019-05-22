package runner.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UsersRoomId implements Serializable {

    @Column(name = "idroom")
    private Integer idRoom;

    @Column(name = "iduser")
    private Integer idUser;

    public UsersRoomId(Integer idRoom, Integer idUser) {
        this.idRoom = idRoom;
        this.idUser = idUser;
    }

    public UsersRoomId() {
    }

    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        final Integer prime = 31;
        Integer result = 1;
        result = prime * result
                + ((idRoom == null) ? 0 : idRoom.hashCode());
        result = prime * result + idUser;
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
        UsersRoomId other = (UsersRoomId) obj;
        if (idRoom == null) {
            if (other.idRoom != null)
                return false;
        } else if (!idRoom.equals(other.idRoom))
            return false;
        if (idUser != other.idUser)
            return false;
        return true;
    }
}
