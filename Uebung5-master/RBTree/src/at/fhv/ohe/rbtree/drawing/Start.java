package at.fhv.ohe.rbtree.drawing;

import at.fhv.ohe.rbtree.RBTree;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Start {


    public TextField textField;
    public Canvas canvas;
    private RBTree<Integer> tree;


    public Start() {
        tree = new RBTree<>(Integer::compareTo);
    }

    public void AddButton_click(MouseEvent mouseEvent) {
        int i = Integer.parseInt(textField.getText());
        textField.clear();

        tree.insert(i);
    }


    private void redrawCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int height = tree.getHeight();

        int step_X = (int) (canvas.getWidth() / Math.pow(2,height));
        int step_Y = (int) (canvas.getHeight() / height);

        RBTree<Integer>.RBNode<Integer> root = tree.getRoot();



    }
}
