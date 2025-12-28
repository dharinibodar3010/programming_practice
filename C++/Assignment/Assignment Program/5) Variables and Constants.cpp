/*Que 1: Variables and Constants
o Write a C++ program that demonstrates the use of variables and constants. Create
variables of different data types and perform operations on them.
o Objective: Understand the difference between variables and constants.*/

#include<iostream>
using namespace std;

int main()
{
    // constants declaration
    const float PI = 3.14159;
    
    // variable declaration
    int age = 20;
    float radius = 5.5;
    char grade = 'A';
    double salary = 25000.50;
    bool isStudent = true;
    
    // perform operations
    int nextYearAge = age + 1;
    double area = PI * radius * radius;
    double bonus = salary * 0.10;
    
    // display output
    cout<<"Constant PI = "<<PI<<endl;
    cout<<"Age = "<<age<<endl;
    cout<<"Next Year Age = "<<nextYearAge<<endl;
    cout<<"Circle Area = "<<area<<endl;
    cout<<"Grade = "<<grade<<endl;
    cout<<"Salary = "<<salary<<endl;
    cout<<"Bonus (10%) = "<<bonus<<endl;
    cout<<"Is Student = "<<isStudent<<endl;
    
    return 0;
}

// Objective:

// How to declare and use variables of different data types.

// How to define constants using const.

// How variables can change, while constants remain fixed.
