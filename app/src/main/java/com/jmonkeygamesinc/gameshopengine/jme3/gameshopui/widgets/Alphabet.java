package com.jmonkeygamesinc.gameshopengine.jme3.gameshopui.widgets;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jmonkeygamesinc.gameshopengine.jme3.graphics.GameShopLayer;

public class Alphabet {

    public GameShopLayer layer;

    public char c;

    public Alphabet(char c, int width, int height){

        this.c = c;

        this.layer = new GameShopLayer(width, height);
    }

    public void generateCharacter(){

       // layer.drawCircle(0,0, this.layer.height * 2, ColorRGBA.fromRGBA255(0,0,0,0));
        if (c == 'A'){

            layer.drawLine( new Vector2f(0, 0), new Vector2f((float) layer.width / 2, layer.height), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
            layer.drawLine( new Vector2f((float) layer.width / 2, layer.height), new Vector2f((float) layer.width , 0), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
            layer.drawLine( new Vector2f((float) layer.width / 3, (float) layer.height / 2), new Vector2f((float) layer.width * ((float) 2 /3) , (float) layer.height / 2), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));

        }

    }
}
