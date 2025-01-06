package org.game.unit;

import lombok.Getter;
import lombok.Setter;
import org.game.Weather;
import org.game.gui.Coordinates;

@Getter
public class Vessel extends GameUnit {
    private VesselType vesselType;
    @Setter
    private boolean readyToHelp;
    @Setter
    private boolean helping;
    @Setter
    private boolean canMove;
    private int breeze_move_points;
    private int calm_move_points;
    private int storm_move_points;

    public Vessel(boolean isFirstPlayer, Coordinates coordinates) {
        super(isFirstPlayer, coordinates);
    }
    public Vessel(boolean isFirstPlayer, VesselType vesselType){
        super();
        this.setFirstPlayer(isFirstPlayer);
        this.vesselType = vesselType;
        initialState();
    }
    public Vessel(boolean isFirstPlayer, VesselType vesselType,String id){
        super();
        this.setFirstPlayer(isFirstPlayer);
        this.vesselType = vesselType;
        this.setId(id);
        this.setUnitType(UnitType.VESSEL);
        initialState();
    }

    @Override
    void initialState() {
        super.initialState();
        switch (vesselType){
            case THREE_DECKER_SHIP_OF_LINE -> setUp(VesselType.THREE_DECKER_SHIP_OF_LINE);
            case TWO_DECKER_SHIP_OF_LINE -> setUp(VesselType.TWO_DECKER_SHIP_OF_LINE);
            case FRIGATE -> setUp(VesselType.FRIGATE);
            case TENDER -> setUp(VesselType.TENDER);
            case BRIG -> setUp(VesselType.BRIG);
            case GALLEON -> setUp(VesselType.GALLEON);
            case STEAM_FRIGATE -> setUp(VesselType.STEAM_FRIGATE);
            case BATTERY -> setUp(VesselType.BATTERY);
            case GALLEY -> setUp(VesselType.GALLEY);
            case CORVETTE -> setUp(VesselType.CORVETTE);
            case MONITOR -> setUp(VesselType.MONITOR);
            case STEAMSHIP -> setUp(VesselType.STEAMSHIP);
        }
    }
    private void setUp(VesselType type){
        setType(type.getType());
        setBase_hit_points(type.getHit_points());
        setCurrent_hit_point(getBase_hit_points());
        setBase_shots(type.getShots());
        setCurrent_shots(getBase_shots());
        setMovePoints(type.getBreeze_move_points());
        setFire_range(type.getFire_range());
        setMaxMP(type.getBreeze_move_points());
        breeze_move_points = type.getBreeze_move_points();
        calm_move_points = type.getCalm_move_points();
        storm_move_points = type.getStorm_move_points();
        setCanShoot(true);
        setCanMove(true);
    }

    /**
     * @param currentWeather 
     */
    @Override
    public void setCurrentWeather(Weather currentWeather) {
        super.setCurrentWeather(currentWeather);
        switch (currentWeather.wind()){
            case BREEZE -> setMovePoints(vesselType.getBreeze_move_points());
            case CALM -> setMovePoints(vesselType.getCalm_move_points());
            case STORM -> setMovePoints(vesselType.getStorm_move_points());
        }
    }


    /**
     * 
     */
    @Override
    public void newDayState() {
        super.newDayState();
/*        setMovePoints(getVesselType().getBreeze_move_points());
        setCurrent_shots(getVesselType().getShots());
        if(isOnRepair()){
            canMove = false;
            setCanShoot(false);
        }*/
    }
}
