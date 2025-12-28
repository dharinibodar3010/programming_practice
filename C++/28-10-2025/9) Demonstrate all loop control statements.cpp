#include <iostream>
using namespace std;

//Program: Demonstrate all loop control statements

int main() 
{
    int i;
    
    cout << "=== FOR LOOP ===" << endl;
    for (i = 1; i <= 5; i++) 
    {
        cout << i << " ";
    }

    cout << "\n\n=== WHILE LOOP ===" << endl;
    i = 1;
    while (i <= 5) 
    {
        cout << i << " ";
        i++;
    }

    cout << "\n\n=== DO-WHILE LOOP ===" << endl;
    i = 1;
    do 
    {
        cout << i << " ";
        i++;
    } 
    while (i <= 5);

    cout << "\n\n=== BREAK STATEMENT ===" << endl;
    for (i = 1; i <= 10; i++) 
    {
        if (i == 5)
            break;  // exits the loop when i = 5
        cout << i << " ";
    }

    cout << "\n\n=== CONTINUE STATEMENT ===" << endl;
    for (int i = 1; i <= 10; i++) 
    {
        if (i == 5)
            continue;  // skips printing 5
        cout << i << " ";
    }
    
    cout << "\n\n=== GOTO ===" << endl;
    i = 1;
    
    start: // label
        cout << i << " ";
        i++;
        
        if(i <= 5)
        {
            goto start; // jump back to label
        }
        
        cout << "\nLoop ended using goto.";

    return 0;
}
