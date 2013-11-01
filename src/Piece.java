import java.awt.Color;
import java.awt.Event;

import javax.swing.JButton;
import javax.swing.JToggleButton;

public class Piece extends JButton {
	int x, y,count=0;
	int liberty;
	String name;

	public Piece(int x, int y,int count) {
		super();
		this.x = x;
		this.y = y;
		this.count= count;

	}
	public Piece(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		

	}


	public int getXPosition() {
		return x;
	}

	public void setXPosition(int x) {
		this.x = x;
	}

	public int getYPosition() {
		return y;
	}

	public void setYPosition(int y) {
		this.y = y;
	}
	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return count;
	}
	public int[] getConnected(Piece p,Piece[][]list){
		int x = p.getXPosition();
		int y = p.getYPosition();
		Color c = list[x][y].getBackground();
		Color c1 = list[x-1][y].getBackground();
		Color c2 = list[x][y-1].getBackground();
		Color c3 = list[x+1][y].getBackground();
		Color c4 = list[x][y+1].getBackground();
		int []connect = {0,0,0,0};
		
		if(c1==c)connect[0]=1;
		else if(c1==Color.LIGHT_GRAY)connect[0]=0;
		else connect[0]=-1;
		
		if(c2==c)connect[1]=1;
		else if(c2==Color.LIGHT_GRAY)connect[1]=0;
		else connect[1]=-1;
		
		if(c3==c)connect[2]=1;
		else if(c3==Color.LIGHT_GRAY)connect[2]=0;
		else connect[2]=-1;
		
		if(c4==c)connect[3]=1;
		else if(c4==Color.LIGHT_GRAY)connect[3]=0;
		else connect[3]=-1;
		
		return connect;
	}
	public boolean hasConnected(Piece p, Piece[][]list){
		boolean value=false;
		for(int i = 0;i<4;i++){
		if(getConnected(p,list)[i]==1)value= true;
		}
		return value;
		
		
	}
	public int getLiberty(Piece p,Piece[][]list) {
		int x = p.getXPosition();
		int y = p.getYPosition();
		Color c = p.getBackground();
		
		int connect[]  = getConnected(p, list);
	
		
		return liberty;
	}

	public void setLiberty(int liberty) {
		this.liberty = liberty;
	}
	public String getStringColor(){
		Color c = this.getBackground();
		if(c==Color.BLACK)return "Black";
		else return "White";
	}
	public int[] getBlock(Piece p, Piece[][]list){
		int x = p.getXPosition();
		int y = p.getYPosition();
		int []a = {0,0,0};
		int max_v = 1;
		for(int i=0;i<8;i++){
			if(list[x-4+i][y].getBackground()==list[x-3+i][y].getBackground()&&list[x-4+i][y].getBackground()==list[x][y].getBackground()){max_v++;
			
			}
			else {a[0] = x-4+i+1;
				  a[1]= y;
				a[2]= max_v;  
				}
		}
		return a;
		
	}

	public boolean isFiveRow(Piece p, Piece[][]list){
		int x = p.getXPosition();
		int y = p.getYPosition();
		boolean isTrue = false;
		if(x==-1)isTrue=false;
		else{
		
		for(int i = 0;i<5;i++){
		//vertical
			if(list[x-4+i][y].getBackground()==list[x-3+i][y].getBackground()&&list[x-3+i][y].getBackground()==list[x-2+i][y].getBackground()&&list[x-2+i][y].getBackground()==list[x-1+i][y].getBackground()&&
					list[x-1+i][y].getBackground()==list[x+i][y].getBackground()  &&list[x][y].getBackground()!=Color.LIGHT_GRAY)
				{
				
				if(list[x-4+i][y].getBackground()!=list[x-5+i][y].getBackground()&&list[x+i][y].getBackground()!=list[x+1+i][y].getBackground())
					isTrue=true;
				
				}
			
		//horizontal
			if(list[x][y-4+i].getBackground()==list[x][y-3+i].getBackground()&&list[x][y-3+i].getBackground()==list[x][y-2+i].getBackground()&&list[x][y-2+i].getBackground()==list[x][y-1+i].getBackground()&&
				list[x][y-1+i].getBackground()==list[x][y+i].getBackground()&&list[x][y].getBackground()!=Color.LIGHT_GRAY)
				{
				
				if(list[x][y-4+i].getBackground()!=list[x][y-5+i].getBackground()&&list[x][y+i].getBackground()!=list[x][y+1+i].getBackground())
					isTrue=true;
				
				}
		//diagonal to right
			if(list[x-4+i][y-4+i].getBackground()==list[x-3+i][y-3+i].getBackground()&&list[x-3+i][y-3+i].getBackground()==list[x-2+i][y-2+i].getBackground()&&list[x-2+i][y-2+i].getBackground()==list[x-1+i][y-1+i].getBackground()&&
				list[x-1+i][y-1+i].getBackground()==list[x+i][y+i].getBackground()&&list[x][y].getBackground()!=Color.LIGHT_GRAY)
				{
				
				if(list[x-4+i][y-4+i].getBackground()!=list[x-5+i][y-5+i].getBackground()&&list[x+i][y+i].getBackground()!=list[x+1+i][y+1+i].getBackground())
					isTrue=true;
				
				}
		//diagonal to left
			if(list[x-4+i][y+4-i].getBackground()==list[x-3+i][y+3-i].getBackground()&&list[x-3+i][y+3-i].getBackground()==list[x-2+i][y+2-i].getBackground()&&list[x-2+i][y+2-i].getBackground()==list[x-1+i][y+1-i].getBackground()&&
					list[x-1+i][y+1-i].getBackground()==list[x+i][y-i].getBackground()&&list[x][y].getBackground()!=Color.LIGHT_GRAY)
					{
					
					if(list[x-4+i][y+4-i].getBackground()!=list[x-5+i][y+5-i].getBackground()&&list[x+i][y-i].getBackground()!=list[x+1+i][y-1-i].getBackground())
						isTrue=true;
					
					}
			
		}
		}

			return isTrue;
		
	}
}
