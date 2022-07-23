package com.kazurayam.googleglogo;

import java.nio.file.Path;

public class GoogleGLogoConverterResult {
    private Path svg;
    private Path png;

    public GoogleGLogoConverterResult(Path svg, Path png) {
        this.svg = svg;
        this.png = png;
    }
    public Path getSvg() {
        return svg;
    }
    public Path getPng() {
        return png;
    }
}
