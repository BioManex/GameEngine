package textures;

import java.nio.ByteBuffer;

public class TextureData {

    private int width;
    private int height;
    private ByteBuffer buffer;

    public TextureData(int width, int height, ByteBuffer buffer) {
        this.width = width;
        this.height = height;
        this.buffer = buffer;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
