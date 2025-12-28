#include <iostream>
using namespace std;

// Hybrid inheritance
// Base class
class A
{
    public:
    int a;
    void get_a(int a1)
    {
        a = a1;
    }
};

// Derived class from A
class B : public A
{
    public:
    int b;
    void get_b(int b1)
    {
        b = b1;
    }
};

// Another derived class from A
class C : public A
{
    public: 
    int c;
    void get_c(int c1)
    {
        c = c1;
    }
};

// Derived class from both B and class C 
class D : public B, public C
{
    public:
    void multiply()
    {   
        // to avoid ambiguity, we use B::a or C::a (both have A's member)
        cout << "Multiplication: " << (B::a * b * c) << endl;
    }
};

// Main Function 
int main()
{
   D d1;
   int x, y, z;
   
   cout << "Enter first number (a): ";
   cin >> x;
   
   cout << "Enter second number (b): ";
   cin >> y;
   
   cout << "Enter third number (c): ";
   cin >> z;
   
   d1.B::get_a(x);
   d1.get_b(y);
   d1.get_c(z);
   
   d1.multiply();

    return 0;
}
