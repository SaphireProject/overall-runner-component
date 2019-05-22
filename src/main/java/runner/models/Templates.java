package runner.models;

import javax.persistence.*;

@Entity
@Table(name = "templates")
public class Templates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String template;

    private String runpath;

    public Templates(String template, String runpath) {
        this.template = template;
        this.runpath = runpath;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getRunPath() {
        return runpath;
    }

    public void setRunPath(String runpath) {
        this.runpath = runpath;
    }
}