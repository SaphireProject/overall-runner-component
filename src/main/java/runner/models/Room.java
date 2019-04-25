package runner.models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="room")
public class Room {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private LocalDate create_data;

    private Integer win_user;

    private Integer owner_id;

    private boolean started;

    private Integer id_template;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreate_data() {
        return create_data;
    }

    public void setCreate_data(LocalDate create_data) {
        this.create_data = create_data;
    }

    public Integer getWin_user() {
        return win_user;
    }

    public void setWin_user(Integer win_user) {
        this.win_user = win_user;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Integer getId_template() {
        return id_template;
    }

    public void setId_template(Integer id_template) {
        this.id_template = id_template;
    }
}
