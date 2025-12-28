#include <iostream>
using namespace std;

// Area of shape using struct 

struct Shape
{
    int width;
    int height;
};

int main()
{
    Shape s; // no need to write 'struct' again in c++
    
    s.width = 50;
    s.height = 60;
    
    cout << "Value is " << s.width * s.height << endl;
    
    return 0;
}
