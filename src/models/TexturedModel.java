package models;

import textures.TextureModel;

public class TexturedModel {
    private RawModel rawModel;
    private TextureModel texture;



    public RawModel getRawModel() {
        return rawModel;
    }

    public int getTexture() {
        return texture.getID();
    }

    public TexturedModel(RawModel model, TextureModel texture) {
        this.rawModel = model;
        this.texture = texture;
    }
}
