#include <iostream>
using namespace std;

// Static Data Member (Object Counter)

// static keyword : single shared value among all objects of a class
class Account
{
public:
    int accno;
    string name;
    static int count; // static data member declaration
    
    // Parameterized Constructor
    Account(int accno, string name)
    {
        this->accno = accno;
        this->name = name;
        count++; // increment static counter when object created
    }
    
    // Member Function to display details
    void display()
    {
        cout << accno << " " << name << endl; 
    }
};

// Initialize static data member
int Account::count = 0;

int main()
{
    Account a1(101, "ABCD");
    Account a2(102, "PQRS");
    
    a1.display();
    a2.display();
    
    cout << "Total Objects are: " << Account::count << endl;

    return 0;
}
