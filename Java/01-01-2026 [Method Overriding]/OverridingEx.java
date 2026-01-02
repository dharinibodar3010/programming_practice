package jan01012026;

class RBI
{
	int rate()
	{
		return 0;
	}
}

class SBI extends RBI
{
	int rate()
	{
		return 7;
	}
}

class ICICI extends RBI 
{
	int rate()
	{
		return 8;
	}
}

class AXIS extends RBI
{
	int rate()
	{
		return 9;
	}
}

public class OverridingEx 
{
	public static void main(String[] args) 
	{
//		SBI s = new SBI();
//		ICICI i = new ICICI();
//		AXIS a = new AXIS();
//		
//		System.out.println(s.rate());
//		System.out.println(i.rate());
//		System.out.println(a.rate());
		
//		OR
		
		RBI r;
		
		r = new SBI();
		System.out.println(r.rate());
		
		r = new ICICI();
		System.out.println(r.rate());
		
		r = new AXIS();
		System.out.println(r.rate());
		 	
	}
}
