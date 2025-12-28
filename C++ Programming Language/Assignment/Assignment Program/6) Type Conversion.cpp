/*Que 2: Type Conversion
o Write a C++ program that performs both implicit and explicit type conversions and
prints the results.
o Objective: Practice type casting in C++.*/

#include <iostream>
using namespace std;

int main()
{
    //implicit type Conversion (automatic)
    int intValue = 10;
    double doubleValue;
    
    //int automatically converted to double
    doubleValue = intValue;
    
    cout<<"Implicit Type Conversion: "<<endl;
    cout<<"Integer Value: "<<intValue<<endl;
    cout<<"Converted to double: "<<doubleValue<<endl;
    
    //explicit type Conversion (manual casting)
    double num1 = 9.78;
    int num2;
    
    //manually convert double to int
    num2 = (int) num1; //or use stactic_cast<int>(num1)
    
    cout<<"\nExplicit Type Conversion: "<<endl;
    cout<<"Double Value: "<<num1<<endl;
    cout<<"Converted to Integer: "<<num2<<endl;
    
    //another example: arithmetic type casting
    int a = 5, b = 2;
    double result;
    
    //explicit Conversion to get decimal result
    result = (double)a/b;
    
    cout<<"\nExplicit Conversion in Expression: "<<endl;
    cout<<"Result of (double)a/b: "<<result<<endl;

    return 0;
}
