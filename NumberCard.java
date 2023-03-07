package UNO.model;

public class NumberCard extends Card{

  private int value;
  public NumberCard(int value, CardColor cardColor) {
    super(cardColor, CardType.NUMBER);

    this.value = value;
  }
  public int getValue(){
    return this.value;
  }

  @Override
  public String toString() {
    return "{" +
        value + ", " + getCardColor() +
        '}';
  }

}
