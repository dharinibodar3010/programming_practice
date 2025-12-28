/*Que 4: Setting Up Development Environment
o Write a program that asks for two numbers and displays their sum. Ensure this is
done after setting up the IDE (like Dev C++ or CodeBlocks).
o Objective: Help students understand how to install, configure, and run programs
inan IDE.*/

#include <iostream>
using namespace std;

int main()
{
    int num1, num2, sum;
    
    // Ask user to enter two numbers
    cout<<"Enter first number: ";
    cin>>num1;
    
    cout<<"Enter second number: ";
    cin>>num2;
    
    // calculate sum
    sum = num1 + num2;
    
    // display result
    cout<<"The sum of "<<num1<<" and "<<num2<<" is: "<<sum<<endl;

    return 0;
}
