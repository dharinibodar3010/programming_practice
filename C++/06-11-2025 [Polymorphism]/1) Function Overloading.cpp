#include <iostream>
using namespace std;

// Function Overloading : when two or more methods having same name but number of parameters are different

class Calculation
{
    public:
    int cal(int a, int b) // addition
    {
        return a+b;
    }
    
    int cal(int c, int d, int e) // multiplication
    {
        return c*d*e;
    }
};

int main()
{
    Calculation c1;
    
    cout << c1.cal(6,3) << endl;
    cout << c1.cal(9,2) << endl;
    cout << c1.cal(6,3,2) << endl;

    return 0;
}
