package com.jan10012026;

public class GcEx 
{
	@Override
	protected void finalize() throws Throwable 
	{
		System.out.println("Data Destroyed");
	}

	public static void main(String[] args) 
	{
		GcEx g1 = new GcEx();
		GcEx g2 = new GcEx();
		GcEx g3 = new GcEx();
		
		g1 = null;
		g2 = null;
		
		System.gc();
	}
}
