/*2. Factorial Calculation Using Recursion
o Write a C++ program that calculates the factorial of a number using recursion.
o Objective: Understand recursion in functions.*/

#include <iostream>
using namespace std;

int factorial(int n)
{
    if(n == 0 || n == 1)
     {
        return 1; // base case
     }
    else
     {
        return n * factorial(n - 1); //recursive call
     }
}

int main()
{
    int num;
    
    cout<<"Enter positive number: ";
    cin>>num;
    
    if(num < 0 )
    {
        cout<<"Error: factorial of a negative number doesn't exist."<<endl;
    }
    else
    {
        cout<<"factorial of "<<num<<" is "<<factorial(num)<<endl;
    }
    
    return 0;
}
