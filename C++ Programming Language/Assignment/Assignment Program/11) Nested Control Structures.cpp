/* Que 4. Nested Control Structures
o Write a program that prints a right-angled triangle using stars(*) with a nested loop.
o Objective: Learn nested control structures. */

#include<iostream>
using namespace std;

int main()
{
    int i, j, n;
    
    cout<<"Enter number for rows: ";
    cin>>n;
    
    for(i=1; i<=n; i++) //outer loop for rows
    {
        for(j=1; j<=i; j++) //inner loop for printing stars in each row
        {
            cout<<"*";
        }
        
        //move to the next line after each row
        cout<<"\n"; //also use endl;
    }
    
    return 0;
}
