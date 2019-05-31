package runner.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "users_room")
public class UsersRoom {

    @EmbeddedId
    private UsersRoomId id;

    @Column
    private Integer status;

    @Column
    private String chosentank;

    @Column
    private Integer idstrategy;

    @Column
    private Boolean checkinvite;

    public UsersRoom(Integer idRoom, Integer idUser) {
        UsersRoomId usersRoomId = new UsersRoomId(idRoom, idUser);
        this.id = usersRoomId;
        status = 0;
        checkinvite = false;
    }

    public UsersRoom() {
    }

    public Boolean isCheckInvite() {
        return checkinvite;
    }

    public void setCheckInvite(boolean checkinvite) {
        this.checkinvite = checkinvite;
    }

    public UsersRoomId getId() {
        return id;
    }

    public void setId(UsersRoomId id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getChosenTank() {
        return chosentank;
    }

    public void setChosenTank(String chosentank) {
        this.chosentank = chosentank;
    }

    public Integer getIdStrategy() {
        return idstrategy;
    }

    public void setIdStrategy(Integer idstrategy) {
        this.idstrategy = idstrategy;
    }

}