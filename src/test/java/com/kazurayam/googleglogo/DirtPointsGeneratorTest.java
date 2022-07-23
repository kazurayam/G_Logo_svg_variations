package com.kazurayam.googleglogo;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DirtPointsGeneratorTest {

    @Test
    public void test_smoke() {
        DirtPointsGenerator generator = new DirtPointsGenerator();
        generator.setXBound(24);
        generator.setYBound(24);
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            points.add(generator.nextPoint());
        }
        System.out.println("<points>");
        points.forEach(p -> {
            System.out.println("  <p x=\"" + p.getX() + "\" y=\"" + p.getY() + "\"/>");
        });
        System.out.println("</points>");
    }
}
