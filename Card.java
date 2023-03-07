package UNO.model;

/**
 * Card Class of the UNO Card game
 * Notes the properties used to identify cards in UNO : Number, Color and Action
 * @author chero
 */
public class Card {

    private CardColor cardColor;
    private CardType cardType;


    // constructor
    public Card(CardColor cardColor, CardType cardType) {
        this.cardColor = cardColor;
        this.cardType = cardType;
    }


    // Getters

    public CardColor getCardColor() {
        return cardColor;
    }

    public CardType getCardType() {
        return cardType;
    }


    @Override
    public String toString() {
        return "Card{" +
                "cardColor=" + cardColor +
                ", cardType=" + cardType +
                '}';
    }


}
