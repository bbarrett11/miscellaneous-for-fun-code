import java.util.Arrays;
import java.util.Scanner;

public class sortme {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		while(true)
		{
			char[] c = new char[26];
			String s = scan.nextLine();
			if(s.equals("0"))
				break;
			int i = Integer.valueOf(s.split(" ")[0]);
			c=s.split(" ")[1].toCharArray();
			String[] start = new String[i];
			for(int w = 0; w<i;w++)
			{
				start[w]=scan.nextLine();
			}
			String[] arr = new String[i];
			
			for(int w = 0; w < i; w++)
			{
				int insert = 0;
				for(int h =i-1; h >=0; h--)
				{
					if(h==0)
					{
						insert = 0;
						break;
					}
					
					if(arr[h-1]==null)
						continue;
					if(compareTo(arr[h-1],start[w],c)<0)
					{
						insert = h;
						break;
					}
					arr[h]=arr[h-1];
				}
				arr[insert]=start[w];
			}
			for(String q: arr)
				{
				System.out.println(q);
				}
		}
		

	}
	public static int compareTo(String s1, String s2, char[] c)
	{
		for(int i = 0; i<(s1.length()>s2.length()?s1.length():s2.length());i++)
		{
			int i1 =0, i2 =0;
			if(s1.length() == i)
			{
				return -1;
			}
			if(s2.length() == i)
			{
				return 1;
			}
			for(int h = 0; h < c.length;h++)
			{
				if(s1.charAt(i)==c[h])
					i1=h;
				if(s2.charAt(i)==c[h])
					i2=h;
			}
			if(i1 > i2)
			{
				return 1;
			}
			if(i2 > i1)
			{
				return -1;
			}
		}
		return 0;
	}
}
