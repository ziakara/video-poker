READ ME: by Zia Kara

===============================================================================

This is a game of video poker. The user starts off with 100 tokens. 
They are then introduced to the game, and asked how many tokens they want to bet 
before seeing the hand they are dealt. After entering the bet amount, they are dealt 
a hand. They are given the option to reject some, if any, cards, before the hand is 
evaluated. After the hand is evaluated, the result of the hand is printed, as well 
as the amount of tokens they won, if any. Their new evaluated total is printed given 
the amount of tokens they won, or didn't, after the hand. They are then given the 
option to play again, or end the game.

In the card class, the suits and ranks are compared for easier sorting. 
The ranks and suits were also encoded in order to print the Card objects. 
I also added a getSuit() and getRank() method in order to easily compare the 
suit and ranks of cards in my Game class.

In the deck class, a new deck of cards is created, and shuffled. There is also 
deal() to deal a card as well.

The player class keeps track of the player's bankroll. It adds the bankroll if 
the player wins, subtracts the player's initial bet from their bankroll, and 
keeps a running tab of the bankroll.

I also added a few methods relating to the player's hand in order to keep 
track of their hand.

In the game class, in the play method, the actual game of poker happens. 
I ensured the ability to play multiple rounds again was there, as well as 
the bet by the players to be between 1-5, as per the instructions. After testing 
out multiple rounds of video poker, I found the hand kept adding a new hand to the 
previous one. I added a for loop at the end of the game, before starting it again if
 the player wanted to, to remove all the cards from the hand, in order to render it
  empty.

I used this resource to get what each poker hand is composed of:

https://www.cardplayer.com/rules-of-poker/hand-rankings


