#include<iostream>
using namespace std;

// Assigning values to class members without constructor

class student
{
    public:
    int id;
    string name;
    
    void display()
    {
        cout<<id<<name<<endl;
    }
};

int main()
{
    //student s1;
    //student s1 = student();
    
    student s1;
    
    s1.id = 101;
    s1.name = "test";
    
    cout<<s1.id<<endl;
    cout<<s1.name<<endl;
    
    return 0;
}
