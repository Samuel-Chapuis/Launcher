package fr.samuel.ui.panel;


import fr.samuel.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {
    void inti(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
    String getname();
}
