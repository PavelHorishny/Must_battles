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

public class VesselProcessor implements VesselService/*,Repairable,TakingPartAtRepair*/{
    NamesRandomizer namesRandomizer = Context.getNameRandomizer();
    WeatherService weatherProcessor = new WeatherProcessor();

    FortificationService fortificationService;
    MapService mapService = new MapProcessor();
    ArrayList <Vessel> firstPlayerVessels = new ArrayList<>();

    public VesselProcessor(){
    }

    @Override
    public List<Vessel> getListOfAllVessels(Map<String,GameUnit> fleet) {
        return fleet.values().stream().filter(e->e.getUnitType().equals(UnitType.VESSEL)).map(Vessel.class::cast).toList();
    }

    private boolean checkIfSurfaceIsPort(Surface surface){
        return surface.getType().equals(SurfaceType.PORT);
    }

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


    @Override
    public void setAllVessels(Map<String, GameUnit> fleet, Surface[][] map) {
        __setVesselsToBothPlayers(__shufflePlayersVessels(__generateVesselsWithNames(true)), __shufflePlayersVessels(__generateVesselsWithNames(false)),fleet,map);
    }

    @Override
    public void setFortificationService(FortificationService service) {
        fortificationService=service;
    }

    @Override
    public boolean checkIfCanMove(Vessel vessel) {
        return !vessel.isOnRepair() && !vessel.isHelping();

    }

    @Override
    public boolean checkIfCanShoot(Vessel vessel, Surface[][] map) {
        Surface surface = map[vessel.getCoordinates().axisX()][vessel.getCoordinates().axisY()];
        if(mapService.checkIfPositionIsPort(map,surface.getCoordinates())){
            if(fortificationService.checkIfPortIsRoyal(surface.getFortification())&&vessel.isFirstPlayer()!=surface.getFortification().isFirstPlayer()){
                return false;
            }else return !vessel.isOnRepair() && !vessel.isHelping();
        }else {
            return true;
        }
    }

    private boolean checkingPresenceOfEnemyVesselsInPortOfDestroyedFortification(Fortification fortification){
        boolean first = fortification.getPort().stream().filter(surface -> !surface.isEmpty()).anyMatch(surface -> surface.getUnit().isFirstPlayer());
        boolean second = fortification.getPort().stream().filter(surface -> !surface.isEmpty()).anyMatch(surface -> !surface.getUnit().isFirstPlayer());
        if(first&&second){
            if(fortification.getStateType().equals(StateType.DESTROYED)){
                fortification.getPort().stream().filter(port -> !port.isEmpty()).forEach(port -> {
                    port.getUnit().setHelping(false);
                    port.getUnit().setReadyToHelp(false);
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

    private void __setVesselsToBothPlayers(Stack<Vessel> firstPlayerFleet, Stack<Vessel> secondPlayerFleet, Map<String, GameUnit> fleet, Surface[][] map) {
        __putFleetIntoFortificationsPorts(true,map,fleet,firstPlayerFleet);
        __putFleetIntoFortificationsPorts(false,map,fleet,secondPlayerFleet);
    }
    /**
     * Accepts int that represents quantity of vessels in fortification of given type, Fortification where vessels will be placed, Surface [][] that keeps map,
     *  Map<String,GameUnit> where vessel object will keep, Stack<Vessel> that keeps Vessel objects in random order
     * Void
     * Method allocates Vessels objects in port field of Surface object and adds vessel object to Map<String,GameUnit> in int quantity according to game rules */

    private void __addVesselsIntoFortificationPort(int size, Fortification fortification, Surface [][] map, Map<String,GameUnit> fleet, Stack<Vessel> playersFleet){
        for(int i = 0; i<size; i++){
            Vessel tmp = playersFleet.pop();
            tmp.setCoordinates(fortification.getPort().get(i).getCoordinates());
            mapService.addUnit(tmp,map);
            __addVesselToFleet(fleet,tmp);
        }
    }
    private void __addVesselToFleet(Map<String,GameUnit> fleet, Vessel vessel){
        fleet.put(vessel.getId(),vessel);
    }
    /**
     * Accepts boolean that represents player, Surface [][] that keeps map, Map<String, GameUnit> that keeps Fortification objects and Stack <Vessel> of vessels in random order
     * Void
     * Method filters Map<String,GameUnit> by given player, that according to Fortification type calls setVesselsInPort method with necessary parameters
     * and allocates Vessel objects from Stack<Vessel>*/

    private void __putFleetIntoFortificationsPorts(boolean isFirstPlayer, Surface [][] map, Map<String,GameUnit> fleet, Stack<Vessel> vessels){
        fortificationService.getFortificationsByType(fortificationService.getFortificationsOfPlayer(fleet,isFirstPlayer),FortificationType.FIRST_LINE_FORT)
                .forEach(fortification -> __addVesselsIntoFortificationPort(4,fortification,map,fleet,vessels));
        fortificationService.getFortificationsByType(fortificationService.getFortificationsOfPlayer(fleet,isFirstPlayer),FortificationType.SECOND_LINE_FORT)
                        .forEach(fortification -> __addVesselsIntoFortificationPort(5,fortification,map,fleet,vessels));

        fortificationService.getFortWIthBigPort(fortificationService.getFortificationsOfPlayer(fleet,isFirstPlayer)).getPort().stream().filter(Surface::isEmpty).findFirst().ifPresent(surface -> {
            Vessel vessel = vessels.pop();
            vessel.setCoordinates(surface.getCoordinates());
            mapService.addUnit(vessel,map);
            __addVesselToFleet(fleet,vessel);
        });

    }
    /**
     * Accepts ArrayList<Vessel>
     * Returns Stack<Vessel>
     * Method takes an arrayList of vessels and fills a Stack in random order*/

    private Stack<Vessel> __shufflePlayersVessels(ArrayList<Vessel> vessels){
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

    private ArrayList<Vessel> __generateVesselsWithNames(boolean isFirstPlayer){
        ArrayList<Vessel> vessels = new ArrayList<>();
        Arrays.stream(VesselType.vesselTypes).forEach(vesselType -> __makeVesselsByType(vesselType,vessels,isFirstPlayer));
        return vessels;
    }
    /**
     * Accepts VesselType, ArrayList<Vessel> that will be modified,boolean that indicates player
     * Void
     * Method calls addVesselsWithNames method for given VesselType, and passes necessary parameters for given VesselType*/

    private void __makeVesselsByType(VesselType vesselType, ArrayList<Vessel> vessels, boolean isFirstPlayer){
        switch (vesselType){
            case THREE_DECKER_SHIP_OF_LINE -> __addNamesToVessels(vessels,MockedData.THREE_DECKER_SHIP_THE_LINE_QNT/2,isFirstPlayer,namesRandomizer.bigBattleshipsNames,vesselType);
            case TWO_DECKER_SHIP_OF_LINE -> __addNamesToVessels(vessels,MockedData.TWO_DECKER_SHIP_THE_LINE_QNT/2,isFirstPlayer,namesRandomizer.smallBattleshipsNames,vesselType);
            case FRIGATE -> __addNamesToVessels(vessels,MockedData.FRIGATES_QNT/2,isFirstPlayer,namesRandomizer.frigatesNames,vesselType);
            case TENDER -> __addNamesToVessels(vessels,MockedData.TENDERS_QNT/2,isFirstPlayer,namesRandomizer.tendersNames,vesselType);
            case BRIG -> __addNamesToVessels(vessels,MockedData.BRIGS_QNT/2,isFirstPlayer,namesRandomizer.brigNames,vesselType);
            case GALLEON -> __addNamesToVessels(vessels,MockedData.GALLEONS_QNT/2,isFirstPlayer,namesRandomizer.galleonsNames,vesselType);
            case STEAM_FRIGATE -> __addNamesToVessels(vessels,MockedData.STEAM_FRIGATE_QNT/2,isFirstPlayer,namesRandomizer.steamFrigateNames,vesselType);
            case BATTERY -> __addNamesToVessels(vessels,MockedData.NAVAL_BATTERY_QNT/2,isFirstPlayer,namesRandomizer.navalBatteryNames,vesselType);
            case GALLEY -> __addNamesToVessels(vessels,MockedData.GALLEYS_QNT/2,isFirstPlayer,namesRandomizer.galleysName,vesselType);
            case CORVETTE -> __addNamesToVessels(vessels,MockedData.STEAM_CORVETTE_QNT/2,isFirstPlayer,namesRandomizer.steamCorvetteNames,vesselType);
            case MONITOR -> __addNamesToVessels(vessels,MockedData.MONITOR_QNT/2,isFirstPlayer,namesRandomizer.monitorNames,vesselType);
            case STEAMSHIP -> __addNamesToVessels(vessels,MockedData.STEAMSHIP_QNT/2,isFirstPlayer,namesRandomizer.steamshipNames,vesselType);
        }
    }
    /**
     * Accepts ArrayList<Vessel> that must be filled, int quantity of iterations that depends on quantity of vessels that player should have of certain type,
     * boolean that indicates player, Stack<String> that contains names for certain vessel type, VesselType
     * void
     * Method adds to ArrayList<Vessel> new Vessel objects of certain VesselType in given quantity and sets names for it taken from Stack <String> of mocked names*/

    private void __addNamesToVessels(ArrayList<Vessel> vessels, int qnt, boolean player, Stack<String> names, VesselType vesselType){
        for (int i = 0; i<qnt; i++){
            vessels.add(new Vessel(player,vesselType,names.pop()));
        }
    }

    private boolean hasUnitReachedMaxHP(Vessel vessel){
        return vessel.getCurrent_hit_point()>=vessel.getVesselType().getHit_points();
    }

    @Override
    public boolean isUnitCanBeRepaired(GameUnit gameUnit, Surface [] [] map) {
        Vessel vessel = (Vessel) gameUnit;
        if(vessel.getCurrent_hit_point()<vessel.getVesselType().getHit_points()){
            if(mapService.checkIfPositionIsPort(map,vessel.getCoordinates())){
                Fortification fortification = map[vessel.getCoordinates().axisX()][vessel.getCoordinates().axisY()].getFortification();
                return fortification.isFirstPlayer() == vessel.isFirstPlayer() && !fortification.getStateType().equals(StateType.DESTROYED);
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public void repairUnit(GameUnit gameUnit) {
        Vessel vessel = (Vessel) gameUnit;
        vessel.setCurrent_hit_point(vessel.getCurrent_hit_point()+1);
        if(hasUnitReachedMaxHP(vessel)){
            vessel.setCurrent_hit_point(vessel.getVesselType().getHit_points());
            vessel.setOnRepair(false);
        }
    }

    @Override
    public boolean isUnitReadyToTakePartAtRepair(GameUnit gameUnit, Surface[][] map) {
        Vessel vessel = (Vessel) gameUnit;
        Surface surface = map[vessel.getCoordinates().axisX()][vessel.getCoordinates().axisY()];
        if(checkIfSurfaceIsPort(surface)){
            Fortification fortification = surface.getFortification();
            return !vessel.isHelping() && fortification.getCurrent_hit_point() < fortification.getFortificationType().getHit_points() && fortification.isFirstPlayer()==vessel.isFirstPlayer() | checkingPresenceOfEnemyVesselsInPortOfDestroyedFortification(fortification);
        }else {
            return false;
        }
    }

    @Override
    public void whenUnitDestroyed(GameUnit unit, Map<String, GameUnit> fleet, Surface[][] map) {
        Vessel vessel = (Vessel) unit;
        if(vessel.isOnRepair()){
            mapService.removeUnit(vessel,map);
            removeVesselFromFleet(vessel,fleet);
        }else if (vessel.getCurrent_hit_point()<=0){
            mapService.removeUnit(vessel,map);
            removeVesselFromFleet(vessel,fleet);
        }
    }

    private void removeVesselFromFleet(Vessel vessel, Map<String,GameUnit> fleet){
        fleet.remove(vessel.getId(),vessel);
    }
    //TODO test for vessel processor
}
