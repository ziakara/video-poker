// Poker test class.

public class PokerTest{

    public static void main(String[] args){
        if (args.length<1){
            Game g = new Game();
            g.play();
        }
        else{
            Game g = new Game(args);
            g.testPlay();
        }
    }

}
