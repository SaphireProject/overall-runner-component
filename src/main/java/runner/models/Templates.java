package runner.models;

import javax.persistence.*;

@Entity
@Table(name ="templates")
public class Templates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String template;

    private String runPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getRunPath() {
        return runPath;
    }

    public void setRunPath(String runPath) {
        this.runPath = runPath;
    }
}