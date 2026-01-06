package com.task;

import java.util.Scanner;

class Book
{
	int BookId;
	String BookName;
	boolean isIssued;
	
//	constructor
	Book(int id, String name)
	{
		BookId = id;
		BookName = name;
		isIssued = false;
	}
	
	void displayBook()
	{
		System.out.println(BookId + "\t" + BookName +"\t" + (isIssued ? "Issued" : "Available"));
	}
}

class Library
{
//	Aarray of object
	Book books[] = new Book[100];
	
	int count = 0;
	
//	Add book
	void addBook(int id, String name)
	{
		books[count] = new Book(id, name);
		count++;
		
		System.out.println("Book Added Successfully");
	}
	
//	Display Books
	void displaybooks()
	{
		if(count == 0)
		{
			System.out.println("No books available");
			
			return;
		}
		
		System.out.println("ID\tName\tStatus");
		
		for(int i = 0; i < count; i++)
		{
			books[i].displayBook();
		}
	}
	
//	Issue Book
	void issueBook(int id)
	{
		for(int i = 0; i < count; i++)
		{
			if(books[i].BookId == id)
			{
				if(!books[i].isIssued)
				{
					books[i].isIssued = true;
					
					System.out.println("Book Issued Successfully");
				}
				else
				{
					System.out.println("Book Already Issued");
				}
				return;
			}
		}
		System.out.println("Book not Found");
	}
	
//	Return Book
	void returnBook(int id)
	{
		for(int i = 0; i < count; i++)
		{
			if(books[i].BookId == id)
			{
				if(books[i].isIssued)
				{
					books[i].isIssued = false;
					
					System.out.println("Book Return Successfully");
				}
				
				else
				{
					System.out.println("Book was not issued");
				}
				return;
			}
		}
		
		System.out.println("Book not Found");
	}
}

public class LibrarayManagementSystem 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		Library lib = new Library();
		
		while(true)
		{
			System.out.println("\n-----Library Management System-----\n");
			System.out.println("1. Add Book");
			System.out.println("2. Display Book");
			System.out.println("3. Issue Book");
			System.out.println("4. Return Book");
			System.out.println("5. Exit");
			
			System.out.println("Enter choice: ");
			int choice = sc.nextInt();
			
			switch(choice)
			{
			case 1: 
				System.out.println("Enter Book ID: ");
				int id = sc.nextInt();
				sc.nextLine();
				
				System.out.println("Enter Book Name: ");
				String name = sc.next();
				
				lib.addBook(id, name);
				lib.displaybooks();
				sc.nextLine();
				
				break;
				
			case 2:
				lib.displaybooks();
				
				break;
				
			case 3:
				System.out.println("Enter Book ID to Issue: ");
				lib.issueBook(sc.nextInt());
				
				break;
				
			case 4: 
				System.out.println("Enter Book ID to Return: ");
				lib.returnBook(sc.nextInt());
				
				break;
				
			case 5: 
				System.out.println("Thank you");
				System.exit(0);
				
			default:
				System.out.println("Invalid Choice");
			
			}
		}
	}
}