/*3. Inheritance Example
o Write a program that implements inheritance using a base class Person and derived
classes Student and Teacher. Demonstrate reusability through inheritance.
o Objective: Learn the concept of inheritance.*/

#include <iostream>
using namespace std;

// Base class
class person
{
    public:
    string name;
    int age;
    
    void getData()
    {
        cout << "Enter name: ";
        cin >> name;
        
        cout << "Enter age: ";
        cin >> age;
    }
    
    void showData()
    {
        cout << "Name: " << name << endl;
        cout << "Age: " << age << endl;
    }
};

// Derived class: Student inherits from person
class student : public person
{
    public:
    int rollNo;
    
    void getstudentData()
    {
        getData();
        cout << "Enter rollNo: ";
        cin >> rollNo;
    }
    
    void showstudentData()
    {
        cout << "/n--- Student Information ---" << endl;
        showData();
        cout << "Roll No: " << rollNo << endl;
    }
};

// Derived class: Teacher inherits from person
class teacher : public person
{
    public:
    string subject;
    
    void getteacherData()
    {
        getData();
        cout << "Enter Subject: ";
        cin >> subject;
    }
    
    void showteacherData()
    {
        cout << "/n--- Teacher Information ---" << endl;
        showData();
        cout << "Subject: " << subject << endl;
    }
};

// Main Function
int main()
{
    // create object
    student s;
    teacher t;
    
    s.getstudentData();
    t.getteacherData();
    
    s.showstudentData();
    t.showteacherData();

    return 0;
}
