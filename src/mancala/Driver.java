package mancala;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws InterruptedException
	{
		Mancala game = new Mancala();
		ArrayList<Integer[]> master = new ArrayList<Integer[]>();
		ArrayList<Character> wins = new ArrayList<Character>();
		ArrayList<String> sequences = new ArrayList<String>();

		SeededBot topBot  = new SeededBot(game,false,"654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321654321");
		SeededBot botBot  = new SeededBot(game,true,"324124434233421342531536462614542562615114354651656355234314414115324433346545315646443565543256412464552556131462366613442131615355336311136316661566466123123112645526111324122663325143323241315115643636245143466425141135242252646453115312251245644221312646344133246554214354244133252135");
		int waitTime = 0;
		int numIterations = 10000000;
		boolean showGame = false;
		for(int i = 0; i < numIterations; i++)
		{
			game = new Mancala();
			topBot.setGame(game);
			botBot.setGame(game);
			while(!game.checkEnd())
			{
				while(botBot.isTurn()&&!game.checkEnd())
				{
					if(showGame)
					{
						System.out.println(" "+Arrays.toString(botBot.getGame().getUpperPlayer()));
						System.out.println(botBot.getGame().getUpperBin() +"                  "+botBot.getGame().getLowerBin());
						System.out.println(" "+Arrays.toString(botBot.getGame().getLowerPlayer()));
						System.out.println("");
					}
					botBot.setGame(botBot.makeMove(botBot.getGame()));
					Thread.sleep(waitTime);
				}
				if(showGame)
				{
					System.out.println(" "+Arrays.toString(topBot.getPlayer()));
					System.out.println(botBot.getGame().getUpperBin() +"                  "+botBot.getGame().getLowerBin());
					System.out.println(" "+Arrays.toString(botBot.getPlayer()));
					System.out.println("");	
				}
				Thread.sleep(waitTime);
				game = botBot.getGame();
				game.setLowerTurn(false);
				if(	game.checkEnd())
					break;
				topBot.setGame(game);
				while(topBot.isTurn()&&!game.checkEnd())
				{
					if(showGame)
					{
						System.out.println(" "+Arrays.toString(topBot.getGame().getUpperPlayer()));
						System.out.println(topBot.getGame().getUpperBin() +"                  "+topBot.getGame().getLowerBin());
						System.out.println(" "+Arrays.toString(topBot.getGame().getLowerPlayer()));
						System.out.println("");	
					}
					topBot.setGame(topBot.makeMove(topBot.getGame()));
					Thread.sleep(waitTime);

				}
				game = topBot.getGame();
				botBot.setGame(game);
				if(showGame)
				{
					System.out.println(" "+Arrays.toString(game.getUpperPlayer()));
					System.out.println(topBot.getGame().getUpperBin() +"                  "+topBot.getGame().getLowerBin());
					System.out.println(" "+Arrays.toString(game.getLowerPlayer()));
					System.out.println("");	
				}
				game.setLowerTurn(true);

				if(game.checkEnd())
					break;
			}
			if(game.getUpperBin()>game.getLowerBin())
			{
				//System.out.println("Upper Player Moves: "+topBot.getMoves());
				master.add(topBot.getMoves().toArray(new Integer[topBot.getMoves().size()]));
				wins.add('U');
				//sequences.add(topBot.getSequence());
				topBot.makeWinner();
				botBot.makeLoser();
			}
			else if(game.getUpperBin()<game.getLowerBin())
			{
				//System.out.println("Lower Player Moves: "+botBot.getMoves());
				master.add(botBot.getMoves().toArray(new Integer[botBot.getMoves().size()]));
				wins.add('L');
				//sequences.add(botBot.getSequence());
				botBot.makeWinner();
				topBot.makeLoser();
			}
			else
			{
				botBot.makeLoser();
				topBot.makeLoser();
			}
			//System.out.println(" "+Arrays.toString(game.getUpperPlayer()));
			//System.out.println(topBot.getGame().getUpperBin() +"                  "+topBot.getGame().getLowerBin());
			//System.out.println(" "+Arrays.toString(game.getLowerPlayer()));
			if(botBot.isWinner())
			{
				topBot = new SeededBot(game, false, new String(doMutations(topBot.getSequence().toCharArray(),topBot.getGame().getUpperBin())));
				botBot = new SeededBot(game, true, new String(botBot.getSequence()));
				//System.out.println(/*botBot.getSequence()+*/"(botbot) is winner of game "+ i+"");
			}
			else if(topBot.isWinner())
			{
				topBot = new SeededBot(game, false, new String(topBot.getSequence()));
				botBot = new SeededBot(game, true, new String(doMutations(botBot.getSequence().toCharArray(),botBot.getGame().getLowerBin())));
				//System.out.println("(topbot) is winner of game "+ i+"");
			}
			else
			{
				topBot = new SeededBot(game, false, new String(doMutations(topBot.getSequence().toCharArray(),topBot.getGame().getUpperBin())));
				botBot = new SeededBot(game, true, new String(doMutations(botBot.getSequence().toCharArray(),botBot.getGame().getLowerBin())));
				//System.out.println("No one (draw) is winner of game "+ i+"");

			}
			
		}
		doAnalysis(master,wins,sequences);

	}
	
	public static char[] doMutations(char[] s, double points)
	{
		for(int i = 0; i < s.length; i++)
		{
			if(Math.random() < (30.0-points)/20.0)
			{
				s[i] = (char)((int)(Math.random()*6)+49);
			}
		}
		return s;
	}
	
	
	
	public static void doAnalysis(ArrayList<Integer[]> master, ArrayList<Character> wins, ArrayList<String> seqs)
	{
		int[] firstDigit = new int[10];
		int[] secondDigit= new int[10];
		int[][] firstTwo = new int[6][6];
		long sizeTotal = 0;
		for(Integer[] win : master)
		{
			//System.out.println(Arrays.toString(win));
			sizeTotal+=win.length;
			firstDigit[win[0]]++;
			secondDigit[win[1]]++;
			firstTwo[win[0]-1][win[1]-1]++;
		}
		System.out.println("Average Time: "+(sizeTotal/master.size()));
		System.out.println("Firsts: "+Arrays.toString(firstDigit));
		System.out.println("Seconds: "+Arrays.toString(secondDigit));
		int max = 0,tK=0,tJ=0;
		for(int k = 0; k < firstTwo.length; k++)
		{
			for(int j =0; j < firstTwo[0].length; j++)
			{
				if(firstTwo[k][j]>max)
				{
					tK=k+1;tJ=j+1;
					max = firstTwo[k][j];
				}
			}
		}
		System.out.println(Arrays.deepToString(firstTwo));
		System.out.println("Most common 2 values: "+(tK+1)+" "+(tJ+1));
		int lWins = 0,uWins = 0,streak = 0,maxStreak=0,k=0,maxStreakIndex=0;
		char streakSide = '0', maxStreakSide = '0';
		for(Character c : wins)
		{
			if(c == 'L')
			{
				lWins++;
			}
			else if(c== 'U')
			{
				uWins++;
			}
			if(!(streakSide==c))
			{
				if(streak>maxStreak)
				{
					maxStreak = streak;
					maxStreakSide = streakSide;
					maxStreakIndex=k;
				}
				streakSide = c;
				streak=0;
			}
			streak++;
			if(streak > maxStreak)
			{
				maxStreak = streak;
				maxStreakSide = streakSide;
				maxStreakIndex=k;
			}
			k++;
		}
		
		//System.out.println(wins);
		System.out.println("Num L wins: "+ lWins);
		System.out.println("Number of U wins: "+uWins);
		System.out.println("Max streak: "+maxStreak + " by "+maxStreakSide+" with \n"+seqs.get(maxStreakIndex));

	}
}
