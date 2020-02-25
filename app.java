
/*
#########################################################################
#																		#
#	PROJECT -- 	PAC_MAZE in java using Canvas							#
#	BY - 		Atul Kumar Singh(AKS)									#
#	Email - 	akatul789@gmail.com										#
#																		#
#	Done under the esteemed guidance of Lakshmeeswari Gondi Ma'am		#
#																		#
#########################################################################
*/


// Importing Canvas and event classes/interfaces
import java.awt.*;
import java.awt.event.*;
public class app
{
	public app()  
	{  
	Frame f= new Frame("Pac_MAZE__BY-AKS");
	f.add(new MyCanvas(),BorderLayout.CENTER); 
	//f.setLayout(null);
	f.setSize(1100,850);
	f.setVisible(true);
	f.addWindowListener(new WindowAdapter()
		{
		public void windowClosing(WindowEvent e)
		{System.exit(0);}
  		});
	}

	public static void main(String args[])
  	{
		new app();
	}
}


class MyCanvas extends Canvas implements KeyListener
{
	int p=40,q=280,m,n,time=5,x=80,y=285,co=0,pos,a=1;
	public MyCanvas()
	{
		setBackground(Color.white);
		addKeyListener(this);
		setSize(1024,768);
	}

	void MOVE()
	{
		/*if(co==1)
		x+=2;
		else if(co==-1)
		x-=2;
		else if(co==2)
		y+=2;
		else if(co==-2)
		y-=2;*/
		// Same code as above ,, cut short using conditional operator
		int temp;
		temp=(co==1)?x+=2:(co==-1)?x-=2:(co==2)?y+=2:(co==-2)?y-=2:0;
	}

	void displaypac(Graphics g)
	{
			while(p!=m-35 || q!=n+70)
			{
				g.setColor(Color.white);
				//g.fillArc(x,y,60,60,0,360);   //disapper pac //1 deg. increase to erase
				g.fillRect(x,y,62,62);	//Disapper Pac-man.. repainted white square on top
				p-=1;q+=2;
				if(x!=pos && y!=pos)
				{MOVE();}
				g.setColor(Color.green);
				g.fillArc(x,y,60,60,p,q); //Opening face pac-man (increasing arc angles to open mouth of pac-man)
				try
				{Thread.sleep(time);}		//PAUSE FOR SOMETIME ..//to look like its animating
				catch(Exception e){}
			}	

			while(p!=m || q!=n)
			{
				g.setColor(Color.white);
				//g.fillArc(x,y,60,60,0,360);   //disapper pac  //1 deg. increase to erase
				g.fillRect(x,y,62,62);
				p++;q-=2;
				if(x!=pos && y!=pos)
				{MOVE();}
				g.setColor(Color.green);
				g.fillArc(x,y,60,60,p,q); //closing face pac-man (increasing arc angles to close mouth of pac-man)
				try
				{Thread.sleep(time);}	 //PAUSE FOR SOMETIME ..//to look like its animating		
				catch(Exception e){}
			}
	}


	boolean checkx()
	{
		int corx[][]={{80,425},{150,425},{150,355},{368,355},{368,565},{504,565},{80,635},{368,635},{720,635},
				{80,495},{220,495},{80,565},{290,565},{220,425},{290,425},
				{650,495},{720,495},{790,495},
				{80,75},{436,75},{504,75},
				{370,145},{436,145},{370,215},{720,215},
				{-1,-1},{80,215},{150,215},{300,215},{-1,-1},{150,285},{300,285},{436,285},{504,285},
				{150,145},{300,145},{650,355},{720,355},
				{-1,-1},{504,145},{580,145},{720,145},{580,565},{720,565},{580,285},{720,285},{580,75},{790,75},
				{790,635},{860,635},{-1,-1}};
		for(int i=0;i<corx.length;i++)
		{
			if(corx[i][1]==y && corx[i][0]==x)
			{
				if(co==1)
				{	if(corx[i+1][1]!=y)
					return false;
					else
					{
					pos=corx[i+1][0];
					return true;
					}
				}
				else if(co==-1 && i!=0)
				{	if(corx[i-1][1]!=y)
					return false;
					else
					{
					pos=corx[i-1][0];
					return true;
					}
				}				
			}
		}
	return false;

	}
	
	boolean checky()
	{
		int cory[][]={{80,285},{80,425},{150,355},{150,425},{368,355},{368,565},{368,635},{720,565},{720,635},
				{80,495},{80,565},{80,635},{220,425},{220,495},{290,425},{290,495},
				{80,75},{80,215},{436,75},{436,145},
				{-1,-1},{436,285},{436,495},{504,285},{504,565},
				{150,145},{150,215},{-1,-1},{300,215},{300,285},{370,145},{370,215},{504,75},{504,145},
				{-1,-1},{720,215},{720,285},{580,285},{580,565},
				{-1,-1},{580,75},{580,145},{790,75},{790,495},{790,635},
				{-1,-1},{650,355},{650,495},{720,425},{720,495}};
		for(int i=0;i<cory.length;i++)
		{
			if(cory[i][1]==y && cory[i][0]==x)
			{
				if(co==2)
				{	if(cory[i+1][0]!=x)
					return false;
					else
					{
					pos=cory[i+1][1];
					return true;
					}
				}
				else if(co==-2 && i!=0)
				{	if(cory[i-1][0]!=x)
					return false;
					else
					{
					pos=cory[i-1][1];
					return true;
					}
				}				
			}
		}
	return false;
	}

	public void movepac(Graphics g)
	{
		if(co==1 && checkx())
		{
			m=40;n=280;
			p=m;q=n;
			while(x<pos)
			{
				displaypac(g);
			}
		}
		else if(co==-1 && checkx())
		{
			m=-140;n=280;
			p=m;q=n;
			while(x>pos)
			{
				displaypac(g);
			}
		}
		else if(co==2 && checky())
		{

			m=-50;n=280;
			p=m;q=n;
			while(y<pos)
			{
				displaypac(g);
			}
		}
		else if(co==-2 && checky())
		{
			m=130;n=280;
			p=m;q=n;
			while(y>pos)
			{
				displaypac(g);
			}
		}

		else
		{g.setColor(Color.green);
		g.fillArc(x,y,60,60,p,q);
		}
	}

public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    switch( keyCode ) { 
        case KeyEvent.VK_UP:
            co=-2;repaint();
            break;
        case KeyEvent.VK_DOWN:
            co=2;repaint();
            break;
        case KeyEvent.VK_LEFT:
           co=-1;repaint();
		
            break;
        case KeyEvent.VK_RIGHT :
            co=1;repaint();
            break;
     }
} 
public void finsh(Graphics g)
{
	g.setColor(Color.WHITE);
	g.fillRect(120,320,600,100);
	g.fillRect(90,430,600,100);
	
	g.setColor(Color.black);
	g.setFont(new Font("Courier", Font.BOLD,100));
	g.drawString(" WELL DONE !!",130,400);
	g.drawString(" MISSION ACCOMPLISHED !!",70,530);
	
}

	public void keyPressed( KeyEvent e ) { }
	public void keyTyped( KeyEvent e ) {    }
	public void paint(Graphics x)
	{
		//super.paint(x);

		x.setColor(Color.black);
		x.setFont(new Font("Courier", Font.BOLD,40));
		x.drawString("##** PAC MAZE **##",270,60);

   		Graphics2D g = (Graphics2D) x;
		g.setStroke(new BasicStroke(4));
		g.setColor(Color.black);

		g.drawLine(75,70,855,70);  //row 1 x
		g.drawLine(145,140,430,140); //x
		g.drawLine(645,140,785,140); //x
		g.drawLine(855,70,855,630);//y
		g.drawLine(570,70,570,140); //y
		g.drawLine(75,70,75,350); //y

		g.drawLine(430,210,785,210); //row 2
		g.drawLine(215,210,365,210); //x
		g.drawLine(145,140,145,210);//y
		g.drawLine(365,140,365,280); //y
		g.drawLine(500,140,500,210); //y
		g.drawLine(785,140,785,420); //y

		g.drawLine(75,280,295,280); //row 3
		g.drawLine(365,280,715,280); //x

		g.drawLine(145,350,430,350); //row 4
		g.drawLine(645,350,785,350); //x
		g.drawLine(570,280,570,630); //y
		g.drawLine(145,280,145,420); //y
		g.drawLine(645,350,645,560);  //y

		g.drawLine(75,350,75,700); //row 5 y
		g.drawLine(215,420,365,420); //x
		g.drawLine(715,420,785,420); //x
		g.drawLine(715,420,715,490); //y
		g.drawLine(785,420,785,490); //y
		g.drawLine(75,420,75,700); //y
		g.drawLine(432,350,432,560); //y
		g.drawLine(500,350,500,560); //y

		g.drawLine(75,490,215,490); //row 6 x
		g.drawLine(365,420,365,630); //y
		g.drawLine(215,420,215,490); //x
 
		g.drawLine(145,560,365,560); //row 7
		g.drawLine(645,560,785,560); //x
		g.drawLine(432,560,500,560); //x
		g.drawLine(285,490,285,560); //y

		g.drawLine(145,630,365,630); //row 8
		g.drawLine(145,631,365,631); //x
		g.drawLine(425,630,715,630); //x
		g.drawLine(785,560,785,700); //y

		g.drawLine(75,700,855,700); //row 9
		
	update(g);
	}

	public void update(Graphics g)
	{
		movepac(g);
		if(x==860)
		finsh(g);	
	}
}    
