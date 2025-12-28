#include <iostream>
using namespace std;

// Exception Handling Example

// function to perform division of two integers
float division(int x, int y)
{
    // check if demonator is zero
    if(y == 0)
    {
        throw "Attempted to divide by zero!"; // throw exception (string message)
    }
    return(x/y);
}


int main()
{
    try // try block - code that may cause exception
    {
        cout << division(25,5);
    }
    
    catch (const char* e) // catch block - handles exception if throw
    {
        cerr << e << endl; // print error message to standard error
    }
    
    return 0;
}
