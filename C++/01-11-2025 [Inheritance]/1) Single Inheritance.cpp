#include <iostream>
using namespace std;

// Single Inheritance 

class A
{
    public: 
    void a()
    {
        cout << "A Called" << endl;
    }
};

class B : public A
{
    public: 
    void b()
    {
        cout << "B Called" << endl;
    }
};


int main()
{
    B b1; // create object
    
    b1.a();
    b1.b();
    
    return 0;
}
