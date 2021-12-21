package Momo;

// import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class MyPromotion implements Promotion{
    int budget=50000;
    float winrate = (float) 0.9;

    @Override
    public void run(List<String> history,HashMap<String,Integer> drinks) {//history to know when to give out drinks, drinks to know the price to fit into budget
        HashMap<String,Integer> purchase  = new HashMap<String,Integer>();
        for(String buy:history){
            int count = purchase.containsKey(buy) ? purchase.get(buy) : 0;
            purchase.put(buy, count + 1);

        }
        for(String item:purchase.keySet()){

            if(purchase.get(item)>=3 && Math.random() - winrate<0){
                int price = drinks.get(item);
                if(price < budget )
                {                
                    System.out.println("Congratulation you just win a "+item);
                    budget -= drinks.get(item);
                }
            } 
        }
        
    }
    
}
