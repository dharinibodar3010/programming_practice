#include <iostream>
using namespace std;

class student
{
    public:
        int id;
        string name;
        string surname;
        
        // parameterized constructor with multiple objects
        student(int i, string n, string s)
        {
            id= i;
            name = n;
            surname = s;
        }
        
        void display()
        {
            cout<<"ID: "<<id<<endl;
            cout<<"Name: "<<name<<endl;
            cout<<"Surname: "<<surname<<endl;
            cout<<"-------------------"<<endl;
        }
};

int main()
{
    // creating multiple student objects
    student s1  (101, "kishan", "a");
    student s2  (102, "kartik", "b");
    student s3  (103, "dharini", "c");
    student s4  (104, "raj", "d");
    student s5  (105, "vijay", "e");
    
    // displaying all student details
    s1.display();
    s2.display();
    s3.display();
    s4.display();
    s5.display();

    return 0;
}
