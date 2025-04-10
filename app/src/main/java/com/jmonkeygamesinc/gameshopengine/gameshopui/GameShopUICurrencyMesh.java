package com.jmonkeygamesinc.gameshopengine.gameshopui;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.texture.Texture2D;
import com.jmonkeygamesinc.gameshopengine.graphics.GameShopATMS;
import com.jmonkeygamesinc.gameshopengine.graphics.GameShopCurrencyMesh;
import com.jmonkeygamesinc.gameshopengine.graphics.GameShopCurrencySurface;

public class GameShopUICurrencyMesh extends GameShopCurrencyMesh {
    public GameShopUICurrencyMesh(SimpleApplication app, Node node, GameShopCurrencySurface[] gspSurfaces, GameShopATMS atms) {
        super(app, node, gspSurfaces, atms);

    }

    @Override
    public void initShapes(){




        // Vertex positions in space
//        Vector3f[] vertices = new Vector3f[4];
//        vertices[0] = new Vector3f(0,0,0);
//        vertices[1] = new Vector3f(3,0,0);
//        vertices[2] = new Vector3f(0,3,0);
//        vertices[3] = new Vector3f(3,3,0);
//
//        // Texture coordinates
//        Vector2f[] texCoord = new Vector2f[4];
//        texCoord[0] = new Vector2f(0,0);
//        texCoord[1] = new Vector2f(1,0);
//        texCoord[2] = new Vector2f(0,1);
//        texCoord[3] = new Vector2f(1,1);
//
//        // Indexes. We define the order in which mesh should be constructed
//        short[] indexes = {2, 0, 1, 1, 3, 2};

        // Setting buffers
        updateShapes();

        // *************************************************************************
        // First mesh uses one solid color
        // *************************************************************************

        // Creating a geometry, and apply a single color material to it
        geom = new Geometry("OurMesh", m);
        //Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");

        Material mat = new Material(app.getAssetManager(), "MatDefs/GameShopUI.j3md");

        Texture2D texture = new Texture2D(this.atms.makeATMS());

        mat.setColor("Color", ColorRGBA.fromRGBA255(128,128,128,128));

        mat.setTexture("ColorMap", texture);


        geom.setMaterial(mat);


        //node.getChild(0).set

        // Attaching our geometry to the root node.
        node.attachChild(geom);

        this.app.getRootNode().attachChild(node);

    }
}
