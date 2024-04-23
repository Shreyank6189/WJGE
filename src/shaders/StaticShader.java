package shaders;


public class StaticShader extends ShaderProgram{

    private static final String VERTEX_FILE = "C:\\WJGE PROJECT SAVE\\WJGE\\src\\shaders\\vertexShader.glsl";
    private static final String FRAGMENT_FILE = "C:\\WJGE PROJECT SAVE\\WJGE\\src\\shaders\\fragmentShader.glsl";
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);

    }

    @Override
    protected void getAllUniformLocation() {

    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
}
