package model;

import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player {
    private String playerId;
    private String playerName;
    private int playerPoints;

    private int bet;

    public SimplePlayer(String playerId, String playerName, int playerPoints) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerPoints = playerPoints;
    }

    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public int getPoints() {
        return this.playerPoints;
    }

    @Override
    public void setPoints(int points) {
        this.playerPoints = points;
    }

    @Override
    public String getPlayerId() {
        return this.playerId;
    }

    @Override
    public boolean setBet(int bet) {
        this.bet = bet;
        return true;
    }

    @Override
    public int getBet() {
        return this.bet;
    }

    @Override
    public void setBetType(BetType betType) {

    }

    @Override
    public BetType getBetType() {
        return null;
    }

    @Override
    public void resetBet() {

    }
}
