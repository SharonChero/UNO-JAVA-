package UNO.model;

import java.util.ArrayList;
import java.util.List;

public class CardList {

  private final List<Card> cardList = new ArrayList<>();

  public void addCard(Card card){
    cardList.add(card);
  }


  public boolean hasCard(Card card){
    return  cardList.stream().anyMatch( card1 -> card1.equals(card));
  }

  public boolean removeCards(Card card) {
    var cardToRemove = isWildCard(card) ? findCardOfType(card.getCardType()) : card;
    return cardList.remove(cardToRemove);
  }

  public List<Card> getCardList(){
    return  cardList;
  }

  private Card findCardOfType(CardType type) {
    for (var card : cardList) {
      if (card.getCardType() == type) {
        return card;
      }
    }

    return null;
  }

  public static boolean isWildCard(Card card) {
    return card.getCardType() == CardType.WILD_CARD || card.getCardType() == CardType.WILD_DRAW_FOUR;
  }

  @Override
  public String toString() {
    return "CardList{" +
        "cardList=" + cardList +
        '}';
  }
}
