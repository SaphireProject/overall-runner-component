package runner.jsonObject;

import org.json.JSONObject;

public class FrameJson {
    private int snapshotNumber;
    private AnimationJson animations;

    public FrameJson() {
    }

    public FrameJson(AnimationJson animations, int snapshotNumber) {
        this.snapshotNumber = snapshotNumber;
        this.animations = animations;
    }

    public int getSnapshotNumber() {
        return snapshotNumber;
    }

    public void setSnapshotNumber(int snapshotNumber) {
        this.snapshotNumber = snapshotNumber;
    }

    public AnimationJson getAnimations() {
        return animations;
    }

    public void setAnimations(AnimationJson animations) {
        this.animations = animations;
    }

}
