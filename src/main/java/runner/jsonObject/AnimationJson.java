package runner.jsonObject;

import java.util.List;

public class AnimationJson {
    private List<TankJson> tanks;
    private List<BulletJson> bullets;

    public AnimationJson() {
    }

    public AnimationJson(List<TankJson> tanks , List<BulletJson> bullets) {
        this.tanks = tanks;
        this.bullets = bullets;
    }

    public List<TankJson> getTanks() {
        return tanks;
    }

    public void setTanks(List<TankJson> tanks) {
        this.tanks = tanks;
    }

    public List<BulletJson> getBullets() {
        return bullets;
    }

    public void setBullets(List<BulletJson> bullets) {
        this.bullets = bullets;
    }
}
