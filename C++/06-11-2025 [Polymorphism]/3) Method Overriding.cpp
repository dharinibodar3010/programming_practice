#include <iostream>
using namespace std;

// Method Overriding : when two or more methods having a same name and number of parameters are also same 

class Rbi
{
    public:
    int rate() // same methods and same parameters
    {
        return 0;
    }
};

class Sbi : public Rbi
{
    public:
    int rate()
    {
        return 7;
    }
};

class Icici : public Rbi
{
    public:
    int rate()
    {
        return 8;
    }
};

class Axis : public Rbi
{
    public:
    int rate()
    {
        return 9;
    }
};


int main()
{
    // Sbi s;
    // Icici i;
    // Axis a;
    
    // cout << "SBI rate of interest: " << s.rate() << endl;
    // cout << "ICICI rate of interest: " << i.rate() << endl;
    // cout << "AXIS rate of interest: " << a.rate() << endl;
    
    Rbi r;
    
    return 0;
}
