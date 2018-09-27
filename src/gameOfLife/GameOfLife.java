package gameOfLife;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLife implements MouseListener,ActionListener{
	
	private int[][] map;
	private JPanel[][] panels;
	private JFrame frame = new JFrame();
	private JPanel mainPanel = new JPanel();
	private JButton go = new JButton();
	private JPanel goPanel = new JPanel();
	private int squareLength;
	
	public GameOfLife(int squareLength) 
	{
		this.squareLength =squareLength;
		map = new int[squareLength][squareLength];
		panels = new JPanel[squareLength][squareLength];
		frame.setSize(1500, 1500);
		go.setName("go");
		go.setText("RunThrough");
		go.addActionListener(this);
		goPanel.add(go);
		mainPanel.setLayout(new GridLayout(squareLength,squareLength));
		
		for(int i = 0; i < map.length;i++)
			for(int h = 0; h < map[0].length;h++)
				map[i][h]=0;
		for(int i = 0; i < panels.length;i++)
			for(int h = 0; h < panels[0].length;h++)
			{	panels[i][h]=new JPanel();
				panels[i][h].setBackground(Color.WHITE);
				panels[i][h].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				panels[i][h].addMouseListener(this);
				panels[i][h].setName(i+" "+h);
				mainPanel.add(panels[i][h]);
				
			}
		frame.setLayout(new BorderLayout());
		frame.add(goPanel,BorderLayout.NORTH);
		frame.add(mainPanel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void update()
	{
		System.out.println("hi");
		frame.getContentPane().remove(mainPanel);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(squareLength,squareLength));
		for(int i = 0; i < panels.length;i++)
			for(int h = 0; h < panels[0].length;h++)
			{	panels[i][h]=new JPanel();
				panels[i][h].setBackground(map[i][h]==0?Color.WHITE:Color.RED);
				panels[i][h].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				panels[i][h].addMouseListener(this);
				panels[i][h].setName(i+" "+h);
				mainPanel.add(panels[i][h]);
			}
		frame.add(mainPanel,BorderLayout.CENTER);
		frame.invalidate();
		frame.validate();
	}
	
	public void evaluate()
	{
		
		int[][] tempMap = new int[squareLength][squareLength];
		for(int i = 0; i < map.length;i++)
		{
			for(int h = 0; h < map[0].length;h++)
			{
				int total = 0;
				try{total+=map[i-1][h-1];}catch(Exception e) {};
				try{total+=map[i-1][h];}catch(Exception e) {};
				try{total+=map[i-1][h+1];}catch(Exception e) {};
				try{total+=map[i][h-1];}catch(Exception e) {};
				try{total+=map[i][h+1];}catch(Exception e) {};
				try{total+=map[i+1][h-1];}catch(Exception e) {};
				try{total+=map[i+1][h];}catch(Exception e) {};
				try{total+=map[i+1][h+1];}catch(Exception e) {};
				if(total <2 ||total>3)
				{
					tempMap[i][h]=0;
				}
				else if(total==3)
				{
					tempMap[i][h]=1;
				}
				else
				{
					tempMap[i][h]=map[i][h];
				}
				
			}
		}
		for(int i = 0; i < map.length;i++)
			for(int h = 0; h < map[0].length;h++)
				map[i][h]=tempMap[i][h];
		
		System.out.println(Arrays.deepToString(map));
		update();
		
	}
	
	public static void main(String args[])
	{
		GameOfLife t = new GameOfLife(50);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		((JPanel)e.getSource()).setBackground(Color.red);
		String s = ((JPanel)e.getSource()).getName();
		map[new Integer(s.split(" ")[0])][new Integer(s.split(" ")[1])] =1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getName().equals("go"));
			evaluate();
		System.out.println(((JButton)e.getSource()).getName());
	}
	
}
