import java.util.ArrayList;

public class Dealership implements IPurchase {

    private ArrayList<VehicleType> cars;
    private ArrayList<Part> spareParts;
    private ArrayList<Customer> customers;
    private double till;

    public Dealership(double till){
        this.till = till;
        this.spareParts = new ArrayList<>();
        this.cars = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public int getNumberOfCarsInStock(){
        return this.cars.size();
    }

    public ArrayList getCars(){
        return this.cars;
    }

    public void addCarToStock(VehicleType car){
        this.cars.add(car);
    }

    public void removeCarFromStock(VehicleType car){
        this.cars.remove(car);
    }

    public int getNumberOfSparePartsInStock(){
        return this.spareParts.size();
    }

    public ArrayList getParts(){
        return this.spareParts;
    }

    public void addSparePartToStock(Part sparePart){
        this.spareParts.add(sparePart);
    }

    public void removePartFromStock(Part sparePart){
        this.spareParts.remove(sparePart);
    }

    public double getTill(){
        return till;
    }

    public void addMoneyToTill(double amount){
        getTill();
        till += amount;
    }

    public void decreaseMoneyInTill(double amount){
        getTill();
        till -= amount;
    }

    public void takeInCustomer(Customer customer){
        customers.add(customer);
    }

    public ArrayList getCustomers(){
        return this.customers;
    }

    public int getNoOfCustomers(){
        return this.customers.size();
    }


    public void buySomething(Buyable item){
        double itemPrice = item.getPrice();
        double moneyInTill = getTill();
        if(moneyInTill >= itemPrice) {
            if (item instanceof VehicleType) {
                addCarToStock((VehicleType) item);
            } else if (item instanceof Part) {
                addSparePartToStock((Part) item);
            } else {
                System.out.println("Invalid transaction");
                return;
            }
            decreaseMoneyInTill(itemPrice);
        } else {
            System.out.println("Insufficient funds to make transaction");
        }
    }

    public void sellSomething(Customer customer, Buyable item){
        if(customer.getCash() >= item.getPrice()){
            if (item instanceof VehicleType){
                removeCarFromStock((VehicleType) item);
            } else if (item instanceof Part){
                removePartFromStock((Part) item);
            } else{
                System.out.println("Customer has insufficient funds to make the purchase");
                return;
            }
            addMoneyToTill(item.getPrice());
            customer.addItem(item);
        }
    }
}
