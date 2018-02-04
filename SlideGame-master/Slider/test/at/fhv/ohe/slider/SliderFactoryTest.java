package at.fhv.ohe.slider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SliderFactoryTest {

    @Test
    void create_Empty() {
        // Empty = 3x3 field random
        Slider<Integer> slider = SliderFactory.createSlider();
        assertEquals(3, slider.getWidth());
        assertEquals(3, slider.getHeight());
    }

    @Test
    void create_WithBounds() {
        // Size x,Y and random fill
        Slider<Integer> slider = SliderFactory.createSliderBySize(5,5);
        assertEquals(5, slider.getWidth());
        assertEquals(5, slider.getHeight());

    }

    @Test
    void create_WithBounds_NegativeNumbers() {
        try {
            Slider slider = SliderFactory.createSliderBySize(-1,5);
            fail("Exception must be Thrown :(");
        } catch (IllegalArgumentException e) {
            assertEquals("Size must be bigger than 1", e.getMessage());
        }
        try {
            Slider slider = SliderFactory.createSliderBySize(-1,-1);
            fail("Exception must be Thrown :(");
        } catch (IllegalArgumentException e) {
            assertEquals("Size must be bigger than 1", e.getMessage());
        }
        try {
            Slider slider = SliderFactory.createSliderBySize(5,-1);
            fail("Exception must be Thrown :(");
        } catch (IllegalArgumentException e) {
            assertEquals("Size must be bigger than 1", e.getMessage());
        }
    }

}