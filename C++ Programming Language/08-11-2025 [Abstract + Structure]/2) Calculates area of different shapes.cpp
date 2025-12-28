#include <iostream>
using namespace std;

// Calculates area of different shapes using structure and conditional statements

struct Shape 
{
    int a, b, choice;
};

int main() {
    Shape s;

    cout << "--- Calculates Area of Shapes ---\n";
    cout << "1. for Rectangle\n";
    cout << "2. for Circle\n";
    cout << "3. for Square\n";
    
    cout << "Enter Your Choice: ";
    cin >> s.choice;

    if (s.choice == 1) {
        cout << "Enter length and breadth: ";
        cin >> s.a >> s.b;
        int ans = s.a * s.b;
        cout << "Area of Rectangle = " << ans << endl;
    } 
    else if (s.choice == 2) {
        cout << "Enter radius: ";
        cin >> s.a;
        float ans = 3.14 * s.a * s.a;
        cout << "Area of Circle = " << ans << endl;
    } 
    else if (s.choice == 3) {
        cout << "Enter side: ";
        cin >> s.a;
        int ans = s.a * s.a;
        cout << "Area of Square = " << ans << endl;
    } 
    else {
        cout << "Invalid choice!" << endl;
    }

    return 0;
}
