package com.jmonkeygamesinc.gameshopengine;

import android.content.Context;
import android.os.Environment;

import com.jme3.app.LegacyApplication;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.scene.Node;
import com.jmonkeygamesinc.gameshopengine.jme3.gameshopui.GameShopUICurrencyMesh;
import com.jmonkeygamesinc.gameshopengine.jme3.gameshopui.widgets.Alphabet;
import com.jmonkeygamesinc.gameshopengine.jme3.global.GameShopCurrencyMeshHash;
import com.jmonkeygamesinc.gameshopengine.jme3.graphics.GameShopATMS;
import com.jmonkeygamesinc.gameshopengine.jme3.graphics.GameShopCurrencyLine;
import com.jmonkeygamesinc.gameshopengine.jme3.graphics.GameShopCurrencyMesh;
import com.jmonkeygamesinc.gameshopengine.jme3.graphics.GameShopCurrencySurface;
import com.jmonkeygamesinc.gameshopengine.jme3.graphics.GameShopLayer;
import com.jmonkeygamesinc.gameshopengine.jme3.niftygui.StartScreenController;
import com.jmonkeygamesinc.gameshopengine.jme3.ui.Selector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.lessvoid.nifty.Nifty;

import com.jme3.renderer.android.AndroidGL;

//import com.simsilica.lemur.Button;
//import com.simsilica.lemur.Command;
//import com.simsilica.lemur.Container;
//import com.simsilica.lemur.GuiGlobals;
//import com.simsilica.lemur.Label;
//import com.simsilica.lemur.style.BaseStyles;

/**
 * <b>Your Actual Jme Game class.</b>
 * <br>
 * To use it inside Android :
 * <ol>
 * <li>Create an instance of it inside the #{@link MainActivity}</li>
 * <li>Set that instance using #{@link com.jme3.view.surfaceview.JmeSurfaceView#setLegacyApplication(LegacyApplication)}</li>
 * <li>Start the game using #{@link com.jme3.view.surfaceview.JmeSurfaceView#startRenderer(int)}</li>
 * </ol>
 *
 * @author Lynden Jay Evans of JMonkeyGames Inc.
 */
public final class MyGame extends SimpleApplication {

    Context context;
    private Nifty nifty;
    public MyGame(Context context){
        super();

        this.context = context;
    }
    @Override
    public void simpleInitApp() {
        flyCam.setEnabled(true);

        this.setShowSettings(false);
        this.setDisplayStatView(false);

        viewPort.setBackgroundColor(ColorRGBA.White);

        //for i shapes draw
//        final Sphere mySphere = new Sphere(10, 50, 50);
//        final Geometry geometry = new Geometry("ball", mySphere);
//        geometry.setLocalScale(0.05f);
//        final Material material = new Material(assetManager.loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
//        material.setColor("Color", ColorRGBA.randomColor().mult(2f));
//        geometry.setMaterial(material);
//        rootNode.attachChild(geometry);

        GameShopATMS atms = new GameShopATMS("Rune", 128,128, new Vector4f[]{new Vector4f(0,1,0,1)});

        atms.layer.drawCircle(64,64, 128, ColorRGBA.fromRGBA255( 0,0,0,255));
        atms.layer.drawLine(new Vector2f(28, 48), new Vector2f(54, 16), (short)8, ColorRGBA.fromRGBA255(255,220,171,255));

        atms.layer.drawRectangle(new Vector2f(32, 64), new Vector2f(118, 108),  ColorRGBA.fromRGBA255( 255,255,255,255));
        atms.layer.drawRectangle(new Vector2f(32, 32), new Vector2f(48, 64),  ColorRGBA.fromRGBA255( 255,255,255,255));
        atms.layer.drawRectangle(new Vector2f(48, 16), new Vector2f(118, 64),ColorRGBA.fromRGBA255( 255,220,171,255) );

        // atms.layer.drawCircle(64,64, 64, ColorRGBA.fromRGBA255( 0,255,0,255));
       // atms.layer.drawCircle(32,64, 32, ColorRGBA.fromRGBA255( 255,255,255,255));

        GameShopCurrencyLine[] cl = new GameShopCurrencyLine[4];

        for (int i = 0; i < 4; i++){

            cl[i] = new GameShopCurrencyLine(new Vector3f[]{ new Vector3f(0,i, 0), new Vector3f(1,i, 0), new Vector3f(2,i, 0), new Vector3f(3,i, 0)}, 8);

        }

        GameShopCurrencySurface cs = new GameShopCurrencySurface("0", cl);

        GameShopCurrencyMesh cm = new GameShopCurrencyMesh(this, new Node("SquareCircle"), new GameShopCurrencySurface[]{cs}, atms);
        cm.initShapes();

        GameShopCurrencyMeshHash.getInstance().cMeshes.put("Main", cm);


        //**********************//

        GameShopATMS testLayer = new GameShopATMS("TestLayer" ,75,25, new Vector4f[]{new Vector4f(0,1,0,1)});

        Alphabet letterUpperCaseA = new Alphabet('A', 25, 25);
        letterUpperCaseA.generateCharacter();

        //testLayer.layer.drawCircle(0,0,90, ColorRGBA.fromRGBA255(0,0,0,0));
        testLayer.layer.drawCircle(0,0,90, ColorRGBA.fromRGBA255(255,0,0,255));


        testLayer.layer.copyLayer(letterUpperCaseA.layer, new Vector2f(0,0));
        float zAxis = 0f;//this.getCamera().getFrustumNear();
        GameShopATMS atmsUI = new GameShopATMS("GameShopUI", 1920,1080, new Vector4f[]{new Vector4f(0,1,0,1)});

       // atmsUI.layer.drawCircle(64,64, 64, ColorRGBA.fromRGBA255( 0,255,0,255));
        atmsUI.layer.drawSquare(32,64, 256, ColorRGBA.fromRGBA255( 0,0,255,255));
        atmsUI.layer.drawCircle(96,54, 32, ColorRGBA.fromRGBA255( 0,255,0,128));
        //atmsUI.layer.drawAspectRatioSquare(96,128, 32, ColorRGBA.fromRGBA255( 255,0,0,128));
        //atmsUI.layer.drawAspectRatioSquare(64,32, 32, ColorRGBA.fromRGBA255( 255,0,255,128));

        atmsUI.layer.copyLayer(testLayer.layer, new Vector2f(0f, 1055f));
        GameShopCurrencyLine[] clUI = new GameShopCurrencyLine[4];

//        for (int i = 0; i < 4; i += 1){
//
//            clUI[i] = new GameShopCurrencyLine(new Vector3f[]{ new Vector3f(-1,((((float) i-5))+ (i * .33f)), 0), new Vector3f(-.33f,((((float) i-5))+ (i * .33f)), 0), new Vector3f(.33f,((((float) i-5))+ (i * .33f)), 0), new Vector3f(1f,((((float) i-5))+ (i * .33f)), 0)}, 2);
//
//        }

        //for (int i = 0; i < 4; i += 1){

        clUI[0] = new GameShopCurrencyLine(new Vector3f[]{ new Vector3f(-1.335f, -1.335f, zAxis), new Vector3f(1f, -1.335f, zAxis), new Vector3f(1f,-1.335f, zAxis), new Vector3f(3.335f,-1.335f, zAxis)}, 2);
        clUI[1] = new GameShopCurrencyLine(new Vector3f[]{ new Vector3f(-1.335f,-.33f, zAxis), new Vector3f(1f,-.33f, zAxis), new Vector3f(1f,-.33f, zAxis), new Vector3f(3.3f,-.33f, zAxis)}, 2);
        clUI[2] = new GameShopCurrencyLine(new Vector3f[]{ new Vector3f(-1.335f,.33f, zAxis), new Vector3f(1f,.33f, zAxis), new Vector3f(1f,.33f, zAxis), new Vector3f(3.3f,.33f, zAxis)}, 2);
        clUI[3] = new GameShopCurrencyLine(new Vector3f[]{ new Vector3f(-1.335f,1.335f, zAxis), new Vector3f(1f,1.335f, zAxis), new Vector3f(1f,1.335f, zAxis), new Vector3f(3.335f,1.335f, zAxis)}, 2);

       // }

        GameShopCurrencySurface csUI = new GameShopCurrencySurface("0", clUI);

        GameShopUICurrencyMesh cmUI = new GameShopUICurrencyMesh(this, new Node("UI"), new GameShopCurrencySurface[]{csUI}, atmsUI);
        cmUI.initShapes();


        //GameShopCurrencyMeshHash.getInstance().cMeshes.put("Main", cm);

        Selector selector = new Selector(this);


//        NiftyJmeDisplay niftyDisplay = NiftyJmeDisplay.newNiftyJmeDisplay(
//                assetManager,
//                inputManager,
//                audioRenderer,
//                guiViewPort);
//        nifty = niftyDisplay.getNifty();
//        StartScreenController startScreen = new StartScreenController(this, selector);
////        nifty.loadControlFile("nifty-default-controls.xml");
////        nifty.loadStyleFile("nifty-default-styles.xml");
//        nifty.fromXml("Interface/start_screen.xml", "start", startScreen);
//        //startScreen.fillMyListBox();
//        // attach the nifty display to the gui view port as a processor
//        guiViewPort.addProcessor(niftyDisplay);

        // disable the fly cam
//        flyCam.setEnabled(false);
//        flyCam.setDragToRotate(true);


        inputManager.setCursorVisible(true);
       // writeFileOnInternalStorage();//context, "hi.file", "HI EVERYONE");



    }

    @Override
    public void update() {
        super.update();
    }

    public void writeFileOnInternalStorage(){ //Context mcoContext, String sFileName, String sBody){
        //System.out.println("FILE: " + mcoContext.getFilesDir());
        //File dir = new File(mcoContext.getFilesDir(), "mydir");
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(dir, "hi.file");
//        if(!dir.exists()){
//            dir.mkdir();
//        }

        //Write to file
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.append("HI EVERYONE!");
        } catch (IOException e) {
            //Handle exception
        }

//        try {
//            File gpxfile = new File(dir, sFileName);
//            FileWriter writer = new FileWriter(gpxfile);
//            writer.append(sBody);
//            writer.flush();
//            writer.close();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
    }


//    public void writeFile(){
//
//        File fileMetadata = new File("hi.txt");
//        fileMetadata.setName("hi.txt");
//        java.io.File filePath = new java.io.File("files/hi.txt");
//        FileContent mediaContent = new FileContent("text/jpeg", filePath);
//        DriveService driveService = driveService.files().create(fileMetadata, mediaContent)
//                .setFields("id")
//                .execute();
//    }
}