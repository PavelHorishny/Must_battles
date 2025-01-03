package org.game.services;

import org.game.Context;
import org.game.EndGame;
import org.game.gui.StateType;
import org.game.map.Surface;
import org.game.mockData.MockedData;
import org.game.mockData.NamesRandomizer;
import org.game.unit.*;

import java.util.*;

public class FortificationProcessor implements FortificationService{

    private final NamesRandomizer namesRandomizer;

    public FortificationProcessor() {
        namesRandomizer = Context.getNameRandomizer();
    }

    /**
     * Accepts Surface multidimensional array and Map <String, GameUnit>
     * void
     * Set random names for Fortifications, update Map*/
    @Override
    public void getStandardFortifications(Surface[][] map, Map<String, GameUnit> fleet) {
        ArrayList <Fortification> tmp = MockedData.STANDARD_FORT_POSITION;
        tmp.forEach(fortification -> map[fortification.getCoordinates().axisX()][fortification.getCoordinates().axisY()].setUnit(fortification));
        setFortificationNames(fleet,tmp);
    }

    /**
     * Method accepts Map and List of Fortification Objects
     * sets a random name to each object
     * fulfill Map with Object's id as a key and Object itself as a value*/
    private void setFortificationNames(Map<String, GameUnit> fleet, List<Fortification> fortifications){
        fortifications.forEach(e->{
            if(e.getFortificationType()== FortificationType.ROYAL_PORT){
                e.setId(namesRandomizer.royalPortNames.pop());
            }else {
                e.setId(namesRandomizer.fortificationsNames.pop());
            }
            fleet.put(e.getId(), e);
        });
    }
    /**
     * Method accepts Surface array and Fortification object
     * switches SurfaceType to PORT
     * adds Surface to port Object's field */
    @Override
    public void setPortLocations(Set<Surface> port, Fortification fortification) {
        port.forEach(surface -> {
            fortification.getPort().add(surface);
            surface.setFortification(fortification);
        });
    }

    private boolean isRoyalPortIsNotEmpty(Fortification fortification){
        return fortification.getPort().stream().anyMatch(surface -> !surface.isEmpty());
    }

    @Override
    public void checkFortificationsAtMoveEnd(Map<String, GameUnit> fleet, boolean player){
        ArrayList<Fortification> set = new ArrayList<>();
        Fortification fort = findPlayersRoyalFort(fleet,player);
        set.add(fort);
        Fortification fort1 = findPlayersRoyalFort(fleet,!player);
        set.add(fort1);
        fort.getPort().forEach(surface -> {
            if(!surface.isEmpty())System.out.println(surface.getUnit().toUnitData().toString());
        });
        set.forEach(System.out::println);
        set.forEach(fortification -> {
            if(!isRoyalPortIsNotEmpty(fortification)){
                fortification.setCapturing(false);
                fortification.setCurrent_hit_point(fortification.getFortificationType().getHit_points());
            }else {
                fortification.getPort().forEach(surface -> {
                    if(!surface.isEmpty()){
                        surface.getUnit().setCanShoot(false);
                    }
                });
            }
        });
    }

    /**
     * @param fleet
     * @param endGame
     */
    @Override
    public void checkFortificationsAtDayEnd(Map<String, GameUnit> fleet, EndGame endGame) {
        fleet.values().stream().filter(unit -> unit.getUnitType().equals(UnitType.FORTIFICATION)).map(Fortification.class::cast).forEach(fortification -> {
            fortification.setCurrent_shots(fortification.getFortificationType().getShots());
            fortification.setCanShoot(true);
            if(fortification.getFortificationType().equals(FortificationType.ROYAL_PORT)){
                if(fortification.isCapturing()){
                    fortification.setCurrent_hit_point(fortification.getCurrent_hit_point()-1);
                    if(fortification.getCurrent_hit_point()<=0){
                        endGame.setEndGame(true);
                        endGame.setMessage(testString(fortification));
                    }
                }
                if(!fortification.isCapturing()&&isRoyalPortIsNotEmpty(fortification)){
                    fortification.setCapturing(true);
                }
            }else {
                if(fortification.isOnRepair()){
                repairFortification(fortification);
                    }
                if(fortification.isReadyForRepair()){
                    fortification.setOnRepair(true);
                    fortification.setReadyForRepair(false);
                    fortification.getPort().stream().filter(surface -> !surface.isEmpty()).toList().forEach(surface -> {
                        Vessel vessel = (Vessel) surface.getUnit();
                        if(vessel.isReadyToHelp()) ((Vessel) surface.getUnit()).setHelping(true);
                    });
                }
            }
        });
    }

    /**
     * @param fleet
     */
    @Override
    public void restoreFortificationsData(Map<String, GameUnit> fleet) {
        fleet.values().stream().filter(unit -> unit instanceof Fortification).map(Fortification.class::cast).forEach(Fortification::newDayState);
    }

    /**
     * @param fortification
     */
    @Override
    public boolean checkIfFortificationCanBeRepaired(Fortification fortification/*, Surface[][] map*/) {
        if(checkingPresenceOfEnemyVessels(fortification)) {
            int qnt = 0;
            for (int i = 0; i < fortification.getPort().size(); i++) {
                if (!fortification.getPort().get(i).isEmpty()) {
                    Vessel vessel = (Vessel) fortification.getPort().get(i).getUnit();
                    if (vessel.isReadyToHelp()) {
                        qnt++;
                    }
                }

            }
            return qnt >= 3;
        }else {
            return false;
        }

    }
    private boolean checkingPresenceOfEnemyVessels(Fortification fortification){
        boolean first = fortification.getPort().stream().filter(surface -> !surface.isEmpty()).anyMatch(surface -> surface.getUnit().isFirstPlayer());
        boolean second = fortification.getPort().stream().filter(surface -> !surface.isEmpty()).anyMatch(surface -> !surface.getUnit().isFirstPlayer());
        if(first&&second){
            if(fortification.getStateType().equals(StateType.DESTROYED)){
                fortification.getPort().stream().filter(surface -> !surface.isEmpty()).forEach(surface -> {
                    Vessel vessel = (Vessel) surface.getUnit();
                    vessel.setHelping(false);
                    vessel.setReadyToHelp(false);
                });
                fortification.setOnRepair(false);
                fortification.setCurrent_hit_point(0);
            }
            return false;
        }else {
            return true;
        }
    }

    private Fortification findPlayersRoyalFort(Map<String, GameUnit> fleet, boolean player) {
        return fleet.values().stream().filter(unit -> unit.getUnitType().equals(UnitType.FORTIFICATION)).map(Fortification.class::cast).toList()
                .stream().filter(fortification -> fortification.isFirstPlayer()==player).toList()
                .stream().filter(fortification -> fortification.getFortificationType().equals(FortificationType.ROYAL_PORT))
                .findFirst().orElse(null);
    }

    private String testString(Fortification fortification){
        if(fortification.isFirstPlayer()){
            return "Second player wins";
        }else {
            return  "First player wins";
        }
    }
    private void repairFortification(Fortification fortification){
        fortification.setCurrent_hit_point(fortification.getCurrent_hit_point()+2);
        if(fortification.getStateType().equals(StateType.DESTROYED)){
            if(fortification.getCurrent_hit_point()>=fortification.getFortificationType().getHit_points()){
                boolean newPlayer = fortification.getPort().stream().filter(surface -> !surface.isEmpty()).findFirst().get().getUnit().isFirstPlayer();
                fortification.setFirstPlayer(newPlayer);
                fortification.setStateType(StateType.PASSIVE);
                fortification.setCurrent_hit_point(fortification.getFortificationType().getHit_points());
                fortification.setOnRepair(false);
            }
        }
        if(fortification.getCurrent_hit_point()>=fortification.getFortificationType().getHit_points()){
            fortification.setCurrent_hit_point(fortification.getFortificationType().getHit_points());
            fortification.setOnRepair(false);
        }
    }
}
