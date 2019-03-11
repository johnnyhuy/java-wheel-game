package model;

import model.enumeration.BetType;
import model.interfaces.Player;

public class SimplePlayer implements Player {
    private String playerId;
    private String playerName;
    private int playerPoints;
    private int bet;
    private BetType betType;

    public SimplePlayer(String playerId, String playerName, int playerPoints) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerPoints = playerPoints;
    }

    /**
     * Convert Player object to string.
     *
     * @return String
     */
    public String toString() {
        return String.format(
            "Player: id=%s, name=%s, bet=%d, betType=%s, points=%d",
            this.playerId,
            this.playerName,
            this.bet,
            this.betType,
            this.playerPoints
        );
    }

    /**
     * Get the player name.
     *
     * @return String
     */
    @Override
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Set the player name.
     *
     * @param playerName - human readable player name
     */
    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Get the players points to bet.
     *
     * @return int
     */
    @Override
    public int getPoints() {
        return this.playerPoints;
    }

    /**
     * Get player betting points.
     *
     * @param points - for betting (updated by GameEngineImpl via BetType enum with each win or loss)
     */
    @Override
    public void setPoints(int points) {
        this.playerPoints = points;
    }

    /**
     * Get the player ID string.
     *
     * @return String
     */
    @Override
    public String getPlayerId() {
        return this.playerId;
    }

    /**
     * Set the players bet.
     *
     * @param bet - the bet in points
     * @return boolean - true if bet has been set
     */
    @Override
    public boolean setBet(int bet) {
        this.bet = bet;
        return true;
    }

    /**
     * Get the players current bet amount.
     *
     * @return int
     */
    @Override
    public int getBet() {
        return this.bet;
    }

    /**
     * Get the players placed bet type.
     *
     * @return BetType
     */
    @Override
    public BetType getBetType() {
        return this.betType;
    }

    /**
     * Set the players placed bet type.
     *
     * @param betType - enum representing the type of bet being placed
     */
    @Override
    public void setBetType(BetType betType) {
        this.betType = betType;
    }

    @Override
    public void resetBet() {

    }
}
