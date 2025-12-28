/* Que 1. Simple Calculator Using Functions
o Write a C++ program that defines functions for basic arithmetic operations (add,
subtract, multiply, divide). The main function should call these based on user input.
o Objective: Practice defining and using functions in C++.
*/

#include <iostream>
using namespace std;

//function declarations
float add(float a, float b);
float subtract(float a, float b);
float multiply(float a, float b);
float divide(float a, float b);

int main()
{
    float num1, num2;
    int choice;
    
    cout<<"-----Simple Calculator-----"<<endl;
    cout<<"1. Addition"<<endl;
    cout<<"2. Subtraction"<<endl;
    cout<<"3. Multiplication"<<endl;
    cout<<"4. Division"<<endl;
    
    cout<<"Enter your choice (1-4): ";
    cin>>choice;
    
    cout<<"Enter first number: ";
    cin>>num1;
    
    cout<<"Enter second number: ";
    cin>>num2;
    
    //switch case to call appropriate function
    switch(choice)
    {
        case 1:
            {
            cout<<"Result: "<<add(num1, num2)<<endl;
            }
            break;
        
        case 2:
            {
            cout<<"Result: "<<subtract(num1, num2)<<endl;
            }
            break;
        
        case 3:
            {
            cout<<"Result: "<<multiply(num1, num2)<<endl;
            }
            break;
        
        case 4:
            if(num2 != 0)
            {
                cout<<"Result: "<<divide(num1, num2)<<endl;
            }
            else
            {
                cout<<"Error: Division by zero is not allowed!"<<endl;    
            }
            break;
        
        default:
            cout<<"Invalid choice! please choose between (1-4)."<<endl;
    }
    
    return 0;
}

//function definitions
float add(float a, float b)
{
    return a + b;
}

float subtract(float a, float b)
{
    return a - b;
}

float multiply(float a, float b)
{
    return a * b;
}

float divide(float a, float b)
{
    return a / b;
}
