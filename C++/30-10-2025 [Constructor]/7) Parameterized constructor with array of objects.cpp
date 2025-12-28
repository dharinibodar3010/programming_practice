#include<iostream>
using namespace std;

// parameterized constructor with array of objects

class student
{
    public:
        int id;
        string name;
        
        // parameterized constructor
        student(int i, string n)
        {
            id = i;
            name = n;
        }
        
        // default constructor (needed for array declaration)
        student() {}
        
        void display()
        {
            cout<<"Your ID is: "<<id<<endl;
            cout<<"Your Name is: "<<name<<endl;
            cout<<"-----------------------------"<<endl;
        }
    
};

int main()
{
    int n, i;
    
    cout<<"Enter number of student: ";
    cin>>n;
    
    student s[n];  // array of objects
    
    // taking user input for all students
    for(i = 0; i < n; i++)
    {
        int id;
        string name;
        
        cout<<"\nEnter ID for student "<<i+1<<" : ";
        cin>>id;
        
        cout<<"Enter Name for student "<<i+1<<" : ";
        cin>>name;
        
        // Assign values using parameterized constructor
        s[i] = student(id, name);
    }
    
    // displaying all student details
    cout<<"\n===== Student Details =====\n";
    for(i = 0; i < n; i++)
    {
        s[i].display();
    }
    
    return 0;
}
