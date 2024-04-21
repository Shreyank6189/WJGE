package Main;

import utils.FastNoiseLite;

public class PerlinCalculator {



    public static int Xq;
    public static int Yq;
    public static int Zq;
    public static float[][][] perlin3dData;
    public static float[][] perlin2dData;
    static FastNoiseLite noise = new FastNoiseLite();

    public static void main(String[] args) {
        noise.SetNoiseType(FastNoiseLite.NoiseType.Perlin);

    }

    public float calc3dPerlin(int xdata, int ydata, int zdata) {
        perlin3dData = new float[xdata][ydata][zdata];

        for (int x = 0; x < xdata; x++) {
            for (int y = 0; y < ydata; y++) {
                for (int z = 0; z < zdata; z++) {
                    Xq = x;
                    Yq = y;
                    Zq = z;
                    perlin3dData[Xq][Yq][Zq] = noise.GetNoise(Xq, Yq, Zq);


                }
            }
        }

        return perlin3dData[Xq][Yq][Zq];

    }

    public float calc2dPerlin(int xdata, int ydata) {
        perlin2dData = new float[xdata][ydata];

        for (int x = 0; x < xdata; x++) {
            for (int y = 0; y < ydata; y++) {
                Xq = x;
                Yq = y;
                perlin2dData[Xq][Yq] = noise.GetNoise(Xq, Yq);


            }
        }

        return perlin2dData[Xq][Yq];
    }

}
