package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import bean.Cell;
import properties.Properties;
import properties.STATUS_CODE;

public class Board extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Cell cell[][];
	JLabel lbl;
	char owner;
	
	int shipsPlaced;
	boolean vslInProgres;
	int vslStatus[];
	int shipSunk=0;
	//vslStatus[i]=0 =>ship not placed
	//vslStatus[i]=1 =>ship being placed
	//vslStatus[i]=2 =>ship placed
		 	
	//called only once
	private void GUI(){
		
		cell=new Cell[Properties.boardSize][Properties.boardSize];
		
		//create layout
		GridLayout gridLayout=new GridLayout(Properties.boardSize, Properties.boardSize);
		gridLayout.setHgap(10);
		gridLayout.setVgap(10);
		
		
		//setLayout
		this.setLayout(gridLayout);
		int index=0;
		//add labels
		for(int i=0;i<Properties.boardSize;i++) {
			for(int j=0;j<Properties.boardSize;j++) {
				
				if(owner=='c') {
					lbl=new JLabel(""+STATUS_CODE.HIDN);
				}else {
					lbl=new JLabel(""+STATUS_CODE.FREE);
				}
				
				lbl.setName(i+""+j);
				lbl.setForeground(Color.GREEN);
				
				//update status code
				Cell cel=new Cell();
				
				cel.setX(i);
				cel.setY(j);
				cel.setVeselNo(-1);
				cel.setStatus(STATUS_CODE.FREE);
				cell[i][j]=cel;
				
				lbl.addMouseListener(this);
				lbl.setBorder(BorderFactory.createLineBorder(Color.BLUE));
				index=i*Properties.boardSize+j;
				this.add(lbl,index);
			}
		}
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.setSize(600, 400);
		this.setBackground(Color.BLACK);
		this.setVisible(true);
	}
	
	
	
	public Board(char c) {
		// TODO Auto-generated constructor stub
		owner=c;
		shipsPlaced=0;
		vslStatus= new int[5];
		for(int i=0;i<5;i++) {
			vslStatus[i]=0;
		}
		vslInProgres=false;
		
		
		GUI();
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		lbl=(JLabel) e.getSource();
		String name=lbl.getName();
		int x,y;
		
		//get cell index
		x=Character.getNumericValue(name.charAt(0));
		y=Character.getNumericValue(name.charAt(1));
		
		// placeShip()`on user board 
		if(owner=='u') {
			//ship place
			if(vslInProgres==true) {
				int index=getVslInProgress();
				if(BattleShip.game.user.veselsCellCount[index]<=Properties.vesselSize[index]) {
					if(cell[x][y].getStatus()==STATUS_CODE.FREE) {
						
						BattleShip.game.user.veselsCellCount[index]+=1;
						
						cell[x][y].setStatus(STATUS_CODE.SHIP);
						cell[x][y].setVeselNo(index);
						
						
						lbl.setText(""+STATUS_CODE.SHIP);
						lbl.setForeground(Color.cyan);
						if(BattleShip.game.user.veselsCellCount[index]==Properties.vesselSize[index]) {
							vslStatus[index]=2;
							vslInProgres=false;
							shipsPlaced+=1;
						}
					}	
				}
					
			}
		}
		else {
			
			//attack handle on computer board
			if(BattleShip.gameStarted==true) {
				if(cell[x][y].getStatus()==STATUS_CODE.SHIP) {
					cell[x][y].setStatus(STATUS_CODE.HIT);
					lbl.setText("HIT");
					lbl.setForeground(Color.RED);	
					BattleShip.game.computer.veselsCellCount[cell[x][y].getVeselNo()]-=1;
					//is ship sunk
					if(BattleShip.game.computer.veselsCellCount[cell[x][y].getVeselNo()]==0) {
						for(int i=0;i<Properties.boardSize;i++) {
							for(int j=0;j<Properties.boardSize;j++) {
								if(cell[i][j].getVeselNo()==cell[x][y].getVeselNo()) {
									lbl=(JLabel)BattleShip.game.computer.board.getComponent(i*Properties.boardSize+j);
									lbl.setText("SINK");
									lbl.setForeground(Color.BLACK);
								}
							}
						}
						shipSunk+=1;
					}
					
				}
				else if(cell[x][y].getStatus()==STATUS_CODE.FREE ) {
					cell[x][y].setStatus(STATUS_CODE.MISS);
					lbl.setText("MISS");
					lbl.setForeground(Color.WHITE);
				}
				
				//check if user won
				if(shipSunk==5) {
					JOptionPane.showMessageDialog(this, "Congratulations You Won");
					BattleShip.gameStarted=false;
				}
				else{
					//now computer's turn to attack
					JOptionPane.showMessageDialog(null, "My Turn");
					BattleShip.game.computer.launchAttack();
				}
			}
			
		}
		
	}



	private int getVslInProgress() {
		// TODO Auto-generated method stub
		int index=-1;
		for(int i=0;i<Properties.boardSize;i++) {
			if(vslStatus[i]==1) {
				index=i;
				break;
			}
		}
		return index;
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
