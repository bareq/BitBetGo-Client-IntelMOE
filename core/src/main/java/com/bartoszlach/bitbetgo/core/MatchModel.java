package com.bartoszlach.bitbetgo.core;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by bartoszlach on 29.12.2015.
 */
public class MatchModel implements Serializable {

    private long id;

    private Timestamp matchDate;
    private String league;
    private String teamName1;
    private String teamName2;
    private String teamImage1;
    private String teamImage2;
    private long bank;

    public MatchModel() {
    }

    public MatchModel(long id, Timestamp matchDate, String league, String teamName1, String teamName2, String teamImage1, String teamImage2, long bank) {
        this.id = id;
        this.matchDate = matchDate;
        this.league = league;
        this.teamName1 = teamName1;
        this.teamName2 = teamName2;
        this.teamImage1 = teamImage1;
        this.teamImage2 = teamImage2;
        this.bank = bank;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the matchDate
     */
    public Timestamp getMatchDate() {
        return matchDate;
    }

    /**
     * @param matchDate the matchDate to set
     */
    public void setMatchDate(Timestamp matchDate) {
        this.matchDate = matchDate;
    }

    /**
     * @return the league
     */
    public String getLeague() {
        return league;
    }

    /**
     * @param league the league to set
     */
    public void setLeague(String league) {
        this.league = league;
    }

    /**
     * @return the teamName1
     */
    public String getTeamName1() {
        return teamName1;
    }

    /**
     * @param teamName1 the teamName1 to set
     */
    public void setTeamName1(String teamName1) {
        this.teamName1 = teamName1;
    }

    /**
     * @return the teamName2
     */
    public String getTeamName2() {
        return teamName2;
    }

    /**
     * @param teamName2 the teamName2 to set
     */
    public void setTeamName2(String teamName2) {
        this.teamName2 = teamName2;
    }

    /**
     * @return the teamImage1
     */
    public String getTeamImage1() {
        return teamImage1;
    }

    /**
     * @param teamImage1 the teamImage1 to set
     */
    public void setTeamImage1(String teamImage1) {
        this.teamImage1 = teamImage1;
    }

    /**
     * @return the teamImage2
     */
    public String getTeamImage2() {
        return teamImage2;
    }

    /**
     * @param teamImage2 the teamImage2 to set
     */
    public void setTeamImage2(String teamImage2) {
        this.teamImage2 = teamImage2;
    }

    /**
     * @return the bank
     */
    public long getBank() {
        return bank;
    }

    /**
     * @param bank the bank to set
     */
    public void setBank(long bank) {
        this.bank = bank;
    }
}
