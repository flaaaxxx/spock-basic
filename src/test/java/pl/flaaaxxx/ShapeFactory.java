package pl.flaaaxxx;

import pl.flaaaxxx.spockbasic.Polygon;
import pl.flaaaxxx.spockbasic.Renderer;

public class ShapeFactory {
    private final Renderer renderer;

    public ShapeFactory(Renderer renderer) {
        this.renderer = renderer;
    }

    public Polygon createDefaultPolygon() {
        return new Polygon(4, renderer);
    }
}
