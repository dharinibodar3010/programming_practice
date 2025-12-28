#include <iostream>
using namespace std;

//Program: Function program

// Function declaration(prototype)
int add(int a, int b); 

int main() 
{
    int x, y, sum;

    cout << "Enter two numbers: ";
    cin >> x >> y;

    // Function call
    sum = add(x, y);

    cout << "Sum = " << sum;
    return 0;
}

// Function definition
int add(int a, int b) 
{
    return a + b;   // returns the sum of two numbers
}
