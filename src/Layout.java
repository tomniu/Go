

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.*;

public class Layout extends JPanel{
	Piece [][]list = new Piece[21][21];
	
	
	public Layout(){
		
		
		setLayout(new GridLayout(21,21));
		
		for(int i=0;i<21;i++){
			for(int j = 0;j<21;j++){
			add(list[i][j]= new Piece(i,j,2000));
		}
				
		}
		for(int i = 1;i<20;i++){
			for(int j = 1;j<20;j++){
				list[i][j].setBackground(Color.LIGHT_GRAY);
				list[i][j].setActionCommand("Piece");
			}
		}
		
		
		
		setPreferredSize(new Dimension(1680,950));
		

	}
	
	public Piece[][] getList(){
		return list;
		
	}

	public static void resetBoard(Piece[][]list){

		for(int i = 1;i<20;i++){
			for(int j = 1;j<20;j++){
				list[i][j].setBackground(Color.LIGHT_GRAY);
				list[i][j].setEnabled(true);
				list[i][j].setCount(2000);
			}
		}
	}
	
}