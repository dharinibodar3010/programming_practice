package jan01012026;

public class OverloadingEx1 
{
	int cal(int a, int b)
	{
		return a+b;
	}
	
	int cal(int a, int b, int c)
	{
		return a*b*c;
	}
	
	public static void main(String[] args) 
	{
		OverloadingEx1 O1 = new OverloadingEx1();
		System.out.println(O1.cal(5, 5));
		System.out.println(O1.cal(2, 2, 2));	
	}
}
