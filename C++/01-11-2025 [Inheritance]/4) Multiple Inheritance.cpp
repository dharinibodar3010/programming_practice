#include <iostream>
using namespace std;

// Multiple Inheritance

// base class A
class A 
{
    public:
    void a()
    {
        cout << "A Called" << endl;
    }
};

// base class B
class B 
{
    public:
    void b()
    {
        cout << "B Called" << endl;
    }
};

// derived class C inherits from both A and B
class C : public A, public B 
{
    public:
    void display()
    {
       a(); // call from class A
       b(); // call from class B
    }
};

// Main Function
int main()
{
    C c1;
    c1.display();

    return 0;
}
