package mygame;


import com.pesegato.mermaid.PrismAction;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pesegato
 */
public class ActionCG extends PrismAction{

    @Override
    public void action() {
                System.out.println("Pressed CG!");
                stateManager.detach(stateManager.getState(MainMenustate.class));
        
    }

}
