#include <iostream>
using namespace std;

class student
{
    public:
    
    //data members
    int id;
    string name;
    string city;
    
    //method
    void display()
    {
        cout<<id<<" "<<name<<" "<<city<<endl;
    }
};


int main()
{
    //object
    student s1;
    student s2;
    
    //value assign
    s1.id = 101;
    s1.name = "dharini";
    s1.city = "rajkot";
    
    s1.display(); //method call
    
    s2.id = 102;
    s2.name = "kishan";
    s2.city = "rajkot";
    
    s2.display(); 
    
    return 0;
}
