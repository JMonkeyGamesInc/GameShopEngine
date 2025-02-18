package com.jmonkeygamesinc.gameshop.graphics;

import com.jme3.math.Vector3f;

/**
 *
 * @author Lynden Jay Evans of JMonkeyGames Inc.
 */
public class GameShopCurrencySurface {

    public GameShopCurrencyLine[] currencyLines;
    public GameShopCurrencyLine[] vInfinitesimals;

    public float width;
    public float height;

    public float infWidth;
    public float infHeight;

    public GameShopCurrencySurface(GameShopCurrencyLine[] currencyLines){

        this.currencyLines = currencyLines;
        makeVerticalLines();


    }

    public int getTotalVertices(){

        // for (GameShopPolySurface gsps: this.gspSurfaces){

        // totalVertices += gsps.vInfinitesimals[0].infinitesimals.length * 4;

        int totalVertices = 0;

        for (GameShopCurrencyLine vi: vInfinitesimals){

            totalVertices += vi.infinitesimals.length;
        }

        infWidth = vInfinitesimals[0].infinitesimals.length;
        infHeight = vInfinitesimals.length;
        return totalVertices;
    }

    public void makeVerticalLines(){
//     this.dim = 3;
//
        width = (currencyLines[0].points[currencyLines[0].points.length - 1].x - currencyLines[0].points[0].x);
        height = (currencyLines[currencyLines.length - 1].points[0].y - currencyLines[0].points[0].y);
        this.vInfinitesimals = new GameShopCurrencyLine[currencyLines[0].infinitesimals.length];

        for (int i = 0; i < this.vInfinitesimals.length; i++){

            this.vInfinitesimals[i] = new GameShopCurrencyLine(new Vector3f[]{currencyLines[0].infinitesimals[i], currencyLines[1].infinitesimals[i], currencyLines[2].infinitesimals[i], currencyLines[3].infinitesimals[i]}, 4);

        }
    }

}
