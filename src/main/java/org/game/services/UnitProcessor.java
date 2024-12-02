package org.game.services;

import lombok.Getter;
import org.game.BackToGUIConverter;
import org.game.MapAreaState;
import org.game.State;
import org.game.map.Surface;
import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Getter
public class UnitProcessor implements UnitService{
    private Surface[][] map;
    private ArrayList <Surface> route; // maybe redundant
    private Map <String, GameUnit> fleet = new HashMap<>();
    private int day;

    private final MapService mapProcessor = new MapProcessor();
    private final FortificationService fortificationProcessor = new FortificationProcessor();
    private final VesselProcessor vesselProcessor = new VesselProcessor();



    //NamesRandomizer namesRandomizer = new NamesRandomizer();
    public UnitProcessor() {
        //giveFortNames();
    }


    @Override
    public State initialGameState() {
        day = 1;
        map = mapProcessor.generateStandardMap();
        //fortificationProcessor.setPortLocations(map);
        fortificationProcessor.getStandardFortifications(map,fleet);
        vesselProcessor.getVessels(fleet,map);
        //fleet.values().stream().map(Fortification.class::cast).toList().forEach(u-> System.out.println(u.getPort().size()+" "+u.getCoordinates().toString()));
        return State.builder().mapAreaState(MapAreaState.builder().map(BackToGUIConverter.convertMap(map)).fleet(BackToGUIConverter.convertFleet(fleet)).build()).build();
        //TODO make to gui converter
    }

    @Override
    public State unitSelected(String id) {
        GameUnit unit = fleet.get(id);
        System.out.println(unit.getId());
        System.out.println(unit.getHit_points());
        return null;
    }
}
