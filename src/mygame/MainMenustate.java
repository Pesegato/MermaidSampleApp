/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.BaseAppState;
import com.jme3.font.BitmapFont;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.shadow.EdgeFilteringMode;
import com.jme3.shadow.PointLightShadowRenderer;
import com.pesegato.goldmonkey.GM;
import com.pesegato.goldmonkey.Mermaid;
import com.pesegato.mermaid.MermaidAppState;
import com.pesegato.mermaid.PButton;
import com.pesegato.mermaid.PRadioGroup;
import com.pesegato.mermaid.PSwitch;
import com.pesegato.mermaid.PrismaticPane;
import com.simsilica.lemur.input.InputMapper;

/**
 *
 */
public class MainMenustate extends BaseAppState {

    public static BitmapFont font = new BitmapFont();
    PrismaticPane pp;
    Node rootNode = new Node();

    private void addWallButton(PButton pbutton){
        pbutton.setMaterial(createPswitchmat());
        rootNode.attachChild(pbutton);
    }
    
    @Override
    protected void initialize(Application app) {

        al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.3f));
        rootNode.addLight(al);
        rootNode.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
        
        addWallButton(Mermaid.getWallButton("butPlay",app.getStateManager()));
        addWallButton(Mermaid.getWallButton("butCG",app.getStateManager()));
        
        Material mat = new Material(app.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors", true);
        mat.setColor("Diffuse", GM.getColorRGBA("ColorB"));
        mat.setColor("Ambient", GM.getColorRGBA("ColorB"));
        Geometry header = new Geometry("header", new Box(7.5f, .8f, 2));
        header.setMaterial(mat);
        header.setLocalTranslation(0, 4, -6.8f);
        rootNode.attachChild(header);
        PRadioGroup radio = new PRadioGroup();
        rootNode.attachChild(new PSwitch("Video", -6 + 0 * 2.5f, 4, createPswitchmat(), 0, radio));
        rootNode.attachChild(new PSwitch("Game", -6 + 1 * 2.5f, 4, createPswitchmat(), 1, radio));
        rootNode.attachChild(new PSwitch("Input", -6 + 2 * 2.5f, 4, createPswitchmat(), 2, radio));
        rootNode.attachChild(new PSwitch("Audio", -6 + 3 * 2.5f, 4, createPswitchmat(), 3, radio));
        rootNode.attachChild(new PSwitch("Other", -6 + 4 * 2.5f, 4, createPswitchmat(), 4, radio));
        rootNode.attachChild(new PSwitch("Info", -6 + 5 * 2.5f, 4, createPswitchmat(), 5, radio));

        Box b = new Box(20, 20, 1);
        Geometry geom = new Geometry("Box", b);

        // mat.setColor("Specular", ColorRGBA.White.clone());
        // mat.getTextureParam("ParallaxMap").getTextureValue().setWrap(WrapMode.Repeat);
        //mat.setFloat("Shininess", 0);
        geom.setMaterial(mat);
        geom.setLocalTranslation(-8, -1, -6);
        rootNode.attachChild(geom);

        pp = new PrismaticPane(mat, rootNode);
        radio.setPrismaticPane(pp);
        
        pp.addFace(Mermaid.getLabel("video"));
        pp.addFace(Mermaid.getLabel("game"));
        pp.addFace(Mermaid.getLabel("input"));
        pp.addFace(Mermaid.getLabel("sound"));
        pp.addFace(Mermaid.getLabel("other"));

        //PBooleanProperty ic;
        //ic = new PBooleanProperty("enable comic", new PPropertyAccess<Boolean>(getState(SettingsAppState.class), "enable_comic"));
        //ic.initialize(null);
        //getState(MermaidAppState.class).miscSettings.attachChild(ic.getWidget());
        //getState(MermaidAppState.class).miscSettings.attachChild(ic);
        //getState(MermaidAppState.class).miscSettings.addBooleanProperty("Enable intro comic", getState(SettingsAppState.class), "enable_comic");
        
        pp.addFace(getState(MermaidAppState.class).miscSettings);
        
        lamp_light = new PointLight();
        lamp_light.setColor(ColorRGBA.White);
        lamp_light.setRadius(14f);
        lamp_light.setPosition(new Vector3f(((SimpleApplication) getApplication()).getCamera().getLocation()));
        rootNode.addLight(lamp_light);

        plsr = new PointLightShadowRenderer(app.getAssetManager(), 1024);
        plsr.setLight(lamp_light);
        plsr.setShadowIntensity(0.5f);
        plsr.setEdgeFilteringMode(EdgeFilteringMode.PCFPOISSON);
//plsr.displayDebug();

        /*
         PointLightShadowFilter slsf = new PointLightShadowFilter(assetManager, 512);
         slsf.setLight(lamp_light);    
         slsf.setShadowIntensity(0.5f);
         slsf.setEdgeFilteringMode(EdgeFilteringMode.PCFPOISSON);  
         //slsf.setEnabled(false);
         FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
         fpp.addFilter(slsf);
        
         //fpp.addFilter(Performance.ssaoFilter);
         */
        app.getViewPort().addProcessor(plsr);
    }

    public static Material matClone;

    private Material createPswitchmat() {
        matClone = new Material(getApplication().getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        matClone.setBoolean("UseMaterialColors", true);
        matClone.setColor("Diffuse", GM.getColorRGBA("ColorA"));
        matClone.setColor("Ambient", GM.getColorRGBA("ColorA"));
        return matClone.clone();
    }
    AmbientLight al;
    PointLight lamp_light;
    PointLightShadowRenderer plsr;

    @Override
    public void update(float tpf) {
        Vector2f click2d = getApplication().getInputManager().getCursorPosition();
        Vector3f click3d = ((SimpleApplication) getApplication()).getCamera().getWorldCoordinates(new Vector2f(click2d.x, click2d.y), z_pos).clone();
        //Vector3f dir = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
        //spot.setDirection(dir);
        lamp_light.setPosition(click3d);
        //sun.setDirection(dir);
    }
    public static float z_pos = 0.9f;

    InputMapper inputMapper;

@Override
public void cleanup(Application app) {
}

    @Override
    protected void onEnable() {
        ((SimpleApplication) getApplication()).getRootNode().attachChild(rootNode);
    }

    @Override
    protected void onDisable() {
        rootNode.removeFromParent();
        rootNode.removeLight(lamp_light);
        rootNode.removeLight(al);
        getApplication().getViewPort().removeProcessor(plsr);
        rootNode.detachAllChildren();
    }
}
