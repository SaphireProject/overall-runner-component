package runner.models;

import javax.persistence.*;

@Entity
@Table(name = "strategies_map")
public class StrategiesMap {

    @EmbeddedId
    private StrategiesMapId id;

    public StrategiesMapId getId() {
        return id;
    }

    public void setId(StrategiesMapId id) {
        this.id = id;
    }
}