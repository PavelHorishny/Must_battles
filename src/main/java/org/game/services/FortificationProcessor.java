package org.game.services;

import org.game.Context;
import org.game.EndGame;
import org.game.gui.StateType;
import org.game.map.Surface;
import org.game.mockData.MockedData;
import org.game.mockData.NamesRandomizer;
import org.game.unit.*;

import java.util.*;

public class FortificationProcessor implements FortificationService, Repairable{

    private final NamesRandomizer namesRandomizer;
    MapService mapService = new MapProcessor();

    public FortificationProcessor() {
        namesRandomizer = Context.getNameRandomizer();
    }


    private List<Fortification> getStandardFortifications2(){
        return MockedData.STANDARD_FORT_POSITION;
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


    @Override
    public void setStandardFortifications(Map<String, GameUnit> fleet, Surface[][] map) {
        getStandardFortifications2().forEach(fortification -> {
            mapService.addUnit(fortification,map);
            setFortificationNames2(fortification);
            addFortificationToFleet(fortification,fleet);
            setPortToFortification(fortification,mapService.getPort(fortification.getCoordinates(),map));
        });

    }

    @Override
    public List<Fortification> getFortificationsOfPlayer(Map<String, GameUnit> fleet, boolean isFirstPlayer) {
        return fleet.values().stream().filter(unit -> unit.isFirstPlayer()==isFirstPlayer).filter(unit -> unit.getUnitType().equals(UnitType.FORTIFICATION))
                .map(Fortification.class::cast).toList();
    }

    @Override
    public List<Fortification> getFortificationsByType(List<Fortification> forts, FortificationType firstLineFort) {
        return switch (firstLineFort){
            case FIRST_LINE_FORT -> forts.stream().filter(fortification -> fortification.getFortificationType().equals(FortificationType.FIRST_LINE_FORT)).toList();
            case SECOND_LINE_FORT -> forts.stream().filter(fortification -> fortification.getFortificationType().equals(FortificationType.SECOND_LINE_FORT)).toList();
            case ROYAL_PORT -> null;//????
        };
    }

    @Override
    public Fortification getFortWIthBigPort(List<Fortification> fortificationsOfPlayer) {
        return fortificationsOfPlayer.stream().filter(fortification -> fortification.getFortificationType().equals(FortificationType.SECOND_LINE_FORT))
                .filter(fortification -> fortification.getPort().size() > 5).findFirst().orElse(null);
    }

    private void setPortToFortification(Fortification fortification, Set<Surface> port) {
        port.forEach(surface -> fortification.getPort().add(surface));
        fortification.getPort().forEach(surface -> surface.setFortification(fortification));
    }

    private void addFortificationToFleet(Fortification fortification, Map<String, GameUnit> fleet) {
        fleet.put(fortification.getId(),fortification);
    }

    private void setFortificationNames2(Fortification fortification) {
        if(fortification.getFortificationType()== FortificationType.ROYAL_PORT){
            fortification.setId(namesRandomizer.royalPortNames.pop());
        }else {
            fortification.setId(namesRandomizer.fortificationsNames.pop());
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

    @Override
    public boolean isUnitCanBeRepaired(GameUnit gameUnit, Surface[][] map) {
        Fortification fortification = (Fortification) gameUnit;
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
}
