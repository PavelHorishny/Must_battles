package org.game.services;

import org.game.map.Surface;
import org.game.mockData.MockedData;
import org.game.mockData.NamesRandomizer;
import org.game.unit.GameUnit;
import org.game.unit.Vessel;
import org.game.unit.VesselType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class VesselProcessor implements VesselService{
    NamesRandomizer namesRandomizer = new NamesRandomizer();
    ArrayList <Vessel> firstPlayerVessels = new ArrayList<>();
    @Override
    public void getVessels(Map<String, GameUnit> fleet, Surface[][] map) {
        /*{{
            add(new Vessel(true, VesselType.THREE_DECKER_SHIP_OF_LINE));
            add(new Vessel(true,VesselType.THREE_DECKER_SHIP_OF_LINE));
        }};*/
    }

    private void generateFleet(ArrayList<Vessel> vessels, boolean isFirstPlayer){
        Arrays.stream(VesselType.vesselTypes).forEach(vesselType -> {
            switch (vesselType){
                case THREE_DECKER_SHIP_OF_LINE -> {
                    for(int i = 0; i< MockedData.THREE_DECKER_SHIP_THE_LINE_QNT/2; i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.bigBattleshipsNames.pop()));
                    }
                }
                case TWO_DECKER_SHIP_OF_LINE -> {
                    for(int i = 0; i<MockedData.TWO_DECKER_SHIP_THE_LINE_QNT/2;i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.smallBattleshipsNames.pop()));
                    }
                }
                case FRIGATE -> {
                    for(int i=0;i<MockedData.FRIGATES_QNT/2;i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.frigatesNames.pop()));
                    }
                }
                case TENDER -> {
                    for(int i = 0; i<MockedData.TENDERS_QNT/2;i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.tendersNames.pop()));
                    }
                }
                case BRIG -> {for(int i = 0; i<MockedData.BRIGS_QNT/2;i++){
                    vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.brigNames.pop()));
                }
                }
                case GALLEON -> {
                    for(int i = 0; i<MockedData.GALLEONS_QNT/2;i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.galleonsNames.pop()));
                    }
                }
                case STEAM_FRIGATE -> {
                    for(int i = 0; i<MockedData.STEAM_FRIGATE_QNT/2;i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.steamFrigateNames.pop()));
                    }
                }
                case BATTERY -> {
                    for(int i = 0; i<MockedData.NAVAL_BATTERY_QNT/2;i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.navalBatteryNames.pop()));
                    }
                }
                case GALLEY -> {
                    for(int i = 0; i<MockedData.GALLEYS_QNT/2;i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.galleysName.pop()));
                    }
                }
                case CORVETTE -> {
                    for(int i = 0; i<MockedData.STEAM_CORVETTE_QNT/2;i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.steamCorvetteNames.pop()));
                    }
                }
                case MONITOR -> {
                    for(int i = 0; i<MockedData.MONITOR_QNT/2;i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.monitorNames.pop()));
                    }
                }
                case STEAMSHIP -> {
                    for(int i = 0; i<MockedData.STEAMSHIP_QNT/2;i++){
                        vessels.add(new Vessel(isFirstPlayer,vesselType,namesRandomizer.steamshipNames.pop()));
                    }
                }
            }
        });
    }
    /**
     * Each player has 3 first line forts 6 in total
     * Each player has 3 second line forts 6 in total
     * Each player has 1 royal port 2 in total
     * Each player has 2 Big battleships 4 in total
     * Each player has 4 Small battleships 8 in total
     * Each player has 3 Frigates 6 in total
     * Each player has 2 Tenders 4 in total
     * Each player has 5 Brigs 10 in total
     * Each player has 3 Galleons 6 in total
     * Each player has 1 Steam frigate 2 in total
     * Each player has 1 Naval battery 2 in total
     * Each player has 4 Galleys 8 in total;
     * Each player has 1 Steam corvette 2 in total
     * Each player has 1 Monitor 2 in total
     * Each player has 1 Steamship 2 in total
     * */
    //TODO separate methods convert array to stack complete vessel generation
}
