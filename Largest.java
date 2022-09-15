import java.util.Scanner;

class Largest
{
	public static void main(String[] args)
	{
		int a,b,c;
		System.out.println("Enter numbers: ");
		Scanner sc=new Scanner(System.in);  
		a=sc.nextInt();
		b=sc.nextInt();
		c=sc.nextInt();
		if (a>b && a>c)  // comparison by logical AND
		{
			System.out.println("It is the largest number : "+a); 
		}
		else if (b>a && b>c)
		{
			System.out.println("It is the largest number : "+b);
		}
		else
		{
			System.out.println("It is the largest number : "+c);
		}
	}
}
