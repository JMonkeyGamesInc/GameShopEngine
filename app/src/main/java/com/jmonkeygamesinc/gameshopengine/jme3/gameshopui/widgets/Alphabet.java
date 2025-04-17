package com.jmonkeygamesinc.gameshopengine.jme3.gameshopui.widgets;

import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jmonkeygamesinc.gameshopengine.jme3.graphics.GameShopCurrencyLine;
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

        switch (c){

            case 'A':

                layer.drawLine( new Vector2f(0, 0), new Vector2f((float) layer.width / 2, layer.height), (short)3, ColorRGBA.fromRGBA255(255,255,255,255));
                layer.drawLine( new Vector2f((float) layer.width / 2, layer.height), new Vector2f((float) layer.width , 0), (short)3, ColorRGBA.fromRGBA255(255,255,255,255));
                layer.drawLine( new Vector2f((float) layer.width / 3, (float) layer.height / 2), new Vector2f((float) layer.width * ((float) 2 /3) , (float) layer.height / 2), (short)3, ColorRGBA.fromRGBA255(255,255,255,255));

                break;

            case 'B':
                layer.drawLine( new Vector2f(0, 0), new Vector2f((float) 0, layer.height), (short)3, ColorRGBA.fromRGBA255(255,255,255,255));
                layer.drawCurrencyLine( new GameShopCurrencyLine(new Vector3f[]{new Vector3f(0, layer.height, 0), new Vector3f(layer.width, layer.height, 0), new Vector3f(layer.width, (float) layer.height /2, 0), new Vector3f(0, (float) layer.height /2, 0)}, 12), (short) 3, ColorRGBA.White);
                layer.drawCurrencyLine( new GameShopCurrencyLine(new Vector3f[]{new Vector3f(0, (float) layer.height /2, 0), new Vector3f(layer.width, (float) layer.height /2, 0), new Vector3f(layer.width, (float) 0, 0), new Vector3f(0, (float) 0, 0)}, 12), (short) 3, ColorRGBA.White);

                //layer.drawLine( new Vector2f((float) layer.width / 3, (float) layer.height / 2), new Vector2f((float) layer.width * ((float) 2 /3) , (float) layer.height / 2), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));

                break;
            case 'C':


                layer.drawCurrencyLine( new GameShopCurrencyLine(new Vector3f[]{new Vector3f(layer.width, layer.height, 0), new Vector3f(0, layer.height, 0), new Vector3f(0, (float) layer.height/2, 0), new Vector3f(0, (float) layer.height /2, 0)}, 12), (short) 3, ColorRGBA.White);
                layer.drawCurrencyLine( new GameShopCurrencyLine(new Vector3f[]{new Vector3f(0, (float) layer.height /2, 0), new Vector3f(0, (float) 0, 0), new Vector3f(layer.width, (float) 0, 0), new Vector3f(layer.width, (float) 0, 0)}, 12), (short) 3, ColorRGBA.White);

//                layer.drawLine( new Vector2f(0, 0), new Vector2f((float) layer.width / 2, layer.height), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
//                layer.drawLine( new Vector2f((float) layer.width / 2, layer.height), new Vector2f((float) layer.width , 0), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
//                layer.drawLine( new Vector2f((float) layer.width / 3, (float) layer.height / 2), new Vector2f((float) layer.width * ((float) 2 /3) , (float) layer.height / 2), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));

                break;
            case 'D':
                layer.drawLine( new Vector2f(0, 0), new Vector2f((float) 0, layer.height), (short)3, ColorRGBA.fromRGBA255(255,255,255,255));

                layer.drawCurrencyLine( new GameShopCurrencyLine(new Vector3f[]{new Vector3f(0, layer.height, 0), new Vector3f(layer.width, (float) layer.height, 0), new Vector3f(layer.width, (float) layer.height/2, 0), new Vector3f(layer.width, (float) layer.height/2, 0)}, 12), (short) 3, ColorRGBA.White);
                layer.drawCurrencyLine( new GameShopCurrencyLine(new Vector3f[]{new Vector3f(layer.width, (float) layer.height /2, 0), new Vector3f(layer.width, (float) 0, 0), new Vector3f(0, (float) 0, 0), new Vector3f(0, (float) 0, 0)}, 12), (short) 3, ColorRGBA.White);


                // layer.drawCurrencyLine( new GameShopCurrencyLine(new Vector3f[]{new Vector3f(0, (float) layer.height /2, 0), new Vector3f(0, (float) 0, 0), new Vector3f(0, (float) 0, 0), new Vector3f(layer.width, (float) 0, 0)}, 12), (short) 3, ColorRGBA.White);

//                layer.drawLine( new Vector2f(0, 0), new Vector2f((float) layer.width / 2, layer.height), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
//                layer.drawLine( new Vector2f((float) layer.width / 2, layer.height), new Vector2f((float) layer.width , 0), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
//                layer.drawLine( new Vector2f((float) layer.width / 3, (float) layer.height / 2), new Vector2f((float) layer.width * ((float) 2 /3) , (float) layer.height / 2), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));

                break;
            case 'E':

                layer.drawLine( new Vector2f(0, 0), new Vector2f((float) layer.width / 2, layer.height), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
                layer.drawLine( new Vector2f((float) layer.width / 2, layer.height), new Vector2f((float) layer.width , 0), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
                layer.drawLine( new Vector2f((float) layer.width / 3, (float) layer.height / 2), new Vector2f((float) layer.width * ((float) 2 /3) , (float) layer.height / 2), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));

                break;
            case 'F':
                break;
            case 'G':
                break;
            case 'H':
                break;
            case 'I':
                break;
            case 'J':
                break;
            case 'K':
                break;
            case 'L':
                break;
            case 'M':
                break;
            case 'N':
                break;
            case 'O':
                break;
            case 'P':
                break;
            case 'Q':
                break;
            case 'R':
                break;
            case 'S':
                break;
            case 'T':
                break;
            case 'U':
                break;
            case 'V':
                break;
            case 'W':
                break;
            case 'X':
                break;
            case 'Y':
                break;
            case 'Z':
                break;

        }
//        if (c == 'A'){
//
//            layer.drawLine( new Vector2f(0, 0), new Vector2f((float) layer.width / 2, layer.height), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
//            layer.drawLine( new Vector2f((float) layer.width / 2, layer.height), new Vector2f((float) layer.width , 0), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
//            layer.drawLine( new Vector2f((float) layer.width / 3, (float) layer.height / 2), new Vector2f((float) layer.width * ((float) 2 /3) , (float) layer.height / 2), (short)2, ColorRGBA.fromRGBA255(255,255,255,255));
//
//        }

    }
}
