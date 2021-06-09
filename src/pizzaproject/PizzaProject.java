/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaproject;



import java.util.Scanner;


/**
 *
 * @author will
 */
public class PizzaProject {    
    //not sure why I had to declare these strings twice, but it has been the only way I have been able to get it working
    static String[] toppings = {"Pizza cheese", "Diced onion", "Diced green pepper", "Pepperoni", "Sliced mushroom",
             "Diced jalapenos", "Sardines", "Pineapple chunks", "Tofu", "Ham Chunks", "Dry red pepper",
             "Dried basil"};
        
    static char[] listingChar = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
    static String[][] pizza = new String[8][2];
    //the index of the selected toppings from the toppings 
    static int[] alreadySelected = new int[6];
        

    public static void main(String[] args) {
    int toppingCount = 0;

//This will prompt user to choose the crust and will store it in the pizza array
    printCrust();
            pizza[0][1] = "1";
//This will prompt user to choose the crust and store the choice and ammount
    printSauce();
//x will be used to determine if the user wants to continue adding toppings
    char x = 'a';
//This will prompt the user to choose the first topping and store it in the pizza array
    int index = firstTopping();
//The alreadySelected array will store the index of the topping selected for future reference
       alreadySelected[toppingCount] = index;
       toppingCount++;
//ask the user if they want to continue
    x = addOrTerm();
//This loop will continue to ask the user for the next topping until
    while (toppingCount < 6 && x == 'a'){
    index = nthTopping(toppingCount);
        alreadySelected[toppingCount] = index;
        toppingCount++;
    x = addOrTerm();
    }
    printPizza();
    }
    
    public static int nthTopping(int toppingCount) {
    Scanner input = new Scanner(System.in);
        System.out.println("**************************************************\n");
        System.out.println("Please choose one ingredient option: \n");
        //for loop to print contents of both arrays
        for (int i = 0, k=0; i < (toppings.length); i++, k++) {
              if (contains(i, toppingCount)) {
                  k--;
                  continue;
              } else if (k%3==0) {System.out.printf("\n%-25s", listingChar[k] + ". " + toppings[i]);}
                else {System.out.printf("%-25s", listingChar[k] + ". " + toppings[i]);}
            }
        
        System.out.println("\nEnter choice: ");
        //x is the letter they input which indicates their choice of topping
        char x = input.next().charAt(0);
        //this method makes sure the char inputted by the user is valid
        while (x<'a'||x>'l'-toppingCount){
            x=nthToppingWrong(x, toppingCount);
        }
        //this method takes the char they inputted as their choice and searches the array for it. When we find it, its index is returned and stored in the int index.
    int index = findIndex(x);
        int addIndex = increaseIndex(index);
        index+=addIndex;
        pizza[toppingCount + 2][0] = toppings[index];
        /*We are sending the string that is at int index of the array toppings. 
        The method has a switch that identifies the string and calls the appropriate method that will ask for how much of the topping the user wants.
        The method returns a string of the amount they chose.*/
    String amt = amntSwitch(toppings[index]);
        pizza[toppingCount + 2][1] = amt;
        System.out.println("You chose: \t" + listingChar[index-addIndex] + ". " + toppings[index] + " = " + amt +"*");
        System.out.println("**************************************************\n");
        return index;
    }
    public static int increaseIndex (int index) {
        int num=0;
        int i;
        for (i=0; i<toppings.length; i++) {
            for (int j=0; j<pizza.length; j++) {
                if(toppings[i]==pizza[j][0]) {
                    num++;
                    continue;
                }
            }
            if(i==index+num) {
                break;
            }
            }
           
      
        return num;
    }  
    public static char nthToppingWrong(char x, int toppingCount) {
        Scanner input = new Scanner(System.in);
            System.out.println("Please enter the letter corresponding with the topping below");
              for (int i = 0, k=0; i < (toppings.length); i++, k++) {
              if (contains(i, toppingCount)) {
                  k--;
                  continue;
              } else if (k%3==0) {System.out.printf("\n%-25s", listingChar[k] + ". " + toppings[i]);}
                else {System.out.printf("%-25s", listingChar[k] + ". " + toppings[i]);}
            }
        
        System.out.println("\nEnter choice: ");
        //x is the letter they input which indicates their choice of topping
        x = input.next().charAt(0);
        return x;
    }
    public static boolean contains(int i, int toppingCount) {
        for (int j = 0; j < toppingCount; j++) {
            if (alreadySelected[j] == i) {
                return true;
            }
        }
        return false;
    }
    public static int firstTopping() {
          Scanner input = new Scanner(System.in);
        System.out.println("**************************************************\n");
        System.out.println("Please choose one ingredient option: \n");
//for loop to print contents of both arrays in three rows
        for (int i = 0; i < listingChar.length; i++) {
            if (i%3==0) {System.out.printf("\n%-25s", listingChar[i] + ". " + toppings[i]);}
            else {System.out.printf("%-25s", listingChar[i] + ". " + toppings[i]);}
        }
        System.out.println("\nEnter choice: ");
        //x is the letter they input which indicates their choice of topping
        char x = input.next().charAt(0);
//make sure the selection is a viable char
        while (x<'a'||x>'l') {
            x=firstToppingWrong();
        }
        //this method takes the char they inputted as their choice and searches the array for it. When we find it, its index is returned and stored in the int index.
    int index = findIndex(x);
        pizza[2][0] = toppings[index];
        /*We are sending the string that is at int index of the array toppings. 
        The method has a switch that identifies the string and calls the appropriate method that will ask for how much of the topping the user wants.
        The method returns a string of the amount they chose.*/
    String amt = amntSwitch(toppings[index]);
        pizza[2][1] = amt;
        System.out.println("You chose: \t" + listingChar[index] + ". " + toppings[index] + " = " + amt+"*");
        System.out.println("**************************************************\n");
        return index;
}
    public static char firstToppingWrong() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the letter corresponding with the topping below");
            for (int i = 0; i < listingChar.length; i++) {
            if (i%3==0) {System.out.printf("\n%-25s",listingChar[i] + ". " + toppings[i]);}
            else {System.out.printf("%-25s",listingChar[i] + ". " + toppings[i]);}
        }
        System.out.println("\nEnter choice: ");
        //x is the letter they input which indicates their choice of topping
        char x = input.next().charAt(0);
        return x;
    }
    public static String amntSwitch(String x) {
        String value = "";
        switch (x) {
            case "Pizza cheese": value = qCup(); break;
            case "Diced onion": value = eCup(); break;
            case "Diced green pepper": value = eCup(); break;
            case "Pepperoni": value = pieces(); break;
            case "Sliced mushroom": value = eCup(); break;
            case "Diced jalapenos": value = eCup(); break;
            case "Sardines": value = sardines(); break;
            case "Pineapple chunks": value = pieces(); break;
            case "Tofu": value = qCup(); break;
            case "Ham Chunks": value = ham(); break;
            case "Dry red pepper": value = sprinkle(); break;
            case "Dried basil": value = sprinkle(); break;
        }
       return value;
    }
    public static String qCup() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose amount: \n");
        System.out.println("a.1/4 cup \t\t b. 1/2 cup \n");
        System.out.println("Enter choice: ");
        char qc = input.next().charAt(0);
        qc = aOrB(qc);
        String result = "";
        switch (qc) {
            case 'a': result = "1/4 cup"; break;
            case 'b': result = "1/2 cup"; break;
        }
        return result;
    }
    public static String eCup() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose amount: \n");
        System.out.println("a.1/8 cup \t\t b. 1/4 cup \n");
        System.out.println("Enter choice:");
        char ec = input.next().charAt(0);
        ec=aOrB(ec);
        String result = "";
        switch (ec) {
            case 'a': result = "1/8 cup"; break;
            case 'b': result = "1/4 cup"; break;
        }
        return result;
    }
    public static String pieces() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose amount: \n");
        System.out.println("a.2 pieces \t\t b. 4 pieces \t\t c. 6 pieces \t\t d. 8 pieces");
        System.out.println("Enter choice:");
        char ca = input.next().charAt(0);
        ca=abcd(ca);
        String result = "";
        switch (ca) {
            case 'a': result = "2 pieces"; break;
            case 'b': result = "4 pieces"; break;
            case 'c': result = "6 pieces"; break;
            case 'd': result = "8 pieces"; break;
        }
        return result;
    }
    public static String sardines() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose amount: \n");
        System.out.println("a.1 piece \t\t b. 2 pieces \t\t c. 3 pieces \t\t d. 4 pieces");
        System.out.println("Enter choice:");
        char s = input.next().charAt(0);
        s=abcd(s);
        String result = "";
        switch (s) {
            case 'a': result = "1 pieces"; break;
            case 'b': result = "2 pieces"; break;
            case 'c': result = "3 pieces"; break;
            case 'd': result = "4 pieces"; break;
        }
        return result;
    }
    public static String ham() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose amount: \n");
        System.out.println("a.4 piece \t\t b. 8 pieces \t\t c. 12 pieces \t\t d. 16 pieces");
        System.out.println("Enter choice:");
        char h = input.next().charAt(0);
        h=abcd(h);
        String result = "";
        switch (h) {
            case 'a': result = "4 pieces"; break;
            case 'b': result = "8 pieces"; break;
            case 'c': result = "12 pieces"; break;
            case 'd': result = "16 pieces"; break;
        }
        return result;
    }
    public static String sprinkle() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please choose amount: \n");
        System.out.println("a.1 generous sprinkle \t\t b. 2 generous sprinkles \n");
        System.out.println("Enter choice:");
        char gs = input.next().charAt(0);
        gs=aOrB(gs);
        String result = "";
        switch (gs) {
            case 'a': result = "1 generous sprinkle"; break;
            case 'b': result = "2 generous sprinkles"; break;
        }
        return result;
    }
    public static int findIndex(char n){
        int i = 0;
        while (i < listingChar.length) {
            if (listingChar[i] == n) {
                return i;
            }
            i++;
        }
        return i;
    }
    public static char printCrust() {
      Scanner input = new Scanner(System.in);
        System.out.println("**************************************************");
        System.out.println("Please enter one crust option: \n");
        System.out.println("a. regular crust \t\t b. gluten-free crust \n");
        System.out.print("Enter your choice: ");
        char c = input.next().charAt(0);
        c = aOrB(c);
        if (c == 'a'){
            pizza[0][0] = "Regular crust";
            System.out.println("* You chose: \t" + c+". regular crust");
        }else if (c == 'b') {
            System.out.println("* You chose: \t" + c + ". gluten-free crust");
            pizza[0][0] = "Gluten-free crust";
    }
        else {aOrB(c);}
        return c;
    }
    public static char printSauce() {
      Scanner input = new Scanner(System.in);
        System.out.println("**************************************************\n");
        System.out.println("Please choose one sauce option: \n");
        System.out.println("a. red sauce \t\t b. no red sauce \n");
        System.out.print("Enter choice: ");
        char s = input.next().charAt(0);
        s=aOrB(s);
        if (s == 'a') {
            pizza[1][0] = "Red sauce";
       }
        else if (s == 'b') 
           pizza[1][0] = "No sauce";
//select how much sauce if 'a' was chosen
    if (s == 'a') {String amt = qCup();
        pizza[1][1] = amt;
        System.out.println("* You chose:  "+ s + ". red sauce = " + amt+"*");
        pizza[1][1] = amt;
        }else if (s=='b') {
            pizza[1][1] = "";
            System.out.println("* You chose:  "+ s +". no red sauce");
        }
    return s;
    }
    public static char aOrB(char n) {
      Scanner input = new Scanner(System.in);
        if (n!='a' && n!='b') {
        System.out.println("Please enter 'a' or 'b' for your choice. \n");
        System.out.print("Enter your choice: ");
        n = input.next().charAt(0);
        }
        return n;
    }
    public static char abcd(char n) {
        Scanner input = new Scanner(System.in);
        if (n!='a' && n!='b' && n!='c' && n!='d') {
        System.out.println("Please enter 'a' 'b' 'c' or 'd' for your choice. \n");
        System.out.print("Enter your choice: ");
        n = input.next().charAt(0);
        }
        return n;
    }
    public static void printPizza() {
        System.out.println("**************************************************\n");
        System.out.println("*Your pizza recipe*");
        for (int i = 0; i < pizza.length; i++) {
            if(pizza[i][0] == null)
                break;
            System.out.printf("%-25s%s%n", pizza[i][0], pizza[i][1]);
        }
        System.out.println("\n* Pizza is to be appropriately cooked until crust is cooked and toppings are warmed.");
        System.out.println("\n**************************************************");
}
    public static char addOrTerm () {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to add another ingredient?\n");
        System.out.println("a. continue \t\t b. finished \n");
        System.out.print("Enter choice: ");
        char c = input.next().charAt(0);
        c = aOrB(c);
        return c;
    }
}


    