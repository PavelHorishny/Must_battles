package org.game.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UnitProcessorTest {
    static GameProcessor unitProcessor;
    @BeforeAll
    static void start(){
        unitProcessor = new GameProcessor();
    }
/*    @Test
    void prt(){
        unitProcessor.getFleet().forEach((k,v)->assertEquals(k,v.getId()));
    }*/
}