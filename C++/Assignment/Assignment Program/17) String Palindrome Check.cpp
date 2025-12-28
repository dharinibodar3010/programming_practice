/*3. String Palindrome Check
o Write a C++ program to check if a given string is a palindrome (reads the same
forwards and backwards).
o Objective: Practice string operations.
*/

#include<iostream>
using namespace std;

int main()
{
    string str, rev = ""; // empty at first
    
    cout << "Enter a string: ";
    cin >> str; // input a single word
    
    // Reverse the String 
    for(int i = str.length() - 1; i >= 0; i--)
    {
        rev += str[i];
    }
    
    // Check if palindrome
    if (str == rev)
    {
        cout << "This string is a palidrome." << endl;
    }
    else 
    {
        cout << "This string is not palidrome." << endl;
    }
    
    return 0;
    
}
