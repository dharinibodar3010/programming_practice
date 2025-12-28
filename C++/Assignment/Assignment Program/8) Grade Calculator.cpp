/*Que 1. Grade Calculator
o Write a C++ program that takes a student’s marks as input and calculates the grade
based on if-else conditions.
o Objective: Practice conditional statements (if-else).*/

#include<iostream>
using namespace std;

int main()
{
    int marks;
    
    //input marks from the user
    cout<<"Enter your marks (0-100): ";
    cin>>marks;
    
    //check for valid input
    if(marks < 0 || marks > 100)
    {
        cout<<"Invalid marks!Please enter a value between 0 and 100."<<endl;
    }
    else
    {
        //determine grade using if-else ladder
        if(marks >= 90)
        {
            cout<<"Grade: A+"<<endl;
        }
        else if(marks >= 80)
        {
            cout<<"Grade: A"<<endl;
        }
        else if(marks >= 70)
        {
            cout<<"Grade: B"<<endl;
        }
        else if(marks >= 60)
        {
            cout<<"Grade: C"<<endl;
        }
        else if(marks >= 50)
        {
            cout<<"Grade: D"<<endl;
        }
        else
        {
            cout<<"Grade: E"<<endl;
        }
    }
    
    return 0;
}
