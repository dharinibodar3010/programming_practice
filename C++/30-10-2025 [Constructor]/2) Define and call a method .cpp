#include<iostream>
using namespace std;

// Define and call a method (member function) of a class
class student
{
    //method
    public:
    void display()
    {
        cout<<"Hii";
    }
};

int main()
{
    student s1;
    
    s1.display(); // method call
    
    return 0;
}
