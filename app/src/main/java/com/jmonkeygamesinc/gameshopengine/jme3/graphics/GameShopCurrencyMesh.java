package com.jmonkeygamesinc.gameshopengine.jme3.graphics;

import com.jme3.app.SimpleApplication;
import com.jme3.material.RenderState;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Mesh;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

import com.jme3.scene.VertexBuffer.Type;
import com.jme3.texture.Texture2D;
import com.jme3.util.BufferUtils;

import java.util.Arrays;

/**
 *
 * @author Lynden Jay Evans of JMonkeyGames Inc.
 */
public class GameShopCurrencyMesh {

    public float[] vertices;
    public int[] indices;
    //public Vector3f[] positions;
    public float[] texCoord;

    public GameShopATMS atms;

    public int vaoId;

    //public GameShopObject gso;

    public GameShopCurrencySurface[] gspSurfaces;

    public int[] surfacePeek;

    //(x,y) widthRange //(z,w) heightRange
    //public Vector4f[] textureSlices;
    //Groups Of 4. [group][4x]
    //public GameShopCurrencyLine[][] gspLines;

    public Node node;
    public SimpleApplication app;

    //public String name;

    public Mesh m;
    public Geometry geom;
    public GameShopCurrencyMesh(SimpleApplication app,  Node node, GameShopCurrencySurface[] gspSurfaces, GameShopATMS atms){

       // this.name = name;

        this.app = app;
        this.node = node;
        this.gspSurfaces = new GameShopCurrencySurface[gspSurfaces.length];
        this.gspSurfaces = gspSurfaces;

        this.atms = atms;

        this.surfacePeek = new int[gspSurfaces.length];

        this.m = new Mesh();

        //this.textureSlices = new Vector4f[textureSlices.length];
        //this.textureSlices = textureSlices;
        // this.gspLines = new GameShopCurrencyLine[gspLines.length][4];
        //  this.gspLines = gspLines;

//         atms = new GameShopATMS(100, 100);
//         atms.layer.drawCircle(50, 50, 25, new Vector4f(0,0,1,1));
//         atms.makeATMS();
        // allocateVertices(gspLines);
//      this.indices = new int[]{
//        0, 1, 3, 3, 1, 2,
//    };

        allocateVertices();
        allocateIndices();
        allocateTexCoords();

//      this.texCoords = new float[]{
//      
//          0,0, 0,1, 1,1, 1,0
//          
//      };
    }




    public void allocateVertices(){

        int totalVertices = 0;
        for (GameShopCurrencySurface gsps: this.gspSurfaces){
            for (GameShopCurrencyLine gscl: gsps.vInfinitesimals){

                // System.out.println(totalVertices);
                totalVertices += gscl.infinitesimals.length * 3;
            }
        }
        this.vertices = new float[totalVertices];

        int i = 0;

        for (GameShopCurrencySurface gsps: this.gspSurfaces){
            for (GameShopCurrencyLine gscl: gsps.vInfinitesimals){

                for (com.jme3.math.Vector3f v: gscl.infinitesimals){

                    this.vertices[i] = v.x;
                    this.vertices[i + 1] = v.y;
                    this.vertices[i + 2] = v.z;
//                  if (this.vertices[i ]  ==  0.8234531f){
//                  
//                      System.out.println("ROUNDED: " + (i));
//                  }
                    if (this.vertices[i + 2]  ==  0f){

                        //System.out.println("ROUNDED: " + (i + 2));
                        if ((i + 3) % 3 == 0){


                            //  System.out.println("Z CORD: " + (i + 2));
                        }
                    }
                    i+=3;
                }
                // System.out.println(totalVertices);
                //totalVertices += gscl.infinitesimals.length;
            }
        }

          // System.out.println(vertices.length);
         //System.out.println(Arrays.toString(vertices));

        int j = 0;
        for (float v: vertices){

            if (j == 0){

                //   System.out.print(" x " + v);
                j++;

            } else if (j == 1){


                // System.out.print(" y " + v);
                j++;

            } else if (j == 2){


                // System.out.print(" z " + v);
                // System.out.println("");
                j = 0;
            }

        }
//          for (GameShopCurrencyLine gscl: vInfinitesimals){
//           
//              for (com.jme3.math.Vector3f v: gscl.infinitesimals){
//              
//                  
//                  i++;
//              }
//          }

        // System.out.println(vertices.length);
        // System.out.println(Arrays.asList(vertices));
//        int totalVertices = 0;
//        int j = 0;
//        for (GameShopCurrencySurface gsps: this.gspSurfaces){
//        
//          gsps.getTotalVertices();
//          // totalVertices += gsps.getTotalVertices();
//          System.out.println(gsps.infWidth);
//          System.out.println(gsps.infHeight);
//           this.surfacePeek[j] = (int) (gsps.infWidth * gsps.infHeight);
//           totalVertices += this.surfacePeek[j];
//            j++;
//        }
//        
//        vertices = new float[totalVertices * 3];
//        int i = 0;
//        for (GameShopCurrencySurface gsps: this.gspSurfaces){
//         
//           for (GameShopCurrencyLine vi: gsps.vInfinitesimals){
//             
//              for (com.jme3.math.Vector3f v : vi.infinitesimals){
//              
//                  vertices[i] = v.x;
//                  vertices[i + 1] = v.y;
//                  vertices[i + 2] = v.z;
//              
//                  i += 3;
//              }
//           }
//        }
    //   System.out.println(vertices.length);
//        System.out.println(Arrays.toString(vertices));
    }

    int skips = 0;

    public void allocateIndices(){

        int finalIndex = 0;
        //Find Poly Surface.  Determine Dimensions.  Go To Where In Array Start And End
        //Indices Seem To Be Half Of The Vertices.

        int totalIndices = 0;

//         for (GameShopCurrencySurface gsps: this.gspSurfaces){
//         
//             for (GameShopCurrencyLine gspl: gsps.vInfinitesimals){
//             
//                 for (com.jme3.math.Vector3f v: gspl.infinitesimals){
//                 totalIndices += 6;
//                 //totalIndices += 12;
//                 }
//             }
//         }

        totalIndices = ((this.vertices.length / 3) * 6) + 8;// - this.gspSurfaces[0].currencyLines.length;

        // totalIndices -= 150;
        //  totalIndices = (totalIndices/4) * 6;

        // System.out.println("totalIndices: " + totalIndices);
        indices = new int[totalIndices];

        int index = 0;
        int i = 0;
        int line = 0;
        int l = 0;
        int lineNum = 0;
        boolean isBreak = false;

        for (GameShopCurrencySurface gsps: this.gspSurfaces){

            if (isBreak){

                break;
            }
//            if (index + 6 >= totalIndices){
//        
//            break;
//        }
            for (GameShopCurrencyLine gspl: gsps.vInfinitesimals){

//                 if (index + 6 >= totalIndices){
//        
//            break;
//        }
                if (isBreak){

                    break;
                }
                for (com.jme3.math.Vector3f v: gspl.infinitesimals){

//                     if (index + 6 >= totalIndices){
//        
//            break;
//        }
//                    if (isBreak){
//
//                        break;
//
//                    }
//        
//            if (l > 0 && l % (gspl.numPoints) == 0) {
// 
//           
//            indices[index] =  0;
//            indices[index + 1] = 0;
//            indices[index + 2] = 0;
//            indices[index + 3] = 0;
//            indices[index + 4] = 0;
//            indices[index + 5] = 0;
//            l = 0;
//            continue;
//            } else {
//               
//                if ((int)(i + gspl.numPoints + 2) < (int)((gspl.infinitesimals.length))) {
                    //System.out.println((int)(i));

                    //if (index == 0 || index % gspl.infinitesimals.length != 0){

                    if ((short) (i + gspl.numPoints + 1) == (short)(vertices.length/3) - 1){
                   // if ((short) (i + gspl.numPoints + 1) == (short)((384)/6)-1){

                        finalIndex = index;
                        // System.out.println("BREAK");
                        isBreak = true;
                        break;
                    }
                   // System.out.println("numPoints " + gspl.numPoints);

                   // if (i >= gspl.numPoints  && (int)( (float)((i) * gspl.numPoints) % gspl.numPoints) == 0){

                    if (lineNum == gspl.numPoints){
                     //   System.out.println("i" + i);
                        indices[index] = 0;
                        indices[index + 1] = 0;
                        indices[index + 2] = 0;
                        indices[index + 3] = 0;
                        indices[index + 4] = 0;
                        indices[index + 5] = 0;
                        i++;
                        index+=6;
                        lineNum = 0;
                        continue;
                    }

                    indices[index] = (short) (i + gspl.numPoints + 1);
                    indices[index + 1] = (short) (i + 1);
                    indices[index + 2] = (short) (i);

                    indices[index + 3] = (short) (i + 1);
                    indices[index + 4] = (short) (i + gspl.numPoints + 1);
                    indices[index + 5] = (short) (i + gspl.numPoints + 2);



                    // }

//                 else {
//                    
//                        indices[index] = (short) -1;
//                    indices[index + 1] = (short) -1;
//                    indices[index + 2] = (short) -1;
//                    indices[index + 3] = (short) -1;
//                    indices[index + 4] = (short) -1;
//                    indices[index + 5] = (short) -1;
//               
//                       // skips++;
//                    }
                    //skips++;
                    //  }
//                 
//                 else {
//                    indices[index] =  0;
//                    indices[index + 1] = 0;
//                    indices[index + 2] = 0;
//                    indices[index + 3] = 0;
//                    indices[index + 4] = 0;
//                    indices[index + 5] = 0;
//                   skips++;
//                }


                    i++;
                    l++;

                    index += 6;


                    lineNum++;
                    //i++;
                }



                if (i % (gspl.infinitesimals.length * gspl.infinitesimals.length) == 0) {

                    line++;
                }


            }
        }

        indices = Arrays.copyOfRange(indices, 0, finalIndex);
       // System.out.println("indices");
       // System.out.println(Arrays.toString(indices));
    }

    public void allocateTexCoords(){

        // this.texCoord = new com.jme3.math.Vector2f[(this.vertices.length/6) - (skips * 2) - 4];

        //this.texCoord = new com.jme3.math.Vector2f[this.vertices.length];
        this.texCoord = new float [(this.vertices.length/3) * 2];

        int i = 0;
        float x = 0;
        float y = 0;
        int slice = 0;
        int v = 0;
        for (GameShopCurrencySurface gsps: this.gspSurfaces){
            if (gsps.currencyLines.length / 4 != atms.textureSamples.length){

                System.out.println("You Need 4 CurrencyLines for every 1 Texture Sample");
                for (float tc: this.texCoord){

                    tc = 0f;
                }
                return;
            }

            int maxLines = gsps.vInfinitesimals.length;
            int lines = 0;

            //System.out.println("currencyLines " + gsps.currencyLines.length);
            //System.out.println("vInfinitesimals " + gsps.vInfinitesimals.length);

        }


        while ( v < texCoord.length){

            if (v == 0){
                x = atms.textureSamples[slice].x;
                y  = atms.textureSamples[slice].z;
            }
            if (slice == atms.textureSamples.length){

                break;
            }

            //texCoord[v] = new com.jme3.math.Vector2f((atms.textureSamples[slice].x + (x)) , (atms.textureSamples[slice].z + (y)));

            texCoord[v] = atms.textureSamples[slice].x + (x);
            texCoord[v + 1] = atms.textureSamples[slice].z + (y);

            this.gspSurfaces[slice].getTotalVertices();
            //y += (((float) 1 /(((float) this.gspSurfaces[slice].vInfinitesimals[0].infinitesimals.length /atms.textureSamples.length))));
//y += (((float) 1 /(((float) this.gspSurfaces[slice].vInfinitesimals[0].infinitesimals.length /atms.textureSamples.length))));

            y += (float)(1/ ( this.gspSurfaces[slice].infWidth - 1));

            if (y > atms.textureSamples[slice].w){
                y  = atms.textureSamples[slice].z;
                x += (float)(1/ ( this.gspSurfaces[slice].infHeight - 1));

                //x += (((float) 1 /(((float) this.gspSurfaces[slice].vInfinitesimals.length /atms.textureSamples.length))));
            }




//            if (x > atms.textureSamples[slice].y) {
//
//                x = atms.textureSamples[slice].x;
//                slice++;
//            }


/*
            if (i > vInfinitesimals[lines].numPoints) {

                i = 0;
                y += .5f;
            }
            texCoord[v] = new Vector2f((((float)y)), ((float)i/(float)vInfinitesimals[lines].numPoints));

           // texCoord[v] = new Vector2f();

           // texCoord[v] = new Vector2f();

            i++;
            
            */
            v+=2;
        }
        i++;
        slice++;

       // System.out.println(Arrays.toString(this.texCoord));
    }

    public float[] convertVector2ToFloat(com.jme3.math.Vector2f[] v){

        float[] convert = new float[(int)v.length * 2];

        int i = 0;
        int j = 0;
        for (com.jme3.math.Vector2f cv: v){

            convert[i] = cv.x;
            convert[i + 1] = cv.y;
            i+=2;
            //j++;
        }
        return convert;
    }


    public void updateShapes(){

        m.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        m.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(texCoord));
        m.setBuffer(Type.Index, 1, BufferUtils.createIntBuffer(indices));
        m.updateBound();
//        m.updateCounts();
//        if (geom != null) {
//            geom.updateModelBound();
//        }
       // geom.updateModelBound();
        //node.c
        //node.getChild(0).
    }
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
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
       // mat.getAdditionalRenderState().setDepthTest(true);
        mat.getAdditionalRenderState().setFaceCullMode(RenderState.FaceCullMode.Off);
        //Material mat = new Material(app.getAssetManager(), "MatDefs/GameShopUI.j3md");

        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        mat.setTransparent(true); //not sure if it was needed rly
        geom.setQueueBucket(RenderQueue.Bucket.Transparent);


//        mat.getAdditionalRenderState().setBlendEquation(RenderState.BlendEquation.Add);
//        mat.getAdditionalRenderState().setBlendEquationAlpha(RenderState.BlendEquationAlpha.Max);

        Texture2D texture = new Texture2D(this.atms.makeATMS());

        mat.setColor("Color", ColorRGBA.fromRGBA255(255,255,255,255));

        mat.setTexture("ColorMap", texture);


        geom.setMaterial(mat);


         //node.getChild(0).set

        // Attaching our geometry to the root node.
        node.attachChild(geom);

        this.app.getRootNode().attachChild(node);

    }

//    public void updateShapes(){
//
//    }

//    public void allocateVertices(Vector3f[] vertices){
//        
//      this.positions = new Vector3f[vertices.length];
//      this.positions = vertices;
//        
//      this.vertices = new float[vertices.length * 3];
//         
//        int i = 0;
//        for (Vector3f vertex: vertices){
//        
//            this.vertices[i] = vertex.x;
//            this.vertices[i + 1] = vertex.y;
//            this.vertices[i + 2] = vertex.z;
//            
//            i += 3;
//        }
//    }

    //FloatBuffer verticesBuffer;// = MemoryUtil.memAllocFloat(vertices.length);
    //IntBuffer indicesBuffer;// = MemoryUtil.memAllocInt(indices.length);
    //FloatBuffer textureCoordinatesBuffer;

//    public void allocateBuffer(){
//
//        bindVertexArrayObject();
//        bindVerticesBuffer();
//        bindIndicesBuffer();
//        bindTextureCoordinatesBuffer();
//        this.atms.makeATMS();
//        //unbind();
//
//    }

   // public void unbind(){
//        glBindBuffer(GL_ARRAY_BUFFER, 0);
//        glBindVertexArray(0);
//
//    }

   // public void outputHashValues(){

//
//
//      (GameShopIndexHash.getInstance().indexHash.get(this));
//      System.out.println(GameShopShaderHash.getInstance().getGLShaderProgram("Hello GameShop"));
//      System.out.println(GameShopShapeHash.getInstance().shapeHash.get(this));
//      System.out.println(GameShopVertexHash.getInstance().vertexHash.get(this));
  //  }
//    public void bindVertexArrayObject(){
//
//        GameShopVertexHash.getInstance().addVertexArrayObject(this);
//        glBindVertexArray(GameShopVertexHash.getInstance().vertexHash.get(this));
//    }
//
//    public void bindVerticesBuffer(){
//        verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
//        verticesBuffer.put(vertices).flip();
//        GameShopShapeHash.getInstance().addPoly(this);
//        glBindBuffer(GL_ARRAY_BUFFER, GameShopShapeHash.getInstance().shapeHash.get(this));
//        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
//        glEnableVertexAttribArray(0);
//        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
//        memFree(verticesBuffer);
//    }
//
//    public void bindIndicesBuffer(){
//        indicesBuffer = MemoryUtil.memAllocInt(indices.length);
//
//        indicesBuffer.put(indices).flip();
//        GameShopIndexHash.getInstance().addIndex(this);
//        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, GameShopIndexHash.getInstance().indexHash.get(this));
//        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
//        memFree(indicesBuffer);
//    }
//
//    public void bindTextureCoordinatesBuffer(){
//
//        textureCoordinatesBuffer = MemoryUtil.memAllocFloat(texCoord.length * 2);
//        textureCoordinatesBuffer.put((texCoord)).flip();
//        GameShopTextureCoordsHash.getInstance().addPoly(this);
//        glBindBuffer(GL_ARRAY_BUFFER, GameShopTextureCoordsHash.getInstance().textureCoordsHash.get(this));
//        glBufferData(GL_ARRAY_BUFFER, textureCoordinatesBuffer, GL_STATIC_DRAW);
//        glEnableVertexAttribArray(2);
//        glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);
//        memFree(textureCoordinatesBuffer);
//    }
//
//    public void draw(){
//        // assert(GameShopATMSHash.getInstance().atmsHash.get(atms) != null);
//
//        // System.out.println(atms);
//        glActiveTexture(GL_TEXTURE0);
//        glBindTexture(GL_TEXTURE_2D,  GameShopATMSHash.getInstance().atmsHash.get(atms));//GameShopATMSHash.getInstance().atmsHash.get(atms.uniqueID)
//        // Draw the mesh
//        glBindVertexArray(GameShopVertexHash.getInstance().vertexHash.get(this));
//
//        glDrawElements(GL_TRIANGLES, indices.length,GL_UNSIGNED_INT, 0);
//
//
//    }
}
