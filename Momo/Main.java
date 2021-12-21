package Momo;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> exceptedNotes= new ArrayList<Integer>();// adding the bank notes that we except as money
        exceptedNotes.add(10000);
        exceptedNotes.add(20000);
        exceptedNotes.add(50000);
        exceptedNotes.add(100000);
        exceptedNotes.add(200000);
        HashMap<String,Integer> drinks = new HashMap<String,Integer>();// adding the drinks and their price
        drinks.put("coke",10000);
        drinks.put("pepsi",10000);
        drinks.put("soda",20000);
        
        CoinOperatedSodaMachine machine = new CoinOperatedSodaMachine(new MyPromotion(), exceptedNotes, drinks);
        System.out.println("Machine activated");
        Scanner scan = new Scanner(System.in);
        String cmd = "";
        while(!cmd.equals("exit")){
            machine.buy(scan);
            cmd = scan.nextLine();
        }
        scan.close();
    }
}
