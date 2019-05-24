package runner.jsonObject;

public class TankJson {
    private String id;
    private int positionX;
    private int positionY;
    private String color;
    private boolean isAlive;

    public TankJson() {
    }

    public TankJson(String id , int positionX , int positionY , String color , boolean isAlive) {
        this.id = id;
        this.positionX = positionX;
        this.positionY = positionY;
        this.color = color;
        this.isAlive = isAlive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}