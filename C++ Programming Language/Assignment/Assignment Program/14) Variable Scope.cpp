/*3. Variable Scope
o Write a program that demonstrates the difference between local and global
variables in C++. Use functions to show scope.
o Objective: Reinforce the concept of variable scope.*/

#include <iostream>
using namespace std;

// Global Variable
int num = 10;

// Function to show Global Variable
void showGlobal()
{
    cout << "Global Variable (showglobal function): " << num << endl;
}

// Function to show local Variable
void showLocal()
{
    int num = 20;
    cout << "Local Variable: " << num << endl;
    cout << "To access global variable (showLocal function): " << ::num << endl;
}

int main()
{
   cout << "Global Variable (int main function): " << num << endl;
   
   // Call functions
   showGlobal();
   showLocal();
   
   return 0;
}
