#include <iostream>
using namespace std;

//Program: Demonstrate all operators

int main()
{
    int a = 10, b = 3;
    
    cout<<"-----Arithmetic Operators-----"<<endl;
    cout<<"Addition: "<<a + b<<endl;
    cout<<"Subtraction: "<<a - b<<endl;
    cout<<"Multiplication: "<<a * b<<endl;
    cout<<"Division: "<<a / b<<endl;
    cout<<"Modulus: "<<a % b<<endl;
    
    cout<<"\n-----Relational Operators-----"<<endl;
    cout<<"a == b: "<<(a == b)<<endl;
    cout<<"a != b: "<<(a != b)<<endl;
    cout<<"a > b: "<<(a > b)<<endl;
    cout<<"a < b: "<<(a < b)<<endl;
    cout<<"a >= b: "<<(a >= b)<<endl;
    cout<<"a <= b: "<<(a <= b)<<endl;
    
    cout<<"\n-----Logical Operators-----"<<endl;
    cout<<"(a > 0 && b > 0): "<<((a > 0) && (b > 0))<<endl;
    cout<<"(a > 0 || b < 0): "<<((a > 0) || (b < 0))<<endl;
    cout<<"!(a == b): "<<(!(a == b))<<endl;
    
    cout<<"\n-----Assignment Operators-----"<<endl;
    int c = a;
    cout<<"c += b: "<<(c += b)<<endl;
    cout<<"c -= b: "<<(c -= b)<<endl;
    cout<<"c *= b: "<<(c *= b)<<endl;
    cout<<"c /= b: "<<(c /= b)<<endl;
    cout<<"c %= b: "<<(c %= b)<<endl;
    
    cout<<"\n-----Increment & Decrement Operators-----"<<endl;
    int x = 5;
    cout<<"x = "<<x<<endl;
    cout<<"Pre increment (++x): "<<(++x)<<endl;
    cout<<"Post increment (x++): "<<(x++)<<endl;
    cout<<"After post increment: "<<x<<endl;
    cout<<"Pre decrement (--x): "<<(--x)<<endl;
    cout<<"Post decrement (x--): "<<(x--)<<endl;
    cout<<"After post decrement: "<<x<<endl;
    
    cout<<"\n-----Ternary Operator-----"<<endl;
    cout<<"a > b? true : false -> "<<((a > b)? true : false)<<endl;
    
    cout<<"\n-----Bitwise Operators-----"<<endl;
    cout<<"a & b: "<<(a & b)<< endl;
    cout<<"a | b: "<<(a | b)<< endl;
    cout<<"a ^ b: "<<(a ^ b)<< endl;
    cout<<"a << 1: "<<(a << 1)<< endl;
    cout<<"a >> 1: "<<(a >> 1)<< endl;
    cout<<"~a: "<<(~a)<< endl;

    cout << "\n-----sizeof Operator-----" << endl;
    cout << "Size of int: " << sizeof(int) << " bytes" << endl;
    cout << "Size of float: " << sizeof(float) << " bytes" << endl;
    cout << "Size of double: " << sizeof(double) << " bytes" << endl;
    cout << "Size of char: " << sizeof(char) << " bytes" << endl;



    return 0;
}
