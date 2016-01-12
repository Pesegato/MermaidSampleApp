/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pesegato.mermaid.settings;

/**
 *
 * @author Pesegato
 */
public class Benable_comic {
    public static boolean enable_comic = true;

    public boolean isEnable_comic(){
        return enable_comic;
    }
    
    public void setEnable_comic(boolean enable_comic){
        Benable_comic.enable_comic=enable_comic;
    }

}
