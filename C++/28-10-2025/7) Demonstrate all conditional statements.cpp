#include <iostream>
using namespace std;

//program: Demonstrate all conditional statements

int main() {

    int num;
    cout << "Enter a number: ";
    cin >> num;

    // 1) Simple if
    if (num > 0) 
    {
        cout << "Positive number" << endl;
    }

    // 2) if-else
    if (num % 2 == 0) 
    {
        cout << "Even number" << endl;
    } 
    else 
    {
        cout << "Odd number" << endl;
    }

    // 3) if-else if-else ladder
    if (num > 0) 
    {
        cout << "Number is positive" << endl;
    } 
    else if (num < 0) 
    {
        cout << "Number is negative" << endl;
    } 
    else 
    {
        cout << "Number is zero" << endl;
    }

    // 4) Nested if
    if (num > 0) 
    {
        if (num < 10) 
        {
            cout << "Positive and single-digit number" << endl;
        } 
        else 
        {
            cout << "Positive and multi-digit number" << endl;
        }
    }

    // 5) Switch statement
    int day;
    cout << "\nEnter day number (1-3): ";
    cin >> day;

    switch (day) 
    {
        case 1:
            cout << "Monday" << endl;
            break;
        case 2:
            cout << "Tuesday" << endl;
            break;
        case 3:
            cout << "Wednesday" << endl;
            break;
        default:
            cout << "Invalid day number" << endl;
    }

    return 0;
}
