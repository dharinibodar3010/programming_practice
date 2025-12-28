#include <iostream>
using namespace std;

// Area of Rectangle Using Structure and Constructor
struct Shape 
{
    int width, height;

    // Constructor to initialize values
    Shape(int w, int h) 
    {
        width = w;
        height = h;
    }

    // Function to calculate area
    void areaOfRectangle() 
    {
        cout << "Area of Rectangle is: " << (width * height) << endl;
    }
};

int main() 
{
    // No need to write 'struct' again in C++
    Shape s(4, 4);
    s.areaOfRectangle();

    return 0;
}
