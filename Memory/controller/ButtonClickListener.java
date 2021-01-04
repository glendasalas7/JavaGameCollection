package Memory.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import Memory.model.Stats;
import Memory.view.GameScreen;
import Memory.view.EndScreen;
import Memory.view.MemMenuScreen;

public class ButtonClickListener implements ActionListener {
	public GameScreen panel;
	public ArrayList<String> card;
	private Stats stats;
	public JFrame window;
	private long elapsedTime;//end time
	private int count;//count of cards that are actve
	private int tracker;//keeps track of what card is being compared to the rest
	private long startTime;//start time
	
	public ButtonClickListener(GameScreen panel, ArrayList<String> card, Stats stats){
		this.panel = panel;
		this.card = card;
		window = panel.getWindow();
		count = 0;
		startTime = System.currentTimeMillis();
		this.stats = stats;
	}

	public void checkRemaining(GameScreen panel, long elapsed){
		for(var c: panel.getmemoryCards()){
			if(c.isVisible() == true) return;
		}
		elapsedTime = (System.currentTimeMillis() - elapsed)/1000;
		stats.setCT(elapsedTime);
		window.getContentPane().removeAll();
		var completed = new EndScreen(window, elapsedTime, stats);
		completed.stepIn();
		window.pack();
		window.revalidate();
	}

	public GameScreen checkButtonCount(GameScreen panel, int cardCount){//Ensure only 2 cards active at a time
		for(var c:panel.getmemoryCards()){
			if(c.getText() != "")
				c.setText("");
		}
		return panel;
	}

	public void checkButtons(GameScreen panel, String cardSymbol, JButton card, int t){//Check pairs for matches
		int count = 1;
		for(var c: panel.getmemoryCards()){
			if(c.getText() == cardSymbol && t != count){
				c.setVisible(false);
				card.setVisible(false);
				return;
			}
			count++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		var button = e.getSource();//returns reference of button
		if(button == panel.getExitButton()){
			stats.decrementGC();
			window.getContentPane().removeAll();
			var memMenu = new MemMenuScreen(window, stats);
			memMenu.enter();
			window.pack();
			window.revalidate(); //changing contents			
		} else { //flipping and unflipping card
			if(button == panel.getmemoryCards().get(0)){ //Card 1
				String mem1 = card.get(0);
				if(panel.getmemoryCards().get(0).getText() == mem1){
					panel.getmemoryCards().get(0).setText("");
					mem1 = "";
					count--;
				}
				else if(panel.getmemoryCards().get(0).getText() == ""){
					if(count >=2){
						checkButtonCount(panel, count);
						count = 0;
					}
					panel.getmemoryCards().get(0).setText(mem1);
					count++;
					tracker = 1;
					checkButtons(panel, mem1, panel.getmemoryCards().get(0), tracker);
					checkRemaining(panel, startTime);
				}
			}else if(button == panel.getmemoryCards().get(1)){ //Card 2
				String mem2= card.get(1);
				if(panel.getmemoryCards().get(1).getText() == mem2){
					panel.getmemoryCards().get(1).setText(""); 
					mem2 = "";
					count--;
				}
				else if(panel.getmemoryCards().get(1).getText() == ""){
					if(count >=2){
						checkButtonCount(panel, count);
						count = 0;
					}
					panel.getmemoryCards().get(1).setText(mem2);
					count++;
					tracker = 2;
					checkButtons(panel, mem2, panel.getmemoryCards().get(1), tracker);
					checkRemaining(panel, startTime);
				}
			}else if(button == panel.getmemoryCards().get(2)){ //Card 3
				String mem3 = card.get(2);
				if(panel.getmemoryCards().get(2).getText() == mem3){
					panel.getmemoryCards().get(2).setText(""); 
					mem3 = "";
					count--;
				}
				else if(panel.getmemoryCards().get(2).getText() == ""){
					if(count >=2){
						checkButtonCount(panel, count);
						count = 0;
					}
					panel.getmemoryCards().get(2).setText(mem3);
					count++;
					tracker = 3;
					checkButtons(panel, mem3, panel.getmemoryCards().get(2), tracker);
					checkRemaining(panel, startTime);
				}
			}else if(button == panel.getmemoryCards().get(3)){ //Card 4
		    	String mem4 = card.get(3);
				if(panel.getmemoryCards().get(3).getText() == mem4){
					panel.getmemoryCards().get(3).setText(""); 
					mem4 = "";
					count--;
				}
				else if(panel.getmemoryCards().get(3).getText() == ""){
					if(count >=2){
						checkButtonCount(panel, count);
						count = 0;
					}
					panel.getmemoryCards().get(3).setText(mem4);
					count++;
					tracker = 4;
					checkButtons(panel, mem4, panel.getmemoryCards().get(3), tracker);
					checkRemaining(panel, startTime);
				}
			}else if(button == panel.getmemoryCards().get(4)){ //Card 5
				String mem5 = card.get(4);
				if(panel.getmemoryCards().get(4).getText() == mem5){
					panel.getmemoryCards().get(4).setText(""); 
					mem5 = "";
					count--;
				}
				else if(panel.getmemoryCards().get(4).getText() == ""){
					if(count >=2){
						checkButtonCount(panel, count);
						count = 0;
					}
					panel.getmemoryCards().get(4).setText(mem5);
					count++;
					tracker = 5;
					checkButtons(panel, mem5, panel.getmemoryCards().get(4), tracker);
					checkRemaining(panel, startTime);
				}
			}else if(button == panel.getmemoryCards().get(5)){ //card 6
		 		String mem6 = card.get(5);
				if(panel.getmemoryCards().get(5).getText() == mem6){
					panel.getmemoryCards().get(5).setText(""); 
					mem6 = "";
					count--;
				}
				else if(panel.getmemoryCards().get(5).getText() == ""){
					if(count >=2){
						checkButtonCount(panel, count);
						count = 0;
					}
					panel.getmemoryCards().get(5).setText(mem6);
					count++;
					tracker = 6;
					checkButtons(panel, mem6, panel.getmemoryCards().get(5), tracker);
					checkRemaining(panel, startTime);
				}
			}else if(button == panel.getmemoryCards().get(6)){ //card 7
				String mem7 = card.get(6);
				if(panel.getmemoryCards().get(6).getText() == mem7){
					panel.getmemoryCards().get(6).setText(""); 
					mem7 = "";
					count--;
				}
				else if(panel.getmemoryCards().get(6).getText() == ""){
					if(count >=2){
						checkButtonCount(panel, count);
						count = 0;
					}
					panel.getmemoryCards().get(6).setText(mem7);
					count++;
					tracker = 7;
					checkButtons(panel, mem7, panel.getmemoryCards().get(6), tracker);
					checkRemaining(panel, startTime);
				}
			}else if(button == panel.getmemoryCards().get(7)){//card 8
				String mem8 = card.get(7);
				if(panel.getmemoryCards().get(7).getText() == mem8){
					panel.getmemoryCards().get(7).setText(""); 
					mem8 = "";
					count--;
				}
				else if(panel.getmemoryCards().get(7).getText() == ""){
					if(count >=2){
						checkButtonCount(panel, count);
						count = 0;
					}
					panel.getmemoryCards().get(7).setText(mem8);
					count++;
					tracker = 8;;
					checkButtons(panel, mem8, panel.getmemoryCards().get(7), tracker);
					checkRemaining(panel, startTime);
				}
			}else if(button == panel.getmemoryCards().get(8)){//card 9
				String mem9 = card.get(8);
				if(panel.getmemoryCards().get(8).getText() == mem9){
					panel.getmemoryCards().get(8).setText(""); 
					mem9 = "";
					count--;
				}
				else if(panel.getmemoryCards().get(8).getText() == ""){
					if(count >=2){
						checkButtonCount(panel, count);
						count = 0;
					}
					panel.getmemoryCards().get(8).setText(mem9);
					count++;
					tracker = 9;
					checkButtons(panel, mem9, panel.getmemoryCards().get(8), tracker);
					checkRemaining(panel, startTime);
				}
			}else if(button == panel.getmemoryCards().get(9)){//card 10
				String mem10 = card.get(9);
				if(panel.getmemoryCards().get(9).getText() == mem10){
					panel.getmemoryCards().get(9).setText(""); 
					mem10 = "";
					count--;
				}
				else if(panel.getmemoryCards().get(9).getText() == ""){
					if(count >=2){
						checkButtonCount(panel, count);
						count = 0;
					}
					panel.getmemoryCards().get(9).setText(mem10);
					count++;
					tracker = 10;
					checkButtons(panel, mem10, panel.getmemoryCards().get(9), tracker);
					checkRemaining(panel, startTime);
				}
			}
		}
	}
}
