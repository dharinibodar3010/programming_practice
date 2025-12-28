/*Que 2: Basic Input/Output
o Write a C++ program that accepts user input for their name and age and then
displays a personalized greeting.
o Objective: Practice input/output operations using cin and cout.*/

#include <iostream>   // For input and output

using namespace std;

int main() {
    string name;    // Variable to store user's name
    int age;        // Variable to store user's age

    // Taking input from the user
    cout << "Enter your name: ";
    cin >> name;     // Input name (stops at space)

    cout << "Enter your age: ";
    cin >> age;      // Input age

    // Display message
    cout << "Hello, " << name << "! You are " << age << " years old." << endl;

    return 0;
}
