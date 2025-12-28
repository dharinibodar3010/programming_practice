#include<iostream>
using namespace std;

//parameterized constructor using 'this' pointer

class student
{
    public:
        int id;
        string name;
        
        // parameterized constructor
        student(int id, string name)
        {
            this -> id = id; // 'this' refers to the current object
            this -> name = name;
        }
        
        void display()
        {
            cout<<"Your id is: "<<id<<endl;
            cout<<"Your name is: "<<name<<endl;
            cout<<"----------------------------"<<endl;
        }
};

int main()
{
    // creating objects using parameterized constructor
    student s1(101, "Dharini");
    student s2(102, "dhara");
    
    // displaying student details
    s1.display();
    s2.display();
    
    return 0;
}
