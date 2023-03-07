package UNO.model;

import static java.lang.String.format;

public class Player {

  private String name;
  private CardList cardList;
  public Player(String name, CardList cardList) {
    this.name = name;
    this.cardList = cardList;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public CardList getCardList() {
    return cardList;
  }

  public void printCardList(){
    for(int i=0; i < cardList.getCardList().size(); i++){
      System.out.print(format("%s :  %s ", i, cardList.getCardList().get(i).toString()));
    }

    System.out.println();
  }

  public void setCardList(CardList cardList) {
    this.cardList = cardList;
  }

  public static boolean isWildCard(Card card) {
    return card.getCardType() == CardType.WILD_CARD || card.getCardType() == CardType.WILD_DRAW_FOUR;
  }


  public boolean hasCard(Card card) {
    return isWildCard(card)
        ? cardList.getCardList().stream().anyMatch(c -> c.getCardType() == card.getCardType())
        : cardList.getCardList().stream().anyMatch(c -> c.equals(card));
  }

  @Override
  public String toString() {
    return "Player{" +
        "name='" + name + '\'' +
        ", cardList=" + cardList +
        '}';
  }
}
