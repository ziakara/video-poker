// Represents a Player.

import java.util.ArrayList;

public class Player {
		
    private int bankroll;
    private int bet;
    private ArrayList<Card> hand;
		
    // Creates a Player
    public Player(){		
        bankroll = 100;
        bet = 0;
        hand = new ArrayList<Card>();
        
    }
	
    public void bets(int amt){
        bet = amt;
        bankroll = bankroll - amt;
    }

    // adjusts the bankroll if a player wins
    public void winnings(int odds){
        bankroll+= bet*odds;
    }

    // returns current balance of bankroll
    public int getBankroll(){
        return bankroll;
    }
    
    public void addCards(Card c){
        hand.add(c);
    }
    
    public void removeCards(Card c){
        hand.remove(c);
    }
    
    public ArrayList<Card> getHand(){
        return hand;
    }
    
}


