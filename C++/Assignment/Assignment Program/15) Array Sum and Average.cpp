/*Que 1. Array Sum and Average
o Write a C++ program that accepts an array of integers, calculates the sum and
average, and displays the results.
o Objective: Understand basic array manipulation.
*/

#include <iostream>
using namespace std;

int main()
{
    int n, i;
    
    cout<<"Enter the number of elements: ";
    cin>>n;
    
    int arr[100]; //array declaration (max 100 elements)
    
    int sum = 0;
    
    cout<<"Enter "<<n<<" integers: "<<endl;
    for(i=0; i<n; i++)
    {
        cin >> arr[i];
        sum += arr[i];
    }
    
    float average = sum/n;
    
    cout<<"\nSum = "<<sum<<endl;
    cout<<"Average = "<<average<<endl;

    return 0;
}
