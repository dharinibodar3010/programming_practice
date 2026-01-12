package com.jan08012026;

import java.io.File;

public class FileClassDemo 
{
	public void getFileDetails(File file)
	{
		System.out.println(file.exists());
		System.out.println(file.isDirectory());
		System.out.println(file.lastModified());
		System.out.println(file.getName());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getPath());
		System.out.println(file.canExecute());
		System.out.println(file.canRead());
	}
	
	public static void main(String[] args) throws Exception
	{
		FileClassDemo fcd = new FileClassDemo();
		
		File file = new File("MyFile");
		file.createNewFile();
		
		fcd.getFileDetails(file);
	}

}
