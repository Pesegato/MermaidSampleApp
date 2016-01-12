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
public class Bgame_difficulty {

    public enum GameDiff {

        EASY("Easy"), NORMAL("Normal"), HARD("Hard"), VERYHARD("Very hard"), IMPOSSIBLE("Impossible");
        private String name;

        private GameDiff(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    };

    public static GameDiff game_difficulty = GameDiff.EASY;

    public GameDiff getGame_difficulty() {
        return game_difficulty;
    }

    public void setGame_difficulty(GameDiff game_difficulty) {
        Bgame_difficulty.game_difficulty = game_difficulty;
    }

}
