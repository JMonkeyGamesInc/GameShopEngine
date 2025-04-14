//Discontinued.  Using NiftyGUI
precision mediump float;

varying vec2 texCoord1;
uniform vec4 m_Color;
#ifdef COLORMAP
     uniform sampler2D m_ColorMap;
#endif

void main(){
    //returning the color of the pixel (here solid blue)
    //- gl_FragColor is the standard GLSL variable that holds the pixel
    //color. It must be filled in the Fragment Shader.
//    if (m_Color.a < 0.4){
//        discard;
//    }
    #ifdef COLORMAP
        gl_FragColor = m_Color * texture2D(m_ColorMap, texCoord1);
    #else
        gl_FragColor = m_Color;
    #endif
}