package com.molarmak.coursework.entities;

public enum LifeStyle {
    LOW_ACTIVE(1),
    MEDIUM_ACTIVE(2),
    HIGH_ACTIVE(3);

    private final int levelCode;

    LifeStyle(int levelCode) {
        this.levelCode = levelCode;
    }

    public int getLevelCode() {
        return this.levelCode;
    }
}
