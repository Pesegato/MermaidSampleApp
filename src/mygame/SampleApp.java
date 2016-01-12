package mygame;


import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AppState;
import com.pesegato.mermaid.MermaidAppState;
import com.pesegato.goldmonkey.GoldMonkeyAppState;
import com.pesegato.goldmonkey.MermaidBuilder;
import com.pesegato.mermaid.settings.Bgame_difficulty;
import com.pesegato.mermaid.settings.Bsound_volume;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pesegato
 */
public class SampleApp extends SimpleApplication {

    public SampleApp(AppState... appstates) {
        super(appstates);
    }

    public static void main(String[] args) {

        String gmPath[] = new String[]{
            "/Interface/Mermaid/main.xml"
        };
        
        SampleApp app = new SampleApp(
                new GoldMonkeyAppState(false, false, gmPath),
                new MermaidAppState(),
                new MainMenustate()
        );
        app.start();
    }

    @Override
    public void simpleInitApp() {
    }

    @Override
    public void update(){
        super.update();
        //System.out.println(MermaidBuilder.getBoolean("enable_comic"));
        if (Bgame_difficulty.game_difficulty==Bgame_difficulty.GameDiff.IMPOSSIBLE)
            System.out.println("Im possible!!!");
        System.out.println(Bsound_volume.sound_volume);
    }
}
