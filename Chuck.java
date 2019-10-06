
/**
 * Creating the components of Chuckaluck game
 *
 * @author Alaine Banninga
 * @version March 20th, 2018
 */
public class Chuck
{
    private GVdie d1;
    private GVdie d2;
    private GVdie d3;
    private int v1;
    private int v2; 
    private int v3;
    private int credits;
    private String message;

    private boolean betOnOnes;
    private boolean betOnTwos;
    private boolean betOnThrees;
    private boolean betOnFours;
    private boolean betOnFives;
    private boolean betOnSixes;
    private boolean betOnSmall;
    private boolean betOnField;
    private boolean betOnLarge;

    public static final int ONES = 1;
    public static final int TWOS = 2;
    public static final int THREES = 3;
    public static final int FOURS = 4;
    public static final int FIVES = 5;
    public static final int SIXES = 6;
    public static final int SMALL = 7;
    public static final int FIELD = 8;
    public static final int LARGE = 9;

    /** 
     * Constructor for class Chuck
     * Initializes private instance variables
     */
    public Chuck() {
        d1 = new GVdie();
        d2 = new GVdie();
        d3 = new GVdie();

        v1 = 0;
        v2 = 0;
        v3 = 0;

        credits = 10;
        message = "Welcome to my game!";
    }

    /**
     * Getter for game message
     * 
     * @return Returns current game message
     */
    public String getMessage(){
        return message;
    }

    /**
     * Getter for game credits
     * 
     * @returns Returns current amount of credits
     */
    public int getCredits(){
        return credits;
    }

    /**
     * Allows player to manually add a specified number of credits
     * 
     * @param Number of credits the player is adding
     */
    public void addCredits(int amount){
        if(amount > 0){
            credits = credits + amount;
        }
    }

    /**
     * Method for placing bets on what outcome of dice roll will be
     * 
     * @param Number of specified bet 1-9
     */
    public void placeBet(int choice){
        if(choice == Chuck.ONES){
            betOnOnes = true;
        }
        if(choice == Chuck.TWOS){
            betOnTwos = true;
        }
        if(choice == Chuck.THREES){
            betOnThrees = true;
        }
        if(choice == Chuck.FOURS){
            betOnFours = true;
        }
        if(choice == Chuck.FIVES){
            betOnFives = true;
        }
        if(choice == Chuck.SIXES){
            betOnSixes = true;
        }
        if(choice == Chuck.SMALL){
            betOnSmall = true;
        }
        if(choice == Chuck.FIELD){
            betOnField = true;
        }
        if(choice == Chuck.LARGE){
            betOnLarge = true;
        }
    }

    /**
     * Cancels whichever bet user enters as a param
     * 
     * @param Number of bet user would like to cancel
     */
    public void cancelBet(int bet){
        if(bet == Chuck.ONES){
            betOnOnes = false;
        }
        if(bet == Chuck.TWOS){
            betOnTwos = false;
        }
        if(bet == Chuck.THREES){
            betOnThrees = false;
        }
        if(bet == Chuck.FOURS){
            betOnFours = false;
        }
        if(bet == Chuck.FIVES){
            betOnFives = false;
        }
        if(bet == Chuck.SIXES){
            betOnSixes = false;
        }
        if(bet == Chuck.SMALL){
            betOnSmall = false;
        }
        if(bet == Chuck.FIELD){
            betOnField = false;
        }
        if(bet == Chuck.LARGE){
            betOnLarge = false;
        }
    }

    /**
     * Rolls dice after checking if enough credits for current bets
     * Puts value of each die into their respective current values
     * Checks which bets were won or lost
     * Clears all bets at the end of roll
     */
    public void roll(){

        if(enoughCredits()){
            d1.roll();
            d2.roll();
            d3.roll();

            v1=d1.getValue();
            v2=d2.getValue();
            v3=d3.getValue();

            checkAllBets();
            clearAllBets();
        }
        else{ 
            message = "You do not have enough credits";
            v1 = 0;
            v2 = 0;
            v3 = 0;
        }
    }

    /**
     * Resets the game to initial conditions of variables
     */
    public void reset(){
        v1 = 0;
        v2 = 0;
        v3 = 0;
        credits = 10;
        message = "Welcome to my game!";
    }

    /**
     * Clears all bets that were made
     */
    public void clearAllBets(){
        int i = 1; 
        while(i <= 9 && i >= 1){
            cancelBet(i);
            i++;
        }
    }

    /**
     * Checking if a double was rolled matching one of the placed bets
     * 
     * @return Returns true if bet double was rolled, false otherwise
     */
    private boolean isDoubles(int num){
        if(v1 == num && v2 == num)
            return true;
        else if (v1 == num && v3 == num)
            return true;
        else if (v3 == num && v2 == num)
            return true;
        else
            return false;
    }

    /**
     * Checking if a triple was rolled matching one of the placed bets
     * 
     * @return Returns true if bet triple was rolled, false otherwise
     */
    private boolean isTriplets(){
        if(v1 == v2 && v2 == v3)
            return true;
        else return false;
    }

    /**
     * Checking if the large bet was won or lost
     * Subtracting one credit for making the bet,
     * adding 2 credits if bet is won
     */
    private void checkLarge(){
        credits--;
        if((v1 + v2 + v3 > 10) && (!isTriplets())){
            credits = credits + 2;
            message = "You won!";
        }
    }

    /**
     * Checking if the small bet was won or lost
     * Subtracting one credit for making the bet,
     * adding 2 credits if the bet is won
     */
    private void checkSmall(){
        credits--;
        if((v1 + v2 + v3 < 11) && (!isTriplets())){
            credits = credits + 2;
            message = "You won!";
        }
    }

    /**
     * Checking if the field bet was won or lost
     * Subtracting one credit for making the bet,
     * adding 2 credits if the bet is won
     */
    private void checkField(){
        credits--;
        if((v1 + v2 + v3 < 8) || (v1 + v2 + v3 > 12)){
            credits = credits + 2;
            message = "You won!";
        }
    }

    /** 
     * Checking whichever bet (1-6) and adding the appropriate
     * number of credits depending on if a single, double, or
     * triple was won. 1 credit subtracted for making each bet.
     * 
     * @param The number of whichever bet the user wants checked
     */
    private void checkNumber(int num){
        credits--;
        if(isTriplets() && v1 == num){
            credits = credits + 11;
            message = "You won!";
        }
        else if(isDoubles(num)){
            credits = credits + 3;
            message = "You won!";
        }
        else if(v1 == num || v2 == num || v3 == num){
            credits = credits + 2;
            message = "You won!";
        }
    }

    /**
     * Checking each bet to see if bet was won
     */
    private void checkAllBets(){
        message = "Sorry, you lose. Try again.";
        if(betOnOnes){
            checkNumber(Chuck.ONES);
        }
        if(betOnTwos){
            checkNumber(Chuck.TWOS);
        }
        if(betOnThrees){
            checkNumber(Chuck.THREES);
        }
        if(betOnFours){
            checkNumber(Chuck.FOURS);
        }
        if(betOnFives){
            checkNumber(Chuck.FIVES);
        }
        if(betOnSixes){
            checkNumber(Chuck.SIXES);
        }
        if(betOnSmall){
            checkSmall();
        }
        if(betOnField){
            checkField();
        }
        if(betOnLarge){
            checkLarge();
        }
    }

    /**
     * Checking if enough credits are available for selected bets
     * 
     * @return Returns true if enough credits, false otherwise
     */
    public boolean enoughCredits(){
        int creditsNeeded = 0;
        if(betOnOnes)
            creditsNeeded++;
        if(betOnTwos)
            creditsNeeded++;
        if(betOnThrees)
            creditsNeeded++;
        if(betOnFours)
            creditsNeeded++;
        if(betOnFives)
            creditsNeeded++;
        if(betOnSixes)
            creditsNeeded++;
        if(betOnSmall)
            creditsNeeded++;
        if(betOnField)
            creditsNeeded++;
        if(betOnLarge)
            creditsNeeded++;

        if(creditsNeeded <= credits){
            return true;
        }
        else return false;
    }

    /**
     * Test rolling the dice and using the enoughCredits, checkAllBets,
     * and clearAllBets methods.
     * 
     * @param pv1, pv2, and pv3 are values that will be passed to v1, 
     * v2, and v3 respectively to simulate rolling the dice
     */
    public void testRoll(int pv1, int pv2, int pv3){

        if(enoughCredits()){
            v1 = pv1;
            v2 = pv2;
            v3 = pv3;

            checkAllBets();
            clearAllBets();
        }
        else{ 
            message = "You do not have enough credits";
            v1 = 0;
            v2 = 0;
            v3 = 0;
        }
    }

    /** 
     * Returns the value of the requested GVDie
     * 
     * @param the number of requested die (1-3)
     * @return Returns die matching parameter num
     */
    public GVdie getDie(int num){
        if(num == 1)
            return d1;
        else if(num == 2)
            return d2;
        else
            return d3;
    }
}
