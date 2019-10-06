import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/***************************************************************
 * GUI front end to a dice game called Chuck A Luck
 * 
 * @author Scott Grissom 
 * @version September 14, 2013
 ***************************************************************/
public class ChuckGUI extends JFrame implements ActionListener{

    /** visual representation of the dice */
    GVdie d1, d2, d3;

    /** buttons and labels */
    JCheckBox largeCheckBox, smallCheckBox, fieldCheckBox;
    JLabel message, credits;
    Chuck game;

    /** 1-6s checkboxes */
    JCheckBox onesCheckBox, twosCheckBox, threesCheckBox;
    JCheckBox foursCheckBox, fivesCheckBox, sixesCheckBox;

    /** roll button */
    JButton rollButton;

    /** menu items */
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem newGameItem; 
    JMenuItem addItem;

    /****************************************************************
    Main method start the game
     ****************************************************************/    
    public static void main(String args[]){        
        ChuckGUI gui = new ChuckGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Alaine's Game of Chuck A Luck");
        gui.pack();
        gui.setVisible(true);
    }

    /****************************************************************
    Create all elements and display within the GUI
     ****************************************************************/    
    public ChuckGUI(){ 

        // create the game object as well as the GUI Frame   
        game = new Chuck(); 

        // create the lay out
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();
        setBackground(Color.lightGray);

        // position die 1
        d1 = game.getDie(1);
        position.gridx = 2;
        position.gridy = 1;
        position.gridheight = 5;
        add(d1, position);

        // position die 2
        d2 = game.getDie(2);
        position.gridx = 3;
        add(d2, position);

        // position die 3
        d3 = game.getDie(3);   
        position.gridx = 4;
        add(d3, position);

        // position credits label
        position = new GridBagConstraints();        
        credits = new JLabel ();
        credits.setText("Credits: " + game.getCredits()); 
        position.gridx = 2;
        position.gridy = 11;
        add(credits, position);    

        // position message label
        message = new JLabel();
        message.setText(game.getMessage());
        position.gridx = 0;
        position.gridy = 0;
        position.gridwidth = 5;
        position.insets.bottom = 20;
        add(message, position);
        position = new GridBagConstraints(); 

        // create the button and add to grid location (3, 11)
        rollButton = new JButton("Roll");
        position.gridx = 3;
        position.gridy = 11;
        rollButton.addActionListener(this);
        rollButton.setBackground(Color.GREEN);
        add(rollButton, position);

        // position the bet label
        position = new GridBagConstraints(); 
        position.gridx = 0;
        position.gridy = 1;
        add(new JLabel("Your Bets"), position);
        position.anchor = GridBagConstraints.WEST;

        // position the first three checkboxes
        largeCheckBox = new JCheckBox("Large >10");
        position.gridx = 0;
        position.gridy = 2;
        add(largeCheckBox, position);

        smallCheckBox = new JCheckBox("Small <11");
        position.gridy = 3;
        add(smallCheckBox, position);

        fieldCheckBox = new JCheckBox("Field <8 or >12"); 
        position.gridy = 4;
        add(fieldCheckBox, position);

        // positioning 1-6s checkboxes
        onesCheckBox = new JCheckBox("1s"); 
        position.gridy = 5;
        add(onesCheckBox, position);

        twosCheckBox = new JCheckBox("2s"); 
        position.gridy = 6;
        add(twosCheckBox, position);

        threesCheckBox = new JCheckBox("3s"); 
        position.gridy = 7;
        add(threesCheckBox, position);

        foursCheckBox = new JCheckBox("4s"); 
        position.gridy = 8;
        add(foursCheckBox, position);

        fivesCheckBox = new JCheckBox("5s"); 
        position.gridy = 9;
        add(fivesCheckBox, position);

        sixesCheckBox = new JCheckBox("6s"); 
        position.gridy = 10;
        add(sixesCheckBox, position);

        // set up File menu
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        newGameItem = new JMenuItem("New Game");
        addItem = new JMenuItem("Add 10 Credits");
        fileMenu.add(addItem);
        fileMenu.add(newGameItem);
        fileMenu.add(quitItem);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu); 

        // register the three menu items with this action listener
        newGameItem.addActionListener(this);
        quitItem.addActionListener(this);
        addItem.addActionListener(this);
    }

    /****************************************************************
     * Start a new game
     ****************************************************************/
    private void newGame(){
        // reset the game
        game.reset();

        // clear all check boxes in this GUI
        largeCheckBox.setSelected(false);
        fieldCheckBox.setSelected(false);
        smallCheckBox.setSelected(false);
        onesCheckBox.setSelected(false);
        twosCheckBox.setSelected(false);
        threesCheckBox.setSelected(false);
        foursCheckBox.setSelected(false);
        fivesCheckBox.setSelected(false);
        sixesCheckBox.setSelected(false);

    }

    /****************************************************************
    This method called when the roll button or a menu item
    is selected

    @param event - the JComponent just selected
     ****************************************************************/
    public void actionPerformed(ActionEvent event){

        // player selects menu to quit the game
        if (event.getSource() == quitItem)
            System.exit(1);

        // player selects menu item to start a new game    
        if (event.getSource() == newGameItem){
            game.reset();
            newGame();
        }

        // player selects menu item to add more credits    
        if (event.getSource() == addItem){
            game.addCredits(10);
        }

        // player bets on 1  
        if (onesCheckBox.isSelected()){
            game.placeBet(Chuck.ONES);
        }

        // player bets on 2  
        if (twosCheckBox.isSelected()){
            game.placeBet(Chuck.TWOS);
        }

        // player bets on 3  
        if (threesCheckBox.isSelected()){
            game.placeBet(Chuck.THREES);
        }

        // player bets on 4  
        if (foursCheckBox.isSelected()){
            game.placeBet(Chuck.FOURS);
        }

        // player bets on 5  
        if (fivesCheckBox.isSelected()){
            game.placeBet(Chuck.FIVES);
        }

        // player bets on 6  
        if (sixesCheckBox.isSelected()){
            game.placeBet(Chuck.SIXES);
        }

        // player bets on small
        if (smallCheckBox.isSelected()){   
            game.placeBet(Chuck.SMALL);
        }

        // player bets on large
        if (largeCheckBox.isSelected()){
            game.placeBet(Chuck.LARGE);
        }

        // player bets on field
        if (fieldCheckBox.isSelected()){
            game.placeBet(Chuck.FIELD);
        }

        // player clicks on Roll button   
        if (event.getSource() == rollButton){    
            if(!game.enoughCredits())
                JOptionPane.showMessageDialog(null, "Enter More Credits");
            else
                game.roll();  
        }

        // update the credits and message labels
        message.setText(game.getMessage());
        credits.setText("Credits: " + game.getCredits());
    }
}