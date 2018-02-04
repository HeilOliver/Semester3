package at.fhv.ohe.cheesequalitycontroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheeseFactoryTest {
    private QualityControl _qualityControl;
    private CheeseFactory _factory;

    @BeforeEach
    void setUp() {
        _factory = new CheeseFactory("test_cheese",5);
        _qualityControl = new QualityControl();
    }

    private void checkCheese(int holeCount, int maxHole, int sample) {
        Cheese cheese = _factory.getCheese(sample);
        _qualityControl.checkCheese(cheese);

        assertEquals(holeCount, _qualityControl.getHoleCount());
        assertEquals(maxHole, _qualityControl.getMaxHoleSize());
    }

    @Test
    void test_Cheese0() {
        checkCheese(2,36,0);
    }

    @Test
    void test_Cheese1() {
        checkCheese(3,36,1);
    }

    @Test
    void test_Cheese2() {
        checkCheese(11,8,2);
    }

    @Test
    void test_Cheese3() {
        checkCheese(2,1,3);
    }

    @Test
    void test_Cheese4() {
        checkCheese(120,1,4);
    }
}