/*Que 3. Operator Demonstration
o Write a C++ program that demonstrates arithmetic, relational, logical, and bitwise
operators. Perform operations using each type of operator and display the results.
o Objective: Reinforce understanding of different types of operatorsin C++.*/

#include <iostream>
using namespace std;

int main() {
    int a = 10, b = 5;

    // --- Arithmetic Operators ---
    cout << "=== Arithmetic Operators ===" << endl;
    cout << "a + b = " << a + b << endl;
    cout << "a - b = " << a - b << endl;
    cout << "a * b = " << a * b << endl;
    cout << "a / b = " << a / b << endl;
    cout << "a % b = " << a % b << endl;

    // --- Relational Operators ---
    cout << "\n=== Relational Operators ===" << endl;
    cout << "a == b : " << (a == b) << endl;
    cout << "a != b : " << (a != b) << endl;
    cout << "a > b  : " << (a > b) << endl;
    cout << "a < b  : " << (a < b) << endl;
    cout << "a >= b : " << (a >= b) << endl;
    cout << "a <= b : " << (a <= b) << endl;

    // --- Logical Operators ---
    cout << "\n=== Logical Operators ===" << endl;
    int x = 10, y = 5;
    cout << "x && y : " << (x > 0 && y < 0) << endl;  // Logical AND
    cout << "x || y : " << (x > 0 || y < 0) << endl;  // Logical OR
    cout << "!x     : " << (!x) << endl;             // Logical NOT

    // --- Bitwise Operators ---
    cout << "\n=== Bitwise Operators ===" << endl;
    cout << "a & b  : " << (a & b) << endl;   // Bitwise AND
    cout << "a | b  : " << (a | b) << endl;   // Bitwise OR
    cout << "a ^ b  : " << (a ^ b) << endl;   // Bitwise XOR
    cout << "~a     : " << (~a)    << endl;   // Bitwise NOT
    cout << "a << 1 : " << (a << 1) << endl;  // Left Shift
    cout << "a >> 1 : " << (a >> 1) << endl;  // Right Shift

    return 0;
}
