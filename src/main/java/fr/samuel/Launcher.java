package fr.samuel;

import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.model.AuthProfile;
import fr.litarvan.openauth.model.response.RefreshResponse;
import fr.samuel.ui.PanelManager;
import fr.samuel.ui.panels.pages.Login;
import fr.samuel.ui.utils.Helpers;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;

public class Launcher extends Application {
    private PanelManager panelManager;
    private static Launcher instance;
    private final ILogger logger;
    private final Path launcherDir =  GameDirGenerator.createGameDir("launcher-fx", true);
    private final Saver saver;

    private AuthProfile authProfile = null;


    public Launcher() {
        instance = this;
        this.logger = new Logger("[LauncherFX]", this.launcherDir.resolve("launcher.log"));
        if (!this.launcherDir.toFile().exists()) {
            if (!this.launcherDir.toFile().mkdir()) {
                this.logger.err("Unable to create launcher folder");
            }
        }

        saver = new Saver(this.launcherDir.resolve("config.properties"));
        saver.load();
    }

    @Override
    public void start(Stage stage) {
        this.logger.info("Starting launcher");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();

        if (this.isUserAlreadyLoggedIn()) {
            logger.info("Hello " + authProfile.getName());
        } else {
            this.panelManager.showPanel(new Login());
        }
        this.panelManager.showPanel(new Login());
    }

    public void setAuthProfile(AuthProfile authProfile) {
        this.authProfile = authProfile;
    }

    public AuthProfile getAuthProfile() {
        return authProfile;
    }
    public ILogger getLogger(){
        return logger;
    }

    public static Launcher getInstance() {
        return instance;
    }

    public Saver getSaver() {
        return saver;
    }

    public Boolean isUserAlreadyLoggedIn(){
        if (saver.get("accessToken") != null && saver.get("clientToken") != null) {
            Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

            try {
                RefreshResponse response  = authenticator.refresh(saver.get("accessToken"), saver.get("clientToken"));
                saver.set("accessToken", response.getAccessToken());
                saver.set("clientToken", response.getClientToken());
                saver.save();
                this.authProfile = response.getSelectedProfile();

                return true;
            } catch (AuthenticationException ignored) {
                saver.remove("accessToken");
                saver.remove("clientToken");
                saver.save();
            }
        }

        return false;
    }

}