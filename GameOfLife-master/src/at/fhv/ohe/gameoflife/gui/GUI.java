package at.fhv.ohe.gameoflife.gui;

import at.fhv.ohe.gameoflife.map.Cell;
import at.fhv.ohe.gameoflife.map.CellState;
import at.fhv.ohe.gameoflife.rule.IRule;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class GUI {
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println("Welcome to the GameOfLive :)");
        System.out.println("World Size X:");
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        System.out.println("World Size Y:");
        int y = sc.nextInt();

        controller.createNewWorld(x, y);
        printToScreen(controller.getCells());

        System.out.println("Ready To Play");
        boolean exit = false;

        while (!exit) {
            switch (sc.nextLine()) {
                case "a":
                    controller.alterCells();
                    printToScreen(controller.getCells());
                    break;
                case "ab":
                    System.out.println("By?");
                    int by = sc.nextInt();
                    for (int i = 0; i < by; i++) {
                        controller.alterCells();
                    }
                    printToScreen(controller.getCells());
                    break;
                case "d":
                    Set<Map.Entry<String, IRule>> entries = controller.getDisasters().entrySet();
                    int i = 0;
                    for (Map.Entry<String, IRule> entry : entries) {
                        System.out.println(i++ + " - " + entry.getKey());
                    }
                    System.out.println("Please select");
                    i = sc.nextInt();
                    int j = 0;
                    for (Map.Entry<String, IRule> entry : entries) {
                        if (j++ == i) {
                            controller.addDisaster(entry.getValue());
                        }
                    }
                    printToScreen(controller.getCells());
                    break;

                case "e":
                    exit = true;

                case "help":
                case "h":
                default:
                    System.out.println("Help");
                    System.out.println("a - for alter cells");
                    System.out.println("ab - for alter cells by factor");
                    System.out.println("d - for disaster");
                    System.out.println("e - for exit");
            }
        }


    }


    private static void printToScreen(List<Cell> cells) {
        cells.sort((o1, o2) -> o1.getLocation().getY() < o2.getLocation().getY() ? -1 : (o1.getLocation().getY() > o2.getLocation().getY()) ? 1 :
                (o1.getLocation().getX() < o2.getLocation().getX()) ? -1 : (o1.getLocation().getX() > o2.getLocation().getX()) ? 1 : 0
        );

        System.out.println(cells.size());
        int val = 0;
        for (Cell cell : cells) {
            if (cell.getLocation().getY() != val) {
                val = cell.getLocation().getY();
                System.out.print("\n");
            }

            if (cell.getState() == CellState.DEAD) {
                System.out.print(" - ");
            } else {
                System.out.print(" o ");
            }
        }
        System.out.print("\n");

    }
}
