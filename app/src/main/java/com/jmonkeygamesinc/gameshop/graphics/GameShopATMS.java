package com.jmonkeygamesinc.gameshop.graphics;
import com.jme3.math.Vector4f;
import com.jme3.texture.Image;
//import org.lwjgl.BufferUtils;

//import static org.lwjgl.opengl.GL30.*;


/**
 *
 * @author gameshopengine
 */
public class GameShopATMS {

    public int width;
    public int height;
    public int textureId;
    public GameShopLayer layer;
    public Vector4f[] textureSamples;
    //public String[] drawCalls;
    //public GameShopLanguageProcessor[] gslp;
    // public String name;
    //public String uniqueID;
    public String name;
    public GameShopATMS(String name, int width, int height, Vector4f[] textureSamples){

        this.name = name;
        //this.name = name;
        this.width = width;
        this.height = height;
        this.layer = new GameShopLayer(width, height);
        this.textureSamples = textureSamples;
        // gslp = new GameShopLanguageProcessor[1];
        //this.drawCalls = new String[1];
    }

    public Image makeATMS(){
        //textureId = glGenTextures();

        //DefaultImageRaster dir = new DefaultImageRaster(new Image(), 0, 0, true);

//        byte[] outputLayer = layer.outputLayer();
       // ByteBuffer pixels = BufferUtils.createByteBuffer(layer.outputLayer().length);
        //pixels.put(layer.outputLayer());
//        for (int i = 0; i < layer.outputLayer().length; i++) {
//            pixels.putShort(outputLayer[i]);
//        }
        //pixels.flip();

        return this.layer.image;
        //Make Texture



//     if (this.uniqueID == null){
//     this.uniqueID = GameShopATMSHash.getInstance().addATMS(this);
//     }
//        GameShopATMSHash.GameShopATMSHashgetInstance().addATMS(this.name, this);
//        //glBindTexture(GL_TEXTURE_2D, GameShopATMSHash.getInstance().atmsHash.get(uniqueID));
//        glBindTexture(GL_TEXTURE_2D, GameShopATMSHash.getInstance().atmsHash.get(this));
//
//        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
//        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
//        // glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0,
//        //       GL_RGBA, GL_FLOAT, layer.outputLayer());
//        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0,
//                GL_RGBA, GL_BYTE, pixels);
//        glGenerateMipmap(GL_TEXTURE_2D);
        //assert (GameShopATMSHash.getInstance().atmsHash.get(this) != null);
    }

//       public void addGameShopLanguageProcessors(int index, GameShopLanguageProcessor[] gslp){
//
//       // public void addDrawCalls(int index, String[] drawCalls){
//
//        boolean start = this.gslp[0] != null;
//
//        ArrayList<GameShopLanguageProcessor> newGSRs = new ArrayList<>(Arrays.asList(gslp));
//
//        ArrayList<GameShopLanguageProcessor> oldGSRs = null;
//
//        if (start) {
//            oldGSRs = new ArrayList<>(Arrays.asList(this.gslp));
//        }
//
//        ArrayList<GameShopLanguageProcessor> lowerGSRs = new ArrayList<>();
//
//        ArrayList<GameShopLanguageProcessor> higherGSRs = new ArrayList<>();
//
//        if (start) {
//            for (int i = 0; i < index; i++) {
//                if (i < oldGSRs.size()){
//                lowerGSRs.add(oldGSRs.get(i));
//                }
//            }
//
//            for (int i = index; i < oldGSRs.size(); i++) {
//                if (i < oldGSRs.size()){
//                higherGSRs.add(oldGSRs.get(i));
//                }
//            }
//            this.gslp = new GameShopLanguageProcessor[(oldGSRs.size()) + (newGSRs.size())];
//
//        } else {
//            this.gslp = new GameShopLanguageProcessor[newGSRs.size()];
//        }
//
//
//
//        int i = 0;
//        if (start) {
//            for (GameShopLanguageProcessor dc : lowerGSRs) {
//
//
//                this.gslp[i] = dc;
//                i++;
//            }
//        }
////        System.out.println(Arrays.toString(oldDrawCalls.toArray()));
//  //      System.out.println(Arrays.toString(newDrawCalls.toArray()));
//
//        for (GameShopLanguageProcessor dc: newGSRs){
//
//            this.gslp[i] = dc;
//            i++;
//        }
//
//        if (start) {
//            for (GameShopLanguageProcessor dc : higherGSRs) {
//
//                this.gslp[i] = dc;
//                i++;
//            }
//        }
//
//    }
//
//       public void process(){
//
//           for (GameShopLanguageProcessor gs: this.gslp){
//
//               gs.process();
//
//               if (gs.methodName.equals("drawCircle")){
//
//                   String[] color = gs.parseArg(gs.args[3]);
//                   this.layer.drawCircle(Integer.parseInt(gs.args[0]), Integer.parseInt(gs.args[1]), Integer.parseInt(gs.args[2]),  (new org.joml.Vector4f(Float.parseFloat(color[0]), Float.parseFloat(color[1]), Float.parseFloat(color[2]), Float.parseFloat(color[3]))));
//               }
//
//               else if (gs.methodName.equals("drawSquare")){
//
//                   String[] color = gs.parseArg(gs.args[3]);
//                   this.layer.drawSquare(Integer.parseInt(gs.args[0]), Integer.parseInt(gs.args[1]), Integer.parseInt(gs.args[2]),  (new org.joml.Vector4f(Float.parseFloat(color[0]), Float.parseFloat(color[1]), Float.parseFloat(color[2]), Float.parseFloat(color[3]))));
//               }
//
//               else if (gs.methodName.equals("drawRectangle")){
//                    String[] start = gs.parseArg(gs.args[0]);
//                    String[] end = gs.parseArg(gs.args[1]);
//                    String[] color = gs.parseArg(gs.args[2]);
//
//                    this.layer.drawRectangle(new Vector2f(Integer.parseInt(start[0].trim()), Integer.parseInt(start[1].trim())), new Vector2f(Integer.parseInt(end[0].trim()), Integer.parseInt(end[1].trim())), new org.joml.Vector4f(Float.parseFloat(color[0].trim()), Float.parseFloat(color[1].trim()), Float.parseFloat(color[2].trim()), Float.parseFloat(color[3].trim())));
//               }
//
//               else if (gs.methodName.equals("drawLine")){
//
//                   String[] pointA = gs.parseArg(gs.args[0]);
//                   String[] pointB = gs.parseArg(gs.args[1]);
//                   String radius = gs.args[2];
//                   String[] color = gs.parseArg(gs.args[3]);
//
//                   this.layer.drawLine(new Vector2f(Integer.parseInt(pointA[0].trim()), Integer.parseInt(pointA[1].trim())), new Vector2f(Integer.parseInt(pointB[0].trim()), Integer.parseInt(pointB[1].trim())), (short)Short.parseShort(radius.trim()), new org.joml.Vector4f(Float.parseFloat(color[0].trim()), Float.parseFloat(color[1].trim()), Float.parseFloat(color[2].trim()), Float.parseFloat(color[3].trim())));
//               }
//
//               else if (gs.methodName.equals("drawPolyLine")) {
//
//                   String[] line = gs.parseArg(gs.args[0]);
//                   String[] line1 = gs.parseArg(gs.args[1]);
//                   String[] line2 = gs.parseArg(gs.args[2]);
//                   String[] line3 = gs.parseArg(gs.args[3]);
//                   String radius = gs.args[4];
//                   String[] color = gs.parseArg(gs.args[5]);
//
//                   GameShopPolyLine cl = new GameShopPolyLine(new com.jme3.math.Vector3f[] {
//
//                       new com.jme3.math.Vector3f(Float.parseFloat(line[0].trim()), Float.parseFloat(line[1].trim()),Float.parseFloat(line[2].trim())),
//                       new com.jme3.math.Vector3f(Float.parseFloat(line1[0].trim()), Float.parseFloat(line1[1].trim()),Float.parseFloat(line1[2].trim())),
//                       new com.jme3.math.Vector3f(Float.parseFloat(line2[0].trim()), Float.parseFloat(line2[1].trim()),Float.parseFloat(line2[2].trim())),
//                       new com.jme3.math.Vector3f(Float.parseFloat(line3[0].trim()), Float.parseFloat(line3[1].trim()),Float.parseFloat(line3[2].trim()))
//
//                   }, 16);
//
//                   org.joml.Vector4f colour = new org.joml.Vector4f(Float.parseFloat(color[0].trim()), Float.parseFloat(color[1].trim()), Float.parseFloat(color[2].trim()), Float.parseFloat(color[3].trim()));
//                   this.layer.drawPolyLine(cl, Short.parseShort(radius.trim()), colour);
//
//               }
//           }
//
//           makeATMS();
//       }
}

