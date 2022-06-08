package pl.flaaaxxx.spockbasic;

public class Renderer {
    private Palette palette;

    public Renderer(Palette palette) {
        this.palette = palette;
    }

    public void drawLine() {
    }

    public Colour getForegroundColour() {
        return palette.getPrimaryColour();
    }
}
