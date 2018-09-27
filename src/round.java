import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class round {

	public static void main(String[] args) throws FileNotFoundException {

			Scanner scan = new Scanner(new File("round.in.txt"));
		
		while(true)
		{
		int n = scan.nextInt();
		if(n==0)
			break;
		int t = scan.nextInt();
		ArrayList<Integer> a = new ArrayList<Integer>();
		for(int i = 0; i < n; i++)
		{
			a.add(0);
		}
		boolean go = true;
		int c=0;
		while(go)
		{
		for(int i=0; i < t;i++)
			{
			a.set(c,a.get(c)+1);
			c++;
			c%=a.size();
			}
			c=c-1 < 0 ? a.size()-1:c-1;
			a.remove(c);
			go=false;
			for(Integer w :a)
			{
				if(a.get(0)!=w)
					go=true;
			}
		}
		System.out.println(a.size()+" "+a.get(0));
		}

	}

}
