import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;




public class Gomoku extends JPanel{
	int count = 0;
	Layout l = new Layout();

	Piece[][] list = l.getList();
	
	Piece restart = new Piece(-1, -1);
	Piece undo = new Piece(-1,-2);
	
	JRadioButton normal_mode = new JRadioButton();
	JRadioButton ai_mode = new JRadioButton();
	
	JLabel white_wins = new JLabel("white                ");
	JLabel black_wins = new JLabel("black                ");
	
	JPanel statsBox = new JPanel();
	int WHITE=0 ;
	int BLACK = 0;
	int ai_count=0;
	
	public Gomoku(){
		//statsBox.setLayout(new BoxLayout(statsBox,BoxLayout.Y_AXIS));
		//Gautham is a piece of shit. he didn't win against my ai. Ha
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		ActionListener b = new ButtonHandler();
		for(int i = 1;i<20;i++){
			for(int j = 1;j<20;j++){
				list[i][j].addActionListener(b);
			}
		}
		statsBox.setPreferredSize(new Dimension(125, 950));
		
		JPanel ui_control = new JPanel();
		
		ui_control.add(restart);
		ui_control.add(undo);
		
		JPanel stats = new JPanel();
		
		stats.add(white_wins);
		stats.add(black_wins);
		
		statsBox.add(ui_control);

		statsBox.add(stats);

		statsBox.add(ai_mode);
		statsBox.add(normal_mode);
		

		ButtonGroup mode = new ButtonGroup();
		mode.add(ai_mode);
		mode.add(normal_mode);
		normal_mode.setSelected(true);
		
		ai_mode.setText("AI mode");
		normal_mode.setText("Human vs Human");
		restart.setText("restart");
		undo.setText("undo last move");
		
		ai_mode.addActionListener(b);
		normal_mode.addActionListener(b);
		restart.addActionListener(b);
		undo.addActionListener(b);
		
		add(l);
		add(statsBox);

}

public class ButtonHandler implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
//normal mode
		int a = 0,b = 0;
		//pro rules
		if(count==1){
			for(int i = 1;i<20;i++){
				for(int j = 1;j<20;j++){
					if(list[i][j].getCount()==0){
						a = i;b=j;System.out.println(a+" "+b);
						}
					
				}
			}
			for(int i = 0;i<5;i++){
				for(int j = 0;j<5;j++){
					list[a-2+i][b-2+j].setEnabled(false);
				}
			}
		}
		
		
		if(count==2){
			for(int i = 1;i<20;i++){
				for(int j = 1;j<20;j++){
					if(list[i][j].getCount()==0){
						a = i;b=j;
						}
					
				}
			}
			for(int i = 0;i<5;i++){
				for(int j = 0;j<5;j++){
					list[a-2+i][b-2+j].setEnabled(true);
				}
			}
		}
		//end pro rules
		if(normal_mode.isSelected()){
			
	
	
		if(e.getActionCommand()=="Piece"){
			
			Piece p = (Piece) e.getSource();
		
			if(p.getBackground()==Color.LIGHT_GRAY){
		
				
				
			if(count%2==0){
				p.setBackground(Color.black);
				p.setCount(count);
			}
			
			else{
				p.setBackground(Color.white);
				p.setCount(count);
			}
			
			count++;
			
			}
		
		if(p.isFiveRow(p, list)){
			JOptionPane.showMessageDialog(null, p.getStringColor()+" wins" );
			if(p.getStringColor().equals("White"))WHITE++;
			else BLACK++;
		}
		
	}
		
		
	else{
	
		if(e.getSource()==restart){
			Layout.resetBoard(list);
			count=0;
			white_wins.setText("White: "+WHITE);
			black_wins.setText("Black: "+BLACK);
			a=0;b=0;
		}
		else if(e.getSource()==undo){
			for(int i = 1;i<20;i++){
				for(int j = 1;j<20;j++){
					if(list[i][j].getCount()==count-1)list[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
			
			count--;
			System.out.println(count);
			
		}
		
	
	}





}

//ai mode
else{
	if(ai_count==0){
	Layout.resetBoard(list);
	count=0;
	WHITE=0;
	BLACK=0;
	}
	int x = 0,y = 0;
	
	Random r= new Random();
	if(e.getActionCommand()=="Piece"){
		
		Piece p = (Piece) e.getSource();
		int ox = p.getXPosition();
		int oy = p.getYPosition();
		if(p.getBackground()==Color.LIGHT_GRAY){
	
		if(count%2==0){
			p.setBackground(Color.black);
			p.setCount(count);
		}
		
		else{
			p.setBackground(Color.white);
			p.setCount(count);
		}
		
		
		
		Color c;
		if(count%2==1){c = Color.black;}
		else c = Color.white;
		
		if(p.getBlock(p, list)[2]>2){
			list[p.getBlock(p, list)[0]][p.getBlock(p, list)[1]].setBackground(c);
		}
		
		else{
			while(x==0&&y==0){
				x = r.nextInt(3)-1;
				y= r.nextInt(3)-1;
				}
			list[ox+x][oy+y].setBackground(c);
			count=count+2;
		}
	
		}
	
	if(p.isFiveRow(p, list)){
		JOptionPane.showMessageDialog(null, p.getStringColor()+" wins" );
		if(p.getStringColor().equals("White"))WHITE++;
		else BLACK++;
	}
	
	
	
}
	
	
	ai_count++;
	
}

		
	//end
		

	
	}
	
}


public static void main(String []args){
	JFrame window = new JFrame("GOMOKU");
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.getContentPane().add(new Gomoku());
	window.pack();
	window.setVisible(true);
}
}
