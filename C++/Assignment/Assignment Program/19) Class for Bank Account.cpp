/*2. Class for Bank Account
o Create a class BankAccount with data members like balance and member functions
like deposit and withdraw. Implement encapsulation by keeping the data members
private.
o Objective: Understand encapsulation in classes.*/

#include <iostream>
using namespace std;

class BankAccount
{
    private:
        float balance; // private data member (Encapsulation)
        
    public: 
        // constructor to initialize balance
        BankAccount(float initialBalance)
        {
            if(initialBalance >= 0)
            {
                balance = initialBalance;
            }
            else
            {
                balance = 0;
            }
        }
        
        // function to deposit money
        void deposit(float amount)
        {
            if(amount > 0)
            {
                balance += amount;
                cout << "Deposit: " << amount << endl;
            }
            else
            {
                cout << "Invalid deposit amount!" << endl;    
            }
        }
        
        // function to withdraw money
        void withdraw(float amount)
        {
            if(amount > 0 && amount <= balance)
            {
                balance -= amount;
                cout << "Withdraw: " << amount << endl;
            }
            else if(amount > balance)
            {
                cout << "Insufficient balance!" << endl;
            }
            else
            {
                cout << "Invalid withdrawal amount!" << endl;
            }
        }
        
        // function to check current balance
        void checkBalance()
        {
            cout << "Current Balance: " << balance << endl;
        }
};

// Main function
int main()
{
    BankAccount account1(1000); //create object with initial balance
    
    cout << "---- Bank Transaction ----" << endl;
    
    account1.checkBalance(); // show initial balance
    account1.deposit(500); // deposit money
    account1.withdraw(300); // withdraw money
    account1.checkBalance(); // show final balance
    
    return 0;
}
