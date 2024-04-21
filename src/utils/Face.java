package utils;

import java.util.List;

public class Face {
    public List<Integer> vertexIndices;
    public List<Integer> textureIndices;
    public List<Integer> normalIndices;

    public Face(List<Integer> vertexIndices, List<Integer> textureIndices, List<Integer> normalIndices) {
        this.vertexIndices = vertexIndices;
        this.textureIndices = textureIndices;
        this.normalIndices = normalIndices;
    }

    public List<Integer> getVertexIndices() {
        return vertexIndices;
    }

    public List<Integer> getTextureIndices() {
        return textureIndices;
    }

    public List<Integer> getNormalIndices() {
        return normalIndices;
    }
}
