package org.game.unit;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Images {
    public static final UnitIcons THREE_DECKER_SHIP_OF_LINE_ST;

    static {
        try {
            THREE_DECKER_SHIP_OF_LINE_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResourceAsStream("/img/ThreeDSoL/1_ThreeDSoL.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResourceAsStream("/img/ThreeDSoL/1_ThreeDSoL_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResourceAsStream("/img/ThreeDSoL/1_ThreeDSoL_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons TWO_DECKER_SHIP_OF_LINE_ST;

    static {
        try {
            TWO_DECKER_SHIP_OF_LINE_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/TwoDSoL/1_TwoDSoL.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/TwoDSoL/1_TwoDSoL_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/TwoDSoL/1_TwoDSoL_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons FRIGATE_ST;

    static {
        try {
            FRIGATE_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Frigate/1_Frigate.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Frigate/1_Frigate_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Frigate/1_Frigate_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons TENDER_ST;

    static {
        try {
            TENDER_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Tender/1_Tender.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Tender/1_Tender_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Tender/1_Tender_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons BRIG_ST;

    static {
        try {
            BRIG_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Brig/1_Brig.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Brig/1_Brig_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Brig/1_Brig_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons GALLEON_ST;

    static {
        try {
            GALLEON_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galleon/1_Galleon.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galleon/1_Galleon_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galleon/1_Galleon_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons STEAM_FRIGATE_ST;

    static {
        try {
            STEAM_FRIGATE_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SteamFrigate/1_SteamFrigate.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SteamFrigate/1_SteamFrigate_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SteamFrigate/1_SteamFrigate_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons BATTERY_ST;

    static {
        try {
            BATTERY_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Battery/1_battery.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Battery/1_battery_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Battery/1_battery_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons GALLEY_ST;

    static {
        try {
            GALLEY_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galleon/1_Galleon.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galleon/1_Galleon_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galleon/1_Galleon_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons CORVETTE_ST;

    static {
        try {
            CORVETTE_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Corvette/1_Corvette.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Corvette/1_Corvette_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Corvette/1_Corvette_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons MONITOR_ST;

    static {
        try {
            MONITOR_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Monitor/1_Monitor.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Monitor/1_Monitor_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Monitor/1_Monitor_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons STEAMER_ST;

    static {
        try {
            STEAMER_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Steamer/1_Steamer.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Steamer/1_Steamer_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Steamer/1_Steamer_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons THREE_DECKER_SHIP_OF_LINE_ND;

    static {
        try {
            THREE_DECKER_SHIP_OF_LINE_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResourceAsStream("/img/ThreeDSoL/2_ThreeDSoL.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResourceAsStream("/img/ThreeDSoL/2_ThreeDSoL_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResourceAsStream("/img/ThreeDSoL/2_ThreeDSoL_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons TWO_DECKER_SHIP_OF_LINE_ND;

    static {
        try {
            TWO_DECKER_SHIP_OF_LINE_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/TwoDSoL/2_TwoDSoL.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/TwoDSoL/2_TwoDSoL_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/TwoDSoL/2_TwoDSoL_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons FRIGATE_ND;

    static {
        try {
            FRIGATE_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Frigate/2_Frigate.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Frigate/2_Frigate_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Frigate/2_Frigate_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons TENDER_ND;

    static {
        try {
            TENDER_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Tender/2_Tender.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Tender/2_Tender_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Tender/2_Tender_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons BRIG_ND;

    static {
        try {
            BRIG_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Brig/2_Brig.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Brig/2_Brig_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Brig/2_Brig_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons GALLEON_ND;

    static {
        try {
            GALLEON_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galleon/2_Galleon.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galleon/2_Galleon_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galleon/2_Galleon_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons STEAM_FRIGATE_ND;

    static {
        try {
            STEAM_FRIGATE_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SteamFrigate/2_SteamFrigate.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SteamFrigate/2_SteamFrigate_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SteamFrigate/2_SteamFrigate_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons BATTERY_ND;

    static {
        try {
            BATTERY_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Battery/2_battery.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Battery/2_battery_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Battery/2_battery_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons GALLEY_ND;

    static {
        try {
            GALLEY_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galley/2_Galley.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galley/2_Galley_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Galley/2_Galley_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons CORVETTE_ND;

    static {
        try {
            CORVETTE_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Corvette/2_Corvette.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Corvette/2_Corvette_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Corvette/2_Corvette_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons MONITOR_ND;

    static {
        try {
            MONITOR_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Monitor/2_Monitor.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Monitor/2_Monitor_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Monitor/2_Monitor_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons STEAMER_ND;

    static {
        try {
            STEAMER_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Steamer/2_Steamer.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Steamer/2_Steamer_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Steamer/2_Steamer_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons FIRST_LINE_FORT_ST;

    static {
        try {
            FIRST_LINE_FORT_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/FLFort/1_FLFort.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/FLFort/1_FLFort_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/FLFort/1_FLFort_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons FIRST_LINE_FORT_ND;

    static {
        try {
            FIRST_LINE_FORT_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/FLFort/2_FLFort.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/FLFort/2_FLFort_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/FLFort/2_FLFort_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons SECOND_LINE_FORT_ST;

    static {
        try {
            SECOND_LINE_FORT_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SLFort/1_SLFort.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SLFort/1_SLFort_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SLFort/1_SLFort_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons SECOND_LINE_FORT_ND;

    static {
        try {
            SECOND_LINE_FORT_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SLFort/2_SLFort.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SLFort/2_SLFort_selected.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SLFort/2_SLFort_under.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons FIRST_LINE_FORT_DESTROYED;

    static {
        try {
            FIRST_LINE_FORT_DESTROYED = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/FLFort/0_FLFort.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/FLFort/0_FLFort.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/FLFort/0_FLFort.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons SECOND_LINE_FORT_DESTROYED;

    static {
        try {
            SECOND_LINE_FORT_DESTROYED = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SLFort/0_SLFort.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SLFort/0_SLFort.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/SLFort/0_SLFort.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons ROYAL_PORT_ST;

    static {
        try {
            ROYAL_PORT_ST = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Royal/1_Royal_Port.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Royal/1_Royal_Port.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Royal/1_Royal_Port.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final UnitIcons ROYAL_PORT_ND;

    static {
        try {
            ROYAL_PORT_ND = new UnitIcons(
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Royal/2_Royal_Port.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Royal/2_Royal_Port.png"))),
                    ImageIO.read(Objects.requireNonNull(Images.class.getResource("/img/Royal/2_Royal_Port.png"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //TODO must solve the issue with destroyed and royal port icons
}
