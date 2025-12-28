/*1. Class for a Simple Calculator
o Write a C++ program that defines a class Calculator with functions for addition,
subtraction, multiplication, and division. Create objects to use these functions.
o Objective: Introduce basic class structure.*/

#include<iostream>
using namespace std;

//-----------------------------
//  class declaration
//-----------------------------
class Calculator
{
    public:
        // Function for addition
        float add(float a, float b)
        {
            return a + b;
        }
        
        // Function for subtraction
        float subtract(float a, float b)
        {
            return a - b;
        }
        
        // Function for multiplication
        float multiply(float a, float b)
        {
            return a * b;
        }
        
        // Function for division
        float divide(float a, float b)
        {
            if (b != 0)
            {
                return a / b;
            }
            else
            {
                cout << "Error! Divison by zero not allowed." << endl;
                return 0;
            }
        }
};

//-----------------------------
//   MAIN FUNCTION
//-----------------------------
int main()
{
    // Object creation
    Calculator c;
    
    float num1, num2;
    
    // User input
    cout << "Enter two numbers: ";
    cin >> num1 >> num2;
    
    cout << "Addition: " << c.add(num1, num2) << endl;
    cout << "Subtraction: " << c.subtract(num1, num2) << endl;
    cout << "Multiplication: " << c.multiply(num1, num2) << endl;
    cout << "Divison: " << c.divide(num1, num2) << endl;
    
    return 0;
}
