class Operator
{
	public static void main(String[] args)
	{
		float a=6;
		float b=8;
		//Arthmetic operators
		System.out.println("Addition :- "+(a+b));
		System.out.println("Subtraction :- "+(a-b));
		System.out.println("Multiplication :- "+(a*b));
		System.out.println("Division :- "+(a%b));
		System.out.println("Modulus :- "+(a/b));
		
		// Bitwise operators
		System.out.println("a | b " + (a | b));
		System.out.println("a & b "+ (a & b));
		System.out.println("a ^ b "+ (a ^ b));
		
		//Unary operators
		b=++a;
		System.out.println(a);
		System.out.println(b);
		System.out.println(a++ + ++b);
		
		//increment operators
		a++;
		System.out.println("increment of a :- "+a);
		b--;
		System.out.println("decrement of b:- "+b);
		
		//ternary operators
		

	}
}
