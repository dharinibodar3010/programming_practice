#include<iostream>
#include<string>
using namespace std;

//-------------------------------
// CLASS DECLARATION
//-------------------------------
class FoodOrder
{
    private: 
        string customerName;
        int mainChoic, subChoice, quantity;
        float totalBill;
        float grandTotal;
        
    public:
        // Constructor
        FoodOrder()
        {
            mainChoic = 0;
            subChoice = 0;
            quantity = 0;
            totalBill = 0;
            grandTotal = 0;
        }
        
        // Function to get customer name
        void getCustomerName()
        {
            cout << "==================================================" << endl;
            cout << "       Tops Tech. Fast Food Ordering              ";
            cout << "\n==================================================" << endl;
            
            cout << "Please Enter Your Name: ";
            cin >> customerName;
            
            cout << "\nHello " << customerName << "!\n";
            cout << "\nWhat would you like to order?\n";
        }
        
        // Function to display main menu
        void displayMainMenu()
        {
            cout << "\n------------ MENU ------------\n";
            cout << "1) Pizzas\n";
            cout << "2) Burgers\n";
            cout << "3) Sandwich\n";
            cout << "4) Rolls\n";
            cout << "5) Biryani\n";
            cout << "6) Exit\n";
            cout << "-------------------------------\n";
            cout << "Please Enter Your Choice: ";
        }
        
        // Function for food selection
        void takeOrder()
        {
            char ch;
            do
            {
                displayMainMenu();
                cin >> mainChoic;
                
                switch (mainChoic)
                {
                    case 1:
                        pizzaMenu();
                        break;
                    case 2:
                        burgerMenu();
                        break;
                    case 3:
                        sandwichMenu();
                        break;
                    case 4:
                        rollMenu();
                        break;
                    case 5:
                        biryaniMenu();
                        break;
                    case 6:
                        cout << "\nThank you for visiting Tops Tech. Fast Food!\n";
                        return;
                    default:
                        cout << "\nInvalid choice! Please try again.\n";
                }
                
                cout << "\nWould you like to order anything else? (Y/N):  ";
                cin >> ch;
            }
            
            while (ch == 'Y' || ch == 'y');
            
            showFinallBill();
        }
        
        // -------------------------------
        // Individual Food Menus
        // -------------------------------
        void pizzaMenu()
        {
            cout << "\nWhich Pizza would you like to have\n";
            cout << "1) Margherita Rs.250\n";
            cout << "2) Paneer Pizza Rs.300\n";
            cout << "3) Veg Deluxe Rs.350\n";
            
            cout << "Enter your choice: ";
            cin >> subChoice;
            
            string item;
            float price = 0;
            
            switch (subChoice)
            {
                case 1:
                    item = "Margherita Pizza";
                    price = 250;
                    break;
                case 2:
                    item = "Paneer Pizza";
                    price = 300;
                    break;
                case 3:
                    item = "Veg Deluxe Pizza";
                    price = 350;
                    break;
                default:
                    cout << "Invalid choice!\n";
                    return;
            }
            
            cout << "Enter Quantity: ";
            cin >> quantity;
            
            totalBill = price * quantity;
            grandTotal += totalBill;
            printBill(item, price, quantity);
        }
        
        void burgerMenu()
        {
            cout << "\nWhich Burger would you like to have\n";
            cout << "1) Veg Burger Rs.120\n";
            cout << "2) Cheese Burger Rs.150\n";
            cout << "3) Paneer Burger Rs.180\n";
            
            cout << "Enter your choice: ";
            cin >> subChoice;
            
            string item;
            float price = 0;
            
            switch (subChoice)
            {
                case 1:
                    item = "Veg Burger\n";
                    price = 120;
                    break;
                case 2:
                    item = "Cheese Burger\n";
                    price = 150;
                    break;
                case 3:
                    item = "Paneer Burger\n";
                    price = 180;
                    break;
                default:
                    cout << "Invalid choice!\n";
                    return;
            }
            
            cout << "Enter Quantity: ";
            cin >> quantity;
            
            totalBill = price * quantity;
            grandTotal += totalBill;
            printBill(item, price, quantity);    
        }
        
        void sandwichMenu()
        {
            cout << "\nWhich Sandwich would you like to have\n";
            cout << "1) Club Sandwich Rs.200\n";
            cout << "2) veg crispy Sandwich Rs.160\n";
            cout << "3) Extreme Veg Sandwich Rs.180\n";
            
            cout << "Enter your choice: ";
            cin >> subChoice;
            
            string item;
            float price = 0;
            
            switch (subChoice)
            {
                case 1:
                    item = "Club Sandwich\n";
                    price = 200;
                    break;
                case 2:
                    item = "veg crispy Sandwich\n";
                    price = 160;
                    break;
                case 3:
                    item = "Extreme Veg Sandwich\n";
                    price = 180;
                    break;
                default:
                    cout << "Invalid choice!\n";
                    return;
            }
            
            cout << "Enter Quantity: ";
            cin >> quantity;
            
            totalBill = price * quantity;
            grandTotal += totalBill;
            printBill(item, price, quantity);    
        }
        
        void rollMenu() 
        {
        cout << "\nWhich Roll would you like to have?\n";
        cout << "1) Paneer Roll Rs.100\n";
        cout << "2) Veg Roll Rs.80\n";
        cout << "3) Egg Roll Rs.120\n";
        
        cout << "Enter your choice: ";
        cin >> subChoice;

        string item;
        float price = 0;

        switch (subChoice) 
        {
            case 1: 
                item = "Paneer Roll"; 
                price = 100; 
                break;
            case 2: 
                item = "Veg Roll"; 
                price = 80; 
                break;
            case 3: 
                item = "Egg Roll"; 
                price = 120; 
                break;
            default: 
                cout << "Invalid choice!\n"; 
                return;
        }

        cout << "Enter Quantity: ";
        cin >> quantity;

        totalBill = price * quantity;
        grandTotal += totalBill;
        printBill(item, price, quantity);
    }

        void biryaniMenu() 
        {
            cout << "\nWhich Biryani would you like to have?\n";
            cout << "1) Veg Biryani Rs.180\n";
            cout << "2) Paneer Biryani Rs.220\n";
            cout << "3) Hyderabadi Biryani Rs.250\n";
            
            cout << "Enter your choice: ";
            cin >> subChoice;
    
            string item;
            float price = 0;
    
            switch (subChoice) 
            {
                case 1: 
                    item = "Veg Biryani"; 
                    price = 180;
                    break;
                case 2: 
                    item = "Paneer Biryani"; 
                    price = 220; 
                    break;
                case 3: 
                    item = "Hyderabadi Biryani"; 
                    price = 250; 
                    break;
                default: 
                    cout << "Invalid choice!\n"; 
                    return;
            }
    
            cout << "Enter Quantity: ";
            cin >> quantity;
    
            totalBill = price * quantity;
            grandTotal += totalBill;
            printBill(item, price, quantity);
        }

    // Function to print bill
    void printBill(string item, float price, int qty) 
    {
        cout << "\n---------------- Your Order ----------------\n";
        cout << "Item: " << item << "\n";
        cout << "Quantity: " << qty << "\n";
        cout << "Price per Item: Rs." << price << "\n";
        cout << "--------------------------------------------\n";
        cout << "Your Total Bill: Rs." << totalBill << "\n";
        cout << "Your order will be delivered in 40 minutes.\n";
        cout << "Thank you for ordering from Tops Tech. Fast Food!\n";
    }
    
    // Function to show final Bill
    void showFinallBill()
    {
        cout << "============================================" << endl;
        cout << "           FINAL BILL SUMMARY               ";
        cout << "\n============================================" << endl;
        cout << "Customer Name: " << customerName << endl;
        cout << "Total Amout to Pay: Rs. " << grandTotal << endl;
        cout << "============================================" << endl;
    }
};

// ------------------------------
//  MAIN FUNCTION
// ------------------------------
int main() 
{
    FoodOrder order;  // Create Object

    order.getCustomerName(); // Step 1: Get Name
    order.takeOrder();       // Step 2: Take Order

    cout << "\nVisit Again, Have a Nice Day!\n";
    
    return 0;
}
 
        
