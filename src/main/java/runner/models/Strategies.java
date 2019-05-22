package runner.models;

import javax.persistence.*;

@Entity
@Table(name = "strategies")
public class Strategies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "iduser")
    private Integer idUser;

    private String path;

    private String name;

    public Strategies(Integer idUser, String path, String name) {
        this.idUser = idUser;
        this.path = path;
        this.name = name;
    }

    public Strategies() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
