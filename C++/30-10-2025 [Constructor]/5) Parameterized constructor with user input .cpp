#include<iostream>
using namespace std;

//parameterized constructor with user input 

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
    int id1, id2;
    string name1, name2;
    
    // user input
    cout<<"Enter id for student 1: ";
    cin>>id1;
    
    cout<<"Enter name for student 1: ";
    cin>>name1;
    
    cout<<"-------------------------"<<endl;
    
    cout<<"Enter id for student 2: ";
    cin>>id2;
    
    cout<<"Enter name for student 2: ";
    cin>>name2;
    
    cout<<"-------------------------"<<endl;
    
    // creating objects using parameterized constructor
    student s1(id1, name1);
    student s2(id2, name2);
    
    // displaying student details
    s1.display();
    s2.display();
    
    return 0;
}
