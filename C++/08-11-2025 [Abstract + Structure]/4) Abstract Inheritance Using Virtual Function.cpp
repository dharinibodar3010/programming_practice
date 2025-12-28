#include <iostream>
using namespace std;

// Abstract Inheritance Using Virtual Function

// Base Class
class Shape
{
    public:   
    
    virtual void draw2() = 0;    
};

// Derived Class 
class Rectangle : public Shape
{
    public: 
    
    void draw2()
    {
         
    }
    
    void draw()    
    {    
        cout <<"drawing rectangle..." <<endl;    
    }    
};

class Circle : public Shape
{
    public: 
    
    void draw2()
    {
         
    }
     
    void draw()    
    {    
        cout <<"drawing circle..." <<endl;    
    }    
};


int main()
{
    Rectangle rec;  
    Circle cir;
  
    rec.draw();    
    cir.draw();   
    
    return 0;
}
