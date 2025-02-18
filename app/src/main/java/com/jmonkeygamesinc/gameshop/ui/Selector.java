package com.jmonkeygamesinc.gameshop.ui;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetKey;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.TouchInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.TouchListener;
import com.jme3.input.controls.TouchTrigger;
import com.jme3.input.event.TouchEvent;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jmonkeygamesinc.gameshop.global.CurrencyMeshSingleton;
import com.jmonkeygamesinc.gameshop.graphics.GameShopCurrencyMesh;
import com.jmonkeygamesinc.gameshop.graphics.GameShopCurrencySurface;

import java.util.ArrayList;

/**
 *
 * @author Lynden Jay Evans of JMonkeyGames Inc.
 */
public class Selector {

    public GameShopCurrencyMesh selectedCM;
    public GameShopCurrencySurface selectedCS;

    public ArrayList<Geometry> selectors;

    SimpleApplication app;
    public String mode = "NONE"; // CURRENCYMESH, CURRENCYSURFACE, VECTOR3F
    public Selector(SimpleApplication app){

        this.app = app;
        selectors = new ArrayList<>();
        for (GameShopCurrencyMesh cm: CurrencyMeshSingleton.getInstance().cMeshes){

            Box box = new Box(.5f, .5f, .5f);
            Geometry geom = new Geometry("Box", box);
            Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
            material.setColor("Color", ColorRGBA.Red);
            geom.setMaterial(material);
            geom.setLocalTranslation(cm.gspSurfaces[0].vInfinitesimals[cm.gspSurfaces[0].vInfinitesimals.length/2].infinitesimals[cm.gspSurfaces[0].vInfinitesimals[cm.gspSurfaces[0].vInfinitesimals.length/2].infinitesimals.length/2]);
            app.getRootNode().attachChild(geom);
            selectors.add(geom);
        }

        initKeys();
    }

    /** Declaring the "Shoot" action and mapping to its triggers. */
    private void initKeys() {
        app.getInputManager().addMapping("pick target",
                 new TouchTrigger(TouchInput.ALL)); // trigger 2: left-button click
        app.getInputManager().addListener(touchListener, "pick target");
    }
    /** Defining the "Shoot" action: Determine what was hit and how to respond. */
    final private TouchListener touchListener = new TouchListener() {
        @Override
        public void onTouch(String name, TouchEvent event, float tpf) {
            CollisionResults results = new CollisionResults();
            if (name.equals("pick target")) {

                if (event.getType() == TouchEvent.Type.TAP) {

                    Vector2f click2d = new Vector2f(event.getX(), event.getY());
                    Vector3f click3d = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
                    Vector3f dir = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
                    // Aim the ray from the clicked spot forwards.
                    Ray ray = new Ray(click3d, dir);

                    app.getRootNode().collideWith(ray, results);
                    // 4. Print the results
                    System.out.println("----- Collisions? " + results.size() + "-----");
                    for (int i = 0; i < results.size(); i++) {
                        String hit = results.getCollision(i).getGeometry().getName();

                        if (hit.equals("Box")) {
                            // For each hit, we know distance, impact point, name of geometry.
                            float dist = results.getCollision(i).getDistance();
                            Vector3f pt = results.getCollision(i).getContactPoint();

                            System.out.println("* Collision #" + i);
                            System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
                        }
                    }
                }
            }
        }
    };

}
