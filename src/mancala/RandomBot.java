package mancala;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomBot {
	
	private Mancala game;
	private ArrayList<Integer> moves = new ArrayList<Integer>();
	private boolean winner=false;
	private Random rand = new Random();
	private int moveNum = 0;
	private boolean lowerPlayer;
	
	public RandomBot(Mancala game,boolean isLower)
	{
		this.game = game;
		lowerPlayer=isLower;
	}
	
	public Mancala makeMove(Mancala m) throws InterruptedException
	{
		if(!winner)
		{
			
			int i = rand.nextInt(6)+1;
			
			if(lowerPlayer)
			{
				//System.out.println("not stuck");
				while((m.getLowerPlayer()[i-1]==0))
				{i = rand.nextInt(6)+1;}
				m.setLowerTurn(m.GetInputLower(i));
			}
			else
			{
				while((m.getUpperPlayer()[i-1]==0))
					i = rand.nextInt(6)+1;

				m.setLowerTurn(!m.GetInputUpper(i));
			}
			moves.add(i);
		}
		else
		{
			if(moves.size() <= moveNum)
			{
				int i = rand.nextInt(6)+1;
				
				if(lowerPlayer)
				{
					while((m.getLowerPlayer()[i-1]==0))
						i = rand.nextInt(6)+1;

					m.setLowerTurn(m.GetInputLower(i));
				}
				else
				{
					while((m.getUpperPlayer()[i-1]==0))
						i = rand.nextInt(6)+1;

					m.setLowerTurn(!m.GetInputUpper(i));
				}
				moves.add(i);
			}
			else
			{
				if(lowerPlayer)
				{
					
					m.setLowerTurn(m.GetInputLower(moves.get(moveNum)));
				}
				else
					m.setLowerTurn(!m.GetInputUpper(moves.get(moveNum)));
			}
		}
		moveNum++;
		return m;
	}
	
	public Mancala getGame()
	{
		return game;
	}
	
	public int[] getPlayer()
	{
		return lowerPlayer ?game.getLowerPlayer():game.getUpperPlayer();
	}
	
	public ArrayList<Integer> getMoves(){return moves;}
	
	public void makeWinner() {winner=true;}

	public void makeLoser() {moves = new ArrayList<Integer>();winner=false;}
		
	public boolean isTurn()
	{
		return (lowerPlayer&&game.getLowerTurn())||(!lowerPlayer&&!game.getLowerTurn());
	}
	
	public void setGame(Mancala m)
	{
		this.game = m;
	}
}
