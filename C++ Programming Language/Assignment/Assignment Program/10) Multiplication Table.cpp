/*3. Multiplication Table
o Write a C++ program to display the multiplication table of a given number using a for
loop.
o Objective: Practice using loops.*/

#include<iostream>
using namespace std;

int main()
{
    int i, number;
    
    //user input
    cout<<"Enter Any Number: ";
    cin>>number;
    
    cout<<"multiplication table of "<<number<<":\n";
    
    //use a for loop to display the table
    for(i=1; i<=10; i++)
    {
        cout<<number<<" x "<<i<<" = "<<number * i<<endl;
    }
    
    return 0;
}
