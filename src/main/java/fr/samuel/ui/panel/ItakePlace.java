package fr.samuel.ui.panel;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public interface ItakePlace {
    default void setCanTakeAllSize(Node node){
        GridPane.setHgrow(node, Priority.ALWAYS);
        GridPane.setVgrow(node, Priority.ALWAYS);
    }

    default void setCantTakeAllWidth(Node ... node){
        for(Node n : node){
            GridPane.setVgrow(n, Priority.ALWAYS);
        }
    }
}
