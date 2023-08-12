// This class plays a game of video poker.

import java.util.*;

public class Game {
	
    private Player p;
    private Deck cards;
    private ArrayList<Card> hand;
    private Scanner input;
	
    public Game(String[] testHand){
        // Constructor to help test code.
        // Uses testHand to make a hand for the player.
        // The cards are encoded as follows:
            // c = clubs
            // d = diamonds
            // h = hearts
            // s = spades
            // 1-3 = ace-king
            // for example: s1 = ace of spades
        // as an example: testhand = {s1, s13, s12, s11, s10} = royal flush
        hand = new ArrayList<Card>();
        int suit = 0;
        int rank = 0;
        char tempSuit;
        String tempRank;
        String testString;
        
        // Checks the string for the suit
        for (int i = 0; i < 5; i++){
            testString = testHand[i];
            tempSuit = testString.charAt(0);
            
            if (tempSuit == 'c'){
                suit = 1;
            }
            else if (tempSuit == 'd'){
                suit = 2;
            }
            else if (tempSuit == 'h'){
                suit = 3;
            }
            else if (tempSuit == 's'){
                suit = 4;
            }
            // Lets the user know if the input is invalid
            else {
                System.out.println("Sorry invalid input!");
            }
            // Checks the string for the rank
            if (testHand[i].length() <= 3){
                tempRank = testString.substring(1);
                rank = Integer.parseInt(tempRank);
            }
            else if (testHand[i].length() <= 4){
                tempRank = testString.substring(1,2);
                rank = Integer.parseInt(tempRank);
            }
            // Lets the user know if the input is invalid
            else {
                System.out.println("Sorry invalid input!");
            }
            // Creates a new card and adds it to the hand.
            Card c = new Card(suit, rank);
            hand.add(c);
        }
    }
	
    // No-argument constructor to play a normal game,
    public Game(){
		p = new Player();
        cards = new Deck();
        hand = new ArrayList<Card>();
        cards.shuffle();
        input = new Scanner(System.in);
    }
	
    // method to play the game
    public void play(){
        int playAgain = 1;
        int tokenBet = 0;
        int multiplier = 0;
        
        // Allows the player to play multiple games.
        while (playAgain == 1){
            cards.shuffle();
            
            System.out.println("Time to play Video Poker!");
            System.out.println("You have " + p.getBankroll() + " tokens.");
            System.out.println("How many tokens do you want to bet?");
            System.out.println("(Enter a number from 1-5)");
            tokenBet = input.nextInt(); // Player's bet amount
            p.bets(tokenBet); // Subtracts the bet from the bankroll
        
            // This ensures the bet is between 1 and 5 tokens.
            while (tokenBet == 0 || tokenBet > 6){
                System.out.println("Hey! Only pick a number from 1-5!");
                System.out.println("Try again! (Enter a number from 1-5)");
                tokenBet = input.nextInt();
            }
            
            // Deals a new hand
            for (int i = 0; i < 5; i++){
                p.addCards(cards.deal());
            }
            hand = new ArrayList<Card>(p.getHand());
            
            // Prints the hand for the user to see
            System.out.println("Your cards are:");
            for (Card element : hand){
                System.out.println("" + element.toString());
            }
            
            // Lets the player select the amount of cards they want to reject
            // if they wish, or none at all
            System.out.println("How many cards do you want to reject?");
            System.out.println("(Pick the total number of cards you want" +
                               " to reject (from 0-5), then hit enter:)");
            int reject = input.nextInt();
            
            // The user can specify the index of which cards to reject
            if (reject != 0 && reject < 6){
                for (int i = 0; i < reject; i++){
                    System.out.println("Which cards do you want to reject?");
                    System.out.println("(Enter the index of ONE card you want" +
                                       " to remove (1-5), then hit enter:)");
                    int number = input.nextInt();
                    p.removeCards(hand.get(number-1));
                    p.addCards(cards.deal());    
                }
        
                // Prints the new hand for the user to see
                hand = new ArrayList<Card>(p.getHand());
                System.out.println("Your new hand is:");
                for (Card element : hand){
                    System.out.println("" + element.toString());
                }
            }
            // Calls the checkHand(), and returns a multiplier
            // which I have assigned to the variable multiplier
            // in order to display the amount of tokens won by the hand
            multiplier = this.checkHand();
            
            // Adjusts the bankroll given the multipler of the hand
            p.winnings(multiplier);
        
        // Prints the amount of tokens won + updated token balance    
        System.out.println("You won " + multiplier*tokenBet + " tokens!");
        System.out.println("Your updated token balance is: " + p.getBankroll());
        
        // Asks the user if they want to continue to play or not
        System.out.println("Do you want to play again? (1-yes, 0-no)");
        playAgain = input.nextInt();
        
        // Removes the cards from the hand
        for (int i = 0; i < 5; i++){
            p.removeCards(hand.get(i));
        }       
        } 
    }
    
    // method to test the checkHand method.
    // takes the hand passaged as a command line arguemtn and
    // prints the result.
    public void testPlay(){
        checkHand();
    }
	
    // Takes an ArrayList of 5 Card objects as an input.
    // It evaluates the ahdn, and prints the result to the console.
    // It also returns the multiplier of the bet.
	public int checkHand(){
        // sorts the hand prior to checking it
        Collections.sort(hand);
        
        // uses the helper methods and to see what the hand evaluates to
        // returns the multipler given the hand
        if (royalFlush()){
            System.out.println("Congrats you have a Royal Flush!");
            return 250;
        }
        
        else if (straightFlush()){
            System.out.println("Congrats you have a Straight Flush!");
            return 50;
        }
        
        else if (fourKind()){
            System.out.println("Congrats you have a Four of a Kind!");
            return 25;
        }
        
        else if (fullHouse()){
            System.out.println("Congrats you have a Full House!");
            return 6;
        }
        
        else if (flush()){
            System.out.println("You have a Flush!");
            return 5;
        }
        
        else if (straight()){
           System.out.println("You have a Straight!");
           return 4;
        }
        
        else if (threeKind()){
            System.out.println("You have a Three of a Kind!");
            return 3;
        }
        
        else if (twoPair()){
            System.out.println("You have Two Pairs!");
            return 2;
        }   
        
        else if (onePair()){
            System.out.println("You have a Pair!");
            return 1;
        }  

        else {
            System.out.println("Sorry, you don't have any hands!");
            return 0;
        }
    }

    // helper methods for the above
    
    // The description of the poker hands are from:
    // https://www.cardplayer.com/rules-of-poker/hand-rankings
    
    // Royal Flush: A, K, Q, J, 10 all same suit
    public boolean royalFlush(){
        // checks if straightFlush() is true, and that there is an ace
        if (straightFlush() && hand.get(0).getRank() == 1){
            return true;
        }
        return false;
    }
    
    // Straight Flush: Five cards in a sequence, all in the same suit. 
    public boolean straightFlush(){
        // checks if straight() and flush() are true
        if (straight() && flush()){
            return true;
        }
        return false;
    }

    // Four of a Kind: All four cards of the same rank. 
    public boolean fourKind(){
        int count = 0;
        int i = 0;
        
        // Checks if at different indexes of the hand, the ranks of 4 cards
        // in the sorted hand are equal to each other
        while (i <= 1){
            if (hand.get(i).getRank() == hand.get(i + 1).getRank() && 
                hand.get(i + 1).getRank() == hand.get(i + 2).getRank() &&
                hand.get(i + 2).getRank() == hand.get(i + 3).getRank()){
                count++;
                i++;
            }
            i++;
        }

        if (count == 1){
            return true;
        }
        else{
            return false;
        }
    }
    
    // Full House: Three of a kind with a pair. 
    public boolean fullHouse(){
        // checks if threeKind() and twoPair() are true
        // given that if there is if threeKind() is true
        // there would be a pair in those 3 cards
        if (threeKind() && twoPair()){
            return true;
        }
        return false;   
    }
    
    // Flush: Any five cards of the same suit, but not in a sequence. 
    public boolean flush(){
        // checks that all cards have the same suit
        if (hand.get(0).getSuit() == hand.get(1).getSuit() &&
            hand.get(1).getSuit() == hand.get(2).getSuit() &&
            hand.get(2).getSuit() == hand.get(3).getSuit() && 
            hand.get(3).getSuit() == hand.get(4).getSuit()){
            return true;
        }
        return false;
    }
    
    // Straight: Five cards in a sequence, but not of the same suit. 
    public boolean straight(){
        // This assumes, the cards are sorted
        // Checks that the first and last cards are in a sequence
        if (hand.get(4).getRank() - hand.get(0).getRank() == 4){
            return true;
        }
        
        // Checks for the case of an ace in a straight
        else if (hand.get(0).getRank()==1 && 
                 hand.get(4).getRank()-hand.get(1).getRank() == 3){
            return true;
        }

        return false;
    }
    
    // Three of a Kind: Three cards of the same rank. 
    public boolean threeKind(){
        int count = 0;
        int i = 0;
        // Checks if at different indexes of the hand, the ranks of 3 cards
        // in the sorted hand are equal to each other
        while (i <= 2){
            if (hand.get(i).getRank() == hand.get(i + 1).getRank() && 
                hand.get(i + 1).getRank() == hand.get(i + 2).getRank()){
                count++;
                i++;
            }
            i++;
        }

        if (count == 1){
            return true;
        }
        else{
            return false;
        }
    }
    
    // Two Pair: Two different pairs. 
    public boolean twoPair(){
        int count = 0;
        int i = 0;
        
        // Checks how many pairs the hand has
        while (i < 4){
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()){
                count++;
                i++;
            }
            i++;
        }
        
        if (count == 2){
            return true;
        }
        else{
            return false;
        }
    }
    
    // Pair: Two cards of the same rank. 
    public boolean onePair(){
        int count = 0;
        int i = 0;
        
        // Checks how many pairs the hand has
        while (i < 4){
            if (hand.get(i).getRank() == hand.get(i + 1).getRank()){
                count++;
                i++;
            }
            i++;
        }

        if (count == 1){
            return true;
        }
        
        else{
            return false;
        }
    }
}
