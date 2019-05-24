package runner.jsonObject;

public class FrameJson {
    private static int idSnapshot=1;
    private int snapshotNumber;
    private AnimationJson animations;

    public FrameJson() {
    }

    public FrameJson(AnimationJson animations) {
        this.snapshotNumber = idSnapshot;
        this.animations = animations;
        idSnapshot++;
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
