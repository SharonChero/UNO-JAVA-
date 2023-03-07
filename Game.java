package UNO.model;

/**
 * @ensure Create a game class that can design the functionality of UNO
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Game {

  // possible variables


  public static int currectPlayerIndex;

  private static final int TOTALCARDSPERPLAYER = 7;
  public static Player[] players;

  private static Deck deck = new Deck();
  private static ArrayList<Card> playerHands;
  public static DrawPile drawPile;
  private static List<Card> finalDeck;

  boolean uno = false;
  public static final Stack<Card> discardPile = new Stack<>();

  public static Scanner scanner = new Scanner(System.in);

  /**
   * The method gets the cards in the deck Class, creates and shuffle a Drawpile
   * The Method assigns 7 cards to two players.
   * @param playersnames
   */
  public static void startGame(List<String> playersnames) {
    finalDeck = deck.getCardsinDeck();
    List shuffledCards = deck.shuffle(finalDeck);
    drawPile = new DrawPile(shuffledCards);
    players = new Player[playersnames.size()];
    int y = 0;
    for (String playerName : playersnames) {
      Player player = new Player(playerName, new CardList());
      players[y++] = player;
    }
    currectPlayerIndex = 0;
    playerHands = new ArrayList<>();

    for (int i = 0; i < TOTALCARDSPERPLAYER; i++) {
      for (int p = 0; p < playersnames.size(); p++) {
        CardList currentPlayerCards = players[p].getCardList();
        currentPlayerCards.addCard(drawPile.drawCard());
      }
    }
    discardPile.add(drawPile.drawCard());

  }

  /**
   * The method sets a functionality for the player turns
   * @param player
   * @param playedCard
   */

  public static void playGame(Player player, Card playedCard) {
    switch (playedCard.getCardType()) {
      case NUMBER -> {
        savePlayedCard(playedCard, player);
        nextPlayer();
      }
      case SKIP -> {
        savePlayedCard(playedCard, player);
        nextPlayer();
        nextPlayer();
      }
      case REVERSE -> {
        savePlayedCard(playedCard, player);
        reverse();
      }
      case DRAW_TWO -> {
        savePlayedCard(playedCard, player);
        nextPlayer();
        Player playerToDraw = players[currectPlayerIndex];
        drawCards(playerToDraw, 2);
        nextPlayer();
      }
      case WILD_CARD -> {
        savePlayedCard(playedCard, player);
        nextPlayer();
      }
      case WILD_DRAW_FOUR -> {
        savePlayedCard(playedCard, player);
        nextPlayer();
        Player playerToDraw = players[currectPlayerIndex];
        drawCards(playerToDraw, 4);
        nextPlayer();
      }
    }
  }


  /**
   * This method is used to implement the top card in the stockpile
   * @param card
   * @param player
   */
  public static void savePlayedCard(Card card, Player player) {
    player.getCardList().removeCards(card);
    discardPile.add(card);
    // check uno
  }

  /**
   * This method is used to get the number of cards in a player's hand.
   * @return
   */

  public List<Card> getHandCards()
  {return Collections.unmodifiableList(playerHands);
  }



  /**
   * This method is used to get the current player.
   */
  public static void nextPlayer() {
    currectPlayerIndex = Math.floorMod(currectPlayerIndex - 1, players.length);
  }

  /**
   * This method is used to reverse direction of the game.
   */

  public static void reverse() {
    currectPlayerIndex = (currectPlayerIndex + 1) % players.length;
  }

  /**
   * This method is used to draw cards
   * @param player
   * @param number
   */

  private static void drawCards(Player player, int number) {
    for (int i = 0; i < number; i++) {
      player.getCardList().addCard(drawPile.drawCard());
    }
  }

  /**
   * @ensure played Card is valid. Is of required type: color and number
   * @param cardPlayed
   * @param topCard
   * @return
   */
  public static boolean isCardValid(Card cardPlayed, Card topCard) {

    if (topCard.getCardType() != null && cardPlayed.getCardType().equals(CardType.NUMBER) && topCard.getCardType().equals(CardType.NUMBER)) {
      NumberCard cardPlayednumberCard = (NumberCard) cardPlayed;
      NumberCard topCardNumberCard = (NumberCard) topCard;
      if (cardPlayednumberCard.getValue() == topCardNumberCard.getValue()) {
        return true;
      }

      if (cardPlayednumberCard.getCardColor().equals(topCardNumberCard.getCardColor())) {
        return true;
      }
    }

    if (cardPlayed.getCardType().equals(CardType.REVERSE)) {
      return true;
    }

    if (cardPlayed.getCardType().equals(CardType.WILD_CARD) || cardPlayed.getCardType().equals(CardType.DRAW_TWO) ||
            cardPlayed.getCardType().equals(CardType.WILD_DRAW_FOUR) || cardPlayed.getCardType().equals(CardType.SKIP)) {
      return true;
    }

    if (topCard.getCardType().equals(CardType.WILD_CARD) || topCard.getCardType().equals(CardType.DRAW_TWO) ||
            topCard.getCardType().equals(CardType.WILD_DRAW_FOUR) || topCard.getCardType().equals(CardType.SKIP)
            || topCard.getCardType().equals(CardType.REVERSE)) {
      return true;
    }
    return false;
  }

  /**
   * Calculating the possible points for the winner
   * @param cards
   * @return
   */

  public static int calculatePoints(List<Card> cards) {
    int total = 0;
    for (Card card : cards) {
      if (card.getCardType().equals(CardType.REVERSE)) {
        total += 20;
      } else if (card.getCardType().equals(CardType.DRAW_TWO)) {
        total += 20;
      } else if (card.getCardType().equals(CardType.SKIP)) {
        total += 20;
      } else if (card.getCardType().equals(CardType.WILD_CARD)) {
        total += 50;
      } else if (card.getCardType().equals(CardType.WILD_DRAW_FOUR)) {
        total += 50;
      }
    }
    return total;
  }
  /**
   * Ensure Winner has a score/ points of >= 500
   * @param score
   * @return
   */
  public  static  boolean isWinner(int score) {
    if (score >= 500) {
      return true;
    }
    return false;
  }


}

