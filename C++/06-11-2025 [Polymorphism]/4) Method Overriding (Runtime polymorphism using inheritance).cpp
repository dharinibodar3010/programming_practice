#include <iostream>
using namespace std;

// Method Overriding (Runtime polymorphism using inheritance)

class Rbi
{
    public:
    virtual int rate() // make it virtual for Overriding
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
    
    // cout<<s.rate()<<endl;
    // cout<<i.rate()<<endl;
    // cout<<a.rate()<<endl;
    
    Rbi *r; // base class pointer
    Sbi s;
    Icici i;
    Axis a;
    
    r = &s;
    cout << "SBI rate of interest: " << r->rate() << endl; // arrow operator(->) is used with pointers to objects (or structures) to access their member (function or member)
    
    r = &i;
    cout << "ICICI rate of interest: " << r->rate() << endl;
    
    r = &a;
    cout << "AXIS rate of interest: " << r->rate() << endl;
    
    
    
    return 0;
}
