// Deck class

public class Deck {
	
    private Card[] cards;
    private int top; //the index of the top of the deck
	
    public Deck(){
        cards = new Card[52];
        top = 0;
        
        Card card;
        int count = 0;

        // Creates new deck
        for (int s = 1; s <= 4; s++){
            for (int r = 1; r <= 13; r++){
                card = new Card(s, r);
                cards[count] = card;
                count++;
            }
        } 
    }
	
    public void shuffle(){
        for (int i = 0; i < cards.length; i++){
            int r = (int)(Math.random()*52);
            Card temp = cards[r];
            cards[r] = cards[i];
            cards[i] = temp;
        }
    }
    
    public Card deal(){
        if(top > 51){
            top = 0;
            return new Card(0, 0);
        }
        
        else{
            top++;
            return cards[top-1];
        }
    }
	
}
