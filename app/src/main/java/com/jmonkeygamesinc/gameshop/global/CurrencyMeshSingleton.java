package com.jmonkeygamesinc.gameshop.global;

import com.jmonkeygamesinc.gameshop.graphics.GameShopCurrencyMesh;

import java.util.ArrayList;

public class CurrencyMeshSingleton {

    private static CurrencyMeshSingleton _instance;

    public ArrayList<GameShopCurrencyMesh> cMeshes;
    private CurrencyMeshSingleton(){

        cMeshes = new ArrayList<>();

    }

    public static CurrencyMeshSingleton getInstance(){

        if (_instance == null){

            _instance = new CurrencyMeshSingleton();
        }

        return _instance;
    }
}
