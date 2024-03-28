package de.xXSlideSlimeXx.main.doc.mute;

import de.xXSlideSlimeXx.main.doc.mute.BrokenRule;
import de.xXSlideSlimeXx.main.doc.mute.Gamemode;

/**
 * @author xXSlideSlimeXx
 * @since 20.11.2023
 */
public final class ReportDocument {
    private final String name;
    private final String ruleBreaker;
    private String proof;
    private final String extra;
    private final Gamemode gamemode;
    private final BrokenRule brokenRule;

    public ReportDocument(String name, String ruleBreaker, String proof, String extra, Gamemode gamemode, BrokenRule brokenRule) {
        this.name = name;
        this.ruleBreaker = ruleBreaker;
        this.proof = proof;
        this.extra = extra;
        this.gamemode = gamemode;
        this.brokenRule = brokenRule;
    }

    public ReportDocument(String name, String ruleBreaker, String extra, BrokenRule brokenRule, Gamemode gamemode) {
        this(name, ruleBreaker, "", extra, gamemode, brokenRule);
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getExtra() {
        return extra;
    }

    public String getName() {
        return name;
    }

    public String getProof() {
        return proof;
    }

    public String getRuleBreaker() {
        return ruleBreaker;
    }

    public Gamemode getGamemode() {
        return gamemode;
    }

    public BrokenRule getBrokenRule() {
        return brokenRule;
    }
}
