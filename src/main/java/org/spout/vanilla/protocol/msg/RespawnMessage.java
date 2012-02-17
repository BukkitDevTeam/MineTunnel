package org.spout.vanilla.protocol.msg;

import org.spout.api.protocol.Message;

public final class RespawnMessage extends Message {

    private final byte dimension, difficulty, mode;
    private final int worldHeight;
    private final long seed;
    private final String worldType;

    public RespawnMessage(byte dimension, byte difficulty, byte mode, int worldHeight, long seed, String worldType) {
        this.dimension = dimension;
        this.difficulty = difficulty;
        this.mode = mode;
        this.worldHeight = worldHeight;
        this.seed = seed;
        this.worldType = worldType;
    }

    public byte getDimension() {
        return dimension;
    }

    public byte getDifficulty() {
        return difficulty;
    }

    public byte getGameMode() {
        return mode;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public long getSeed() {
        return seed;
    }

    public String getWorldType() {
        return worldType;
    }

    @Override
    public String toString() {
        return "RespawnMessage{dimension=" + dimension + ",difficulty=" + difficulty + ",gameMode=" + mode + ",worldHeight=" + worldHeight + ",seed=" + seed + ",worldType=" + worldType + "}";
    }
}
