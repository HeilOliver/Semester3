package at.fhv.ohe.slider;

import java.util.Collections;
import java.util.LinkedList;

public class SliderFactory {

    private SliderFactory() {};

    /**
     * Creates an Slider Field with the given Size and initialize it with
     * random numbers from 1 to {@code width}*{@code height}.
     * @param width - The Width of the game Field
     * @param height - The Height of the game Field
     * @throws SliderCreateException - When {@code width} or {@code height} is <= 0
     */
    public static Slider<Integer> createSliderBySize(int width, int height) {
        if (width <= 1 || height <= 1) throw new IllegalArgumentException("Size must be bigger than 1");

        Integer[][] ints = new Integer[width][height];

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i < width * height; i++) {
            list.add(i);
        }
        list.add(null);
        Collections.shuffle(list);

        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                ints[i][j] = list.poll();
            }
        }
        return new Slider<>(ints);
    }

    /**
     * Creates an Slider Field with the Size 3 x 3 and initialize it with integer Values from 1-3
     */
    public static Slider<Integer> createSlider() {
        return createSliderBySize(3,3);
    }
}
