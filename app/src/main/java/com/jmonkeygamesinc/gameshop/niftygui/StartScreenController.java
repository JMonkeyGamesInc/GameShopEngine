package com.jmonkeygamesinc.gameshop.niftygui;

import com.jme3.app.Application;
import com.jme3.math.Vector3f;
import com.jmonkeygamesinc.gameshop.global.GameShopCurrencyMeshHash;
import com.jmonkeygamesinc.gameshop.graphics.GameShopCurrencyLine;
import com.jmonkeygamesinc.gameshop.graphics.GameShopCurrencyMesh;
import com.jmonkeygamesinc.gameshop.graphics.GameShopCurrencySurface;
import com.jmonkeygamesinc.gameshop.ui.Selector;

import java.util.List;
import java.util.Objects;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * A ScreenController for the "start" screen defined in
 * "Interfaces/Nifty/HelloJme.xml", which is used in the TestAppStates and
 * TestNiftyGui applications.
 */
public class StartScreenController implements ScreenController {

    final private Application application;
    public Selector selector;
    public Nifty nifty;
    public Screen screen;
    /**
     * Instantiate a ScreenController for the specified Application.
     *
     * @param app the Application
     */
    public StartScreenController(Application app, Selector selector) {
        this.application = app;
        this.selector = selector;
    }

    /**
     * Nifty invokes this method when the screen gets enabled for the first
     * time.
     *
     * @param nifty (not null)
     * @param screen (not null)
     */
    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind(" + screen.getScreenId() + ")");
        this.nifty = nifty;
        this.screen = screen;
       fillMyListBox();



    }

    /**
     * Nifty invokes this method each time the screen starts up.
     */
    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
        //fillMyListBox();
    }

    /**
     * Nifty invokes this method each time the screen shuts down.
     */
    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }

    /**
     * Fill the listbox with items. In this case with Strings.
     */
    public void fillMyListBox() {
        ListBox listBox = screen.findNiftyControl("myListBox", ListBox.class);
//        listBox.addItem("a");
//        listBox.addItem("b");
//        listBox.addItem("c");
//        listBox.addItem("a");
//        listBox.addItem("b");
//        listBox.addItem("c");

        for (String cm: GameShopCurrencyMeshHash.getInstance().cMeshes.keySet()){

            //assert listBox != null;
            listBox.addItem("[CurrencyMesh]:" + cm);
            int i = 0;
            for (GameShopCurrencySurface cs: Objects.requireNonNull(GameShopCurrencyMeshHash.getInstance().cMeshes.get(cm)).gspSurfaces){

                listBox.addItem("    " + "[CurrencySurface]:" + cs.name);


                int j = 0;
                for (GameShopCurrencyLine cl: cs.currencyLines){

                    listBox.addItem("        " + "[CurrencyLine]:" + i);


                    for (Vector3f v: cl.points){

                        listBox.addItem("            " + "[Vector3f]:" + j);
                        j++;
                    }
                    i++;
                }
            }

        }

       // listBox.getItems().
    }

    /**
     * When the selection of the ListBox changes this method is called.
     */
    @NiftyEventSubscriber(id="myListBox")
    public void onMyListBoxSelectionChanged(final String id, final ListBoxSelectionChangedEvent<String> event) {
        List<String> selection = event.getSelection();
        List<Integer> index = event.getSelectionIndices();
        ListBox<String> listbox = event.getListBox();
        selectHierarchyItem(selection.get(0), index.get(0), listbox);
       // for (String selectedItem : selection) {
            //System.out.println("listbox selection [" + selectedItem + "]");
            //setSelectorToName(selectedItem);
           // selector.selectedObjectName = selectedItem;

       // }
    }

    public void selectHierarchyItem(String selection, int index, ListBox<String> listbox){


        if (selection.contains("[CurrencyMesh]")){
            selector.mode = "CURRENCYMESH";
        } else {
            selector.mode = "CURRENCYSURFACE";
        }

        int i = 0;
        for (GameShopCurrencyMesh cm: GameShopCurrencyMeshHash.getInstance().cMeshes.values()){

            selector.selectedCM = cm;
            //listBox.addItem("[CurrencyMesh]:" + cm.name);

            for (GameShopCurrencySurface cs: cm.gspSurfaces){

                selector.selectedCS = cs;
                //listBox.addItem("    " + "[CurrencySurface]:" + cs.name);

                //int i = 0;
                int vec = 0;
                for (GameShopCurrencyLine cl: cs.currencyLines){

                    selector.selectedCL = cl;
                    //listBox.addItem("        " + "[CurrencyLine]:" + i);

                    //int j = 0;

                    for (Vector3f v: cl.points){

                        selector.selectedVec = vec;
                      //  listBox.addItem("            " + "[Vector3f]:" + j);
                       // j++;
                        vec++;
                        i++;
                        if (i == index){
                            break;
                        }
                    }
                    i++;
                    if (i == index){
                        break;
                    }
                   // i++;
                }
                i++;
                if (i == index){
                    break;
                }
            }
            i++;
            if (i == index){
                break;
            }
        }

        selector.clearSelection();
        selector.clearMovers();
        selector.makeSelection();
        selector.selectFromHierarchy(Integer.parseInt(selection.split(":")[1]));
     }

//    public void setSelectorToName(String name){
//
//        if (name.contains("[CurrencyMesh]")){
//            selector.selectedObjectName = name;
//        } else if (name.contains("[CurrencySurface]")){
//            selector.selectedObjectName = name;
//
//        } else if (name.contains("[CurrencyLine]")){
//
//        } else if (name.contains("[Vector3f]")){
//
//        }
//
//    }

    /**
     * Stop the Application. Nifty invokes this method (via reflection) after
     * the user clicks on the flashing orange panel.
     */
    public void quit() {
        //application.stop();
    }

    public void save(){

        //System.out.println("SAVE");
    }

    public void load(){

    }

    public void move(){

        selector.action = "MOVE";
        selector.enableMove();
    }

    public void select(){

        selector.action = "SELECT";

    }

    public void open(){

        selector.action = "OPEN";
    }

    public void animate(){

        selector.action = "ANIMATE";

    }
}
