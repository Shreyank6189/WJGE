
package models;


public class RawModel {

    private int vaoID;
    private int vertexCount;

    private int id;
    public RawModel(int vaoID, int vertexCount, Integer id) {
        this.vaoID = vaoID;
        System.out.println(this.vaoID);
        this.vertexCount = vertexCount;
        System.out.println(this.vertexCount);

    }

    public RawModel(RawModel rawModel) {
    }


    public void addTextureID(int id) {
        this.id = id;

    }


    public int getVaoID() {
        return vaoID;
    }


    public int getVertexCount() {
        return vertexCount;
    }

    public int getTextureID(){
        return this.id;
    }

}
