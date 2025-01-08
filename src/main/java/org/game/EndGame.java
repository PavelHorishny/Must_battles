package org.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndGame {
    private boolean endGame;
    private String message;
    public boolean isEmpty(){
        return !endGame || message.isEmpty();
    }
}
