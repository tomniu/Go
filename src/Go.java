import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Go extends JPanel{
	int count = 0;
	static Layout l = new Layout();
	static Piece[][] list = l.getList();
	public Go(){
		ActionListener b = new ButtonHandler();
		for(int i = 1;i<20;i++){
			for(int j = 1;j<20;j++){
				list[i][j].addActionListener(b);
			}
		}
		add(l);
	}
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("GO");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new Go());
		window.pack();
		window.setVisible(true);
		
	}
	public class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Piece p = (Piece) e.getSource();
			for(int i=0;i<19;i++){
				for(int j = 0;j<19;j++){
				if(true){
					
					list[i+1][j+1].setBackground(Color.LIGHT_GRAY);
					System.out.println("test");}
				
					if(p.getBackground()==Color.LIGHT_GRAY){
					if(count%2==0)p.setBackground(Color.BLACK);
					else p.setBackground(Color.WHITE);
					}
				}
			
			
		}
			count++;
	}
	
}
}
