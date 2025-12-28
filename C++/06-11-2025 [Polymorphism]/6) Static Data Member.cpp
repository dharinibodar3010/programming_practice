#include <iostream>
using namespace std;

// Static Data Member

// static keyword : single shared value among all objects of a class
class Account
{
public:
    int accno;
    string name;
    static float roi; // static data member declaration
    
    // Parameterized Constructor
    Account(int accno, string name)
    {
        this->accno = accno;
        this->name = name;
    }
    
    // Member Function to display details
    void display()
    {
        cout << accno << " " << name << " " << roi << endl; 
    }
};

// Static member initialization outside the class
float Account::roi = 6.5;

int main()
{
    Account a1(101, "Dharini");
    Account a2(102, "Manan");
    
    a1.display();
    a2.display();

    return 0;
}
