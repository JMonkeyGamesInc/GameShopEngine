package com.jmonkeygamesinc.gameshop.niftygui;

import com.jme3.app.Application;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * A ScreenController for the "start" screen defined in
 * "Interfaces/Nifty/HelloJme.xml", which is used in the TestAppStates and
 * TestNiftyGui applications.
 */
public class StartScreenController implements ScreenController {

    final private Application application;

    /**
     * Instantiate a ScreenController for the specified Application.
     *
     * @param app the Application
     */
    public StartScreenController(Application app) {
        this.application = app;
    }

    /**
     * Nifty invokes this method when the screen gets enabled for the first
     * time.
     *
     * @param nifty (not null)
     * @param screen (not null)
     */
    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind(" + screen.getScreenId() + ")");
    }

    /**
     * Nifty invokes this method each time the screen starts up.
     */
    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
    }

    /**
     * Nifty invokes this method each time the screen shuts down.
     */
    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }

    /**
     * Stop the Application. Nifty invokes this method (via reflection) after
     * the user clicks on the flashing orange panel.
     */
    public void quit() {
        //application.stop();
    }
}
