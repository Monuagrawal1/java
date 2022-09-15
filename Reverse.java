public class Reverse   
{  
public static void main(String[] args)   
{  
int n = 9835, r = 0;  
while(n!= 0)   
{  
int rem = n % 10;  //
r = r * 10 + rem;  
n = n/10;  // reverse the number 
}  
System.out.println("The reverse of the given number is: " + reverse);  
}  
}  
