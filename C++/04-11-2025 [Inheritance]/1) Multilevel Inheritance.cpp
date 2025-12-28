#include<iostream>
using namespace std;

//MULTILEVEL INHERITANCE

class A // BASE CLASS
{
    protected:
    void get_a()
    {
        cout << "A called" << endl;
    }
};

class B : public A
{
    protected:
    void get_b()
    {
        cout << "B called" << endl;
    }
};

class C : public B
{
    public:
    void display()
    {
        C c1;
        c1.get_a();
        c1.get_b();
    }
};

int main()
{
    C c1;
    c1.display();
    
    return 0;
}
