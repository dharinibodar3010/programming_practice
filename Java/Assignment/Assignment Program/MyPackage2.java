package com.MyPackage2;	

/*Lab 11: Create user defined package and import it into another program.*/
/*part : 2*/

import com.MyPackage.MathOperations;	//importing user-defined package

public class MyPackage2 
{
	public static void main(String[] args) 
	{
		MathOperations m = new MathOperations();
		m.add(10, 5);
		m.sub(10, 5);
	}

}
