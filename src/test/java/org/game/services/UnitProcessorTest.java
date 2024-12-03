package org.game.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitProcessorTest {
    static UnitProcessor unitProcessor;
    @BeforeAll
    static void start(){
        unitProcessor = new UnitProcessor();
    }
    @Test
    void prt(){
        unitProcessor.getFleet().forEach((k,v)->assertEquals(k,v.getId()));
    }
}