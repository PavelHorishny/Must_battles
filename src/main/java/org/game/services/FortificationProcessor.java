package org.game.services;

import org.game.Context;
import org.game.gui.StateType;
import org.game.map.Surface;
import org.game.mockData.MockedData;
import org.game.mockData.NamesRandomizer;
import org.game.unit.*;

import java.util.*;

public class FortificationProcessor implements FortificationService {

    private final NamesRandomizer namesRandomizer;
    MapService mapService = new MapProcessor();
    VesselService vesselService;

    public FortificationProcessor() {
        namesRandomizer = Context.getNameRandomizer();
    }


    private List<Fortification> getStandardFortifications2(){
        return MockedData.STANDARD_FORT_POSITION;
    }
    @Override
    public boolean isRoyalPortIsNotEmpty(Fortification fortification){
        return fortification.getPort().stream().anyMatch(surface -> !surface.isEmpty());
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
    public List<Fortification> getFortificationsByType(List<Fortification> forts, FortificationType fortificationType) {
        return switch (fortificationType){
            case FIRST_LINE_FORT -> forts.stream().filter(fortification -> fortification.getFortificationType().equals(FortificationType.FIRST_LINE_FORT)).toList();
            case SECOND_LINE_FORT -> forts.stream().filter(fortification -> fortification.getFortificationType().equals(FortificationType.SECOND_LINE_FORT)).toList();
            case ROYAL_PORT -> forts.stream().filter(fortification -> fortification.getFortificationType().equals(FortificationType.ROYAL_PORT)).toList();
        };
    }

    @Override
    public Fortification getFortWIthBigPort(List<Fortification> fortificationsOfPlayer) {
        return fortificationsOfPlayer.stream().filter(fortification -> fortification.getFortificationType().equals(FortificationType.SECOND_LINE_FORT))
                .filter(fortification -> fortification.getPort().size() > 5).findFirst().orElse(null);
    }

    @Override
    public void setVesselService(VesselService service) {
        vesselService=service;
        //TODO may use @Setter
    }

    @Override
    public List<Fortification> getAllFortifications(Map<String, GameUnit> fleet) {
        return fleet.values().stream().filter(gameUnit -> gameUnit.getUnitType().equals(UnitType.FORTIFICATION)).map(Fortification.class::cast).toList();
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

@Override
public String testString(Fortification fortification){
        if(fortification.isFirstPlayer()){
            return "Second player wins";
        }else {
            return  "First player wins";
        }
        //TODO rename
    }

    @Override
    public boolean checkIfPortIsRoyal(Fortification fortification) {
        return fortification.getFortificationType().equals(FortificationType.ROYAL_PORT);
    }

    @Override
    public boolean checkIfCanShoot(Fortification fortification) {
        return !fortification.isOnRepair();
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

    @Override
    public void repairUnit(GameUnit gameUnit) {
    Fortification fortification = (Fortification) gameUnit;
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
    public void setRepairableStates(boolean state, GameUnit selected) {
        Fortification fortification = (Fortification) selected;
        if(state){
            fortification.setReadyForRepair(true);
        }else{
            if(fortification.isOnRepair()) {
                fortification.setOnRepair(false);
                fortification.setReadyForRepair(false);
                fortification.setCanShoot(fortification.getCurrent_shots()>0);
                fortification.getPort().stream().filter(surface -> !surface.isEmpty()).forEach(surface -> {
                    if(surface.getUnit().isHelping()){
                        vesselService.setHelpInRepairStates(surface.getUnit(),false);
                    }
                });
            }
        }
    }

    @Override
    public void setUnitOnRepair(GameUnit unit) {
        unit.setOnRepair(true);
        unit.setReadyForRepair(false);
        unit.setCanShoot(false);
    }

    @Override
    public void restoreRoyalPort(Fortification fortification) {
        fortification.setCapturing(false);
        fortification.setCurrent_hit_point(fortification.getFortificationType().getHit_points());
    }

    @Override
    public void whenUnitDestroyed(GameUnit unit, Map<String, GameUnit> fleet, Surface[][] map) {
        Fortification fortification = (Fortification) unit;
        fortification.setStateType(StateType.DESTROYED);
        fortification.getPort().stream().filter(surface -> !surface.isEmpty()).forEach(surface -> vesselService.whenUnitDestroyed(surface.getUnit(),fleet,map));
    }
}
