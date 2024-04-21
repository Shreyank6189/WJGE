package textures;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class TextureLoader {

    BufferedImage image;


    public TextureLoader(String path){
    GL11.glEnable(GL11.GL_TEXTURE_2D);



    {
        try {
            image = ImageIO.read(new File(path));
            id = GL11.glGenTextures();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
            if (image == null){
                throw new IOException("Fail to load image");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Inform the user about the error
            System.err.println(STR."Failed to load texture: \{e.getMessage()}");        }
    }



    ByteBuffer buffer = imageUtil.ImageUtil.imageToByteBuffer(image);

    // Upload the texture data
    GL30.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGBA, image.getWidth(), image.getHeight(), 0, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE, buffer);

    // Set texture parameters
    GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
    GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);

    // Unbind the texture
    GL30.glBindTexture(GL30.GL_TEXTURE_2D, 0);

    System.out.println("Texture Binded and Created!");

    }

    public BufferedImage getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    private static Integer id = null;
    private Integer Width = null;
    private Integer Length;

    public int getWidth() {
        return Width;
    }

    public void setNewLength(int newHeight) {
        this.Length = newHeight;
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);

        // Allocate buffer for empty texture
        ByteBuffer buffer = ByteBuffer.allocateDirect(image.getWidth() * Length * 4);

        GL11.glTexSubImage2D (GL11.GL_TEXTURE_2D, 0, GL30.GL_RGBA, image.getWidth(), Length,
                0, GL30.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public int getLength() {
        return Length;
    }

    public void setNewWidth(int newWidth) {
        this.Width = newWidth;
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);

        // Allocate buffer for empty texture
        ByteBuffer buffer = ByteBuffer.allocateDirect(Width * image.getHeight() * 4);

        GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_RGBA, newWidth, image.getHeight(),
                0, GL30.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }




    public void resizeTexture(int newWidth, int newLength) {
        Width = newWidth;
        Length = newLength;

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);

        // Allocate buffer for empty texture
        ByteBuffer buffer = ByteBuffer.allocateDirect(Width * Length * 4);

        GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_RGBA, Width, Length,
                0, GL30.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }


}
