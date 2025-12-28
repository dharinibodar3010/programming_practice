#include <iostream>
using namespace std;

// Hierarchical Inheritance

class Bank
{
    public:
    void bank()
    {
        cout << "Banking" << endl;
    }
};

class Current : public Bank 
{
    public:
    void current()
    {
        cout << "Current" << endl;
    }
};

class Save : public Bank
{
    public:
    void save()
    {
        cout << "Save" << endl;
    }
};

int main()
{
    Current c1;
    Save s1;
    
    c1.bank();
    c1.current();
    s1.save();

    return 0;
}
