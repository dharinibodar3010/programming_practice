#include <iostream>
using namespace std;

// Friend Function

// Class A 
class A 
{
  // Declare class B as a friend of A        
  friend class B;
  
  private:
  int a = 0;
  
  public:
  void printMember()
  {
      cout << a << endl;
  }
};

// Class B (friend of A)
class B
{
    public:
    void change(A & B, int x)
    {
        // B can access private member 'a' of A
        B.a = x;
    }
};

// Main Function
int main()
{
    // Create Object
    A a;
    B b;
    
    a.printMember(); // output: 0
    b.change(a, 5); // change private member 'a' using friend class
    a.printMember(); // output: 5

    return 0;
}
