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
                        // For each hit, we know distance, impact point, name of geometry.
                        float dist = results.getCollision(i).getDistance();
                        Vector3f pt = results.getCollision(i).getContactPoint();
                        String hit = results.getCollision(i).getGeometry().getName();
                        System.out.println("* Collision #" + i);
                        System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
                    }
                }
            }
        }
    };

//        public void onAnalog(String name, float intensity, float tpf) {
//            if (name.equals("pick target")) {
//                // 1. Reset results list.
//                CollisionResults results = new CollisionResults();
//                // 2. Aim the ray from cam loc to cam direction.
//                Ray ray = new Ray(app.getCamera().getLocation(), app.getCamera().getDirection());
//
//               // app.getInputManager().
//                Vector2f click2d = app.getInputManager().getCursorPosition();
//                Vector3f click3d = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
//                Vector3f dir = app.getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
//                // Aim the ray from the clicked spot forwards.
//                //Ray ray = new Ray(click3d, dir);
//                // 3. Collect intersections between Ray and Shootables in results list.
//                app.getRootNode().collideWith(ray, results);
//                // 4. Print the results
//                System.out.println("----- Collisions? " + results.size() + "-----");
//                for (int i = 0; i < results.size(); i++) {
//                    // For each hit, we know distance, impact point, name of geometry.
//                    float dist = results.getCollision(i).getDistance();
//                    Vector3f pt = results.getCollision(i).getContactPoint();
//                    String hit = results.getCollision(i).getGeometry().getName();
//                    System.out.println("* Collision #" + i);
//                    System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
//                }
//                // 5. Use the results (we mark the hit object)
////                if (results.size() > 0) {
////                    // The closest collision point is what was truly hit:
////                    CollisionResult closest = results.getClosestCollision();
////                    // Let's interact - we mark the hit with a red dot.
////                    mark.setLocalTranslation(closest.getContactPoint());
////                    rootNode.attachChild(mark);
////                } else {
////                    // No hits? Then remove the red mark.
////                    rootNode.detachChild(mark);
////                }
//            }
//        }
//    };

    /** A cube object for target practice */
//    private Geometry makeCube(String name, float x, float y, float z) {
//        Box box = new Box(1, 1, 1);
//        Geometry cube = new Geometry(name, box);
//        cube.setLocalTranslation(x, y, z);
//        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat1.setColor("Color", ColorRGBA.randomColor());
//        cube.setMaterial(mat1);
//        return cube;
//    }

    /** A floor to show that the "shot" can go through several objects. */
//    private Geometry makeFloor() {
//        Box box = new Box(15, .2f, 15);
//        Geometry floor = new Geometry("the Floor", box);
//        floor.setLocalTranslation(0, -4, -5);
//        Material mat1 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat1.setColor("Color", ColorRGBA.Gray);
//        floor.setMaterial(mat1);
//        return floor;
//    }

    /** A red ball that marks the last spot that was "hit" by the "shot". */
//    private void initMark() {
//        Sphere sphere = new Sphere(30, 30, 0.2f);
//        mark = new Geometry("BOOM!", sphere);
//        Material mark_mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mark_mat.setColor("Color", ColorRGBA.Red);
//        mark.setMaterial(mark_mat);
//    }

    /** A centred plus sign to help the player aim. */
//    private void initCrossHairs() {
//        setDisplayStatView(false);
//        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
//        BitmapText ch = new BitmapText(guiFont);
//        ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
//        ch.setText("+"); // crosshairs
//        ch.setLocalTranslation( // center
//                settings.getWidth() / 2 - ch.getLineWidth()/2, settings.getHeight() / 2 + ch.getLineHeight()/2, 0);
//        guiNode.attachChild(ch);
//    }

//    private Spatial makeCharacter() {
//        // load a character from jme3test-test-data
//        Spatial golem = assetManager.loadModel("Models/Oto/Oto.mesh.xml");
//        golem.scale(0.5f);
//        golem.setLocalTranslation(-1.0f, -1.5f, -0.6f);
//
//        // We must add a light to make the model visible
//        DirectionalLight sun = new DirectionalLight();
//        sun.setDirection(new Vector3f(-0.1f, -0.7f, -1.0f));
//        golem.addLight(sun);
//        return golem;
//    }
}
