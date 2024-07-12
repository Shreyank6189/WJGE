package Main;

import utils.WindowCreator;

public class Main{

    public static float perlin2ddata;
    public static float perlin3ddata;


    public static void main(String[] args) {

       PerlinCalculator perlcalc = new PerlinCalculator();
        perlin2ddata = perlcalc.calc3dPerlin(10, 10, 10);
        perlin3ddata = perlcalc.calc2dPerlin(232, 231);
        System.out.println(perlin2ddata);
        System.out.println(perlin3ddata);
        new WindowCreator().initApp();
        Main main = new Main();


    }


}