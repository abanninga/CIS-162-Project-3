
/**
 * Testing methods from Chuck class
 *
 * @author Alaine Banninga
 * @version March 20th, 2018
 */
public class ChuckTest
{
   public static void main(String [] args){
        int before = 0;
        Chuck game = new Chuck();
        
        System.out.println("Testing begins...");
        if(game.getCredits() != 10){
            System.out.println("FAIL: credits should start at 10");
        }
        
        // wins bet on Large
        before = game.getCredits();
        game.placeBet(Chuck.LARGE);
        game.testRoll(6,3,3);
        if(game.getCredits() != (before+1)){
            System.out.println("FAIL: should have won betting on Large");
        }        
        
        // loses bet on Large
        before = game.getCredits();
        game.placeBet(Chuck.LARGE);
        game.testRoll(2,3,3);
        if(game.getCredits() != (before-1)){
            System.out.println("FAIL: should have lost betting on Large");
        }  
        
        //adding credits
        game.addCredits(-3);
        if(game.getCredits() != 10)
            System.out.println("FAIL: negative credits should not have been added");
        else
            System.out.println("addCredit method working correctly");
        
        // placing and canceling bets
        game.placeBet(Chuck.THREES);
        game.placeBet(Chuck.FIELD);
        game.cancelBet(Chuck.THREES);
        
        // resetting the game and checking the message and credits
        game.reset();
        if(!game.getMessage().equals("Welcome to my game!"))
            System.out.println("Error - message was not reset");
        else
            System.out.println("Message reset correctly");
        if(game.getCredits() != 10)
            System.out.println("Error - credits not reset");
        else
            System.out.println("Credits reset correctly");
        
        System.out.println("Testing completed.");
    }
}
