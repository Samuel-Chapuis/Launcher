package fr.samuel.ui.panels.pages;

import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.model.AuthAgent;
import fr.litarvan.openauth.model.response.AuthResponse;
import fr.samuel.Launcher;
import fr.samuel.ui.PanelManager;
import fr.samuel.ui.panel.Panel;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class Login extends Panel {
    GridPane loginCard = new GridPane();
    Saver saver = Launcher.getInstance().getSaver();
    TextField userField = new TextField();
    PasswordField passwordField = new PasswordField();
    Label userErrorLabel = new Label();
    Label passwordErrorLabel = new Label();
    Button btnLogin = new Button("Connexion");
    Button msLoginBtn = new Button();


    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStylesheetPath() {
        return "css/login.css";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        // Background
        this.layout.getStyleClass().add("login-layout");


        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.LEFT);
        columnConstraints.setMinWidth(350);
        columnConstraints.setMaxWidth(350);
        this.layout.getColumnConstraints().addAll(columnConstraints, new ColumnConstraints());
        this.layout.add(loginCard, 0, 0);


        //Teste d'une barre horizontale
        /*
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setValignment(VPos.BOTTOM);
        rowConstraints.setMinHeight(500);
        rowConstraints.setMaxHeight(500);
        this.layout.getRowConstraints().addAll(rowConstraints, new RowConstraints());
        this.layout.add(loginCard, 0, 0);
        */

        // Background image
        GridPane bgImage = new GridPane();
        setCanTakeAllSize(bgImage);
        bgImage.getStyleClass().add("bg-image");
        this.layout.add(bgImage, 1, 0);

        // Login card
        setCanTakeAllSize(this.layout);
        loginCard.getStyleClass().add("login-card");
        setLeft(loginCard);
        setCenterH(loginCard);
        setCenterV(loginCard);

        //Login sidebar
        Label title = new Label("Le petit Nom\ndu Serveur");
        title.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 30f));
        title.getStyleClass().add("login-title");
        setCenterH(title);
        setCanTakeAllSize(title);
        setTop(title);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setTranslateY(30d);
        loginCard.getChildren().add(title);


        //User Name / Email
        setCantTakeAllWidth(this.userField);
        setCenterV(userField);
        setCenterH(userField);
        userField.setPromptText("Adresse E-Mail");
        userField.setMaxWidth(300);
        userField.setTranslateY(-70d);
        userField.getStyleClass().add("login-input");
        userField.focusedProperty().addListener((_a, oldValue, newValue) -> {
            if (!newValue) this.updateLoginBtnState(passwordField,passwordErrorLabel);
        });

        //user error
        setCanTakeAllSize(userErrorLabel);
        setCenterV(userErrorLabel);
        setCenterH(userErrorLabel);
        userErrorLabel.getStyleClass().add("login-error");
        userErrorLabel.setTranslateY(-45d);
        userErrorLabel.setMaxWidth(280);
        userErrorLabel.setTextAlignment(TextAlignment.LEFT);

        // Password
        setCanTakeAllSize(passwordField);
        setCenterV(passwordField);
        setCenterH(passwordField);
        passwordField.setPromptText("Mot de passe");
        passwordField.setMaxWidth(300);
        passwordField.setTranslateY(-15d);
        passwordField.getStyleClass().add("login-input");
        passwordField.focusedProperty().addListener((_a, oldValue, newValue) -> {
            if (!newValue) this.updateLoginBtnState(passwordField,passwordErrorLabel);
        });

        // password error
        setCanTakeAllSize(passwordErrorLabel);
        setCenterV(passwordErrorLabel);
        setCenterH(passwordErrorLabel);
        passwordErrorLabel.getStyleClass().add("login-error");
        passwordErrorLabel.setTranslateY(10d);
        passwordErrorLabel.setMaxWidth(280);
        passwordErrorLabel.setTextAlignment(TextAlignment.LEFT);

        // Login button
        setCanTakeAllSize(btnLogin);
        setCenterV(btnLogin);
        setCenterH(btnLogin);
        btnLogin.setDisable(true);
        btnLogin.setMaxWidth(300);
        btnLogin.setTranslateY(40d);
        btnLogin.getStyleClass().add("login-log-btn");
        btnLogin.setOnMouseClicked(e -> this.authenticate(userField.getText(), passwordField.getText()));


        Separator separator = new Separator();
        setCanTakeAllSize(separator);
        setCenterH(separator);
        setCenterV(separator);
        separator.getStyleClass().add("login-separator");
        separator.setMaxWidth(300);
        separator.setTranslateY(90d);

        // Login with label
        Label loginWithLabel = new Label("Ou se connecter avec:".toUpperCase());
        setCanTakeAllSize(loginWithLabel);
        setCenterV(loginWithLabel);
        setCenterH(loginWithLabel);
        loginWithLabel.setFont(Font.font(loginWithLabel.getFont().getFamily(), FontWeight.BOLD, FontPosture.REGULAR, 14d));
        loginWithLabel.getStyleClass().add("login-with-label");
        loginWithLabel.setTranslateY(110d);
        loginWithLabel.setMaxWidth(280d);

        // Microsoft login button
        ImageView view = new ImageView(new Image("images/microsoft.png"));
        view.setPreserveRatio(true);
        view.setFitHeight(30d);
        setCanTakeAllSize(msLoginBtn);
        setCenterH(msLoginBtn);
        setCenterV(msLoginBtn);
        msLoginBtn.getStyleClass().add("ms-login-btn");
        msLoginBtn.setMaxWidth(300);
        msLoginBtn.setTranslateY(145d);
        msLoginBtn.setGraphic(view);
        msLoginBtn.setOnMouseClicked(e -> {});

        loginCard.getChildren().addAll(userField, userErrorLabel, passwordField, passwordErrorLabel, btnLogin, separator, loginWithLabel, msLoginBtn);
    }

    public void updateLoginBtnState(TextField textField, Label erroLabel) {
        if (textField.getText().length() == 0) {
            erroLabel.setText("Le champ ne peut être vide");
        } else {
            erroLabel.setText("");
        }

        btnLogin.setDisable(!(userField.getText().length() > 0 && passwordField.getText().length() > 0));
    }

    public void authenticate(String user, String password) {
        Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

        try {
            AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, user, password, null);

            saver.set("accessToken", response.getAccessToken());
            saver.set("clientToken", response.getClientToken());
            saver.save();

            Launcher.getInstance().setAuthProfile(response.getSelectedProfile());

            this.logger.info("Hello " + response.getSelectedProfile().getName());
            // TODO: redirect the user to the homepage
        } catch (AuthenticationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Une erreur est survenu lors de la connexion");
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}


