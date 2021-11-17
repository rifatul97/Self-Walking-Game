
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * Project: Self-Walking Board Game 
 */

public class sawgame extends JFrame {
	
   JTextField tfDisplay;  
   
   public sawgame() {
	   
      Container cp = getContentPane();
      cp.setLayout(new FlowLayout());
      setLocationRelativeTo(null);
      tfDisplay = new JTextField(10);
 
      JButton btn = new JButton("Press to start the game!");
      
      cp.add(btn);
      
      btn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             boolean validInput = false;  
             boolean validInput2 = false;
             int n;
             int k;
             
             String inputStr = JOptionPane.showInputDialog("Enter the board size ");
             
             do {
                try {
                   n = Integer.parseInt(inputStr);
                } catch (NumberFormatException ex) {
                   n = -1;  
                }
                if (n < 2) {
                   inputStr = JOptionPane.showInputDialog("The number is out of range!");  
                }
                else{
                	validInput = true;
                }
             } while (!validInput);
             
             String i2 = JOptionPane.showInputDialog("Enter number of players: ");
             
             do {
                 try {
                   k = Integer.parseInt(i2);
                 } catch (NumberFormatException ex) {
                    k = -1;  
                 }
                 if (k < 2 ) {
                    i2 = JOptionPane.showInputDialog("There should be at least two player!");
                    continue;
                 } else if(k > n*n){
                	 i2 = JOptionPane.showInputDialog("But the number of players cannot be higher than the availiable board space!(" + n*n + ")");
                 }
                 else{
                	 JOptionPane.showMessageDialog(null, "The game starts NOW!!!");
                     validInput2 = true;
                 }
              } while (!validInput2); 
             SAWGAMEGridLayout gui = new SAWGAMEGridLayout(n,k);
             
             setLocationRelativeTo(null);
             
       	  gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          }
       });
      
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setSize(320, 160);
      setTitle("Self-Walking Board Game!");
      setVisible(true);
   }
 
   public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new sawgame();
         }
      });
   }
}