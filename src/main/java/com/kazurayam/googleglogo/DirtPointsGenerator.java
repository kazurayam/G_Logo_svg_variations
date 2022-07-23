package com.kazurayam.googleglogo;

import java.awt.Point;
import java.util.Random;

public class DirtPointsGenerator {

    private int xBound = 24;
    private int yBound = 24;
    private final Random xRand;
    private final Random yRand;

    public DirtPointsGenerator() {
        xRand = new Random();
        yRand = new Random();
    }

    public void setXBound(int xb) {
        this.xBound = xb;
    }

    public void setYBound(int yb) {
        this.yBound = yb;
    }

    public Point nextPoint() {
        int x = xRand.nextInt(xBound);
        int y = yRand.nextInt(yBound);
        return new Point(x, y);
    }

}
