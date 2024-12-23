package org.game.services;

import org.game.Context;
import org.game.map.Surface;
import org.game.mockData.MockedData;
import org.game.mockData.NamesRandomizer;
import org.game.unit.*;

import java.util.*;

public class VesselProcessor implements VesselService{
    NamesRandomizer namesRandomizer = Context.getNameRandomizer();
    ArrayList <Vessel> firstPlayerVessels = new ArrayList<>();

    public VesselProcessor(){
        //namesRandomizer = new NamesRandomizer();
    }
    /**
     * Accepts Map<String,GameUnit> to keep added vessels objects, Surface [][] that keeps map
     * Void
     * Method calls setFleet with necessary parameters*/

    @Override
    public void setVessels(Map<String, GameUnit> fleet, Surface[][] map) {
        setFleet(randomizeFleet(generateVesselsWithNames(true)),randomizeFleet(generateVesselsWithNames(false)),fleet,map);
    }

    /**
     * @param fleet
     * @return
     */
    @Override
    public List<Vessel> getVessels(Map<String,GameUnit> fleet) {
        return fleet.values().stream().filter(e->e.getUnitType().equals(UnitType.VESSEL)).map(Vessel.class::cast).toList();
    }

    /**
     * Accepts Stack<Vessel> that keeps vessel objects in random order for first player,
     *      Stack<Vessel> that keeps vessel objects in random order for second player, Map<String,GameUnit> to keep game unit objects and Surface [][] that keeps map
     *Void
     * Method calls setVesselsInFort with necessary parameters for each player*/

    private void setFleet(Stack<Vessel> firstPlayerFleet, Stack<Vessel> secondPlayerFleet, Map<String, GameUnit> fleet, Surface[][] map) {
        setVesselsInFort(true,map,fleet,firstPlayerFleet);
        setVesselsInFort(false,map,fleet,secondPlayerFleet);
    }
    /**
     * Accepts int that represents quantity of vessels in fortification of given type, Fortification where vessels will be placed, Surface [][] that keeps map,
     *  Map<String,GameUnit> where vessel object will keep, Stack<Vessel> that keeps Vessel objects in random order
     * Void
     * Method allocates Vessels objects in port field of Surface object and adds vessel object to Map<String,GameUnit> in int quantity according to game rules */

    private void setVesselsInPort(int size, Fortification fortification, Surface [][] map, Map<String,GameUnit> fleet, Stack<Vessel> playerFleet){
        for(int i = 0; i<size; i++){
            Vessel tmp = playerFleet.pop();
            tmp.setCoordinates(fortification.getPort().get(i).getCoordinates());
            map[fortification.getPort().get(i).getCoordinates().axisX()][fortification.getPort().get(i).getCoordinates().axisY()].setUnit(tmp);
            fleet.put(tmp.getId(),tmp);
        }
    }
    /**
     * Accepts boolean that represents player, Surface [][] that keeps map, Map<String, GameUnit> that keeps Fortification objects and Stack <Vessel> of vessels in random order
     * Void
     * Method filters Map<String,GameUnit> by given player, that according to Fortification type calls setVesselsInPort method with necessary parameters
     * and allocates Vessel objects from Stack<Vessel>*/

    private void setVesselsInFort(boolean isFirstPlayer,Surface [][] map,Map<String,GameUnit> fleet,Stack<Vessel> vessels){
        fleet.values().stream().filter(unit -> unit.isFirstPlayer()==isFirstPlayer).filter(unit -> unit.getUnitType().equals(UnitType.FORTIFICATION))
                .map(Fortification.class::cast).toList()
                .forEach(fortification -> {
                    switch (fortification.getFortificationType()){
                        case FIRST_LINE_FORT ->
                            setVesselsInPort(4,fortification,map,fleet,vessels);
                        case SECOND_LINE_FORT ->
                            setVesselsInPort(5,fortification,map,fleet,vessels);
                    }
                });
        fleet.values().stream().filter(unit -> unit.isFirstPlayer()==isFirstPlayer).filter(unit -> unit.getUnitType().equals(UnitType.FORTIFICATION))
                .map(Fortification.class::cast).filter(fortification -> fortification.getFortificationType().equals(FortificationType.SECOND_LINE_FORT))
                .forEach(fortification -> {
                    if(fortification.getPort().size()>5) {
                        Vessel tmp = vessels.pop();
                        tmp.setCoordinates(fortification.getPort().get(5).getCoordinates());
                        map[fortification.getPort().get(5).getCoordinates().axisX()][fortification.getPort().get(5).getCoordinates().axisY()].setUnit(tmp);
                    }
                });
        //TODO find way to simplify last part of method but consider that it is possible that more than one Fortification can have port size bigger than 5
    }
    /**
     * Accepts ArrayList<Vessel>
     * Returns Stack<Vessel>
     * Method takes an arrayList of vessels and fills a Stack in random order*/

    private Stack<Vessel> randomizeFleet(ArrayList<Vessel> vessels){
        Stack<Vessel> fleet = new Stack<>();
        int end = vessels.size();
        while (fleet.size()<end){
            int x = (int) (Math.random()*(vessels.size()));
            fleet.push(vessels.get(x));
            vessels.remove(x);
        }

        return fleet;
    }
    /**
     * Accepts boolean that indicates player
     * Returns ArrayList<Vessel>
     * Method uses array of all vessel types to create all necessary vessel units for given player*/

    private ArrayList<Vessel> generateVesselsWithNames(boolean isFirstPlayer){
        ArrayList<Vessel> vessels = new ArrayList<>();
        Arrays.stream(VesselType.vesselTypes).forEach(vesselType -> addVesselsByType(vesselType,vessels,isFirstPlayer));
        return vessels;
    }
    /**
     * Accepts VesselType, ArrayList<Vessel> that will be modified,boolean that indicates player
     * Void
     * Method calls addVesselsWithNames method for given VesselType, and passes necessary parameters for given VesselType*/

    private void addVesselsByType(VesselType vesselType, ArrayList<Vessel> vessels, boolean isFirstPlayer){
        switch (vesselType){
            case THREE_DECKER_SHIP_OF_LINE -> addVesselsWithNames(vessels,MockedData.THREE_DECKER_SHIP_THE_LINE_QNT/2,isFirstPlayer,namesRandomizer.bigBattleshipsNames,vesselType);
            case TWO_DECKER_SHIP_OF_LINE -> addVesselsWithNames(vessels,MockedData.TWO_DECKER_SHIP_THE_LINE_QNT/2,isFirstPlayer,namesRandomizer.smallBattleshipsNames,vesselType);
            case FRIGATE -> addVesselsWithNames(vessels,MockedData.FRIGATES_QNT/2,isFirstPlayer,namesRandomizer.frigatesNames,vesselType);
            case TENDER -> addVesselsWithNames(vessels,MockedData.TENDERS_QNT/2,isFirstPlayer,namesRandomizer.tendersNames,vesselType);
            case BRIG -> addVesselsWithNames(vessels,MockedData.BRIGS_QNT/2,isFirstPlayer,namesRandomizer.brigNames,vesselType);
            case GALLEON -> addVesselsWithNames(vessels,MockedData.GALLEONS_QNT/2,isFirstPlayer,namesRandomizer.galleonsNames,vesselType);
            case STEAM_FRIGATE -> addVesselsWithNames(vessels,MockedData.STEAM_FRIGATE_QNT/2,isFirstPlayer,namesRandomizer.steamFrigateNames,vesselType);
            case BATTERY -> addVesselsWithNames(vessels,MockedData.NAVAL_BATTERY_QNT/2,isFirstPlayer,namesRandomizer.navalBatteryNames,vesselType);
            case GALLEY -> addVesselsWithNames(vessels,MockedData.GALLEYS_QNT/2,isFirstPlayer,namesRandomizer.galleysName,vesselType);
            case CORVETTE -> addVesselsWithNames(vessels,MockedData.STEAM_CORVETTE_QNT/2,isFirstPlayer,namesRandomizer.steamCorvetteNames,vesselType);
            case MONITOR -> addVesselsWithNames(vessels,MockedData.MONITOR_QNT/2,isFirstPlayer,namesRandomizer.monitorNames,vesselType);
            case STEAMSHIP -> addVesselsWithNames(vessels,MockedData.STEAMSHIP_QNT/2,isFirstPlayer,namesRandomizer.steamshipNames,vesselType);
        }
    }
    /**
     * Accepts ArrayList<Vessel> that must be filled, int quantity of iterations that depends on quantity of vessels that player should have of certain type,
     * boolean that indicates player, Stack<String> that contains names for certain vessel type, VesselType
     * void
     * Method adds to ArrayList<Vessel> new Vessel objects of certain VesselType in given quantity and sets names for it taken from Stack <String> of mocked names*/

    private void addVesselsWithNames(ArrayList<Vessel> vessels,int qnt,boolean player,Stack<String> names,VesselType vesselType){
        for (int i = 0; i<qnt; i++){
            vessels.add(new Vessel(player,vesselType,names.pop()));
        }
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
    //TODO test for vessel processor
}
