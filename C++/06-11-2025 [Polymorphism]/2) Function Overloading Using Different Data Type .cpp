#include <iostream>
using namespace std;

// Function Overloading Using Different Data Type 

class Calculation
{
    public:
    int cal(int a, int b) // addition (integer parameters)
    {
        return a+b;
    }
    
    double cal(double a, double b) // multiplication (double parameters)
    {
        return a*b;
    }
};

int main()
{
    Calculation c1;
    
    cout << c1.cal(6,3) << endl;
    cout << c1.cal(6.00,3.00) << endl;

    return 0;
}
