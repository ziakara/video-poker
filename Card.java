// Representation of a card.

public class Card implements Comparable<Card>{
	
    // suits are encoded from 1-4
    // rank is encoded from 1-13
    private int suit;
    private int rank;
	
    // creates a Card with suit s and rank r
    public Card(int s, int r){
        suit = s;
        rank = r;
    }
	
    // method compares Cards to easily sort them
    public int compareTo(Card c){
        
        if (this.getRank() < c.getRank()){
            return -1;
        }
         
        else if (this.getRank() > c.getRank()){
            return 1;
        }
        
        else {
            if (this.getSuit() > c.getSuit()){
                return 1;
            }
            else if (this.getSuit() < c.getSuit()){
                return -1;
            }
            else {
                return 0;
            }
        }
    }

	// method to print a Card object easily
    public String toString(){
        String rankName = "blank";
        String suitName = "blank";
        
        // Checks suits
        if (suit == 1){
            suitName = "Clubs";
        }
        
        else if (suit == 2){
            suitName = "Diamonds";
        }
        
        else if (suit == 3){
            suitName = "Hearts";
        }
        
        else if (suit == 4){
            suitName = "Spades";
        }
        
        // Checks ranks
        if (rank > 1 && rank < 11){
            rankName = "" + rank;
        }
        
        else if (rank == 1){
            rankName = "Ace";
        }
        
        else if (rank == 11){
            rankName = "Jack";
        }
        
        else if (rank == 12){
            rankName = "Queen";
        }
        
        else if (rank == 13){
            rankName = "King";
        }

        String name = rankName + " of " + suitName;
        return name;
    }

    public int getSuit(){
        return suit;
    }
    
    public int getRank(){
        return rank;
    }

}
