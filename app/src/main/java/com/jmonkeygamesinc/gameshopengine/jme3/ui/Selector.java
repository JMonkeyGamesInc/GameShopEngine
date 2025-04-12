package com.jmonkeygamesinc.gameshopengine.jme3.ui;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetKey;
import com.jme3.collision.CollisionResults;
import com.jme3.input.TouchInput;
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
import com.jmonkeygamesinc.gameshopengine.jme3.global.GameShopCurrencyMeshHash;
import com.jmonkeygamesinc.gameshopengine.jme3.graphics.GameShopCurrencyLine;
import com.jmonkeygamesinc.gameshopengine.jme3.graphics.GameShopCurrencyMesh;
import com.jmonkeygamesinc.gameshopengine.jme3.graphics.GameShopCurrencySurface;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Lynden Jay Evans of JMonkeyGames Inc.
 */
public class Selector {

    public GameShopCurrencyMesh selectedCM;
    public GameShopCurrencySurface selectedCS;
    public GameShopCurrencyLine selectedCL;
    //public Vector3f selectedVector3f;

    public int selectedVec;
    public ArrayList<Geometry> selectors;
    public Geometry selectedSelector;

    public ArrayList<Geometry> movers;
    public Geometry selectedMover;

    public boolean moveEnabled = false;
    SimpleApplication app;

    public String selectedObjectName = "";
    public String mode = "CURRENCYSURFACE"; //NONE, CURRENCYMESH, CURRENCYSURFACE, VECTOR3

    public String action = "SELECT"; //SELECT MOVE OPEN ANIMATE

    public Vector2f lastPosition;
    public Selector(SimpleApplication app){

        this.app = app;
        selectors = new ArrayList<>();
        movers = new ArrayList<>();


        selectedCM = GameShopCurrencyMeshHash.getInstance().cMeshes.get("Main");
        selectedCS = Objects.requireNonNull(GameShopCurrencyMeshHash.getInstance().cMeshes.get("Main")).gspSurfaces[0];
        selectedCL = Objects.requireNonNull(GameShopCurrencyMeshHash.getInstance().cMeshes.get("Main")).gspSurfaces[0].currencyLines[0];
        //selectedVector3f = GameShopCurrencyMeshHash.getInstance().cMeshes.get("Main").gspSurfaces[0].currencyLines[0].points[0];
        selectedVec = 0;
        makeSelection();

        initKeys();
    }



//    public void selectObject(){
//
//        if (mode.equals("CURRENCYMESH")){
//
//            for (GameShopCurrencyMesh cm: CurrencyMeshSingleton.getInstance().cMeshes){
//                if (cm.name.equals(selectedObjectName)){
//
//                    selectedCM = cm;
//                }
//            }
//
//        } else if (mode.equals("CURRENCYSURFACE")){
//
//            for (GameShopCurrencyMesh cm: CurrencyMeshSingleton.getInstance().cMeshes){
//
//                for (GameShopCurrencySurface cs: cm.gspSurfaces){
//
//                    if (cs.name.equals(selectedObjectName)){
//
//                        selectedCM = cm;
//                        selectedCS = cs;
//
//                    }
//                }
//            }
//        } else if (mode.equals("CURRENCYLINE")){
//
//            for (GameShopCurrencyMesh cm: CurrencyMeshSingleton.getInstance().cMeshes){
//
//                for (GameShopCurrencySurface cs: cm.gspSurfaces){
//
//                    for (GameShopCurrencyLine cl: cs.currencyLines){
//
////                        if (cl.name.equals(selectedObjectName)){
////
////                            selectedCM = cm;
////                            selectedCS = cs;
////
////                        }
//                    }
//
//                }
//            }
//        } else if (mode.equals("VECTOR3F")){
//
//        }
//
//    }
    public void resetSelection(){

        for (Geometry g: selectors){

            Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
             if (!mode.equals("CURRENCYSURFACE")) {
            material.setColor("Color", ColorRGBA.Red);
                            } else {
                                material.setColor("Color", ColorRGBA.Orange);
                            }
            g.setMaterial(material);

        }
    }

    public void clearSelection(){


        for (Geometry g: selectors){

            app.getRootNode().detachChild(g);
        }
        selectors.clear();

    }

    public void resetMovers(){

        for (Geometry g: movers){

            Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
            material.setColor("Color", ColorRGBA.Red);
            g.setMaterial(material);

        }
    }

    public void clearMovers(){

        moveEnabled = false;

        for (Geometry g: movers){

            app.getRootNode().detachChild(g);
        }
        movers.clear();
    }
    public void makeSelection(){

        clearSelection();
        if (mode.equals("NONE")) {

            int i = 0;
            for (GameShopCurrencyMesh cm : GameShopCurrencyMeshHash.getInstance().cMeshes.values()) {

                Box box = new Box(.5f, .5f, .5f);
                Geometry geom = new Geometry("SelectBox " + i, box);
                Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
                material.setColor("Color", ColorRGBA.Red);
                geom.setMaterial(material);
                geom.setLocalTranslation(cm.gspSurfaces[0].vInfinitesimals[cm.gspSurfaces[0].vInfinitesimals.length / 2].infinitesimals[cm.gspSurfaces[0].vInfinitesimals[cm.gspSurfaces[0].vInfinitesimals.length / 2].infinitesimals.length / 2]);
                app.getRootNode().attachChild(geom);
                selectors.add(geom);
                i++;
            }
        } else if (mode.equals("CURRENCYMESH")){

            int i = 0;
            for (GameShopCurrencyMesh cm : GameShopCurrencyMeshHash.getInstance().cMeshes.values()) {

                for (GameShopCurrencySurface cs: cm.gspSurfaces) {
                    Box box = new Box(.5f, .5f, .5f);
                    Geometry geom = new Geometry("SelectBox " + i, box);
                    Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
                    material.setColor("Color", ColorRGBA.Orange);
                    geom.setMaterial(material);
                    geom.setLocalTranslation(cm.gspSurfaces[0].vInfinitesimals[cm.gspSurfaces[0].vInfinitesimals.length / 2].infinitesimals[cm.gspSurfaces[0].vInfinitesimals[cm.gspSurfaces[0].vInfinitesimals.length / 2].infinitesimals.length / 2]);

                    app.getRootNode().attachChild(geom);
                    selectors.add(geom);
                    i++;
                }
        }




        } else if (mode.equals("CURRENCYSURFACE")){

            int i = 0;
            for (GameShopCurrencyMesh cm : GameShopCurrencyMeshHash.getInstance().cMeshes.values()) {

                for (GameShopCurrencySurface cs: cm.gspSurfaces) {

                    for (GameShopCurrencyLine cl: cs.currencyLines) {

                        for (Vector3f v: cl.points) {
                            Box box = new Box(.25f, .25f, .25f);
                            Geometry geom = new Geometry("SelectBox " + i, box);
                            Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
                            material.setColor("Color", ColorRGBA.Orange);
                            geom.setMaterial(material);
                            geom.setLocalTranslation(v);

                            app.getRootNode().attachChild(geom);
                            selectors.add(geom);
                            i++;
                        }
                    }
                }
            }

        } else if (mode.equals("VECTOR3")){


        }
    }

    public void enableMove() {

//        Material materialMatch = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
//        materialMatch.setColor("Color", ColorRGBA.Blue);
        if (mode.equals("CURRENCYMESH")) {
            for (Geometry g : selectors) {

                if (g.equals(selectedSelector)) {

                    if (!moveEnabled) {
                        for (int i = 0; i < 6; i++) {
                            Box box = new Box(.25f, .25f, .25f);
                            Geometry geom = new Geometry("MoveBox " + i, box);
                            Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));

                           // if (!mode.equals("CURRENCYSURFACE")) {
                                material.setColor("Color", ColorRGBA.Red);
//                            } else {
//                                material.setColor("Color", ColorRGBA.Orange);
//                            }
                            geom.setMaterial(material);
                            geom.setLocalTranslation(g.getLocalTranslation());
                            //LEFT RIGHT TOP BOTTOM FORWARD BACKWARD
                            if (i == 0) {

                                geom.move(-1, 0, 0);

                            } else if (i == 1) {

                                geom.move(1, 0, 0);
                            } else if (i == 2) {

                                geom.move(0, 1, 0);
                            } else if (i == 3) {

                                geom.move(0, -1, 0);
                            } else if (i == 4) {

                                geom.move(0, 0, -1);
                            } else if (i == 5) {
                                geom.move(0, 0, 1);
                            }

                            //geom.setLocalTranslation(cm.gspSurfaces[0].vInfinitesimals[cm.gspSurfaces[0].vInfinitesimals.length / 2].infinitesimals[cm.gspSurfaces[0].vInfinitesimals[cm.gspSurfaces[0].vInfinitesimals.length / 2].infinitesimals.length / 2]);
                            app.getRootNode().attachChild(geom);
                            movers.add(geom);
                            // i++;
                        }
                        moveEnabled = true;
                    }
                }
            }
        } else if (mode.equals("CURRENCYSURFACE")) {

            for (Geometry g : selectors) {

                if (g.equals(selectedSelector)) {

                    if (!moveEnabled) {
                        for (int i = 0; i < 6; i++) {
                            Box box = new Box(.20f, .20f, .20f);
                            Geometry geom = new Geometry("MoveBox " + i, box);
                            Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
                            material.setColor("Color", ColorRGBA.Red);
                            geom.setMaterial(material);
                            geom.setLocalTranslation(g.getLocalTranslation());
                            //LEFT RIGHT TOP BOTTOM FORWARD BACKWARD
                            if (i == 0) {

                                geom.move(-.75f, 0, 0);

                            } else if (i == 1) {

                                geom.move(.75f, 0, 0);
                            } else if (i == 2) {

                                geom.move(0, .75f, 0);
                            } else if (i == 3) {

                                geom.move(0, -.75f, 0);
                            } else if (i == 4) {

                                geom.move(0, 0, -.75f);
                            } else if (i == 5) {
                                geom.move(0, 0, .75f);
                            }

                            //geom.setLocalTranslation(cm.gspSurfaces[0].vInfinitesimals[cm.gspSurfaces[0].vInfinitesimals.length / 2].infinitesimals[cm.gspSurfaces[0].vInfinitesimals[cm.gspSurfaces[0].vInfinitesimals.length / 2].infinitesimals.length / 2]);
                            app.getRootNode().attachChild(geom);
                            movers.add(geom);
                            // i++;
                        }
                        moveEnabled = true;
                    }
                }
            }
        }



    }

    public void selectFromHierarchy(int index){

        Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
        material.setColor("Color", ColorRGBA.Blue);


       int i = 0;

        if (mode.equals("CURRENCYMESH")) {
            for (GameShopCurrencyMesh cm : GameShopCurrencyMeshHash.getInstance().cMeshes.values()) {

                if (cm.equals(selectedCM)){

                    selectors.get(index).setMaterial(material);
                    selectedSelector = selectors.get(index);
                    enableMove();
                    System.out.println("SELECTED " + index);
                    break;
                }

                i++;
            }
        } else {

            for (GameShopCurrencyMesh cm : GameShopCurrencyMeshHash.getInstance().cMeshes.values()) {

                for (GameShopCurrencySurface cs: cm.gspSurfaces){



                    int clCount = 0;
                    boolean found = false;

                    for (GameShopCurrencyLine cl: cs.currencyLines){
                        int j = 0;
                        for (Vector3f v: cl.points){

//                            if (j > 3){
//                                j = 0;
//                            }
                            if(selectedVec == (clCount * 4) + j){
                            //if (selectedVector3f.equals(v)){
                                selectors.get(index).setMaterial(material);
                                selectedSelector = selectors.get(index);
                                enableMove();
                                System.out.println("SELECTED " + (index));
                                found = true;
                                break;
                            }
                           j++;
                        }
                        if (found){
                            break;
                        }
                        clCount++;
                    }
                }
//                if (cm.equals(selectedCM)){
//
//                    selectors.get(i).setMaterial(material);
//                    selectedSelector = selectors.get(i);
//                    enableMove();
//                    System.out.println("SELECTED " + i);
//                    break;
//                }
//
//                i++;
            }
        }


    }


    //public void
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

                   // if (action.equals("SELECT")){

                        // 4. Print the results
                        System.out.println("----- Collisions? " + results.size() + "-----");
                        int count = 0;
                        for (int i = 0; i < results.size(); i++){

                            String hit = results.getCollision(i).getGeometry().getName();

                            if (hit.contains("SelectBox")){
                                count++;
                            }

                        }
//
//                        if (count == 0){
//
//                            resetSelection();
//                            clearMovers();
//
////                            if (mode.equals("NONE")) {
////                                selectedCM = null;
////                            }
//
//                        }

                        for (int i = 0; i < results.size(); i++) {
                            String hit = results.getCollision(i).getGeometry().getName();


                            if (hit.contains("MoveBox")) {
                                //resetSelection();
                               //clearMovers();
                                resetMovers();
                                // For each hit, we know distance, impact point, name of geometry.
                                float dist = results.getCollision(i).getDistance();
                                Vector3f pt = results.getCollision(i).getContactPoint();

                                System.out.println("* Collision #" + i);
                                System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");

//                                if (mode.equals("NONE")) {
//                                    //selectedCM = CurrencyMeshSingleton.getInstance().cMeshes.get(Integer.parseInt(hit.split(" ")[1]));
//
//                                    Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
//                                    material.setColor("Color", ColorRGBA.Blue);
//
//                                    selectors.get(Integer.parseInt(hit.split(" ")[1])).setMaterial(material);
//                                    selectedSelector = selectors.get(Integer.parseInt(hit.split(" ")[1]));
//                                } else

                                if (mode.equals("CURRENCYMESH")){

                                    Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
                                    material.setColor("Color", ColorRGBA.Blue);

                                    movers.get(Integer.parseInt(hit.split(" ")[1])).setMaterial(material);
                                    selectedMover = movers.get(Integer.parseInt(hit.split(" ")[1]));
                                    lastPosition = new Vector2f(event.getX(), event.getX());

                                } else if (mode.equals("CURRENCYSURFACE")){
                                    Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
                                    material.setColor("Color", ColorRGBA.Blue);

                                    movers.get(Integer.parseInt(hit.split(" ")[1])).setMaterial(material);
                                    selectedMover = movers.get(Integer.parseInt(hit.split(" ")[1]));

                                    lastPosition = new Vector2f(event.getX(), event.getX());


                                } else if (mode.equals("VECTOR3")){


                                }
                                //mode = "CURRENCYMESH";
                                //enableMove();
                            }
                            break;
                        }

                    for (int i = 0; i < results.size(); i++) {
                        String hit = results.getCollision(i).getGeometry().getName();


                        if (hit.contains("SelectBox")) {
                            resetSelection();
                            clearMovers();
                            // For each hit, we know distance, impact point, name of geometry.
                            float dist = results.getCollision(i).getDistance();
                            Vector3f pt = results.getCollision(i).getContactPoint();

                            System.out.println("* Collision #" + i);
                            System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");

                            if (mode.equals("NONE")) {
                                //selectedCM = CurrencyMeshSingleton.getInstance().cMeshes.get(Integer.parseInt(hit.split(" ")[1]));

                                Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
                                material.setColor("Color", ColorRGBA.Blue);

                                selectors.get(Integer.parseInt(hit.split(" ")[1])).setMaterial(material);
                                selectedSelector = selectors.get(Integer.parseInt(hit.split(" ")[1]));
                            } else if (mode.equals("CURRENCYMESH")){

                                Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
                                material.setColor("Color", ColorRGBA.Blue);

                                selectors.get(Integer.parseInt(hit.split(" ")[1])).setMaterial(material);
                                selectedSelector = selectors.get(Integer.parseInt(hit.split(" ")[1]));


                            } else if (mode.equals("CURRENCYSURFACE")){
                                Material material = new Material(app.getAssetManager().loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
                                material.setColor("Color", ColorRGBA.Blue);

                                selectors.get(Integer.parseInt(hit.split(" ")[1])).setMaterial(material);
                                selectedSelector = selectors.get(Integer.parseInt(hit.split(" ")[1]));

                                selectedVec = Integer.parseInt(hit.split(" ")[1]);
                                //int i1 = 0;
//                                for (GameShopCurrencyLine cl: selectedCS.currencyLines){
//                                    for (Vector3f v: cl.points){
//
////                                        if (i1 == Integer.parseInt(hit.split(" ")[1])){
////                                            selectedVector3f = v;
////                                            break;
////                                        }
////                                        i1++;
//
//                                        if (v.distance(selectedSelector.getWorldTranslation()) < .15f){
//                                            selectedVector3f = v;
//                                            //break;
//                                        }
//                                    }
//                                }



                            } else if (mode.equals("VECTOR3")){


                            }
                            //mode = "CURRENCYMESH";
                            enableMove();
                        }
                        break;
                    }
                   // }

                    if (action.equals("MOVE")){

                    }

                    if (action.equals("OPEN")){

                    }

                    if (action.equals("ANIMATE")){

                    }
                } else if (event.getType() == TouchEvent.Type.MOVE && selectedMover != null){

                    Vector2f currentPosition = new Vector2f(event.getX(), event.getY());
                    Vector3f moveDir = new Vector3f();
                    System.out.println("LAST " + lastPosition);
                    System.out.println("CURRENT " + currentPosition);
                    for (int i = 0; i < 6; i++){


                        if (selectedMover.equals(movers.get(i))){

                            if (currentPosition.distance(lastPosition) > 0f){

                                System.out.println("Moving");
                                if (i == 0){

                                    if (currentPosition.x - lastPosition.x > 0){

                                        moveSelectorAndVector(new Vector3f(.1f, 0, 0));
                                    }  else {
                                        moveSelectorAndVector(new Vector3f(-.1f, 0, 0));

                                    }
                                } else if (i == 1){

                                    if (currentPosition.x - lastPosition.x > 0){

                                        moveSelectorAndVector(new Vector3f(.1f, 0, 0));
                                    }  else {
                                        moveSelectorAndVector(new Vector3f(-.1f, 0, 0));

                                    }
                                } else if (i == 2){

                                    if (currentPosition.x - lastPosition.x > 0){

                                        moveSelectorAndVector(new Vector3f(0, .1f, 0));
                                    }  else {
                                        moveSelectorAndVector(new Vector3f(0, -.1f, 0));

                                    }
                                } else if (i == 3){

                                    if (currentPosition.x - lastPosition.x > 0){

                                        moveSelectorAndVector(new Vector3f(0, .1f, 0));
                                    }  else {
                                        moveSelectorAndVector(new Vector3f(0, -.1f, 0));

                                    }
                                } else if (i == 4){

                                    if (currentPosition.x - lastPosition.x > 0){

                                        moveSelectorAndVector(new Vector3f(0, 0, .1f));
                                    }  else {
                                        moveSelectorAndVector(new Vector3f(0, 0, -.1f));

                                    }
                                } else if (i == 5){

                                    if (currentPosition.x - lastPosition.x > 0){


                                        moveSelectorAndVector(new Vector3f(0, 0, .1f));
                                    }  else {
                                        moveSelectorAndVector(new Vector3f(0,0, -.1f));

                                    }
                                }
                            }

                        }
//                        //LEFT RIGHT TOP BOTTOM FORWARD BACKWARD
//                        if (i == 0) {
//
//                            geom.move(-.75f, 0, 0);
//
//                        } else if (i == 1) {
//
//                            geom.move(.75f, 0, 0);
//                        } else if (i == 2) {
//
//                            geom.move(0, .75f, 0);
//                        } else if (i == 3) {
//
//                            geom.move(0, -.75f, 0);
//                        } else if (i == 4) {
//
//                            geom.move(0, 0, -.75f);
//                        } else if (i == 5) {
//                            geom.move(0, 0, .75f);
//                        }
                    }

                }
            }
        }
    };

    public void moveSelectorAndVector(Vector3f distance) {

        if (selectedSelector != null) {
            boolean found = false;

            int lastI = 0;
            int lastJ = 0;
            int lastK = 0;
            String lastL = "";

            int l = 0;
            for (String cm : GameShopCurrencyMeshHash.getInstance().cMeshes.keySet()) {
                if (selectedCM.equals(GameShopCurrencyMeshHash.getInstance().cMeshes.get(cm))) {
                    int k = 0;
                    for (GameShopCurrencySurface cs : Objects.requireNonNull(GameShopCurrencyMeshHash.getInstance().cMeshes.get(cm)).gspSurfaces) {
                        if (selectedCS.equals(cs)) {
                            int j = 0;
                            for (GameShopCurrencyLine cl : cs.currencyLines) {
                               // if (selectedCL.equals(cl)) {

                                    int i = 0;
                                    for (Vector3f v : cl.points) {
                                        if (selectedVec == (j * 4) + i) {
                                            // cl.points[i] = new Vector3f(v.add(distance));
                                            found = true;
                                        }

                                        if (found) {
                                            lastI = i;
                                            break;
                                        }
                                        i++;
                                    }

                               // }

                                if (found) {
                                    lastJ = j;
                                    break;
                                }
                                j++;
                            }
                        }

                        if (found) {
                            lastK = k;
                            break;
                        }
                        k++;
                    }
                }

                if (found) {
                    lastL = cm;
                    break;
                }
                l++;
            }

            for (Geometry g : movers) {
                g.move(distance);
            }
            selectedSelector.move(distance);

            //selectedVector3f = new Vector3f(selectedVector3f.add(distance));
            Objects.requireNonNull(GameShopCurrencyMeshHash.getInstance().cMeshes.get(lastL)).gspSurfaces[lastK].currencyLines[lastJ].moveCurrency(lastI,             Objects.requireNonNull(GameShopCurrencyMeshHash.getInstance().cMeshes.get(lastL)).gspSurfaces[lastK].currencyLines[lastJ].points[lastI].add(distance));
            System.out.println("LINE: " + Objects.requireNonNull(GameShopCurrencyMeshHash.getInstance().cMeshes.get(lastL)).gspSurfaces[lastK].currencyLines[lastJ].points[lastI]);
//        System.out.println("LINE: " +         CurrencyMeshSingleton.getInstance().cMeshes.get(lastL).gspSurfaces[lastK].currencyLines[lastJ].points[lastI]);
            Objects.requireNonNull(GameShopCurrencyMeshHash.getInstance().cMeshes.get(lastL)).gspSurfaces[lastK].updateVerticalLines();

//        CurrencyMeshSingleton.getInstance().cMeshes.get(lastL).node.detachAllChildren();
//        app.getRootNode().detachChild(CurrencyMeshSingleton.getInstance().cMeshes.get(lastL).node);
             Objects.requireNonNull(GameShopCurrencyMeshHash.getInstance().cMeshes.get(lastL)).allocateVertices();
            Objects.requireNonNull(GameShopCurrencyMeshHash.getInstance().cMeshes.get(lastL)).updateShapes();


        }
    }
}
