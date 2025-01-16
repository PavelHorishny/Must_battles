package org.game.services;

import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.game.CardinalPoint;
import org.game.LogToFile;
import org.game.gui.Coordinates;
import org.game.gui.panels.Message;
import org.game.state.GameState;
import org.game.unit.GameUnit;

import java.util.ArrayList;
import java.util.Arrays;

@Setter
public class LogProcessor implements LogService{
    private static final Logger log = LogManager.getLogger(LogProcessor.class);
    private String message;
    private GameState state;
    LogToFile logger = new LogToFile();

    @Override
    public void setShortMessage(GameState state, String message) {
        state.setLogMessage(message);
        logger.addMassageToLog(message);
    }

    @Override
    public void setLongMessage(String message) {
        logger.addMassageToLog(message);
    }
    @Override
    public void startMessage(){
        state.setLogMessage("Game started");
        logger.addMassageToLog("Game started");
        state.setLog(logger.getAllMessages());
    }

    @Override
    public void selectedMessage(String logMessage){
        state.setLogMessage(logMessage);
        String mes = String.format("%s was selected",logMessage);
        logger.addMassageToLog(mes);
        state.setLog(logger.getAllMessages());
    }
    @Override
    public void addMessage(/*GameState state,*/ Message message, String text){
        switch (message){
            case SELECT -> {
                state.setLogMessage(state.getSelected().toLogMessage());
                String mes = String.format("%s was %sed",state.getSelected().toLogMessage(),String.valueOf(message).toLowerCase());
                logger.addMassageToLog(mes);
                state.setLog(logger.getAllMessages());
            }
            case START -> {
                state.setLogMessage("Game started");
                logger.addMassageToLog("Game started");
                state.setLog(logger.getAllMessages());
            }
            case MOVEMENTS_START,MOVEMENTS_END -> {
                logger.addMassageToLog(text);
                state.setLog(logger.getAllMessages());
            }
            case END -> {
            }
            case REPAIR -> {
            }
            case HELP -> {
            }
            case SHOT -> {
            }
        }
    }
//TODO consider of making logging messages
    @Override
    public void setLog(GameState state) {
        state.setLog(logger.getAllMessages());
    }

    @Override
    public void setMessages(ArrayList<String> messages) {

    }

    @Override
    public void moveMessage(GameUnit gameUnit, Coordinates destination) {
        addMessage(Message.MOVEMENTS_END,String.format("%s moved in %s direction under %s wind",gameUnit.toLogMessage(),getDirection(gameUnit.getCoordinates(),destination),gameUnit.getCurrentWeather().wind()));
    }
    private String getDirection(Coordinates s,Coordinates e){
        /*int x = checkIfNotZero(s.axisX(),e.axisX());
        int y = checkIfNotZero(s.axisY(),e.axisY());
        log.warn("x = {}  y={}", x, y);*/
        CardinalPoint point = Arrays.stream(CardinalPoint.cardinalPoints).filter(cardinalPoint -> cardinalPoint.getValue().equals(new Coordinates(checkIfNotZero(s.axisX(),e.axisX()),checkIfNotZero(s.axisY(),e.axisY())))).findAny().get();
        return point.getDescription();

    }
    private int checkIfNotZero(int a, int b){
        return Integer.compare(b - a, 0);
    }
}
