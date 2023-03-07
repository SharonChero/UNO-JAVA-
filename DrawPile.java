package UNO.model;

import java.util.List;
import java.util.Stack;

public class DrawPile {

    private final Stack<Card> cards = new Stack<>();

    public DrawPile(List<Card> cardsList) {
        cards.addAll(cardsList);
    }

    public Card drawCard() {
        return cards.pop();
    }

    public int getSize() {
        return cards.size();
    }

}
