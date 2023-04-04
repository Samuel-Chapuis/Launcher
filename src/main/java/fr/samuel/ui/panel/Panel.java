package fr.samuel.ui.panel;

import fr.flowarg.flowlogger.ILogger;
import fr.samuel.Launcher;
import fr.samuel.ui.PanelManager;
import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public abstract class Panel implements IPanel, IMovable, ItakePlace {
    protected final ILogger logger;
    protected GridPane layout = new GridPane();
    protected PanelManager panelManager;

    public Panel(){
        this.logger = Launcher.getInstace().getLogger();
    }

    @Override
    public void inti(PanelManager panelManager) {
        this.panelManager = panelManager;
        setCanTakeAllSize(this.layout);
    }

    @Override
    public GridPane getLayout() {
        return layout;
    }

    @Override
    public void onShow() {
        FadeTransition transition = new FadeTransition(Duration.seconds(1), this.layout);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();
    }

    @Override
    public abstract String getname();

    @Override
    public void setLeft(Node node) {
        GridPane.setHalignment(node, HPos.LEFT);
    }

    @Override
    public void setRight(Node node) {
        GridPane.setHalignment(node, HPos.RIGHT);
    }

    @Override
    public void setTop(Node node) {
        GridPane.setValignment(node, VPos.TOP);
    }

    @Override
    public void setBottom(Node node) {
        GridPane.setValignment(node, VPos.BOTTOM);
    }

    @Override
    public void setBaseLine(Node node) {
        GridPane.setValignment(node, VPos.BASELINE);
    }

    @Override
    public void setCenterH(Node node) {
        GridPane.setHalignment(node, HPos.CENTER);
    }

    @Override
    public void setCenterV(Node node) {
        GridPane.setValignment(node, VPos.CENTER);
    }
}
