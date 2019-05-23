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

    private String description;

    private String name;

    @Column(name = "typegame")
    private String typeGame;

    public Strategies(Integer idUser, String description, String name) {
        this.idUser = idUser;
        this.description = description;
        this.name = name;
    }

    public Strategies() {

    }

    public String getTypeGame() {
        return typeGame;
    }

    public void setTypeGame(String typeGame) {
        this.typeGame = typeGame;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
