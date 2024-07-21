package org.game.gui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {
    public static final UnitIcons THREE_DECKER_SHIP_OF_LINE_ST;
    public static final UnitIcons TWO_DECKER_SHIP_OF_LINE_ST;
    public static final UnitIcons FRIGATE_ST;
    public static final UnitIcons TENDER_ST;
    public static final UnitIcons BRIG_ST;
    public static final UnitIcons GALLEON_ST;
    public static final UnitIcons STEAM_FRIGATE_ST;
    public static final UnitIcons BATTERY_ST;
    public static final UnitIcons GALLEY_ST;
    public static final UnitIcons CORVETTE_ST;
    public static final UnitIcons MONITOR_ST;
    public static final UnitIcons STEAMER_ST;
    public static final UnitIcons THREE_DECKER_SHIP_OF_LINE_ND;
    public static final UnitIcons TWO_DECKER_SHIP_OF_LINE_ND;
    public static final UnitIcons FRIGATE_ND;
    public static final UnitIcons TENDER_ND;
    public static final UnitIcons BRIG_ND;
    public static final UnitIcons GALLEON_ND;
    public static final UnitIcons STEAM_FRIGATE_ND;
    public static final UnitIcons BATTERY_ND;
    public static final UnitIcons GALLEY_ND;
    public static final UnitIcons CORVETTE_ND;
    public static final UnitIcons MONITOR_ND;
    public static final UnitIcons STEAMER_ND;
    public static final UnitIcons FIRST_LINE_FORT_ST;
    public static final UnitIcons FIRST_LINE_FORT_ND;
    public static final UnitIcons SECOND_LINE_FORT_ST;
    public static final UnitIcons SECOND_LINE_FORT_ND;
    public static final BufferedImage FIRST_LINE_FORT_DESTROYED;
    public static final BufferedImage SECOND_LINE_FORT_DESTROYED;
    static {
        try{
            THREE_DECKER_SHIP_OF_LINE_ST=new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/ThreeDSoL/1_ThreeDSoL.png")),
                    ImageIO.read(new File("src/main/resources/img/ThreeDSoL/1_ThreeDSoL_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/ThreeDSoL/1_ThreeDSoL_under.png")));
            THREE_DECKER_SHIP_OF_LINE_ND=new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/ThreeDSoL/2_ThreeDSoL.png")),
                    ImageIO.read(new File("src/main/resources/img/ThreeDSoL/2_ThreeDSoL_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/ThreeDSoL/2_ThreeDSoL_under.png")));
            TWO_DECKER_SHIP_OF_LINE_ST=new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/TwoDSoL/1_TwoDSoL.png")),
                    ImageIO.read(new File("src/main/resources/img/TwoDSoL/1_TwoDSoL_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/TwoDSoL/1_TwoDSoL_under.png")));
            TWO_DECKER_SHIP_OF_LINE_ND=new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/TwoDSoL/1_TwoDSoL.png")),
                    ImageIO.read(new File("src/main/resources/img/TwoDSoL/1_TwoDSoL_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/TwoDSoL/1_TwoDSoL_under.png")));
            TENDER_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Tender/1_Tender.png")),
                    ImageIO.read(new File("src/main/resources/img/Tender/1_Tender_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Tender/1_Tender_under.png")));
            TENDER_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Tender/2_Tender.png")),
                    ImageIO.read(new File("src/main/resources/img/Tender/2_Tender_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Tender/2_Tender_under.png")));
            STEAM_FRIGATE_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/SteamFrigate/1_SteamFrigate.png")),
                    ImageIO.read(new File("src/main/resources/img/SteamFrigate/1_SteamFrigate_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/SteamFrigate/1_SteamFrigate_under.png")));
            STEAM_FRIGATE_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/SteamFrigate/2_SteamFrigate.png")),
                    ImageIO.read(new File("src/main/resources/img/SteamFrigate/2_SteamFrigate_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/SteamFrigate/2_SteamFrigate_under.png")));
            STEAMER_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Steamer/1_Steamer.png")),
                    ImageIO.read(new File("src/main/resources/img/Steamer/1_Steamer_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Steamer/1_Steamer_under.png")));
            STEAMER_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Steamer/2_Steamer.png")),
                    ImageIO.read(new File("src/main/resources/img/Steamer/2_Steamer_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Steamer/2_Steamer_under.png")));
            CORVETTE_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Corvette/1_Corvette.png")),
                    ImageIO.read(new File("src/main/resources/img/Corvette/1_Corvette_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Corvette/1_Corvette_under.png")));
            CORVETTE_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Corvette/2_Corvette.png")),
                    ImageIO.read(new File("src/main/resources/img/Corvette/2_Corvette_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Corvette/2_Corvette_under.png")));
            BATTERY_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Battery/1_battery.png")),
                    ImageIO.read(new File("src/main/resources/img/Battery/1_battery_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Battery/1_battery_under.png")));
            BATTERY_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Battery/2_battery.png")),
                    ImageIO.read(new File("src/main/resources/img/Battery/2_battery_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Battery/2_battery_under.png")));
            BRIG_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Brig/1_Brig.png")),
                    ImageIO.read(new File("src/main/resources/img/Brig/1_Brig_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Brig/1_Brig_under.png")));
            BRIG_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Brig/2_Brig.png")),
                    ImageIO.read(new File("src/main/resources/img/Brig/2_Brig_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Brig/2_Brig_under.png")));
            FRIGATE_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Frigate/1_Frigate.png")),
                    ImageIO.read(new File("src/main/resources/img/Frigate/1_Frigate_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Frigate/1_Frigate_under.png")));
            FRIGATE_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Frigate/2_Frigate.png")),
                    ImageIO.read(new File("src/main/resources/img/Frigate/2_Frigate_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Frigate/2_Frigate_under.png")));
            GALLEON_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Galleon/1_Galleon.png")),
                    ImageIO.read(new File("src/main/resources/img/Galleon/1_Galleon_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Galleon/1_Galleon_under.png")));
            GALLEON_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Galleon/2_Galleon.png")),
                    ImageIO.read(new File("src/main/resources/img/Galleon/2_Galleon_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Galleon/2_Galleon_under.png")));
            GALLEY_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Galley/1_Galley.png")),
                    ImageIO.read(new File("src/main/resources/img/Galley/1_Galley_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Galley/1_Galley_under.png")));
            GALLEY_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Galley/2_Galley.png")),
                    ImageIO.read(new File("src/main/resources/img/Galley/2_Galley_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Galley/2_Galley_under.png")));
            MONITOR_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Monitor/1_Monitor.png")),
                    ImageIO.read(new File("src/main/resources/img/Monitor/1_Monitor_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Monitor/1_Monitor_under.png")));
            MONITOR_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/Monitor/1_Monitor.png")),
                    ImageIO.read(new File("src/main/resources/img/Monitor/1_Monitor_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/Monitor/1_Monitor_under.png")));
            FIRST_LINE_FORT_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/FLFort/1_FLFort.png")),
                    ImageIO.read(new File("src/main/resources/img/FLFort/1_FLFort_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/FLFort/1_FLFort_under.png")));
            FIRST_LINE_FORT_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/FLFort/2_FLFort.png")),
                    ImageIO.read(new File("src/main/resources/img/FLFort/2_FLFort_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/FLFort/2_FLFort_under.png")));
            SECOND_LINE_FORT_ST = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/SLFort/1_SLFort.png")),
                    ImageIO.read(new File("src/main/resources/img/SLFort/1_SLFort_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/SLFort/1_SLFort_under.png")));
            SECOND_LINE_FORT_ND = new UnitIcons(
                    ImageIO.read(new File("src/main/resources/img/SLFort/2_SLFort.png")),
                    ImageIO.read(new File("src/main/resources/img/SLFort/2_SLFort_selected.png")),
                    ImageIO.read(new File("src/main/resources/img/SLFort/2_SLFort_under.png")));
            FIRST_LINE_FORT_DESTROYED = ImageIO.read(new File("src/main/resources/img/FLFort/0_FLFort.png"));
            SECOND_LINE_FORT_DESTROYED = ImageIO.read(new File("src/main/resources/img/SLFort/0_SLFort.png"));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
