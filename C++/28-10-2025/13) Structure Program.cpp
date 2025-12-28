#include <iostream>
using namespace std;

//Program: Structure Program

// structure definition
struct Student 
{
    int id;
    string name;
    float marks;
};

int main() 
{
    // create structure variable
    Student s1, s2;

    // input data for first student
    cout << "Enter first student details:\n";
    cout << "ID: ";
    cin >> s1.id;
    cout << "Name: ";
    cin >> s1.name;
    cout << "Marks: ";
    cin >> s1.marks;

    // input data for second student
    cout << "\nEnter second student details:\n";
    cout << "ID: ";
    cin >> s2.id;
    cout << "Name: ";
    cin >> s2.name;
    cout << "Marks: ";
    cin >> s2.marks;

    // display student information
    cout << "\n--- Student Details ---\n";
    cout << "ID\tName\tMarks\n";
    cout << s1.id << "\t" << s1.name << "\t" << s1.marks << endl;
    cout << s2.id << "\t" << s2.name << "\t" << s2.marks << endl;

    return 0;
}
