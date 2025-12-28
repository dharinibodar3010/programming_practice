/*2. Matrix Addition
o Write a C++ program to perform matrix addition on two 2x2 matrices.
o Objective: Practice multi-dimensional arrays.*/

#include<iostream>
using namespace std;

int main()
{
    int A[2][2], B[2][2], C[2][2];
    int i, j;
    
    cout << "Enter elements of first 2x2 matrix (A): " << endl;
    for(i=0; i<2; i++)
    {
        for(j=0; j<2; j++)
        {
            cout << "A[" << i << "][" << j << "] = ";
            cin >> A[i][j];
        }
    }
    
    cout << "\nEnter elements of first 2x2 matrix (B): " << endl;
    for(i=0; i<2; i++)
    {
        for(j=0; j<2; j++)
        {
            cout << "B[" << i << "][" << j << "] = ";
            cin >> B[i][j];
        }
    }
    
    // Matrix Addition
    for(i=0; i<2; i++)
    {
        for(j=0; j<2; j++)
        {
            C[i][j] = A[i][j] + B[i][j];
        }
    }
    
    cout << "\nResultant Matrix (A + B) is: " << endl;
    for(i=0; i<2; i++)
    {
        for(j=0; j<2; j++)
        {
            cout << C[i][j] << "\t";
        }
        
        cout << endl;
    }
    
    return 0;

}
