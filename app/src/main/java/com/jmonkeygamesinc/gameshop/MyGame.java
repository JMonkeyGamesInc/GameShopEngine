package com.jmonkeygamesinc.gameshop;

import com.jme3.app.LegacyApplication;
import com.jme3.app.SimpleApplication;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.math.Vector4f;
import com.jme3.scene.Node;
import com.jmonkeygamesinc.gameshop.global.CurrencyMeshSingleton;
import com.jmonkeygamesinc.gameshop.graphics.GameShopATMS;
import com.jmonkeygamesinc.gameshop.graphics.GameShopCurrencyLine;
import com.jmonkeygamesinc.gameshop.graphics.GameShopCurrencyMesh;
import com.jmonkeygamesinc.gameshop.graphics.GameShopCurrencySurface;
import com.jmonkeygamesinc.gameshop.ui.Selector;
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
 * @author pavl_g
 */
public final class MyGame extends SimpleApplication {

    @Override
    public void simpleInitApp() {
        flyCam.setEnabled(false);
        //for i shapes draw
//        final Sphere mySphere = new Sphere(10, 50, 50);
//        final Geometry geometry = new Geometry("ball", mySphere);
//        geometry.setLocalScale(0.05f);
//        final Material material = new Material(assetManager.loadAsset(new AssetKey<>("Common/MatDefs/Misc/Unshaded.j3md")));
//        material.setColor("Color", ColorRGBA.randomColor().mult(2f));
//        geometry.setMaterial(material);
//        rootNode.attachChild(geometry);

        GameShopATMS atms = new GameShopATMS("Circle", 128,128, new Vector4f[]{new Vector4f(0,1,0,1)});

        atms.layer.drawCircle(64,64, 64, ColorRGBA.fromRGBA255( 0,255,0,255));
        atms.layer.drawCircle(32,64, 32, ColorRGBA.fromRGBA255( 255,255,255,255));

        GameShopCurrencyLine[] cl = new GameShopCurrencyLine[4];

        for (int i = 0; i < 4; i++){

            cl[i] = new GameShopCurrencyLine(new Vector3f[]{ new Vector3f(0,i, 0), new Vector3f(1,i, 0), new Vector3f(2,i, 0), new Vector3f(3,i, 0)}, 4);

        }

        GameShopCurrencySurface cs = new GameShopCurrencySurface(cl);

        GameShopCurrencyMesh cm = new GameShopCurrencyMesh(this, new Node("SquareCircle"), new GameShopCurrencySurface[]{cs}, atms);
        cm.drawShapes();

        CurrencyMeshSingleton.getInstance().cMeshes.add(cm);

        Selector selector = new Selector(this);



    }
}