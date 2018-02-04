package at.fhv.ohe.cheesequalitycontroll;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class CheeseFactory {
    private static final int CHEESE_SAMPLE_COUNT = 4;
    private static final String CHEESE_SUFFIX = "cheese";

    private String _cheeseSuffix;
    private int _cheeseSamples;

    public CheeseFactory() {
        this(CHEESE_SUFFIX, CHEESE_SAMPLE_COUNT);
    }

    CheeseFactory(String cheeseSuffix, int cheeseSamples) {
        _cheeseSuffix = cheeseSuffix;
        _cheeseSamples = cheeseSamples;
    }

    Cheese getCheese(int no) {
        try {
            int[][] inks = loadPicture(_cheeseSuffix + no + ".bmp");
            return createCheese(inks);
        } catch (IOException e) {
            return null;
        }
    }

    public Cheese getCheese(String s) {
        int i = s.length() % _cheeseSamples;
        return getCheese(i);
    }

    private int[][] loadPicture(String s) throws IOException{
        InputStream stream = getClass().getResourceAsStream(s);

        if (stream== null) {
            throw new IOException();
        }

        BufferedImage image = ImageIO.read(stream);
        int height = image.getHeight();
        int width = image.getWidth();
        int[][] grid = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = image.getRGB(j,i);
            }
        }

        // To get color
        /*
        int alpha = (rgb >> 24) & 0xFF;
        int red =   (rgb >> 16) & 0xFF;
        int green = (rgb >>  8) & 0xFF;
        int blue =  (rgb      ) & 0xFF;
         */

        return grid;
    }

    private Cheese createCheese(int[][] arr){
        boolean[][] cheese = new boolean[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                cheese[i][j] = (arr[i][j] == -16777216);
            }
        }
        return new Cheese(cheese);
    }
}
