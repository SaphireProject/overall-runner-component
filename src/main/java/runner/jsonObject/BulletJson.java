package runner.jsonObject;

public class BulletJson {
    private int positionX;
    private int positionY;
    private String bulletDirection;
    private boolean isFirstSnapshot;
    private boolean isLastSnapshot;

    public BulletJson() {
    }

    public BulletJson(int positionX , int positionY , String bulletDirection , boolean isFirstSnapshot , boolean isLastSnapshot) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.bulletDirection = bulletDirection;
        this.isFirstSnapshot = isFirstSnapshot;
        this.isLastSnapshot = isLastSnapshot;
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

    public String getBulletDirection() {
        return bulletDirection;
    }

    public void setBulletDirection(String bulletDirection) {
        this.bulletDirection = bulletDirection;
    }

    public boolean isFirstSnapshot() {
        return isFirstSnapshot;
    }

    public void setFirstSnapshot(boolean firstSnapshot) {
        isFirstSnapshot = firstSnapshot;
    }

    public boolean isLastSnapshot() {
        return isLastSnapshot;
    }

    public void setLastSnapshot(boolean lastSnapshot) {
        isLastSnapshot = lastSnapshot;
    }
}
