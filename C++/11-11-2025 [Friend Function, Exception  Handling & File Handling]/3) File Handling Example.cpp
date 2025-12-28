#include <iostream>
#include <fstream>
using namespace std;

// File Handling Example

int main()
{
    char data[100];
    
    // create an output file stream object
    ofstream outfile;
    
    // open file for writing
    outfile.open("test1. txt");
    
    cout << "Enter Your Name: ";
    cin >> data;
    
    // write data to file
    cout << data << endl;
    
    // close the output file
    outfile.close();
    
    
    // create input file stream object
    ifstream infile;
    
    // open file for reading
    infile.open("test1.txt");
    
    // read data from file
    infile >> data;
    
    // close the input file
    infile.close();
    
    return 0;
}
