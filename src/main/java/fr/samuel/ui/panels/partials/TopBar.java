//Permet de faire une bare custom ( en spécifiant bien l'attribut Undecorated sur la fennetre principale ) mais c'est pas tres beau

/*
package fr.samuel.ui.panels.partials;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.samuel.ui.PanelManager;
import fr.samuel.ui.panel.Panel;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileInputStream;
import java.io.InputStream;

public class TopBar extends Panel {
    private GridPane topBar;

    @Override
    public String getName() {
        return "TopBar";
    }

    @Override
    public String getStylesheetPath() {
        return null;
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        this.topBar = this.layout;
        this.layout.setStyle("-fx-background-color: rgb(35, 40, 40);");

        //Partie gauche de la barre
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("images/icon.png")); //On pourrait prendre une autre image de meilleur résolution que l'inconne ici
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(25);
        setLeft(imageView);
        this.layout.getChildren().add(imageView);

        //Centre de la barre
        Label title = new Label("Son petit nom");
        try{
            InputStream fontStream = new FileInputStream("src\\main\\resources\\fonts\\futura.ttf");
            title.setFont(Font.loadFont(fontStream, 18));
        }catch (Exception e){
            System.out.println("Font cannot be load !");
        }
        title.setStyle("-fx-text-fill: white;");
        setCenterH(title);
        this.layout.getChildren().add(title);

        //Partie droite de la barre
        GridPane topBarButton = new GridPane();
        topBarButton.setMinWidth(100d);
        topBarButton.setMaxWidth(100d);
        setCanTakeAllSize(topBarButton);
        setRight(topBarButton);
        this.layout.getChildren().add(topBarButton);

        //Configuration des bouttons
        FontAwesomeIconView closeBtn = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_CLOSE);
        FontAwesomeIconView fullscreenBtn = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_MAXIMIZE);
        FontAwesomeIconView minimizeBtn = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_MINIMIZE);
        setCantTakeAllWidth(closeBtn, fullscreenBtn, minimizeBtn);

        closeBtn.setFill(Color.WHITE);
        closeBtn.setOpacity(.7f);
        closeBtn.setSize("18px");
        closeBtn.setOnMouseEntered(e -> closeBtn.setOpacity(1.f));
        closeBtn.setOnMouseExited(e -> closeBtn.setOpacity(.7f));
        closeBtn.setOnMouseClicked(e -> System.exit(0));
        closeBtn.setTranslateX(70f);

        fullscreenBtn.setFill(Color.WHITE);
        fullscreenBtn.setOpacity(0.70f);
        fullscreenBtn.setSize("14px");
        fullscreenBtn.setOnMouseEntered(e -> fullscreenBtn.setOpacity(1.0f));
        fullscreenBtn.setOnMouseExited(e -> fullscreenBtn.setOpacity(0.7f));
        fullscreenBtn.setOnMouseClicked(e -> this.panelManager.getStage().setMaximized(!this.panelManager.getStage().isMaximized()));
        fullscreenBtn.setTranslateX(50.0d);

        minimizeBtn.setFill(Color.WHITE);
        minimizeBtn.setOpacity(0.70f);
        minimizeBtn.setSize("18px");
        minimizeBtn.setOnMouseEntered(e -> minimizeBtn.setOpacity(1.0f));
        minimizeBtn.setOnMouseExited(e -> minimizeBtn.setOpacity(0.7f));
        minimizeBtn.setOnMouseClicked(e -> this.panelManager.getStage().setIconified(true));
        minimizeBtn.setTranslateX(26.0d);

        topBarButton.getChildren().addAll(closeBtn, fullscreenBtn, minimizeBtn);
    }
}
*/