package UNO.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {

  /**
   * @ensure total number of cards in deck = 108
   */

  private final List<Card> cardsinDeck = new ArrayList<>(108);

  /**
   * Constructor.  Create an unshuffled deck of cards.
   */

  public Deck() {
    prepareDeck();
  }

  private void prepareDeck() {

    addActionCardsToDeck();
    addWildCardsToDeck();
    addNumbersCardsToDeck();
  }

  private void addNumbersCardsToDeck() {
    for (CardColor color : CardColor.values()) {
      cardsinDeck.add(new NumberCard(0, color));
      for (int i = 1; i <= 9; i++) {
        cardsinDeck.add(new NumberCard(i, color));
        cardsinDeck.add(new NumberCard(i, color));
      }
    }
  }

  private void addWildCardsToDeck() {

    for (int i = 0; i < 4; i++) {
      cardsinDeck.add(new WildCard(CardType.WILD_CARD));
      cardsinDeck.add(new WildCard(CardType.WILD_DRAW_FOUR));
    }
  }

  private void addActionCardsToDeck() {
    for (CardColor color : CardColor.values()) {
      cardsinDeck.add(new ActionCard(color, CardType.DRAW_TWO));
      cardsinDeck.add(new ActionCard(color, CardType.DRAW_TWO));

      cardsinDeck.add(new ActionCard(color, CardType.REVERSE));
      cardsinDeck.add(new ActionCard(color, CardType.REVERSE));

      cardsinDeck.add(new ActionCard(color, CardType.SKIP));
      cardsinDeck.add(new ActionCard(color, CardType.SKIP));
    }
  }
  public List<Card> getCardsinDeck(){
    return cardsinDeck;
  }


  public List<Card> shuffle(List<Card> cards) {
    int n = cards.size();

    for (int i = 0; i < cards.size(); i++) {
      int randomValue = ThreadLocalRandom.current().nextInt(0, 107);
      Card randomCard = cards.get(randomValue);
      cards.set(randomValue, cards.get(i));
      cards.set(i, randomCard);
    }

    return cards;
  }





}
