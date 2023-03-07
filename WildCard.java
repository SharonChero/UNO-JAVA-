package UNO.model;

public class WildCard extends Card{

  public WildCard(CardType cardType) {
    super(null, cardType);
  }

  @Override
  public String toString() {
    return "{" +
        getCardType() + ", " + getCardColor() +
        '}';
  }
}
