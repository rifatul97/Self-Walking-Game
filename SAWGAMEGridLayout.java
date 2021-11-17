
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class SAWGAMEGridLayout extends JFrame{

	private JButton[][] square;
	private boolean isfirstturn = true;
	private boolean finishedfirstturns = false;
	private int size = 0;
	private int totalplayer = 0;
	private static int totalgameturn = 0;
	private int currentturn = 1;
	private boolean[][] movesarr;
	private Coords[] initialpts;
	private int currentrow = 0;
	private int currentcol = 0;
	private boolean gameover = false;
	
	public SAWGAMEGridLayout(int n, int k){
		
		super("Self-Walking Video Game");
		setVisible(true);
		
		square = new JButton[n+1][n+1];
		size = n;
		totalplayer = k+1;
		movesarr = new boolean[n+1][n+1];
		initializemovearr(movesarr);
		
        initialpts = new Coords[totalplayer+1];
		Container contents = getContentPane();
		contents.setLayout(new GridLayout(0,n));
		
		ButtonHandler buttonHandler = new ButtonHandler();
		
		for(int i=0; i<n; i++){
			
			for(int j=0; j<n; j++){
				
				square[i][j] = new JButton();
				
				if((i+j)%2 != 0){
					
					square[i][j].setBackground(Color.LIGHT_GRAY);
					
				}
				else{
					square[i][j].setBackground(Color.WHITE);
				}
				
				contents.add(square[i][j]);
				square[i][j].addActionListener(buttonHandler);
				
			}
			
		}
		
		setSize(640,480);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			
			for(int i=0; i<size; i++){
				
				for(int j=0; j<size; j++){
					
					if(source == square[i][j]){
						processClick(i,j);
						return;
					}
				}
			}
		}	
	}
    
    public void createplayer(boolean[] isfirstturn){
    	for(int i=0; i<100; i++){
    		isfirstturn[i] = true;
    	}
    }
    
	public void processClick(int i, int j) {

		if(isfirstturn==true){
			square[i][j].setText("*P1");
			movesarr[i][j] = true;
			square[i][j].getSize();
			isfirstturn = false;
			currentrow = i;
			currentcol = j;
			initialpts[currentturn] = new Coords(i,j);
			currentturn++;
			totalgameturn++;
			return;
		}
		
		
       if(isValidMove(i,j) == false){
    	   return;
       }
	       
		if(finishedfirstturns!=true){
			initialpts[currentturn] = new Coords(i,j);
			 square[i][j].setText("*P" + currentturn);
		}
		else{
			 square[i][j].setText("P" + currentturn);
		}
       
       if(movesarr[i][j]==true){
    	   if(totalgameturn>=4){
    		   if(currentturn==1 || currentturn==totalplayer){
    			   if(initialpts[1].x == i && initialpts[1].y == j){
            		   JOptionPane.showMessageDialog(null, "PLAYER" + currentturn +" WON THE MATCH!");
            		   setVisible(false);
            		   gameover = true;
            	   }
            	   else{
            		   JOptionPane.showMessageDialog(null, "PLAYER" + currentturn +" LOSES!");  
            		   setVisible(false);
            		   gameover = true;
            	   }
    		   }
    		   else if(currentturn==totalplayer-1){
    		    if(initialpts[currentturn].x == i && initialpts[currentturn].y == j){
        		   JOptionPane.showMessageDialog(null, "PLAYER" + currentturn +" WON THE MATCH!");
        		   setVisible(false);
        		   gameover = true;
        	   }
        	   else{
        		   JOptionPane.showMessageDialog(null, "PLAYER" + currentturn +" LOSES!");  
        		   setVisible(false);
        		   gameover = true;
        	   }
		   }
    		   else{
        	   if(initialpts[currentturn+1].x == i && initialpts[currentturn+1].y == j){
        		   JOptionPane.showMessageDialog(null, "PLAYER" + currentturn +" WON THE MATCH!");
        		   setVisible(false);
        		   gameover = true;
        	   }
        	   else{
        		   JOptionPane.showMessageDialog(null, "PLAYER" + currentturn +" LOSES!");  
        		   setVisible(false);
        		   gameover = true;
        	   }
    		   }
        	   
           }
    	   if(gameover==false){
    	   JOptionPane.showMessageDialog(null, "PLAYER" + currentturn +" LOSES!");  
		   setVisible(false);
		   gameover = true;
    	   }
       }
       else{
    	   movesarr[i][j] = true;
       }
       
      
       currentturn++;
       totalgameturn++;
       currentrow = i;
       currentcol = j;
       
       
       if(currentturn == totalplayer){
    	   currentturn = 1;
    	   finishedfirstturns = true;
       }
       
	}

	private boolean isValidMove(int i, int j) {
		
		if(currentcol==size-1 && currentrow == size-1){
			if((i==currentcol-1) && (j==currentrow)){
				return true;
			}
			else if((i==currentcol) && (j==currentcol-1)){
				return true;
			}
			else{
				return false;
			}
		}
		else if(currentrow==size-1 && currentcol == 0){
			if((i==currentrow-1) && (j==currentcol) ){
				return true;
			}
			else if((i==currentrow) && (j==currentcol+1) ){
				return true;
			}
			else{
				return false;
			}
		}
		else if(currentrow==0 && currentcol == 0){
			if(i==currentrow+1 && j==currentcol){
				return true;
			}
			else if(i==currentrow && j==currentcol+1){
				return true;
			}
			else{
				return false;
			}
		}
		else if(currentcol == size-1 && currentrow==0){
			if(i==currentrow+1 && j==currentcol){
				return true;
			}
			else if(i==currentrow && j==currentcol-1){
				return true;
			}
			else{
				return false;
			}
		}
		else if(currentrow == 0 || currentrow == size-1){
			if(currentrow==0){
				if(currentcol==j && currentrow+1 == i){
					return true;
				}
				else if(currentcol+1==j && currentrow==i){
					return true;
				}
				else if(currentcol-1==j && currentrow==i){
					return true;
				}
				else{
					return false;
				}
			}
			else if(currentrow==size-1){
				if(currentcol==j && currentrow-1 == i){
					return true;
				}
				else if(currentcol+1==j && currentrow==i){
					return true;
				}
				else if(currentcol-1==j && currentrow==i){
					return true;
				}
				else{
					return false;
				}
			}
		
		}
		else if(currentcol == 0 || currentcol == size-1){
			if(currentcol==0){
				if(currentrow==i && currentcol+1 == j){
					return true;
				}
				else if(currentrow+1==i && currentcol==j){
					return true;
				}
				else if(currentrow-1==i && currentcol==j){
					return true;
				}
				else{
					return false;
				}
			}
			else if(currentcol==size-1){
				if(currentrow==i && currentcol-1 == j){
					return true;
				}
				else if(currentrow+1==i && currentcol==j){
					return true;
				}
				else if(currentrow-1==i && currentcol==j){
					return true;
				}
				else{
					return false;
				}
			}
		}
		
		else if(currentrow-1==i && currentcol==j){
			return true;
		}
		else if(currentrow+1==i && currentcol==j){
			return true;
		}
		else if(currentrow==i && currentcol+1==j){
			return true;
		}
		else if(currentrow==i && currentcol-1==j){
			return true;
		}
		
		
		return false;
	}
	
	
	public void initializemovearr(boolean[][] moves){
		for(int i=0; i<size; i++){
    		for(int j=0; j<size; j++){
    			moves[i][j] = false;
    		}
    	}
	}
	
        class Coords {
            int x;
            int y;

            public boolean equals(Object o) {
                Coords c = (Coords) o;
                return c.x == x && c.y == y;
            }

            public Coords(int x, int y) {
                super();
                this.x = x;
                this.y = y;
            }

            public int hashCode() {
                return new Integer(x + "0" + y);
            }
        }
        

}
