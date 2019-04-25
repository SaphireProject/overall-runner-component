package runner.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class StrategiesMapId implements Serializable {

    private Integer id_strategy;
    private Integer id_room;

    public StrategiesMapId() {

    }

    public StrategiesMapId(Integer id_strategy, Integer id_room) {
        this.id_strategy = id_strategy;
        this.id_room = id_room;
    }

    public Integer getid_strategy() {
        return id_strategy;
    }

    public void setid_strategy(Integer id_strategy) {
        this.id_strategy = id_strategy;
    }

    public Integer getid_room() {
        return id_room;
    }

    public void setid_room(Integer id_room) {
        this.id_room = id_room;
    }

    @Override
    public int hashCode() {
        final Integer prime = 31;
        Integer result = 1;
        result = prime * result
                + ((id_room == null) ? 0 : id_room.hashCode());
        result = prime * result + id_strategy;
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
        StrategiesMapId other = (StrategiesMapId) obj;
        if (id_room == null) {
            if (other.id_room != null)
                return false;
        } else if (!id_room.equals(other.id_room))
            return false;
        if (id_strategy != other.id_strategy)
            return false;
        return true;
    }

}
