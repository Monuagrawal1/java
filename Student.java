import java.util.Scanner;

class Student
{
	public static void main(String[] args)
	{
		int id;
		String sname,attendance;
		System.out.println("Enter Student id : ");
		Scanner sc=new Scanner(System.in);
		id=sc.nextInt();
		System.out.println("Enter your name : ");
		sname=sc.next();
		System.out.println("Present or absent : ");
		attendance=sc.next();
      
      System.out.println("Student id :"+id);
      System.out.println("Student name :"+sname);
      System.out.println("Student attendance"+attendance);
	}
}
