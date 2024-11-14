package org.game.mockData;

import lombok.Getter;

import java.util.List;
import java.util.Stack;

@Getter
public class NamesRandomizer {

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
        UnitNames names = new UnitNames();
        randomNames(names.royalPortsName, ROYAL_PORT_QNT, royalPortNames);
        randomNames(names.fortificationsNames,FORTIFICATIONS_QNT, fortificationsNames);
        randomNames(names.threeDeckerShipOfLineNames,BIG_BATTLESHIP_QNT, bigBattleshipsNames);
        randomNames(names.twoDeckerShipsOfTheLineNames,SMALL_BATTLESHIP_QNT, smallBattleshipsNames);
        randomNames(names.frigateNames,FRIGATES_QNT,frigatesNames);
        randomNames(names.tenderNames,TENDERS_QNT,tendersNames);
        randomNames(names.brigNames,BRIGS_QNT,brigNames);
        randomNames(names.galleonNames,GALLEONS_QNT,galleonsNames);
        randomNames(names.steamFrigateNames,STEAM_FRIGATE_QNT,steamFrigateNames);
        randomNames(names.navalBatteryNames,NAVAL_BATTERY_QNT,navalBatteryNames);
        randomNames(names.galleyNames,GALLEYS_QNT,galleysName);
        randomNames(names.steamCorvetteNames,STEAM_CORVETTE_QNT,steamCorvetteNames);
        randomNames(names.monitorNames,MONITOR_QNT,monitorNames);
        randomNames(names.steamshipNames,STEAMSHIP_QNT,steamshipNames);
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


    private void randomNames(List<String> names, int qnt, Stack<String> target){
        while (target.size()<qnt){
            int x = (int) (Math.random()*(names.size()));
            target.push(names.get(x));
            names.remove(x);
        }
    }

}
