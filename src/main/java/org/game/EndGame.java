package org.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndGame {
    boolean endGame;
    String message;
    public boolean isEmpty(){
        return !endGame || message.isEmpty();
    }
}
