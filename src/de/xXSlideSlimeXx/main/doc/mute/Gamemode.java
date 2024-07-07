package de.xXSlideSlimeXx.main.doc.mute;

/**
 * @author xXSlideSlimeXx
 * @since 20.11.2023
 */
public enum Gamemode {
    MINIGAMES("Minigames", (byte) 7),
    LOBBY("Lobby", (byte) 10),
    KITPVP("KitPvP", (byte) 6),
    IMMORTAL_FACTIONS("Immortal Factions", (byte) 1),
    SKYBLOCK_DREAM("Skyblock Dream", (byte) 2),
    SKYBLOCK_FANTASY("Skyblock Fantasy", (byte) 3),
    PRISON("Prison", (byte) 4),
    SURVIVIAL("Survivial", (byte) 5),



    LIFESTEAL("Lifesteal", (byte) 8),
    GENS("Gens", (byte) 9),

    ;

    private final String name;
    private final byte reportId;

    Gamemode(String name, byte reportId) {
        this.name = name;
        this.reportId = reportId;
    }

    public String getName() {
        return name;
    }

    public byte getReportId() {
        return reportId;
    }

    public static Gamemode fromName(String name) {
        for(final Gamemode gamemode : Gamemode.values()) {
            if(gamemode.getName().equalsIgnoreCase(name)) {
                return gamemode;
            }
        }
        return null;
    }
}
