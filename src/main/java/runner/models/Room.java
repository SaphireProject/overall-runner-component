package runner.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate createdate;

    private Integer winuser;

    private Integer idowner;

    private boolean started;

    private Integer idtemplate;

    public Room(Integer idowner, boolean started, LocalDate localDate, Integer winuser, Integer idtemplate) {
        this.idowner = idowner;
        this.started = started;
        this.createdate = localDate;
        this.winuser = winuser;
        this.idtemplate = idtemplate;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createdate;
    }

    public void setCreateDate(LocalDate createdate) {
        this.createdate = createdate;
    }

    public Integer getWinUser() {
        return winuser;
    }

    public void setWinUser(Integer winuser) {
        this.winuser = winuser;
    }

    public Integer getIdOwner() {
        return idowner;
    }

    public void setIdOwner(Integer idowner) {
        this.idowner = idowner;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Integer getIdTemplate() {
        return idtemplate;
    }

    public void setId_template(Integer idtemplate) {
        this.idtemplate = idtemplate;
    }
}
