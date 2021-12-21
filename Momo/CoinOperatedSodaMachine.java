package Momo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Collections;


public class CoinOperatedSodaMachine{
    private Promotion promotion;
    private ArrayList<Integer> exceptedNotes;
    private HashMap<String,Integer> drinks;
    public CoinOperatedSodaMachine(Promotion promotion, ArrayList<Integer> exceptedNotes,HashMap<String,Integer> drinks) {
        this.promotion = promotion;
        this.exceptedNotes =   exceptedNotes;
        Collections.sort(this.exceptedNotes);
        Collections.reverse(this.exceptedNotes);// sort decending order to make it easier to get the change
        this.drinks = drinks;
    }
    
    public void buy(Scanner scan){
        int money = 0;
        int bill = 1;
        //insert money
        System.out.println("Please insert your money into the machine (to stop and go to order press 0): ");
        while (bill != 0){
            bill = scan.nextInt();
            if(!exceptedNotes.contains(bill) && bill != 0) {
                System.out.println("Your money is invalid. We only accept: ");
                for(Integer note:exceptedNotes){
                    System.out.println(note);
                }
            }
            else{
                money += bill;
            }
        }
        //choose drinks 
        scan.nextLine();//to wipe the buffer
        String choice = "";
        int remain = money;// make copy of money so that we know how much money they have left and also to refund when they cancel
        ArrayList<String> buys = new ArrayList<String>();
        System.out.println("Please choose the drinks from the list or press cancel to cancel and get refund or press 0 to exit:");

        for(String drink: drinks.keySet()){
            System.out.println(drink+"("+drinks.get(drink)+")");//list the drinks that we have and their prices 
        }
        while ( !choice.equals("0") ){

            choice = scan.nextLine();
            if (choice.equals("cancel")){// customer cancel order
                refund(money);
                return;
            }
            Integer price = drinks.get(choice);
            if(price != null){
                if(remain - price >=0){
                    remain -= price;
                    buys.add(choice);
                }
                else{
                    System.out.println("You didn't insert enough money to buy that.");
                }
            }
            else if (!choice.equals("0")){
                System.out.println("You input an invalid drink");
            }
        }
        // refund
        promotion.run(buys,drinks);
        refund(remain) ;
    }
    private void refund(int money){
        System.out.println("Here is your change:");
        int change=0;
        while (money >0){
            for(Integer note:exceptedNotes){
                if (note<=money){
                    change = note;
                    money -= note; 
                    break;  
                }
            }
            System.out.println(change);
        }
    }

}