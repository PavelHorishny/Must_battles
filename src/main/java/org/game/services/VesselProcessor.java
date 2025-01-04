package org.game.services;

import org.game.Context;
import org.game.gui.Coordinates;
import org.game.gui.StateType;
import org.game.map.Surface;
import org.game.map.SurfaceType;
import org.game.mockData.MockedData;
import org.game.mockData.NamesRandomizer;
import org.game.unit.*;

import java.util.*;

public class VesselProcessor implements VesselService{
    NamesRandomizer namesRandomizer = Context.getNameRandomizer();
    WeatherService weatherProcessor = new WeatherProcessor();
    ArrayList <Vessel> firstPlayerVessels = new ArrayList<>();

    public VesselProcessor(){
    }
    /**
     * Accepts Map<String,GameUnit> to keep added vessels objects, Surface [][] that keeps map
     * Void
     * Method calls setFleet with necessary parameters*/

    @Override
    public void setTheFleet(Map<String, GameUnit> fleet, Surface[][] map) {
        setUpVesselsForBothPlayers(shufflePlayersVessels(generateVesselsWithNames(true)), shufflePlayersVessels(generateVesselsWithNames(false)),fleet,map);
    }

    /**
     * @param fleet
     * @return
     */
    @Override
    public List<Vessel> getListOfAllVessels(Map<String,GameUnit> fleet) {
        return fleet.values().stream().filter(e->e.getUnitType().equals(UnitType.VESSEL)).map(Vessel.class::cast).toList();
    }

    /**
     * @param vessel
     * @param map
     * @return
     */
    @Override
    public boolean checkIfVesselCanBeRepaired(Vessel vessel, Surface[][] map) {
        if(vessel.getCurrent_hit_point()<vessel.getVesselType().getHit_points()){
            if(checkIfSurfaceIsPort(map[vessel.getCoordinates().axisX()][vessel.getCoordinates().axisY()])){
                return map[vessel.getCoordinates().axisX()][vessel.getCoordinates().axisY()].getFortification().isFirstPlayer() == vessel.isFirstPlayer();
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    private boolean checkIfSurfaceIsPort(Surface surface){
        return surface.getType().equals(SurfaceType.PORT);
    }

    /**
     * @param vessel
     * @param destination
     * @param map
     */
    @Override
    public void moveVesselToDestinationPoint(Vessel vessel, Coordinates destination, Surface[][] map) {
        Coordinates start = new Coordinates(vessel.getCoordinates().axisX(),vessel.getCoordinates().axisY());
        int distance = getDistance(vessel, destination);
        vessel.setMovePoints(vessel.getMovePoints()-distance);
        map[start.axisX()][start.axisY()].setUnit(null);
        map [destination.axisX()][destination.axisY()].setUnit(vessel);
    }

    private int getDistance(Vessel vessel, Coordinates destination) {
        int distance;
        if(destination.axisX()- vessel.getCoordinates().axisX()!=0){
            distance = Math.abs(destination.axisX() - vessel.getCoordinates().axisX());
        }else{
            distance = Math.abs(destination.axisY() - vessel.getCoordinates().axisY());
        }
        return distance;
    }

    /*    *//**
     * @param fleet
     *//*
    @Override
    public void restoreVesselsData(Map<String, GameUnit> fleet) {
        fleet.values().stream().filter(unit -> unit instanceof Vessel).map(Vessel.class::cast).forEach(Vessel::newDayState);
    }*/

    /**
     * @param fleet
     */
    @Override
    public void checkVesselsAtDayEnd(Map<String, GameUnit> fleet) {
        fleet.values().stream().filter(unit -> unit instanceof Vessel).map(Vessel.class::cast).forEach(vessel ->{
            vessel.setMovePoints(vessel.getVesselType().getBreeze_move_points());
            vessel.setCurrent_shots(vessel.getVesselType().getShots());
            vessel.setCanMove(true);
            vessel.setCanShoot(true);
            if(vessel.isOnRepair()) repairVessel(vessel);
            if(vessel.isReadyForRepair()){
                vessel.setOnRepair(true);
                vessel.setReadyForRepair(false);
            }
        });
    }


    /**
     * @param vessel
     * @param map
     * @return
     */
    @Override
    public boolean checkIfVesselCanHelp(Vessel vessel, Surface[][] map) {
        Surface surface = map[vessel.getCoordinates().axisX()][vessel.getCoordinates().axisY()];
        if(checkIfSurfaceIsPort(surface)){
            Fortification fortification = surface.getFortification();
            return !vessel.isHelping() && fortification.getCurrent_hit_point() < fortification.getFortificationType().getHit_points() && fortification.isFirstPlayer()==vessel.isFirstPlayer() | checkingPresenceOfEnemyVesselsInPortOfDestroyedFortification(fortification);
        }else {
            return false;
        }
    }

    /**
     * @param unit
     */
    @Override
    public void destroyRepairingVessels(GameUnit unit,Map<String,GameUnit> fleet) {
        Fortification fortification = (Fortification) unit;
        fortification.getPort().stream().filter(surface -> !surface.isEmpty()).forEach(surface -> {
            if(surface.getUnit().isOnRepair()){
                fleet.remove(surface.getUnit().getId(),surface.getUnit());
            }
        });

    }

    private boolean checkingPresenceOfEnemyVesselsInPortOfDestroyedFortification(Fortification fortification){
        boolean first = fortification.getPort().stream().filter(surface -> !surface.isEmpty()).anyMatch(surface -> surface.getUnit().isFirstPlayer());
        boolean second = fortification.getPort().stream().filter(surface -> !surface.isEmpty()).anyMatch(surface -> !surface.getUnit().isFirstPlayer());
        if(first&&second){
            if(fortification.getStateType().equals(StateType.DESTROYED)){
                fortification.getPort().stream().filter(port -> !port.isEmpty()).forEach(port -> {
                    ((Vessel) port.getUnit()).setHelping(false);
                    ((Vessel) port.getUnit()).setReadyToHelp(false);
                });
                fortification.setOnRepair(false);
                fortification.setCurrent_hit_point(0);
            }
            return false;
        }else {
            return true;
        }
    }

    /**
     * Accepts Stack<Vessel> that keeps vessel objects in random order for first player,
     *      Stack<Vessel> that keeps vessel objects in random order for second player, Map<String,GameUnit> to keep game unit objects and Surface [][] that keeps map
     *Void
     * Method calls setVesselsInFort with necessary parameters for each player*/

    private void setUpVesselsForBothPlayers(Stack<Vessel> firstPlayerFleet, Stack<Vessel> secondPlayerFleet, Map<String, GameUnit> fleet, Surface[][] map) {
        putFleetIntoFortificationsPorts(true,map,fleet,firstPlayerFleet);
        putFleetIntoFortificationsPorts(false,map,fleet,secondPlayerFleet);
    }
    /**
     * Accepts int that represents quantity of vessels in fortification of given type, Fortification where vessels will be placed, Surface [][] that keeps map,
     *  Map<String,GameUnit> where vessel object will keep, Stack<Vessel> that keeps Vessel objects in random order
     * Void
     * Method allocates Vessels objects in port field of Surface object and adds vessel object to Map<String,GameUnit> in int quantity according to game rules */

    private void addVesselsIntoFortificationPort(int size, Fortification fortification, Surface [][] map, Map<String,GameUnit> fleet, Stack<Vessel> playersFleet){
        for(int i = 0; i<size; i++){
            Vessel tmp = playersFleet.pop();
            map[fortification.getPort().get(i).getCoordinates().axisX()][fortification.getPort().get(i).getCoordinates().axisY()].setUnit(tmp);
            fleet.put(tmp.getId(),tmp);
        }
    }
    /**
     * Accepts boolean that represents player, Surface [][] that keeps map, Map<String, GameUnit> that keeps Fortification objects and Stack <Vessel> of vessels in random order
     * Void
     * Method filters Map<String,GameUnit> by given player, that according to Fortification type calls setVesselsInPort method with necessary parameters
     * and allocates Vessel objects from Stack<Vessel>*/

    private void putFleetIntoFortificationsPorts(boolean isFirstPlayer, Surface [][] map, Map<String,GameUnit> fleet, Stack<Vessel> vessels){
        fleet.values().stream().filter(unit -> unit.isFirstPlayer()==isFirstPlayer).filter(unit -> unit.getUnitType().equals(UnitType.FORTIFICATION))
                .map(Fortification.class::cast).toList()
                .forEach(fortification -> {
                    switch (fortification.getFortificationType()){
                        case FIRST_LINE_FORT ->
                            addVesselsIntoFortificationPort(4,fortification,map,fleet,vessels);
                        case SECOND_LINE_FORT ->
                            addVesselsIntoFortificationPort(5,fortification,map,fleet,vessels);
                    }
                });
        fleet.values().stream().filter(unit -> unit.isFirstPlayer() == isFirstPlayer).filter(unit -> unit.getUnitType().equals(UnitType.FORTIFICATION))
                .map(Fortification.class::cast).filter(fortification -> fortification.getFortificationType().equals(FortificationType.SECOND_LINE_FORT))
                .filter(fortification -> fortification.getPort().size() > 5).findFirst().
                flatMap(fortification -> fortification.getPort().stream().filter(Surface::isEmpty).findFirst()).ifPresent(surface -> {
                    surface.setUnit(vessels.pop());
                    fleet.put(surface.getUnit().getId(), surface.getUnit());
                });
    }
    /**
     * Accepts ArrayList<Vessel>
     * Returns Stack<Vessel>
     * Method takes an arrayList of vessels and fills a Stack in random order*/

    private Stack<Vessel> shufflePlayersVessels(ArrayList<Vessel> vessels){
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
            case THREE_DECKER_SHIP_OF_LINE -> addNamesToVessels(vessels,MockedData.THREE_DECKER_SHIP_THE_LINE_QNT/2,isFirstPlayer,namesRandomizer.bigBattleshipsNames,vesselType);
            case TWO_DECKER_SHIP_OF_LINE -> addNamesToVessels(vessels,MockedData.TWO_DECKER_SHIP_THE_LINE_QNT/2,isFirstPlayer,namesRandomizer.smallBattleshipsNames,vesselType);
            case FRIGATE -> addNamesToVessels(vessels,MockedData.FRIGATES_QNT/2,isFirstPlayer,namesRandomizer.frigatesNames,vesselType);
            case TENDER -> addNamesToVessels(vessels,MockedData.TENDERS_QNT/2,isFirstPlayer,namesRandomizer.tendersNames,vesselType);
            case BRIG -> addNamesToVessels(vessels,MockedData.BRIGS_QNT/2,isFirstPlayer,namesRandomizer.brigNames,vesselType);
            case GALLEON -> addNamesToVessels(vessels,MockedData.GALLEONS_QNT/2,isFirstPlayer,namesRandomizer.galleonsNames,vesselType);
            case STEAM_FRIGATE -> addNamesToVessels(vessels,MockedData.STEAM_FRIGATE_QNT/2,isFirstPlayer,namesRandomizer.steamFrigateNames,vesselType);
            case BATTERY -> addNamesToVessels(vessels,MockedData.NAVAL_BATTERY_QNT/2,isFirstPlayer,namesRandomizer.navalBatteryNames,vesselType);
            case GALLEY -> addNamesToVessels(vessels,MockedData.GALLEYS_QNT/2,isFirstPlayer,namesRandomizer.galleysName,vesselType);
            case CORVETTE -> addNamesToVessels(vessels,MockedData.STEAM_CORVETTE_QNT/2,isFirstPlayer,namesRandomizer.steamCorvetteNames,vesselType);
            case MONITOR -> addNamesToVessels(vessels,MockedData.MONITOR_QNT/2,isFirstPlayer,namesRandomizer.monitorNames,vesselType);
            case STEAMSHIP -> addNamesToVessels(vessels,MockedData.STEAMSHIP_QNT/2,isFirstPlayer,namesRandomizer.steamshipNames,vesselType);
        }
    }
    /**
     * Accepts ArrayList<Vessel> that must be filled, int quantity of iterations that depends on quantity of vessels that player should have of certain type,
     * boolean that indicates player, Stack<String> that contains names for certain vessel type, VesselType
     * void
     * Method adds to ArrayList<Vessel> new Vessel objects of certain VesselType in given quantity and sets names for it taken from Stack <String> of mocked names*/

    private void addNamesToVessels(ArrayList<Vessel> vessels, int qnt, boolean player, Stack<String> names, VesselType vesselType){
        for (int i = 0; i<qnt; i++){
            vessels.add(new Vessel(player,vesselType,names.pop()));
        }
    }
    private void repairVessel(Vessel vessel){
        vessel.setCurrent_hit_point(vessel.getCurrent_hit_point()+1);
        if(hasUnitReachedMaxHP(vessel)){
            vessel.setCurrent_hit_point(vessel.getVesselType().getHit_points());
            vessel.setOnRepair(false);
        }
    }
    private boolean hasUnitReachedMaxHP(Vessel vessel){
        return vessel.getCurrent_hit_point()>=vessel.getVesselType().getHit_points();
    }
    //TODO test for vessel processor
}
