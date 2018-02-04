package at.fhv.uebung3;

import at.fhv.ohe.cheesequalitycontroll.Cheese;
import at.fhv.ohe.cheesequalitycontroll.CheeseFactory;
import at.fhv.ohe.cheesequalitycontroll.ICheeseIterator;
import at.fhv.ohe.cheesequalitycontroll.QualityControl;

import java.util.Scanner;

public class RunCheeseQuality {

    private final Scanner _sc;
    private final CheeseFactory _factory;
    private final QualityControl _qControl;

    private RunCheeseQuality() {
        System.out.println("SWISS CHEESE QUALITY REPORT TOOL MK1");
        System.out.println("------------------------------------");
        _sc = new Scanner(System.in);
        _factory = new CheeseFactory();
        _qControl = new QualityControl();
        awaitCheese();
    }

    private void awaitCheese() {
        System.out.println("ENTER CHEESE TYPE:");
        String s = _sc.nextLine();
        if(s.length() == 0) {
            return;
        }
        Cheese cheese = _factory.getCheese(s);
        _qControl.checkCheese(cheese);
        printCheese(cheese);

        System.out.println("\nNumber of holes: " + _qControl.getHoleCount());
        System.out.println("\nbiggest hole: " + _qControl.getMaxHoleSize());
        awaitCheese();
    }

    private void printCheese(Cheese cheese) {
        int width = cheese.getWidth();
        int height = cheese.getHeight();

        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }

        System.out.println();
        ICheeseIterator look = cheese.getIterator();
        for (int i = 0; i < height; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                if (look.isInHole(j,i)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("|");
        }
        for (int i = 0; i < width; i++) {
            System.out.print("-");
        }
    }

    public static void main(String[] args) {
        new RunCheeseQuality();
    }

}
