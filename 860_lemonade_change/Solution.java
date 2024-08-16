/*
Problem: Lemonade Change

At a lemonade stand, each lemonade costs $5. Customers are standing in a queue to buy from you and order one at a time (in the order specified by bills). 
Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill. 
You must provide the correct change to each customer so that the net transaction is that the customer pays $5.

Note that you do not have any change in hand at first.

Given an integer array bills where bills[i] is the bill the ith customer pays, return true if you can provide every customer with the correct change, 
or false otherwise.

Example 1:
Input: bills = [5,5,5,10,20]
Output: true
Explanation: 
From the first 3 customers, we collect three $5 bills in order.
From the fourth customer, we collect a $10 bill and give back a $5.
From the fifth customer, we give a $10 bill and a $5 bill.
Since all customers got correct change, we output true.

Example 2:
Input: bills = [5,5,10,10,20]
Output: false
Explanation: 
From the first two customers in order, we collect two $5 bills.
For the next two customers in order, we collect a $10 bill and give back a $5 bill.
For the last customer, we cannot give the change of $15 back because we only have two $10 bills.
Since not every customer received the correct change, the answer is false.

Constraints:
1 <= bills.length <= 10^5
bills[i] is either 5, 10, or 20.
*/

public class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0; // to keep track of the number of $5 bills
        int ten = 0; // to keep track of the number of $10 bills

        for (int bill : bills) {
            if (bill == 5) {
                five++; // simply collect the $5 bill
            } else if (bill == 10) {
                if (five > 0) {
                    five--; // give one $5 bill as change
                    ten++; // collect the $10 bill
                } else {
                    return false; // cannot give correct change
                }
            } else { // when bill is $20
                if (ten > 0 && five > 0) {
                    ten--; // give one $10 bill as change
                    five--; // give one $5 bill as change
                } else if (five >= 3) {
                    five -= 3; // give three $5 bills as change
                } else {
                    return false; // cannot give correct change
                }
            }
        }

        return true; // all customers received correct change
    }

    public static void main(String[] args) {
        Solution lemonadeChange = new Solution();

        int[] bills1 = { 5, 5, 5, 10, 20 };
        System.out.println(lemonadeChange.lemonadeChange(bills1)); // true

        int[] bills2 = { 5, 5, 10, 10, 20 };
        System.out.println(lemonadeChange.lemonadeChange(bills2)); // false
    }
}