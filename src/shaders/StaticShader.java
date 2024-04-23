package shaders;


import org.joml.Matrix4f;

public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE = "C:\\WJGE PROJECT SAVE\\WJGE\\src\\shaders\\vertexShader.glsl";
    private static final String FRAGMENT_FILE = "C:\\WJGE PROJECT SAVE\\WJGE\\src\\shaders\\fragmentShader.glsl";

    private  int location_transformationMatrix;
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);

    }

    @Override
    protected void getAllUniformLocation() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
public void loadTransformationMatrix(Matrix4f matrix4f){
        super.loadMatrix(location_transformationMatrix,matrix4f);
}

}
