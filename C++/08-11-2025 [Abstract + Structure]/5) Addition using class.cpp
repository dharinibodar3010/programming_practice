#include <iostream>
using namespace std;

// Addition using class

class Data 
{
private:
    int x, y, z;

public:
    void add() 
    {
        cout << "Enter two numbers: ";
        cin >> x >> y;

        z = x + y;

        cout << "Sum of two numbers is: " << z << endl;
    }
};


int main() 
{
    Data d;
    d.add();

    return 0;
}
