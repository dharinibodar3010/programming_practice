package jan01012026;

public class OverloadingEx2 
{
	int cal(int a, int b)
	{
		return a + b;
	}
	
	double cal(double a, double b)
	{
		return a * b;
	}
	
	public static void main(String[] args) 
	{
		OverloadingEx2 O2 = new OverloadingEx2();
		
		System.out.println(O2.cal(5, 5));
		System.out.println(O2.cal(5.0, 5.0));
		
	}

}
