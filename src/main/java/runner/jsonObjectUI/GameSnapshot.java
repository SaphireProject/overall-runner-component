package runner.jsonObjectUI;

import runner.jsonObject.EndOfGame;
import runner.jsonObject.FrameJson;
import runner.jsonObject.PreloadFinalJson;
import runner.jsonObject.PreloadJson;
import runner.models.Snapshots;
import runner.models.SnapshotsHelper;

import java.util.List;

public class GameSnapshot {
    private PreloadJson preload;

    private List<FrameJson> frames;

    private EndOfGame endOfGame;

    public GameSnapshot(PreloadJson preload, List<FrameJson> frames, EndOfGame endOfGame) {
        this.preload = preload;
        this.frames = frames;
        this.endOfGame = endOfGame;
    }

    public GameSnapshot() {
    }

    public PreloadJson getPreload() {
        return preload;
    }

    public void setPreload(PreloadJson preload) {
        this.preload = preload;
    }

    public List<FrameJson> getFrames() {
        return frames;
    }

    public void setFrames(List<FrameJson> frames) {
        this.frames = frames;
    }

    public EndOfGame getEndOfGame() {
        return endOfGame;
    }

    public void setEndOfGame(EndOfGame endOfGame) {
        this.endOfGame = endOfGame;
    }
}
