package runner.models;

import javax.persistence.*;

@Entity
@Table(name = "strategies")
public class Strategies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer id_user;

    private String path;

    public Strategies(Integer id_user, String path) {
        this.id_user = id_user;
        this.path = path;
    }

    public Strategies(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
