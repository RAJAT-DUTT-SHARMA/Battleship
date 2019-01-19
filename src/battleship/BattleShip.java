package battleship;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import bean.Cell;
import properties.Properties;
import properties.STATUS_CODE;

public class BattleShip implements ActionListener {

	JFrame frame;
	JPanel initialScreen;
	
	User user;
	Computer computer;
	static BattleShip game;
	
	static Boolean gameStarted=false;	
	

	public static void main(String[] args) {
		// create a UI
		
		game=new BattleShip();
		game.createPlayers();
		game.createUI();
		
	}
	void createPlayers(){
		user=new User(new Board('u'));
		computer=new Computer(new Board('c'));
	}
	
	void createUI() {
		
		frame=new JFrame("BATTLESHIP");
		frame.setSize(1000, 600);
		frame.setResizable(false);
		
		//set initial content of screen	
		initialScreen=new JPanel();
		JLabel welcmLbl=new JLabel("WELCOME USER .........................");
		welcmLbl.setForeground(Color.GREEN);
		welcmLbl.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 40));
		
		JLabel chooseLbl=new JLabel("Please choose the difficulty level :  ");
		chooseLbl.setForeground(Color.BLUE);
		
		JButton buttonEasy=new JButton("EASY");
		buttonEasy.setMaximumSize(new Dimension(40, 20));
		buttonEasy.addActionListener(new ActionListener() {
		
			
			@Override
			public void actionPerformed(ActionEvent e) {
					Properties.difficultyLevel=0;		
					createGameUI();
			}
		});
		
		JButton buttonDif=new JButton("Difficult");
		buttonDif.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Properties.difficultyLevel=1;
				createGameUI();
			}
		});
		
		FlowLayout layout=new FlowLayout();
		layout.setHgap(20);
		layout.setVgap(100);
		layout.setAlignment(FlowLayout.CENTER);
		
		
		initialScreen.setBackground(Color.BLACK);
		initialScreen.setLayout(layout);
		
		initialScreen.add(welcmLbl);
		initialScreen.add(chooseLbl);
		initialScreen.add(buttonEasy);
		initialScreen.add(buttonDif);
		
		initialScreen.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		initialScreen.setVisible(true);
		frame.add(initialScreen);
		
		frame.setVisible(true);
		
	}
	
	void createGameUI() {
		initialScreen.removeAll();
		
		initialScreen.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
		
		BorderLayout borderLayout=new BorderLayout();
		borderLayout.setVgap(40);
		initialScreen.setLayout(borderLayout);
		
		JLabel welLbl=new JLabel("B A T T L E S H I P");
		welLbl.setHorizontalAlignment(SwingConstants.CENTER);
		welLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		initialScreen.add(welLbl, BorderLayout.NORTH);
		
		//GUI USER BOARD
		JPanel panelUsr=new JPanel();
		panelUsr.setLayout(new BorderLayout());
		JLabel lblUser=new JLabel("\tY O U\t");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setForeground(Color.DARK_GRAY);
		lblUser.setBorder(BorderFactory.createLineBorder(Color.cyan));
		lblUser.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		panelUsr.add(lblUser,BorderLayout.NORTH);
		panelUsr.add(user.board,BorderLayout.CENTER);
		initialScreen.add(panelUsr, BorderLayout.WEST);
		
		//GUI COMPUTER BOARD
		JPanel panelComputer=new JPanel();
		panelComputer.setLayout(new BorderLayout());
		JLabel lblComputer=new JLabel("C O M P U T E R");
		lblComputer.setHorizontalAlignment(SwingConstants.CENTER);
		lblComputer.setForeground(Color.DARK_GRAY);
		lblComputer.setBorder(BorderFactory.createLineBorder(Color.cyan));
		lblComputer.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		panelComputer.add(lblComputer, BorderLayout.NORTH);
		panelComputer.add(computer.board,BorderLayout.CENTER);
		initialScreen.add(panelComputer, BorderLayout.EAST);
		
		//GUI RULES
		TextArea ruleLbl=new TextArea("RULES :\n\tClick on opponents board to attack. \n\n\t  FREE : Nothing on the cell \n\t  SHIP : There's a part of ship on the cell \n\t   HIT : Ship is Hit\n\t  MISS : Attack was a miss .\n\t  SINK : All Cell of ship HIT.\n\t  HIDN : Computer cell hidden");
		ruleLbl.setForeground(Color.BLUE);
		ruleLbl.setBackground(Color.WHITE);
		ruleLbl.setEnabled(false);
		ruleLbl.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 15));
		initialScreen.add(ruleLbl,BorderLayout.SOUTH);
		
		//GUI PLACE VESSELS
		JPanel vesselPnl=new JPanel();
		GridLayout gridLayout=new GridLayout(6, 1);
		gridLayout.setVgap(10);
		vesselPnl.setLayout(gridLayout);
		
		JButton adVsl1=new JButton("Add Vessel 1");
		adVsl1.addActionListener(this);
		adVsl1.setName("vsl1");
		JButton adVsl2=new JButton("Add Vessel 2");
		adVsl2.setName("vsl2");
		adVsl2.addActionListener(this);
		JButton adVsl3=new JButton("Add Vessel 3");
		adVsl3.addActionListener(this);
		adVsl3.setName("vsl3");
		JButton adVsl4=new JButton("Add Vessel 4");
		adVsl4.addActionListener(this);
		adVsl4.setName("vsl4");
		JButton adVsl5=new JButton("Add Vessel 5");
		adVsl5.addActionListener(this);
		adVsl5.setName("vsl5");
		JButton btnGo=new JButton("Start the game");
		btnGo.addActionListener(this);
		btnGo.setName("go");
		
		vesselPnl.add(adVsl1);
		vesselPnl.add(adVsl2);
		vesselPnl.add(adVsl3);
		vesselPnl.add(adVsl4);
		vesselPnl.add(adVsl5);
		vesselPnl.add(btnGo);
		vesselPnl.setBackground(Color.BLACK);
		vesselPnl.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
		
		initialScreen.add(vesselPnl,BorderLayout.CENTER);
		
		// update the UI
		initialScreen.updateUI();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		switch(((JButton)e.getSource()).getName()) {
		
			case "vsl1":
				actionTake(1);
				break;
			case "vsl2":
				actionTake(2);
				break;
			case "vsl3":
				actionTake(3);
				break;
			case "vsl4":
				actionTake(4);
				break;
			case "vsl5":
				actionTake(5);
				break;
			case "go":
				if(user.board.shipsPlaced==5) {
					JOptionPane.showMessageDialog(frame, "Your Turn . Click on any cell , on Computer's board to attack .");
					computer.placeShips();
					gameStarted=true;
					
				}else {
					JOptionPane.showMessageDialog(frame, "Place all the vessels on board first.");
				}
				
				break;
		}
	}
	
	void actionTake(int vslNo){
		int vslSize=Properties.vesselSize[vslNo-1];
		if(user.board.vslStatus[vslNo-1]==2) {
			JOptionPane.showMessageDialog(frame, "Vessel "+ vslNo+ " already placed");
		}
		else if(user.board.vslInProgres==false) {
			JOptionPane.showMessageDialog(frame, "Vessel "+ vslNo+ " size : "+vslSize+" . Choose consecutive cells only");
			user.board.vslStatus[vslNo-1]=1;
			user.board.vslInProgres=true;
		}else {
			if(user.board.vslStatus[vslNo-1]==1) {
				JOptionPane.showMessageDialog(frame,"Vessel "+ vslNo +" placement already in progress");	
			}
			else {
				JOptionPane.showMessageDialog(frame,"Complete placing vessel first");		
			}
		}
	}
	
void attackOnUser(Cell cell) {
		
		int compNumber=cell.getX()*Properties.boardSize+cell.getY();
		
		JLabel lbl=(JLabel)user.board.getComponent(compNumber);
		System.out.println(lbl.getName().equals(cell.getX()+""+cell.getY()));
		if(user.board.cell[cell.getX()][cell.getY()].getStatus()==STATUS_CODE.SHIP) {
			user.board.cell[cell.getX()][cell.getY()].setStatus(STATUS_CODE.HIT);
			lbl.setText("HIT");
			lbl.setForeground(Color.RED);
			
			user.veselsCellCount[user.board.cell[cell.getX()][cell.getY()].getVeselNo()]-=1;
			//is ship sunk
			if(user.veselsCellCount[user.board.cell[cell.getX()][cell.getY()].getVeselNo()]==0) {
				for(int i=0;i<Properties.boardSize;i++) {
					for(int j=0;j<Properties.boardSize;j++) {
						if(user.board.cell[i][j].getVeselNo()==user.board.cell[cell.getX()][cell.getY()].getVeselNo()) {
							lbl=(JLabel)user.board.getComponent(i*Properties.boardSize+j);
							
							lbl.setText("SINK");
							lbl.setForeground(Color.BLACK);
						}
					}
				}
				user.board.shipSunk+=1;
			
			}
		}
		else{
				user.board.cell[cell.getX()][cell.getY()].setStatus(STATUS_CODE.MISS);
				lbl.setText(""+STATUS_CODE.MISS);
				lbl.setForeground(Color.WHITE);
			}
		

		if(user.board.shipSunk==5) {
			JOptionPane.showMessageDialog(frame, "YOu LOst ");
			gameStarted=false;
		}
		
	}

	
}