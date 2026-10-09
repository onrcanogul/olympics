package com.huawei.olympics.model;

public class MedalCounts {
    public int goldCount;
    public int silverCount;
    public int bronzeCount;

    public MedalCounts() {
        goldCount = 0;
        silverCount = 0;
        bronzeCount = 0;
    }

    public int getTotalCount() {
        return goldCount + silverCount + bronzeCount;
    }

    public void increaseGoldCount() { goldCount++; }
    public void increaseSilverCount() {
        silverCount++;
    }
    public void increaseBronzeCount() { bronzeCount++; }
}
