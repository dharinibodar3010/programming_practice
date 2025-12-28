#include <iostream>
using namespace std;

int main()
{
    int age = 15;
    
    if(age>=18)
    {
        cout<<"Eligible to vote\n";
        
        //nested if
        if(age>=60)
        {
            cout<<"Senior citizen\n";
        }
        else
        {
            cout<<"Young age\n";
        }
    }
    else
    {
        cout<<"Not Eligible to vote";
    }
    
  return 0;
}
