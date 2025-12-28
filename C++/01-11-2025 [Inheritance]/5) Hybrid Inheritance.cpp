#include <iostream>
using namespace std;

// Hybrid Inheritance

// Base class A
class A
{
   public:
   void a()
   {
       cout << "A Called" << endl;
   }
};

// Base class B
class B 
{
   public:
   void b()
   {
       cout << "B Called" << endl;
   }
};

// Class C inherits from A
class C : public A
{
   public:
   void c()
   {
       cout << "C Called" << endl;
   }
};

// Class D inherits from both B and C
class D : public B, public C
{
   public:
   void d()
   {
       cout << "D Called" << endl;

       // Directly calling inherited functions
       b(); // from B
       c(); // from C
       a(); // from A through C
   }
};

int main()
{
    D d1;
    d1.d();

    return 0;

}
