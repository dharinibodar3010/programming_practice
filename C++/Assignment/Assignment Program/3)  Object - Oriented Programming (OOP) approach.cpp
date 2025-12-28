/*Que 3: POP vs. OOP Comparison Program

o Write two small programs: one using Procedural Programming (POP) to calculate the
area of a rectangle, and another using Object-Oriented Programming (OOP) with a
class and object for the same task.

o Objective: Highlight the difference between POP and OOP approaches.*/

// Approach: 2) Object - Oriented Programming (OOP) approach

#include <iostream>
using namespace std;

// Define a class
class Rectangle 
{
private:
    float length, width;

public:
    // Function to take input
    void input() 
    {
        cout << "Enter length: ";
        cin >> length;
        
        cout << "Enter width: ";
        cin >> width;
    }

    // Function to calculate area
    float area() 
    {
        return length * width;
    }

    // Function to display result
    void display() 
    {
        cout << "Area of rectangle = " << area() << endl;
    }
};

int main() 
{
    Rectangle rect;  // Object creation
    rect.input();
    rect.display();
    
    
    // Explanation of oop style
    
    cout<<"\nExplanation (OOP Style): \n" << "\nUses class to combine data (length, width) and functions (input(), area(), display())." <<

    "\nFocus is on objects that contain both data and behavior." <<

    "\nPromotes encapsulation (data hiding)." << endl;

    return 0;
}
