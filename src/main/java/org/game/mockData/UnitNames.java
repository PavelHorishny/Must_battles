package org.game.mockData;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class UnitNames {
    /**
     * Each player has 3 first line forts 6 in total
     * Each player has 3 second line forts 6 in total
     * Each player has 1 royal port 2 in total
     * Each player has 2 Big battleships 4 in total
     * Each player has 4 Small battleships 8 in total
     * Each player has 3 Frigates 6 in total
     * Each player has 2 Tenders 4 in total
     * Each player has 5 Brigs 10 in total
     * Each player has 3 Galleons 6 in total
     * Each player has 1 Steam frigate 2 in total
     * Each player has 1 Naval battery 2 in total
     * Each player has 4 Galleys 8 in total;
     * Each player has 1 Steam corvette 2 in total
     * Each player has 1 Monitor 2 in total
     * Each player has 1 Steamship 2 in total
     * */

    public final List<String> fortificationsNames = new ArrayList<String>(){{
        add("Fort Alexander");
        add("Fort Constantin");
        add("Fort Denison");
        add("Fort Drum");
        add("Fort Jefferson");
        add("Fort Louvois");
        add("Fort Sumter");
        add("Fort de Bertheaume");
        add("St. Helen's Fort");
        add("Fort Mahon");
        add("Fort National");
        add("Stack Rock Fort");
    }};

    public final List<String> royalPortsName = new ArrayList<>(){{
        add("Horse Sand Fort");
        add("Fort Gaines");
        add("Fort McHenry");
        add("Fort Delaware");
    }};

    public final List<String> threeDeckerShipOfLineNames = new ArrayList<>(){{
        add("HMS Britannia");
        add("HMS Prince");
        add("HMS Royal Sovereign");
        add("HMS Queen");
        add("HMS Victory");
    }};

    public final List<String> twoDeckerShipsOfTheLineNames = new ArrayList<>(){{
        add("Nottingham");
        add("Mary");
        add("York");
        add("Medway");
        add("Pembroke");
        add("Exeter");
        add("Windsor");
        add("Kingstone");
        add("Sunderland");
    }};

    public final List<String> frigateNames = new ArrayList<>(){{
        add("Jules");
        add("Sirene");
        add("Flamand");
        add("Cheval Marin");
        add("Le Havre");
        add("Provencal");
        add("Dunkerquios");
    }};

    public final List<String> tenderNames = new ArrayList<>(){{
        add("Andrey");
        add("Dionisy");
        add("Cherepaha");
        add("Speshny");
    }};

    public final List<String> brigNames = new ArrayList<>(){{
        add("Meteor");
        add("Mastiff");
        add("Manly");
        add("Rattler");
        add("Staunch");
        add("Hecate");
        add("Gallant");
        add("Griper");
        add("Furnace");
        add("Defender");
    }};

    public final List<String> galleonNames = new ArrayList<>(){{
        add("Trinidad");
        add("San Salvador");
        add("Manuela");
        add("La Lavia");
        add("Santa Ana");
        add("Santa Luzia");
    }};

    public final List <String> steamFrigateNames = new ArrayList<>(){{
        add("Roanoke");
        add("Niagara");
    }};

    public final List <String> navalBatteryNames = new ArrayList<>(){{
        add("Kremlin");
        add("Pervenets");
    }};

    public final List <String> galleyNames = new ArrayList<>(){{
        add("Alarm");
        add("Arbuthnot");
        add("Comet");
        add("Pigot");
        add("Viper");
        add("La Reale");
        add("Corona Ottomana");
        add("Lupa");
    }};

    public final List <String> steamCorvetteNames = new ArrayList<>(){{
        add("La Dragon");
        add("Duplex");
    }};

    public final List <String> monitorNames = new ArrayList<>(){{
        add("Latnik");
        add("Canonicus");
    }};

    public final List <String> steamshipNames = new ArrayList<>(){{
        add("Rhynland");
        add("Malabar");
        add("Faith");
        add("Queen Mary");
        add("Explorer");
        add("Escambia");
        add("Lady Wicklow");
        add("Kyarra");
        add("Peleus");
        add("Jeddah");
        add("Delhi");
        add("Hibernia");
    }};
}
