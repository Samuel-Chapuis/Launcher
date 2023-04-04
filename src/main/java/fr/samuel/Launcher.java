package fr.samuel;

import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.samuel.ui.PanelManager;
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


    public Launcher() {
        instance = this;
        this.logger = new Logger("[LauncherFX]", this.launcherDir.resolve("launcher.log"));
        if (!this.launcherDir.toFile().exists()) {
            if (!this.launcherDir.toFile().mkdir()) {
                this.logger.err("Unable to create launcher folder");
            }
        }

        //saver = new Saver(this.launcherDir.resolve("config.properties"));
        //saver.load();
    }

    @Override
    public void start(Stage stage) {
        this.logger.info("Starting launcher");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
    }

    public ILogger getLogger(){
        return logger;
    }

    public static Launcher getInstace() {
        return instance;
    }
}