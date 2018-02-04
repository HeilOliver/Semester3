package at.fhv.ohe.slidergame;

import at.fhv.ohe.slider.Slider;
import at.fhv.ohe.slider.SliderFactory;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SliderController extends GridPane {

    private Slider<Integer> slider;
    private HashMap<Integer, Button> resolver;


    public SliderController() {
        super();
        setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
        initialItems(SliderFactory.createSlider());
    }

    private void initialItems(Slider<Integer> slider){
        int height = slider.getHeight();
        int width = slider.getWidth();

        List<RowConstraints> rows = new LinkedList<>();
        for (int i = 0; i < height; i++) {
            rows.add(new RowConstraints(200));
        }
        getRowConstraints().addAll(rows);

        List<ColumnConstraints> columns = new LinkedList<>();
        for (int i = 0; i < width; i++) {
            columns.add(new ColumnConstraints(200));
        }
        getColumnConstraints().addAll(columns);

        this.slider = slider;


        Integer[][] sliderField = slider.getField();
        resolver = new HashMap<>();

        for (int i = 0; i < slider.getWidth()*slider.getHeight(); i++) {
            Integer field = sliderField[i / slider.getHeight()][i % slider.getWidth()];
            if (field == null) continue;
            Button button = new Button(field.toString());
            button.setOnMouseClicked(this::clicked);
            resolver.put(field, button);
        }

        for (Button button : resolver.values()) {
            button.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
            GridPane.setHgrow(button, Priority.ALWAYS);
        }

        getChildren().addAll(resolver.values());
        draw();
    }

    private void clicked(MouseEvent event) {
        Button source = (Button) event.getSource();

        Integer[][] field = slider.getField();

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                Button button = resolver.get(field[i][j]);
                if (!source.equals(button)) continue;
                slider.move(i,j);
                draw();
                return;
            }
        }

        if (slider.isWon()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have Won!!!!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    public void draw() {
        Integer[][] field = slider.getField();

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                Button button = resolver.get(field[i][j]);
                if (button == null) continue;

                GridPane.clearConstraints(button);
                GridPane.setConstraints(button, i, j);
                //getChildren().add(button);
            }
        }
    }
}
