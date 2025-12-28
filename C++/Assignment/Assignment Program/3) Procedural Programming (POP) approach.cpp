/*Que 3: POP vs. OOP Comparison Program

o Write two small programs: one using Procedural Programming (POP) to calculate the
area of a rectangle, and another using Object-Oriented Programming (OOP) with a
class and object for the same task.

o Objective: Highlight the difference between POP and OOP approaches.*/

// Approach : 1) Procedural Programming (POP) approach

#include<iostream>
using namespace std;

// Function to calculate area
float area(float length, float width)
{
    return length * width;
}

int main()
{
  float length, width;
  
  cout<<"Enter length: ";
  cin>>length;
  
  cout<<"Enter width: ";
  cin>>width;
  
  float result = area(length, width);
  
  cout<<"Area of rectangle = " << result << endl;
  
  
  // Explanation of pop style
  
  cout<<"\nExplanation (POP Style) : \n" << "Uses functions and variables directly.\n"

  <<"Focus is on steps and logic (procedures), not on data organization.\n" <<

  "Data and functions are separate.\n" << endl;
  
  return 0;

}
