package runner.jsonObject;

import java.util.List;

public class PreloadJson {
    private List<String> blocks;

    public PreloadJson() {
    }

    public PreloadJson(List<String> blocks) {
        this.blocks = blocks;
    }

    public List<String> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<String> blocks) {
        this.blocks = blocks;
    }
}
