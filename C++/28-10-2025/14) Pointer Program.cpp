#include <iostream>
using namespace std;

//Program: Pointer Program

int main() 
{
    int num = 10;
    int *ptr = &num;   // pointer stores address of num

    cout << "Value of num: " << num << endl;
    cout << "Address of num: " << &num << endl;
    cout << "Value using pointer: " << *ptr;
    return 0;
}
