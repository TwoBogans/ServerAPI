package io.servertap.api.v1.models.stats;

import com.google.gson.annotations.Expose;

public class Mined {

    @Expose
    private long obsidian;

    @Expose
    private long enderChest;

    public void setObsidian(long obsidian) {
        this.obsidian = obsidian;
    }

    public long getObsidian() {
        return obsidian;
    }

    public void setEnderChest(long enderChest) {
        this.enderChest = enderChest;
    }

    public long getEnderChest() {
        return enderChest;
    }
}
