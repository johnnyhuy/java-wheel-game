package model;

import model.enumeration.BetType;

import static helper.StringHelper.capitalize;

public class BetTypeViewModel {
    private BetType betType;

    public BetTypeViewModel(BetType betType) {
        this.betType = betType;
    }

    public BetType getBetType() {
        return this.betType;
    }

    @Override
    public String toString() {
        return capitalize(this.betType.name());
    }
}
