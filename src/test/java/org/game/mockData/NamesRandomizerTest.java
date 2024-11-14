package org.game.mockData;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class NamesRandomizerTest {
    public static NamesRandomizer namesRandomizer;
    @BeforeAll
    static void setUp() {
        namesRandomizer = new NamesRandomizer();
    }
    @Test
    void checkStackOfRoyalPortNames(){
        assertEquals(2, namesRandomizer.royalPortNames.size());
        namesRandomizer.royalPortNames.forEach(System.out::println);
    }
    @Test
    void  checkStackOfFortNAmes(){
        assertEquals(12, namesRandomizer.fortificationsNames.size());
        namesRandomizer.fortificationsNames.forEach(System.out::println);
    }
    @Test
    void assertStackSizeAccordingToShipQnt(){
        assertEquals(namesRandomizer.getFORTIFICATIONS_QNT(),namesRandomizer.fortificationsNames.size());
        assertEquals(namesRandomizer.getROYAL_PORT_QNT(),namesRandomizer.royalPortNames.size());
        assertEquals(namesRandomizer.getBIG_BATTLESHIP_QNT(),namesRandomizer.bigBattleshipsNames.size());
        assertEquals(namesRandomizer.getSMALL_BATTLESHIP_QNT(),namesRandomizer.smallBattleshipsNames.size());
        assertEquals(namesRandomizer.getFRIGATES_QNT(),namesRandomizer.frigatesNames.size());
        assertEquals(namesRandomizer.getTENDERS_QNT(),namesRandomizer.tendersNames.size());
        assertEquals(namesRandomizer.getBRIGS_QNT(),namesRandomizer.brigNames.size());
        assertEquals(namesRandomizer.getGALLEONS_QNT(),namesRandomizer.galleonsNames.size());
        assertEquals(namesRandomizer.getSTEAM_FRIGATE_QNT(),namesRandomizer.steamFrigateNames.size());
        assertEquals(namesRandomizer.getNAVAL_BATTERY_QNT(),namesRandomizer.navalBatteryNames.size());
        assertEquals(namesRandomizer.getGALLEYS_QNT(),namesRandomizer.galleysName.size());
        assertEquals(namesRandomizer.getSTEAM_CORVETTE_QNT(),namesRandomizer.steamCorvetteNames.size());
        assertEquals(namesRandomizer.getMONITOR_QNT(),namesRandomizer.monitorNames.size());
        assertEquals(namesRandomizer.getSTEAMSHIP_QNT(),namesRandomizer.steamshipNames.size());
    }
}