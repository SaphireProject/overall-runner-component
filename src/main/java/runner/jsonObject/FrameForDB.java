package runner.jsonObject;

public class FrameForDB {
    private int id;
    private String value;

    public FrameForDB(int id , String value) {
        this.id = id;
        this.value = value;
    }

    public FrameForDB() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
