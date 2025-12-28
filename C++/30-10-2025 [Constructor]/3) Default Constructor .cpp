#include <iostream>
using namespace std;

// Default Constructor 

class student
{
   public:
       //default constructor
        student()
        {
            cout<<"Hii";
        }
};

int main()
{
    // object creation - constructor is called automatically
    student s1;
    
    return 0;
}
