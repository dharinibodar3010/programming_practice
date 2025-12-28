#include <iostream>
using namespace std;

//Program: Basic Array Program

int main() {
    int arr[5];  // declaration of array with size 5

    cout << "Enter 5 numbers: ";
    for (int i = 0; i < 5; i++) 
    {
        cin >> arr[i];  // input array elements
    }

    cout << "\nYou entered: ";
    for (int i = 0; i < 5; i++) 
    {
        cout << arr[i] << " ";  // display array elements
    }

    return 0;
}
