package UNO.model;

public class ActionCard extends Card {

  public ActionCard(CardColor cardColor, CardType cardType) {
    super(cardColor, cardType);
  }

  @Override
  public String toString() {
    return "{" +
        getCardColor() + ", " + getCardType() +
        '}';
  }
}
