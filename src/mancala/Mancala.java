package mancala;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Mancala {

	public static void main(String[] args) throws InterruptedException {
		
	Mancala game = new Mancala();	
	}
	private int[] upperPlayer;
	private int[] lowerPlayer;
	private int upperBin,lowerBin;
	private boolean fin = false;
	private boolean lowerTurn = true;
	public Mancala() throws InterruptedException
	{
		 upperPlayer =new int[] {4,4,4,4,4,4};
		lowerPlayer =new int[] {4,4,4,4,4,4};
		upperBin=lowerBin=0;
		fin = false;
		//System.out.println(" "+Arrays.toString(upperPlayer));
		//System.out.println(upperBin +"                  "+lowerBin);
		//System.out.println(" "+Arrays.toString(lowerPlayer));
		
	}
	public boolean GetInputLower(int m) throws InterruptedException
	{

		int move =m;
		System.out.flush();
		int stones = lowerPlayer[move-1];
		if(stones ==0)
			return true;
		lowerPlayer[move-1]=0;
			for(int i = move; i < lowerPlayer.length && stones >0; i++)
			{
				stones--;
				lowerPlayer[i]++;
				if(stones ==0 && lowerPlayer[i]==1)
				{
					lowerBin+=upperPlayer[i];
					upperPlayer[i] =0;
				}

			}
			while(stones >0)
			{
			if(stones > 0)
			{
				stones--;
				lowerBin++;
				if(stones==0)
					return true;
			}
			if(stones >0)
			{
				for(int i = upperPlayer.length-1; i >=0 && stones >0; i--)
				{
					stones--;
					upperPlayer[i]++;
				}
			}
			for(int i = 0; i < lowerPlayer.length && stones >0; i++)
			{
				stones--;
				lowerPlayer[i]++;
				if(stones ==0 && lowerPlayer[i]==1)
				{
					lowerBin+=upperPlayer[i];
					upperPlayer[i] =0;
				}

			}
		}
			return false;
	}
	
	public boolean GetInputUpper(int m) throws InterruptedException
	{
		
		int move=m;
		System.out.flush();
		int stones = upperPlayer[move-1];
		if(stones ==0)
			return true;
		upperPlayer[move-1]=0;
			for(int i = move-2; i >=0 && stones >0; i--)
			{
				stones--;
				upperPlayer[i]++;
				if(stones ==0 && upperPlayer[i]==1)
				{
					upperBin+=lowerPlayer[i];
					lowerPlayer[i] =0;
				}

			}
			while(stones >0)
			{
			if(stones > 0)
			{
				stones--;
				upperBin++;
				if(stones==0)
					return true;
			}
			if(stones >0)
			{
				for(int i = 0; i <lowerPlayer.length && stones >0; i++)
				{
					stones--;
					lowerPlayer[i]++;
				}
			}
			for(int i = upperPlayer.length-1; i >=0 && stones >0; i--)
			{
				stones--;
				upperPlayer[i]++;
				if(stones ==0 && upperPlayer[i]==1)
				{
					upperBin+=lowerPlayer[i];
					lowerPlayer[i] =0;
				}


			}
		}
			return false;
	}
	
	public boolean checkEnd()
	{
		int t=0,l=0;
		for(int i : lowerPlayer)
			l+=i;
		boolean lowerEmpty = l==0;
		for(int i : upperPlayer)
			t+=i;
		boolean upperEmpty = t==0;
		if((lowerTurn && lowerEmpty)||(!lowerTurn && upperEmpty))
		{
			if(lowerEmpty)
				upperBin+=l;
			if(upperEmpty)
				lowerBin+=t;
			return true;
		}

		return false;
	}

	public int getUpperBin()
	{
		return upperBin;
	}
	
	public boolean getLowerTurn()
	{
		return lowerTurn;
	}
	
 	public int getLowerBin()
	{
		return lowerBin;
	}
 	
 	public int[] getLowerPlayer() {return lowerPlayer;}
 	public int[] getUpperPlayer() {return upperPlayer;}

 	public void setLowerTurn(boolean b)
 	{
 		lowerTurn = b;
 	}
}
