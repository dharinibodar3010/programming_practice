/*Que 2. Number Guessing Game
o Write a C++ program that asks the user to guess a number between 1 and 100. The
program should provide hints if the guess is too high or too low. Use loops to allow
the user multiple attempts.
o Objective: Understand while loops and conditional logic.*/

#include<iostream>
#include<cstdlib> // for rand() and srand()
#include<ctime> // for time()
using namespace std;

int main()
{
    int number, guess, attempts = 0;
    
    //Generate a random number between 1 and 100
    srand(time(0)); //seed random number generator
    number = rand() % 100 + 1;
    
    cout<<"welcome to the number guessing game!"<<endl;
    cout<<"lucky number between 1 and 100."<<endl;
    
    //loop until the correct number is guessed
    do
    {
        cout<<"\nEnter Your guess: ";
        cin>>guess;
        attempts++;
        
        if(guess > number)
        {
            cout<<"Too high! Try again."<<endl;
        }
        else if(guess < number)
        {
            cout<<"Too low! Try again."<<endl;
        }
        else
        {
            cout<<"Congratulations! You guessed the correct number "<<number<<" in "<<attempts<<" attempts!"<<endl;
        }
    }
    while(guess != number);
    
    return 0;
}
