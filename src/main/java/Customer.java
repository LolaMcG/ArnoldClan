import java.util.ArrayList;

public class Customer implements IPurchase {

    private String name;
    private double cash;
    private ArrayList<Buyable> stuffBought;

    public Customer(String name, double cash){
        this.name = name;
        this.cash = cash;
        this.stuffBought = new ArrayList<>();
    }

    public void addItem(Buyable item){
        stuffBought.add(item);
    }

    public int getNumberOfItemsBought() {
        return stuffBought.size();
    }

    public double getCash(){
        return this.cash;
    }

    public void buySomething(Buyable item){
        addItem(item);
        cash -= item.getPrice();
    }
}
