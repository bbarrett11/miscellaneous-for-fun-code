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
	public Mancala() throws InterruptedException
	{
		 upperPlayer =new int[] {4,4,4,4,4,4};
		lowerPlayer =new int[] {4,4,4,4,4,4};
		upperBin=lowerBin=0;
		fin = false;
		System.out.println(" "+Arrays.toString(upperPlayer));
		System.out.println(upperBin +"                  "+lowerBin);
		System.out.println(" "+Arrays.toString(lowerPlayer));
		while(!fin)
		{
			while(GetInputLower())
			{
				System.out.println(" "+Arrays.toString(upperPlayer));
				System.out.println(upperBin +"                  "+lowerBin);
				System.out.println(" "+Arrays.toString(lowerPlayer));
			}
			System.out.println(" "+Arrays.toString(upperPlayer));
			System.out.println(upperBin +"                  "+lowerBin);
			System.out.println(" "+Arrays.toString(lowerPlayer));
			while(GetInputUpper())
			{
				System.out.println(" "+Arrays.toString(upperPlayer));
				System.out.println(upperBin +"                  "+lowerBin);
				System.out.println(" "+Arrays.toString(lowerPlayer));
			}
			System.out.println(" "+Arrays.toString(upperPlayer));
			System.out.println(upperBin +"                  "+lowerBin);
			System.out.println(" "+Arrays.toString(lowerPlayer));
		}
	}
	public boolean GetInputLower() throws InterruptedException
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Player 1 make a move pls");
		int move = scan.nextInt();
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
				if(stones == 0)
					return true;
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
				if(stones == 0)
					return true;

			}
		}
			return false;
	}
	
	public boolean GetInputUpper() throws InterruptedException
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Player 2 make a move pls");
		int move = scan.nextInt();
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
				if(stones == 0)
					return true;

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
				for(int i = 0; i <lowerPlayer.length && stones >0; i--)
				{
					stones--;
					lowerPlayer[i]++;
				}
			}
			for(int i = upperPlayer.length-1; i >=0 && stones >0; i++)
			{
				stones--;
				upperPlayer[i]++;
				if(stones ==0 && upperPlayer[i]==1)
				{
					upperBin+=lowerPlayer[i];
					lowerPlayer[i] =0;
				}
				if(stones == 0)
					return true;

			}
		}
			return false;
	}
	public int getUpperBin()
	{
		return upperBin;
	}
	public int getLowerBin()
	{
		return lowerBin;
	}
}
