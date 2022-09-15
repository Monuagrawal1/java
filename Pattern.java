public class Pattern 
{
	public static void main(String[] args)
	{
		int i,j,n=6;
		for(i=n-1;i>=0;i--) //for loop
		{
			for(j=0;j<=i;j++) //nested for loop
			{
				System.out.print(" * ");  //printing star
			}
			System.out.println();  // print on new line
		}
	}
}
