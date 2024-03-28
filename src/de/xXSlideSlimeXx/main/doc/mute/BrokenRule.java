package de.xXSlideSlimeXx.main.doc.mute;

/**
 * @author xXSlideSlimeXx
 * @since 20.11.2023
 */
public enum BrokenRule {
    //Beispiele hinzufügen
    CHAT_FLOOD((byte) 1, PunishTypes.MINOR, "Chat Flood", "Sending 4 or more messages in a really short time OR 15+ random characters",
            "audhwaodoabfz87r", "Hello,\nyou\nare\ncool"),
    CHAT_TROLLING((byte) 2, PunishTypes.MINOR, "Chat Trolling", "Forcefully disconnect player by promising a reward", "ALT+F4 for GM 1"),
    EXCESSIVE_CAPS((byte) 3, PunishTypes.MINOR, "Excessive Caps", "5+ word or 15+ character capitalized; 3 messages with capitalized words",
            "HOW ARE YOU\nSorry caps", "UNCOPYRIGHTABLE"),
    AUTOMATED_ADVERTISEMENT_MESSAGES((byte) 4, PunishTypes.MINOR, "Automated / Advertisement Message", "Sending allowed Advertising message in less than 15 min",
            "Buy cool items: /is Steve", "Buy cool items: /is Steve (after 14min)"),
    ENCOURAGING_RULE_BREAKING((byte) 5, PunishTypes.MINOR, "Encouraging rule breaking", "Encouraging, asking, or incentivizing players to break rules",
            "Cmon say something rude"),
    GENERAL_RUDENESS((byte) 6, PunishTypes.MINOR, "General Rudeness / Swearing", "Rudeness; Swearing; Specific to a player; Trash talking only to certain point",
            "you son of a bitch Steve", "motherfucker how the fuck i fell", "Bro your bed is like your brain its gone", "Fuck you (Steve)", "fk u"),
    FOREIGN_LANGUAGE((byte) 7, PunishTypes.MINOR, "Foreign Language", "Not speaking English; Known words from other languages are allowed",
            "Hallo was machst du gerade?", "Hoe gaat het met je?"),
    LIGHT_ADVERTISING((byte) 8, PunishTypes.MINOR, "Light Advertising", "Mentioning server name or shortcuts; Execeptions are Hypixel, Pika, SnapCraft",
            "seaside is a cool server", "purple prison server is pay-to-win"),
    RIOTING((byte) 9, PunishTypes.MINOR, "Rioting", "Attempt or participating in rioting"),
    SPAMMING((byte) 10, PunishTypes.MINOR, "Spamming", "Sending 3 or more same message in row; Bypassing count also",
            "Hello\nHello1\nHello2"),
    ADVERTISING_SOCIAL_MEDIA((byte) 11, PunishTypes.MODERATE, "Advertising Social Media", "Advertising Social Media with names or links; Username counts",
            "Sub me on youtube SteveYoutube", "Watch my new video https://www.youtube.com/watch?v=dQw4w9WgXcQ",
            "(Username)SubToSteveYoutube"),
    STAFF_DISRESPECT((byte) 12, PunishTypes.MINOR,"Staff Disrespect [Factions]", "Rudeness or disrespectful towards Staff in Factions",
            "Staff imagine spending time to then get grieft"),
    STAFF_IMPERSONATION((byte) 13, PunishTypes.MODERATE,"Staff / Youtuber / Player Impersonation", "Impersonation via Chat, Name, Nickname",
            "Hi, I'm .SteveYoutube (with same skin)", "This is my Server. I'm the owner"),
    SWEARING((byte) 14,PunishTypes.MODERATE,"Swearing with a disease / disability", "Swearing with disease/disability to attack players",
            "You have ebola", "Are you blind? Can you see anything?", "Steve you faggot"),
    INAPPROPRIATE_BEHAVIOR((byte) 15,PunishTypes.MODERATE,"Inappropriate Behavior", "Sexual messages, NSWF, sexually-orientated conversations",
            "Suck my dick", "Do you want to play with my dick?"),
    INAPPROPRIATE_LINKS((byte) 16,PunishTypes.MODERATE,"Unapproved / Inappropriate Links", "Unapproved/Inappropriate Links",
            "virus.com"),
    DISCRIMINATION_RACISM_SEXISM((byte) 17,PunishTypes.SERVERE, "Discrimination / Racism / Sexism", "Insult or prejudicial treatment because of  race, age, gender, sexual orientation,...",
            "Every woman on this server is so bad", "Your a kid go sleep now", "Go back to work nigga"),
    DIRECTING_TRAGIC_EVENTS((byte) 18, PunishTypes.SERVERE, "Directing Tragic Events", "Downplaying tragic events such as war etc.",
            "WW2 was a cool event"),
    THREATS((byte) 19,PunishTypes.SERVERE, "Threats (Death, Suicide)", "Threats ex. Death, Suicide; indirect counts",
            "kys"),
    MUTE_EVADING((byte) 20,PunishTypes.SERVERE, "Mute Evading", "Using alt to bypass mute"),
    DDOS((byte) 21,PunishTypes.SERVERE, "DDoS / Dox Threats", "Threat of revealing personal information or DDoS; Joke is not an excuse",
            "im gonna ddos you"),
    DISTRIBUTING((byte) 22,PunishTypes.SERVERE, "Distributing / Asking for Nudes", "Asking for sexual tape; Joke is not an excuse",
            "Send nudes"),
    BLACKMAILING((byte) 23,PunishTypes.SERVERE, "Blackmailing", "Blackmailing with threat to compromise gameplay experience or account safety"),
    DISCORD_INVITE_LINK((byte) 24,PunishTypes.SERVERE, "Discord Invite Link", "No Invite Links in any way, shape or form"),
    SELLING_FOR_MONEY((byte) 25,PunishTypes.SERVERE, "Selling / Buying in-game items for PayPal", ""),
    SELLING_ACCOUNT((byte) 26,PunishTypes.SERVERE, "Selling JartexNetwork / Minecraft Accounts", ""),
    ADVERTISING_HACK_CLIENTS((byte) 27,PunishTypes.SERVERE, "Advertising Hacked Clients", ""),
    ADVERTISING((byte) 28,PunishTypes.SERVERE, "Advertising", "Share Server IP in any way, shape or form"),
    INAPPROPRIATE_USERNAMES((byte) 29,PunishTypes.SERVERE, "Inappropriate Minecraft Usernames", "Inappropriate, sexual, racist,  offensive Names"),
    INAPPROPRIATE_ITEM_NAME((byte) 30,PunishTypes.SERVERE, "Inappropriate Item Name", "Inappropriate, sexual, or offensive Item Names"),
    INAPPROPRIATE_NICKNAME((byte) 31,PunishTypes.SERVERE, "Inappropriate Nickname", ""),
    BYPASS((byte) 32,PunishTypes.SERVERE, "Bypassing Chat Rules", ""),
    DOXING((byte) 33,PunishTypes.SERVERE, "Doxing / DDoSing", "Sharing personal information without permission; DDoSing", "Steve's real name is Steve"),
    ;

    private final String name;
    private final String desc;
    private final String[] examples;
    private final PunishTypes types;
    private final byte reportId;

    BrokenRule(byte reportId, PunishTypes types, String name, String desc, String... examples) {
        this.name = name;
        this.reportId = reportId;
        this.desc = desc;
        this.types = types;
        this.examples = examples;
    }

    public String getDescription() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public byte getReportId() {
        return reportId;
    }

    public String[] getExamples() {
        return examples;
    }

    public PunishTypes getPunishType() {
        return types;
    }

    public static BrokenRule fromName(String name) {
        for(BrokenRule rule : BrokenRule.values()) {
            if(rule.getName().endsWith(name)) {
                return rule;
            }
        }
        return null;
    }
}
