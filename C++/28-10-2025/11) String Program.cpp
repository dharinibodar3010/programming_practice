#include <iostream>
#include <string>
using namespace std;

//Program: String Program

int main() {
    string str1, str2, str3;

    cout << "Enter first string: "; // printf
    cin >> str1;                   // scanf - user input

    cout << "Enter second string: ";
    cin >> str2;

    // 1) Length
    cout << "\nLength of first string: " << str1.length();
    cout << "\nLength of second string: " << str2.length();

    // 2) Concatenation
    str3 = str1 + " " + str2;
    cout << "\n\nAfter concatenation: " << str3;

    // 3) Copy
    string copyStr = str1;  // copying str1 into copyStr
    cout << "\nCopied string: " << copyStr;

    // 4) Compare
    if (str1 == str2)
        cout << "\nStrings are equal.";
    else if (str1 > str2)
        cout << "\nFirst string is greater.";
    else
        cout << "\nSecond string is greater.";

    return 0;

}
