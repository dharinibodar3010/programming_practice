#include <iostream>
using namespace std;

// HIERARCHICAL INHERITANCE

class shape // PARENT
{
    public:
    int a, b;
    
    void shapedata(int a1, int b1)
    {
        a = a1;
        b = b1;
    }
};

class rectangle : public shape // CHILD
{
    public:
    int arearec()
    {
        return a * b;
    }
};

class triangle : public shape // CHILD
{
    public: 
    int areatri()
    {
        return 0.5 * a * b;
    }
};

int main()
{
    rectangle rec;
    triangle tri;
    
    rec.shapedata(5,8);
    tri.shapedata(8,9);
    
    cout << "Area of Rectangle is: " << rec.arearec() << endl;
    cout << "Area of Triangle is: " << tri.areatri() << endl;
    
    return 0;
}
