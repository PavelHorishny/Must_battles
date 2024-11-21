package org.game.mockData;

import org.game.gui.Coordinates;
import org.game.map.Surface;
import org.game.map.SurfaceType;
import org.game.unit.Fortification;
import org.game.unit.FortificationType;

import java.util.ArrayList;
import java.util.List;

public class MockedData {
    /**
     * Fortifications for standard map*/

    public static final ArrayList<Fortification> STANDARD_FORT_POSITION = new ArrayList<>(){{
        add(new Fortification(new Coordinates(11,5), FortificationType.FIRST_LINE_FORT,true));
        add(new Fortification(new Coordinates(8,15),FortificationType.FIRST_LINE_FORT,true));
        add(new Fortification(new Coordinates(10,28),FortificationType.FIRST_LINE_FORT,true));
        add(new Fortification(new Coordinates(4,7),FortificationType.SECOND_LINE_FORT,true));
        add(new Fortification(new Coordinates(5,21),FortificationType.SECOND_LINE_FORT,true));
        add(new Fortification(new Coordinates(7,23),FortificationType.SECOND_LINE_FORT,true));
        add(new Fortification(new Coordinates(0,26),FortificationType.ROYAL_PORT,true));
        add(new Fortification(new Coordinates(22,3), FortificationType.FIRST_LINE_FORT,false));
        add(new Fortification(new Coordinates(23,25),FortificationType.FIRST_LINE_FORT,false));
        add(new Fortification(new Coordinates(24,16),FortificationType.FIRST_LINE_FORT,false));
        add(new Fortification(new Coordinates(27,6),FortificationType.SECOND_LINE_FORT,false));
        add(new Fortification(new Coordinates(30,19),FortificationType.SECOND_LINE_FORT,false));
        add(new Fortification(new Coordinates(32,12),FortificationType.SECOND_LINE_FORT,false));
        add(new Fortification(new Coordinates(33,7),FortificationType.ROYAL_PORT,false));
    }};

    /**
     * Surface objects list for standard map*/

    public static final Surface[] LAND = new Surface[]{
            new Surface(new Coordinates(0, 22), SurfaceType.LAND),
            new Surface(new Coordinates(0, 21), SurfaceType.LAND),
            new Surface(new Coordinates(0, 23), SurfaceType.LAND),
            new Surface(new Coordinates(0, 24), SurfaceType.LAND),
            new Surface(new Coordinates(0, 25), SurfaceType.LAND),
            new Surface(new Coordinates(0, 26), SurfaceType.LAND),
            new Surface(new Coordinates(0, 27), SurfaceType.LAND),
            new Surface(new Coordinates(0, 28), SurfaceType.LAND),
            new Surface(new Coordinates(0, 29), SurfaceType.LAND),
            new Surface(new Coordinates(0, 30), SurfaceType.LAND),
            new Surface(new Coordinates(0, 31), SurfaceType.LAND),
            new Surface(new Coordinates(1, 28), SurfaceType.LAND),
            new Surface(new Coordinates(1, 29), SurfaceType.LAND),
            new Surface(new Coordinates(1, 30), SurfaceType.LAND),
            new Surface(new Coordinates(1, 31), SurfaceType.LAND),
            new Surface(new Coordinates(2, 7), SurfaceType.LAND),
            new Surface(new Coordinates(2, 8), SurfaceType.LAND),
            new Surface(new Coordinates(2, 31), SurfaceType.LAND),
            new Surface(new Coordinates(3, 0), SurfaceType.LAND),
            //new Surface(new Coordinates(3, 6), SurfaceType.LAND),
            new Surface(new Coordinates(3, 7), SurfaceType.LAND),
            new Surface(new Coordinates(3, 8), SurfaceType.LAND),
            new Surface(new Coordinates(3, 9), SurfaceType.LAND),
            new Surface(new Coordinates(4, 0), SurfaceType.LAND),
            new Surface(new Coordinates(4, 7), SurfaceType.LAND),
            new Surface(new Coordinates(4, 8), SurfaceType.LAND),
            new Surface(new Coordinates(4, 22), SurfaceType.LAND),
            new Surface(new Coordinates(4, 23), SurfaceType.LAND),
            new Surface(new Coordinates(4, 24), SurfaceType.LAND),
            new Surface(new Coordinates(5, 21), SurfaceType.LAND),
            new Surface(new Coordinates(5, 22), SurfaceType.LAND),
            new Surface(new Coordinates(5, 23), SurfaceType.LAND),
            new Surface(new Coordinates(5, 24), SurfaceType.LAND),
            new Surface(new Coordinates(5, 31), SurfaceType.LAND),
            new Surface(new Coordinates(6, 12), SurfaceType.LAND),
            new Surface(new Coordinates(6, 13), SurfaceType.LAND),
            new Surface(new Coordinates(6, 21), SurfaceType.LAND),
            new Surface(new Coordinates(6, 22), SurfaceType.LAND),
            new Surface(new Coordinates(6, 23), SurfaceType.LAND),
            new Surface(new Coordinates(6, 24), SurfaceType.LAND),
            new Surface(new Coordinates(6, 30), SurfaceType.LAND),
            new Surface(new Coordinates(6, 31), SurfaceType.LAND),
            new Surface(new Coordinates(7, 12), SurfaceType.LAND),
            new Surface(new Coordinates(7, 13), SurfaceType.LAND),
            new Surface(new Coordinates(7, 14), SurfaceType.LAND),
            new Surface(new Coordinates(7, 15), SurfaceType.LAND),
            new Surface(new Coordinates(7, 23), SurfaceType.LAND),
            new Surface(new Coordinates(7, 24), SurfaceType.LAND),
            new Surface(new Coordinates(7, 29), SurfaceType.LAND),
            new Surface(new Coordinates(7, 30), SurfaceType.LAND),
            new Surface(new Coordinates(7, 31), SurfaceType.LAND),
            new Surface(new Coordinates(8, 10), SurfaceType.LAND),
            new Surface(new Coordinates(8, 11), SurfaceType.LAND),
            new Surface(new Coordinates(8, 12), SurfaceType.LAND),
            new Surface(new Coordinates(8, 13), SurfaceType.LAND),
            new Surface(new Coordinates(8, 14), SurfaceType.LAND),
            new Surface(new Coordinates(8, 15), SurfaceType.LAND),
            new Surface(new Coordinates(8, 29), SurfaceType.LAND),
            new Surface(new Coordinates(8, 30), SurfaceType.LAND),
            new Surface(new Coordinates(8, 31), SurfaceType.LAND),
            new Surface(new Coordinates(9, 7), SurfaceType.LAND),
            new Surface(new Coordinates(9, 8), SurfaceType.LAND),
            new Surface(new Coordinates(9, 9), SurfaceType.LAND),
            new Surface(new Coordinates(9, 10), SurfaceType.LAND),
            new Surface(new Coordinates(9, 11), SurfaceType.LAND),
            new Surface(new Coordinates(9, 12), SurfaceType.LAND),
            new Surface(new Coordinates(9, 13), SurfaceType.LAND),
            new Surface(new Coordinates(9, 28), SurfaceType.LAND),
            new Surface(new Coordinates(9, 29), SurfaceType.LAND),
            new Surface(new Coordinates(9, 30), SurfaceType.LAND),
            new Surface(new Coordinates(9, 31), SurfaceType.LAND),
            new Surface(new Coordinates(10, 6), SurfaceType.LAND),
            new Surface(new Coordinates(10, 7), SurfaceType.LAND),
            new Surface(new Coordinates(10, 8), SurfaceType.LAND),
            new Surface(new Coordinates(10, 9), SurfaceType.LAND),
            new Surface(new Coordinates(10, 10), SurfaceType.LAND),
            new Surface(new Coordinates(10, 11), SurfaceType.LAND),
            new Surface(new Coordinates(10, 28), SurfaceType.LAND),
            new Surface(new Coordinates(10, 29), SurfaceType.LAND),
            new Surface(new Coordinates(10, 30), SurfaceType.LAND),
            new Surface(new Coordinates(10, 31), SurfaceType.LAND),
            new Surface(new Coordinates(11, 5), SurfaceType.LAND),
            new Surface(new Coordinates(11, 6), SurfaceType.LAND),
            new Surface(new Coordinates(11, 7), SurfaceType.LAND),
            new Surface(new Coordinates(11, 8), SurfaceType.LAND),
            new Surface(new Coordinates(11, 9), SurfaceType.LAND),
            new Surface(new Coordinates(11, 10), SurfaceType.LAND),
            new Surface(new Coordinates(11, 30), SurfaceType.LAND),
            new Surface(new Coordinates(11, 31), SurfaceType.LAND),
            new Surface(new Coordinates(12, 7), SurfaceType.LAND),
            new Surface(new Coordinates(12, 8), SurfaceType.LAND),
            new Surface(new Coordinates(12, 31), SurfaceType.LAND),
            new Surface(new Coordinates(15, 13), SurfaceType.LAND),
            new Surface(new Coordinates(15, 14), SurfaceType.LAND),
            new Surface(new Coordinates(15, 23), SurfaceType.LAND),
            new Surface(new Coordinates(15, 24), SurfaceType.LAND),
            new Surface(new Coordinates(15, 25), SurfaceType.LAND),
            new Surface(new Coordinates(15, 26), SurfaceType.LAND),
            new Surface(new Coordinates(15, 27), SurfaceType.LAND),
            new Surface(new Coordinates(16, 13), SurfaceType.LAND),
            new Surface(new Coordinates(16, 14), SurfaceType.LAND),
            new Surface(new Coordinates(16, 24), SurfaceType.LAND),
            new Surface(new Coordinates(16, 25), SurfaceType.LAND),
            new Surface(new Coordinates(16, 26), SurfaceType.LAND),
            new Surface(new Coordinates(16, 27), SurfaceType.LAND),
            new Surface(new Coordinates(17, 12), SurfaceType.LAND),
            new Surface(new Coordinates(17, 13), SurfaceType.LAND),
            new Surface(new Coordinates(17, 26), SurfaceType.LAND),
            new Surface(new Coordinates(17, 27), SurfaceType.LAND),
            new Surface(new Coordinates(18, 10), SurfaceType.LAND),
            new Surface(new Coordinates(18, 11), SurfaceType.LAND),
            new Surface(new Coordinates(18, 12), SurfaceType.LAND),
            new Surface(new Coordinates(18, 13), SurfaceType.LAND),
            new Surface(new Coordinates(18, 26), SurfaceType.LAND),
            new Surface(new Coordinates(18, 27), SurfaceType.LAND),
            new Surface(new Coordinates(18, 28), SurfaceType.LAND),
            new Surface(new Coordinates(18, 29), SurfaceType.LAND),
            new Surface(new Coordinates(19, 10), SurfaceType.LAND),
            new Surface(new Coordinates(19, 11), SurfaceType.LAND),
            new Surface(new Coordinates(20, 10), SurfaceType.LAND),
            new Surface(new Coordinates(20, 11), SurfaceType.LAND),
            new Surface(new Coordinates(21, 8), SurfaceType.LAND),
            new Surface(new Coordinates(21, 9), SurfaceType.LAND),
            new Surface(new Coordinates(21, 10), SurfaceType.LAND),
            new Surface(new Coordinates(21, 11), SurfaceType.LAND),
            new Surface(new Coordinates(22, 3), SurfaceType.LAND),
            new Surface(new Coordinates(23, 2), SurfaceType.LAND),
            new Surface(new Coordinates(23, 3), SurfaceType.LAND),
            new Surface(new Coordinates(23, 4), SurfaceType.LAND),
            new Surface(new Coordinates(23, 5), SurfaceType.LAND),
            new Surface(new Coordinates(23, 5), SurfaceType.LAND),
            new Surface(new Coordinates(23, 25), SurfaceType.LAND),
            new Surface(new Coordinates(23, 26), SurfaceType.LAND),
            new Surface(new Coordinates(23, 27), SurfaceType.LAND),
            new Surface(new Coordinates(24, 1), SurfaceType.LAND),
            new Surface(new Coordinates(24, 2), SurfaceType.LAND),
            new Surface(new Coordinates(24, 3), SurfaceType.LAND),
            new Surface(new Coordinates(24, 4), SurfaceType.LAND),
            new Surface(new Coordinates(24, 5), SurfaceType.LAND),
            new Surface(new Coordinates(24, 16), SurfaceType.LAND),
            new Surface(new Coordinates(24, 25), SurfaceType.LAND),
            new Surface(new Coordinates(24, 26), SurfaceType.LAND),
            new Surface(new Coordinates(24, 27), SurfaceType.LAND),
            new Surface(new Coordinates(24, 28), SurfaceType.LAND),
            new Surface(new Coordinates(24, 29), SurfaceType.LAND),
            new Surface(new Coordinates(25, 1), SurfaceType.LAND),
            new Surface(new Coordinates(25, 2), SurfaceType.LAND),
            new Surface(new Coordinates(25, 3), SurfaceType.LAND),
            new Surface(new Coordinates(25, 4), SurfaceType.LAND),
            new Surface(new Coordinates(25, 5), SurfaceType.LAND),
            new Surface(new Coordinates(25, 14), SurfaceType.LAND),
            new Surface(new Coordinates(25, 15), SurfaceType.LAND),
            new Surface(new Coordinates(25, 16), SurfaceType.LAND),
            new Surface(new Coordinates(25, 17), SurfaceType.LAND),
            new Surface(new Coordinates(25, 26), SurfaceType.LAND),
            new Surface(new Coordinates(25, 27), SurfaceType.LAND),
            new Surface(new Coordinates(25, 28), SurfaceType.LAND),
            new Surface(new Coordinates(25, 29), SurfaceType.LAND),
            new Surface(new Coordinates(25, 30), SurfaceType.LAND),
            new Surface(new Coordinates(26, 1), SurfaceType.LAND),
            new Surface(new Coordinates(26, 2), SurfaceType.LAND),
            new Surface(new Coordinates(26, 3), SurfaceType.LAND),
            new Surface(new Coordinates(26, 4), SurfaceType.LAND),
            new Surface(new Coordinates(26, 5), SurfaceType.LAND),
            new Surface(new Coordinates(26, 12), SurfaceType.LAND),
            new Surface(new Coordinates(26, 13), SurfaceType.LAND),
            new Surface(new Coordinates(26, 14), SurfaceType.LAND),
            new Surface(new Coordinates(26, 15), SurfaceType.LAND),
            new Surface(new Coordinates(26, 16), SurfaceType.LAND),
            new Surface(new Coordinates(26, 17), SurfaceType.LAND),
            new Surface(new Coordinates(26, 26), SurfaceType.LAND),
            new Surface(new Coordinates(26, 27), SurfaceType.LAND),
            new Surface(new Coordinates(26, 28), SurfaceType.LAND),
            new Surface(new Coordinates(26, 29), SurfaceType.LAND),
            new Surface(new Coordinates(26, 30), SurfaceType.LAND),
            new Surface(new Coordinates(27, 2), SurfaceType.LAND),
            new Surface(new Coordinates(27, 3), SurfaceType.LAND),
            new Surface(new Coordinates(27, 4), SurfaceType.LAND),
            new Surface(new Coordinates(27, 5), SurfaceType.LAND),
            new Surface(new Coordinates(27, 6), SurfaceType.LAND),
            new Surface(new Coordinates(27, 14), SurfaceType.LAND),
            new Surface(new Coordinates(27, 15), SurfaceType.LAND),
            new Surface(new Coordinates(27, 16), SurfaceType.LAND),
            new Surface(new Coordinates(27, 28), SurfaceType.LAND),
            new Surface(new Coordinates(27, 29), SurfaceType.LAND),
            new Surface(new Coordinates(28, 4), SurfaceType.LAND),
            new Surface(new Coordinates(28, 5), SurfaceType.LAND),
            new Surface(new Coordinates(28, 14), SurfaceType.LAND),
            new Surface(new Coordinates(28, 28), SurfaceType.LAND),
            new Surface(new Coordinates(28, 29), SurfaceType.LAND),
            new Surface(new Coordinates(30, 19), SurfaceType.LAND),
            new Surface(new Coordinates(30, 20), SurfaceType.LAND),
            new Surface(new Coordinates(31, 18), SurfaceType.LAND),
            new Surface(new Coordinates(31, 19), SurfaceType.LAND),
            new Surface(new Coordinates(31, 20), SurfaceType.LAND),
            new Surface(new Coordinates(31, 21), SurfaceType.LAND),
            new Surface(new Coordinates(31, 22), SurfaceType.LAND),
            new Surface(new Coordinates(32, 12), SurfaceType.LAND),
            new Surface(new Coordinates(32, 13), SurfaceType.LAND),
            new Surface(new Coordinates(32, 20), SurfaceType.LAND),
            new Surface(new Coordinates(32, 21), SurfaceType.LAND),
            new Surface(new Coordinates(32, 22), SurfaceType.LAND),
            new Surface(new Coordinates(32, 23), SurfaceType.LAND),
            new Surface(new Coordinates(33, 3), SurfaceType.LAND),
            new Surface(new Coordinates(33, 4), SurfaceType.LAND),
            new Surface(new Coordinates(33, 5), SurfaceType.LAND),
            new Surface(new Coordinates(33, 6), SurfaceType.LAND),
            new Surface(new Coordinates(33, 7), SurfaceType.LAND),
            new Surface(new Coordinates(33, 8), SurfaceType.LAND),
            new Surface(new Coordinates(33, 9), SurfaceType.LAND),
            new Surface(new Coordinates(33, 10), SurfaceType.LAND),
            new Surface(new Coordinates(33, 11), SurfaceType.LAND),
            new Surface(new Coordinates(33, 12), SurfaceType.LAND),
            new Surface(new Coordinates(33, 13), SurfaceType.LAND),
            new Surface(new Coordinates(33, 14), SurfaceType.LAND),
            new Surface(new Coordinates(33, 15), SurfaceType.LAND),
            new Surface(new Coordinates(33, 22), SurfaceType.LAND),
            new Surface(new Coordinates(33, 23), SurfaceType.LAND),
    };

    /**
     * List of names for fortifications
     * */

    public static final List<String> FORTIFICATIONS_NAMES = new ArrayList<String>(){{
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

    /**
     * List of royal port names*/

    public static final List<String> ROYAL_PORTS_NAME = new ArrayList<>(){{
        add("Horse Sand Fort");
        add("Fort Gaines");
        add("Fort McHenry");
        add("Fort Delaware");
    }};

    /**
     * List of three decker ship of the lines names*/

    public static final List<String> THREE_DECKER_SHIP_OF_LINE_NAMES = new ArrayList<>(){{
        add("HMS Britannia");
        add("HMS Prince");
        add("HMS Royal Sovereign");
        add("HMS Queen");
        add("HMS Victory");
    }};

    /**
     * List of two decker ship of the lines names*/

    public static final List<String> TWO_DECKER_SHIPS_OF_THE_LINE_NAMES = new ArrayList<>(){{
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

    /**
     * List of frigate names*/

    public static final List<String> FRIGATE_NAMES = new ArrayList<>(){{
        add("Jules");
        add("Sirene");
        add("Flamand");
        add("Cheval Marin");
        add("Le Havre");
        add("Provencal");
        add("Dunkerquios");
    }};

    /**
     * List of tender names*/

    public static final List<String> TENDER_NAMES = new ArrayList<>(){{
        add("Andrey");
        add("Dionisy");
        add("Cherepaha");
        add("Speshny");
    }};

    /**
     * List of brig names*/

    public static final List<String> BRIG_NAMES = new ArrayList<>(){{
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

    /**
     * List of galleon names*/

    public static final List<String> GALLEON_NAMES = new ArrayList<>(){{
        add("Trinidad");
        add("San Salvador");
        add("Manuela");
        add("La Lavia");
        add("Santa Ana");
        add("Santa Luzia");
    }};

    /**
     * List of steam frigate names*/

    public static final List <String> STEAM_FRIGATE_NAMES = new ArrayList<>(){{
        add("Roanoke");
        add("Niagara");
    }};

    /**
     * List of naval battery names*/

    public static final List <String> NAVAL_BATTERY_NAMES = new ArrayList<>(){{
        add("Kremlin");
        add("Pervenets");
    }};

    /**
     * List of galleys names*/

    public static final List <String> GALLEY_NAMES = new ArrayList<>(){{
        add("Alarm");
        add("Arbuthnot");
        add("Comet");
        add("Pigot");
        add("Viper");
        add("La Reale");
        add("Corona Ottomana");
        add("Lupa");
    }};

    /**
     * List of steam corvette names*/

    public static final List <String> STEAM_CORVETTE_NAMES = new ArrayList<>(){{
        add("La Dragon");
        add("Duplex");
    }};

    /**
     * List of monitor names*/

    public static final List <String> MONITOR_NAMES = new ArrayList<>(){{
        add("Latnik");
        add("Canonicus");
    }};

    /**
     * List of steamship names*/

    public static final List <String> STEAMSHIP_NAMES = new ArrayList<>(){{
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
    /**
     * Standard total quantity of unit types*/

    public static final int ROYAL_PORT_QNT = 2;
    public static final int FORTIFICATIONS_QNT = 12;
    public static final int THREE_DECKER_SHIP_THE_LINE_QNT = 4;
    public static final int TWO_DECKER_SHIP_THE_LINE_QNT = 8;
    public static final int FRIGATES_QNT = 6;
    public static final int TENDERS_QNT = 4;
    public static final int BRIGS_QNT = 10;
    public static final int GALLEONS_QNT = 6;
    public static final int STEAM_FRIGATE_QNT = 2;
    public static final int NAVAL_BATTERY_QNT = 2;
    public static final int GALLEYS_QNT = 8;
    public static final int STEAM_CORVETTE_QNT = 2;
    public static final int MONITOR_QNT = 2;
    public static final int STEAMSHIP_QNT = 2;
}
