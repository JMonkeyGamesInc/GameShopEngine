package com.jmonkeygamesinc.gameshopengine.global;

import com.jmonkeygamesinc.gameshopengine.graphics.GameShopCurrencyMesh;

import java.util.HashMap;

/**
 *
 * @author Lynden Jay Evans of JMonkeyGames Inc.
 */
public class GameShopCurrencyMeshHash {

    private static GameShopCurrencyMeshHash _instance;

    public HashMap<String, GameShopCurrencyMesh> cMeshes;
    private GameShopCurrencyMeshHash(){

        cMeshes = new HashMap<>();

    }

    public static GameShopCurrencyMeshHash getInstance(){

        if (_instance == null){

            _instance = new GameShopCurrencyMeshHash();
        }

        return _instance;
    }
}
