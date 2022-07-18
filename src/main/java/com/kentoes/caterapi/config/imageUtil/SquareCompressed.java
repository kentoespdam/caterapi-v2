package com.kentoes.caterapi.config.imageUtil;

public final class SquareCompressed implements ImageFormat {
    private final int size;

    public SquareCompressed(int size) {
        this.size = size;
    }

    @Override
    public int width() {
        return size;
    }

    @Override
    public int height() {
        return size;
    }

    @Override
    public float compression() {
        return .5F;
    }
}
