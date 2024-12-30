package org.game.services;

import org.game.Context;
import org.game.EndGame;
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

/*    @Override
    public void generateFortification(Map<String, GameUnit> fleet) {
        setFortificationNames(fleet, MockedData.STANDARD_FORT_POSITION);
        //TODO check if this method redundant
    }*/
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
        port.forEach(surface -> fortification.getPort().add(surface));
    }
/*    @Override
    public void checkFortificationsAtMoveEnd(Map <String,GameUnit> fleet, EndGame endGame){
        fleet.values().stream().filter(unit -> unit.getUnitType().equals(UnitType.FORTIFICATION)).toList().forEach(fort-> checkForCapturing((Fortification)fort,endGame));
    }*/

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
        set.forEach(fortification -> {
            if(!isRoyalPortIsNotEmpty(fortification)){
                fortification.setCapturing(false);
                fortification.setCurrent_hit_point(fortification.getFortificationType().getHit_points());
            }else {
                fortification.getPort().forEach(surface -> {
                    if(!surface.isEmpty()){
                        Vessel vessel = (Vessel) surface.getUnit();
                        vessel.setCanShot(false);
                    }
                });
            }
        });
        System.out.println("isRoyalPortResult: ");
        System.out.println(isRoyalPortIsNotEmpty(fort));
   /*     if(!isRoyalPortIsNotEmpty(fort)){
            fort.setCapturing(false);
            fort.setCurrent_hit_point(fort.getFortificationType().getHit_points());
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+player+" this port is empty");
        }else{
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! IS NOT EMPTY "+player);
        }*/
    }

    /**
     * @param fleet
     * @param endGame
     */
    @Override
    public void checkFortificationsAtDayEnd(Map<String, GameUnit> fleet, EndGame endGame) {
        fleet.values().stream().filter(unit -> unit.getUnitType().equals(UnitType.FORTIFICATION)).map(Fortification.class::cast).forEach(fortification -> {
            if(fortification.getFortificationType().equals(FortificationType.ROYAL_PORT)){
                if(fortification.isCapturing()/*&&isRoyalPortIsNotEmpty(fortification)*/){
                    fortification.setCurrent_hit_point(fortification.getCurrent_hit_point()-1);
                    if(fortification.getCurrent_hit_point()<=0){
                        endGame.setEndGame(true);
                        endGame.setMessage(testString(fortification));
                    }
                }
                if(!fortification.isCapturing()&&isRoyalPortIsNotEmpty(fortification)){
                    fortification.setCapturing(true);
                }
            }
        });
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
}
