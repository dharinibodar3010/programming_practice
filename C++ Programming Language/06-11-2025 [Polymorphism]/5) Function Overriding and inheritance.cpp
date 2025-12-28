#include <iostream>
using namespace std;

// Function Overriding and inheritance

class Animal
{
    public:
    void eat()
    {
        cout << "Eating...";
    }
};

class Dog : public Animal
{
    public:
    void eat() // function/method Overriding
    {
        cout << "Eating Bread...";
    }
};

int main()
{
    Dog d;
    
    d.eat(); // calls overridden

    return 0;
}
