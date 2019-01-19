package battleship;

import java.awt.Color;
import java.util.Random;
import javax.swing.JOptionPane;
import bean.Cell;
import properties.Properties;
import properties.STATUS_CODE;

public class Computer extends Player{

	public Computer(Board board) {
		super(board);
	}
	
	@Override
	void placeShips() {
		Random rand=new Random();
		int x,y,z;
		int status=0;
		
		for(int i=0;i<5;i++) {
			status=0;
			x=rand.nextInt(6);
			y=rand.nextInt(6);
			z=rand.nextInt(1);
			if(z==0) {
				//horizontal placement
				if(6-x>=Properties.vesselSize[i]) {
					for(int j=0;j<Properties.vesselSize[i];j++) {
						if(board.cell[x+j][y].getStatus()==STATUS_CODE.SHIP) {
							status=-1;
							break;
						}
						
					}
					if(status==0) {
						for(int j=0;j<Properties.vesselSize[i];j++) {
							board.cell[x+j][y].setVeselNo(i);
							board.cell[x+j][y].setStatus(STATUS_CODE.SHIP);
							veselsCellCount[i]+=1;
						}
						board.vslStatus[i]=2;
						board.shipsPlaced+=1;
						
					}else{
						i-=1;
					}
					
				}else {
					i-=1;
				}
		}else {
				//vertical placement
				if(6-y>=Properties.vesselSize[i]) {
					for(int j=0;j<Properties.vesselSize[i];j++) {
						if(board.cell[x][y+j].getStatus()==STATUS_CODE.SHIP) {
							status=-1;
							break;
						}
						
					}
					if(status==0) {
						for(int j=0;j<Properties.vesselSize[i];j++) {
							board.cell[x][y+j].setVeselNo(i);
							board.cell[x][y+j].setStatus(STATUS_CODE.SHIP);
							veselsCellCount[i]+=1;
						}
					}else{
						i-=1;
					}
					
				}else {
					i-=1;
				}		
			}
		}
		
		
		
	}
	
	Cell selectCellToAttack() {
		Cell cell=null;
			//difficulty easy
			Random rand=new Random();
			int x=rand.nextInt(6);
			int y=rand.nextInt(6);
			cell=new Cell();
			cell.setX(x);
			cell.setY(y);
		
		return cell;
		
	}
	
	void launchAttack(){
		Cell cell=selectCellToAttack();
		STATUS_CODE status=BattleShip.game.user.board.cell[cell.getX()][cell.getY()].getStatus();
		while(status==STATUS_CODE.MISS|| status==STATUS_CODE.HIT||status==STATUS_CODE.SUNK) {
			cell=selectCellToAttack();
			status=BattleShip.game.user.board.cell[cell.getX()][cell.getY()].getStatus();
		}
		
		BattleShip.game.attackOnUser(cell);
		if(BattleShip.gameStarted==true) {
		JOptionPane.showMessageDialog(null, "Your turn");
		}
		
	}

}
