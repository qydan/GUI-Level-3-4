import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GUI extends Frame implements ActionListener{
    JPanel panel1, panel2, panel3, panel4, mainPanel;
    JLabel message, line1, line2, line3, line4;
    JFrame gameMenu, instructionMenu, game;
    JTextField userGuess;
    JButton instructionButton, playButton, exitButton, backButton, checkButton, backButton2;

    public GUI(String windowType){

        //Create a new JFrame based on which windowtype its trying to create

        if (windowType.equals("menu")){

            //game menu JFrame
            gameMenu = new JFrame("Game Launcher: Number Guessing Game");

            //Jpanels to fill in space and create a borer layout
            mainPanel = new JPanel();
            panel1 = new JPanel();
            panel2 = new JPanel();
            panel3 = new JPanel();
            panel4 = new JPanel();

            //JButtons
            playButton = new JButton("Play Game");
            instructionButton = new JButton("Instructions");
            exitButton = new JButton("Exit");

            //JButtons dimensions
            playButton.setPreferredSize(new Dimension(50,125));
            exitButton.setPreferredSize(new Dimension(50,125));
            instructionButton.setPreferredSize(new Dimension(50,80));

            //Action listeners
            instructionButton.addActionListener(this);
            exitButton.addActionListener(this);
            playButton.addActionListener(this);

            //Adding elements to the Jpanel, main panel
            mainPanel.setLayout(new BorderLayout());
            mainPanel.add(instructionButton, BorderLayout.CENTER);
            mainPanel.add(exitButton, BorderLayout.SOUTH);
            mainPanel.add(playButton, BorderLayout.NORTH);

            //Backgrund coloring
            panel1.setBackground(Color.BLACK);
            panel2.setBackground(Color.BLACK);
            panel3.setBackground(Color.BLACK);
            panel4.setBackground(Color.BLACK);

            //Adding elements to the JFrame menu
            gameMenu.add(panel1, BorderLayout.NORTH);
            gameMenu.add(panel2, BorderLayout.SOUTH);
            gameMenu.add(panel3, BorderLayout.WEST);
            gameMenu.add(panel4, BorderLayout.EAST);
            gameMenu.add(mainPanel);
            gameMenu.setSize(450, 450);
            gameMenu.setVisible(true);
            gameMenu.setLayout(new BorderLayout());
            gameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        else if (windowType.equals("instructions")){

            //Creating instruction JFrame
            instructionMenu = new JFrame("Instructions");
            instructionMenu.getContentPane().setBackground(Color.BLACK); //sets frame background to black

            //JLabel creation
            line1 = new JLabel("   Instructions For a Number Guessing Game"); 
            line2 = new JLabel(" - Enter a valid number in the textfield");
            line3 = new JLabel(" - Check you guess by pressing the check button");
            line4 = new JLabel(" - Guess numbers until you get the number!");

            //Changing text color to white
            line1.setForeground(Color.WHITE);
            line2.setForeground(Color.WHITE);
            line3.setForeground(Color.WHITE);
            line4.setForeground(Color.WHITE);

            //Jbutton
            backButton = new JButton("Back");
            backButton.addActionListener(this);

            //Adding elements to the instruction menu
            instructionMenu.add(line1);
            instructionMenu.add(line2);
            instructionMenu.add(line3);
            instructionMenu.add(line4);
            instructionMenu.add(backButton);
            instructionMenu.setSize(300, 200);
            instructionMenu.setVisible(true);
            instructionMenu.setLayout(new GridLayout(5,1));

        }

        else if (windowType.equals("play")){

            //JFrame for playing game
            game = new JFrame("Number Guessing Game");
            game.getContentPane().setBackground(Color.BLACK); //sets background of frame to black

            //Jlabels
            message = new JLabel("Enter your guess a number between 1-100 in box below"); 
            message.setForeground(Color.WHITE); //sets text to white

            //JTextfield for entering user input
            userGuess = new JTextField("  Enter Number");
            exitButton = new JButton("Exit");
            exitButton.addActionListener(this);
            
            //JButton and action listener (checking the input of the user)
            checkButton = new JButton("Check guess"); 
            checkButton.addActionListener(this);

            //Jbutton and action listener (bback button)
            backButton2 = new JButton("Back");
            backButton2.addActionListener(this);

            //adding elements to the Jpanel for the gridlayout
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.BLACK);
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(checkButton);
            buttonPanel.add(exitButton);
            buttonPanel.add(backButton2);

            //Adding elements to playing game menu
            game.add(message);
            game.add(userGuess);
            game.add(buttonPanel);
            game.setSize(400, 200);
            game.setVisible(true);
            game.setLayout(new GridLayout(3,1));
        }

    }

    //Action performed method for action listener
    public void actionPerformed(ActionEvent e) {

        //performs different actions based on the source using .getSource() method

        if (e.getSource()==instructionButton) {
            gameMenu.dispose(); //closes the game menu
            new GUI("instructions"); //makes new GUI for instructions
            }
    
        else if (e.getSource()==playButton){
            gameMenu.dispose();
            new GUI("play"); //makes new GUI for play
        }
        else if(e.getSource()==checkButton){

            int number = 47;//can use random function to have actual random num but for testing purposes i set to specific number

            String userInput = userGuess.getText(); //gets the text that is within the text field

            try { 
                int UserInteger = Integer.parseInt(userInput); //checks input using parse and try and then changes to intila text to give hints to the user
                if (UserInteger < number){
                    message.setText("Guess Higher!");
                }
                else if (UserInteger > number){
                    message.setText("Guess Lower!");
                }
                else{
                    message.setText("Congratulations! You Guessed The Number!");
                }

            } catch (NumberFormatException nfe){
                message.setText(userInput + " is not a valid Integer, try again");
            }

        }
        
        else if(e.getSource()==backButton){
            new GUI("menu"); //remakes the game menu
            instructionMenu.dispose(); //closes the instruction menu
        }
        else if(e.getSource()==backButton2){
            new GUI("menu");
            game.dispose(); //closes game 
        }
        else {
            System.exit(0);
        }
    }

}

