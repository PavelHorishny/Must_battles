package org.game.mockData;

import lombok.Getter;

import java.util.List;
import java.util.Stack;

@Getter
public class NamesRandomizer {
    //TODO remake class as a separate service(??)
    private final int ROYAL_PORT_QNT = 2;
    private final int FORTIFICATIONS_QNT = 12;
    private final int BIG_BATTLESHIP_QNT = 4;
    private final int SMALL_BATTLESHIP_QNT = 8;
    private final int FRIGATES_QNT = 6;
    private final int TENDERS_QNT = 4;
    private final int BRIGS_QNT = 10;
    private final int GALLEONS_QNT = 6;
    private final int STEAM_FRIGATE_QNT = 2;
    private final int NAVAL_BATTERY_QNT = 2;
    private final int GALLEYS_QNT = 8;
    private final int STEAM_CORVETTE_QNT = 2;
    private final int MONITOR_QNT = 2;
    private final int STEAMSHIP_QNT = 2;

    public NamesRandomizer() {

        randomNames(MockedData.ROYAL_PORTS_NAME, ROYAL_PORT_QNT, royalPortNames);
        randomNames(MockedData.FORTIFICATIONS_NAMES,FORTIFICATIONS_QNT, fortificationsNames);
        randomNames(MockedData.THREE_DECKER_SHIP_OF_LINE_NAMES,BIG_BATTLESHIP_QNT, bigBattleshipsNames);
        randomNames(MockedData.TWO_DECKER_SHIPS_OF_THE_LINE_NAMES,SMALL_BATTLESHIP_QNT, smallBattleshipsNames);
        randomNames(MockedData.FRIGATE_NAMES,FRIGATES_QNT,frigatesNames);
        randomNames(MockedData.TENDER_NAMES,TENDERS_QNT,tendersNames);
        randomNames(MockedData.BRIG_NAMES,BRIGS_QNT,brigNames);
        randomNames(MockedData.GALLEON_NAMES,GALLEONS_QNT,galleonsNames);
        randomNames(MockedData.STEAM_FRIGATE_NAMES,STEAM_FRIGATE_QNT,steamFrigateNames);
        randomNames(MockedData.NAVAL_BATTERY_NAMES,NAVAL_BATTERY_QNT,navalBatteryNames);
        randomNames(MockedData.GALLEY_NAMES,GALLEYS_QNT,galleysName);
        randomNames(MockedData.STEAM_CORVETTE_NAMES,STEAM_CORVETTE_QNT,steamCorvetteNames);
        randomNames(MockedData.MONITOR_NAMES,MONITOR_QNT,monitorNames);
        randomNames(MockedData.STEAMSHIP_NAMES,STEAMSHIP_QNT,steamshipNames);
    }

    public Stack <String> royalPortNames = new Stack<>();
    public Stack <String> fortificationsNames = new Stack<>();
    public Stack <String> bigBattleshipsNames = new Stack<>();
    public Stack <String> smallBattleshipsNames = new Stack<>();
    public Stack <String> frigatesNames = new Stack<>();
    public Stack <String> tendersNames = new Stack<>();
    public Stack <String> brigNames = new Stack<>();
    public Stack <String> galleonsNames = new Stack<>();
    public Stack <String> steamFrigateNames = new Stack<>();
    public Stack <String> navalBatteryNames = new Stack<>();
    public Stack <String> galleysName = new Stack<>();
    public Stack <String> steamCorvetteNames = new Stack<>();
    public Stack <String> monitorNames = new Stack<>();
    public Stack <String> steamshipNames = new Stack<>();


    private void randomNames(List<String> source, int qnt, Stack<String> target){
        while (target.size()<qnt&& !source.isEmpty()){
            int x = (int) (Math.random()*(source.size()));
            target.push(source.get(x));
            source.remove(x);
        }
    }

}
