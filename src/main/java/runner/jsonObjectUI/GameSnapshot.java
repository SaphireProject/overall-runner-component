package runner.jsonObjectUI;

import runner.jsonObject.EndOfGame;
import runner.jsonObject.FrameJson;
import runner.jsonObject.PreloadFinalJson;
import runner.models.Snapshots;
import runner.models.SnapshotsHelper;

import java.util.List;

public class GameSnapshot {
    private SnapshotsHelper preloadFinalJson;

    private List<Snapshots> frameJson;

    private EndOfGame endOfGame;

    public GameSnapshot(SnapshotsHelper preloadFinalJson, List<Snapshots> frameJson, EndOfGame endOfGame) {
        this.preloadFinalJson = preloadFinalJson;
        this.frameJson = frameJson;
        this.endOfGame = endOfGame;
    }

    public GameSnapshot() {
    }

    public SnapshotsHelper getPreloadFinalJson() {
        return preloadFinalJson;
    }

    public void setPreloadFinalJson(SnapshotsHelper preloadFinalJson) {
        this.preloadFinalJson = preloadFinalJson;
    }

    public List<Snapshots> getFrameJson() {
        return frameJson;
    }

    public void setFrameJson(List<Snapshots> frameJson) {
        this.frameJson = frameJson;
    }

    public EndOfGame getEndOfGame() {
        return endOfGame;
    }

    public void setEndOfGame(EndOfGame endOfGame) {
        this.endOfGame = endOfGame;
    }
}
