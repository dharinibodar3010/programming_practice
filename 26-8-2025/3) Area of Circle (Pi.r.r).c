#include <stdio.h>
int main (){
    float radius, area;
    float PI = 3.14159;
    
    printf("Enter the radius of the circle : ");
    scanf("%f", &radius);//user input 
    
    area = PI * radius * radius;//formula 
    
    printf("\n area of circle = %f", area);
    
    return 0;
}
