import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DealershipTest {

    private Dealership arnoldClan;
    private ArrayList<VehicleType> carsInStock;
    private ArrayList<Part> partsInStock;
    private ElectricCar electricCar1;
    private PetrolCar petrolCar;
    private Part rotaryEngine;
    private Tyre tyre;
    private Customer customer;

    @Before
    public void before(){
        arnoldClan = new Dealership(0);
        carsInStock = new ArrayList<>();
        partsInStock = new ArrayList<>();
        electricCar1 = new ElectricCar("Hyundai", "Orange", 2999.99);
        petrolCar = new PetrolCar("BMW", "Blue", 40000.00);
        rotaryEngine = new Engine("EN123", 349.99);
        tyre = new Tyre("EN123", 49.99);
        customer = new Customer("Victoria", 100000.00);
    }

    @Test
    public void dealership_starts_with_no_cars(){
        assertEquals(0, arnoldClan.getNumberOfCarsInStock());
    }

    @Test
    public void can_add_car_to_stock(){
        System.out.println(arnoldClan.getNumberOfCarsInStock());
        arnoldClan.addCarToStock(electricCar1);
        System.out.println(arnoldClan.getNumberOfCarsInStock());
        assertEquals(1, arnoldClan.getNumberOfCarsInStock());
        carsInStock = arnoldClan.getCars();
        assertTrue(carsInStock.contains(electricCar1));
    }

    @Test
    public void dealership_starts_with_no_parts(){
        assertEquals(0, arnoldClan.getNumberOfSparePartsInStock());
    }

    @Test
    public void can_add_spare_part_to_stock(){
        arnoldClan.addSparePartToStock(rotaryEngine);
        assertEquals(1, arnoldClan.getNumberOfSparePartsInStock());
    }

    @Test
    public void dealership_starts_with_no_money(){
        assertEquals(0.0, arnoldClan.getTill(), 0.0);
    }

    @Test
    public void can_add_money_to_till(){
        arnoldClan.addMoneyToTill(50.00);
        assertEquals(50.00, arnoldClan.getTill(), 0.0);
    }

    @Test
    public void can_decrease_money_in_till() {
        arnoldClan.addMoneyToTill(50.00);
        arnoldClan.decreaseMoneyInTill(20.00);
        assertEquals(30.00, arnoldClan.getTill(), 0.0);
    }

    @Test
    public void can_buy_item_Increases_Stock_Car(){
        arnoldClan.addMoneyToTill(5000.00);
        arnoldClan.buySomething(electricCar1);
        assertEquals(1, arnoldClan.getNumberOfCarsInStock());
        carsInStock = arnoldClan.getCars();
        assertTrue(carsInStock.contains(electricCar1));
    }

    @Test
    public void can_buy_item_increases_stock_Part(){
        arnoldClan.addMoneyToTill(5000.00);
        arnoldClan.buySomething(rotaryEngine);
        assertEquals(1, arnoldClan.getNumberOfSparePartsInStock());
        partsInStock = arnoldClan.getParts();
        assertTrue(partsInStock.contains(rotaryEngine));
    }

    @Test
    public void can_buy_item_Decreases_Till(){
        arnoldClan.addMoneyToTill(500.00);
        arnoldClan.buySomething(rotaryEngine);
        assertEquals(150.01, arnoldClan.getTill(), 0.0);
    }

    @Test
    public void can_remove_item_from_stock_Car(){
        arnoldClan.addCarToStock(electricCar1);
        arnoldClan.addCarToStock(petrolCar);
        arnoldClan.removeCarFromStock(electricCar1);
        assertEquals(1, arnoldClan.getNumberOfCarsInStock());
    }

    @Test
    public void can_remove_item_from_stock_Part(){
        arnoldClan.addSparePartToStock(rotaryEngine);
        arnoldClan.addSparePartToStock(tyre);
        arnoldClan.removePartFromStock(rotaryEngine);
        assertEquals(1, arnoldClan.getNumberOfSparePartsInStock());
    }

    @Test
    public void can_take_in_a_customer(){
        arnoldClan.takeInCustomer(customer);
        assertEquals(1, arnoldClan.getNoOfCustomers());
    }

    @Test
    public void can_sell_Car_to_customer_Decreases_Stock(){
        arnoldClan.addCarToStock(electricCar1);
        arnoldClan.addCarToStock(petrolCar);
        arnoldClan.takeInCustomer(customer);
        arnoldClan.sellSomething(customer, electricCar1);
        assertEquals(1, arnoldClan.getNumberOfCarsInStock());
    }

    @Test
    public void can_sell_Car_to_customer_Increases_Till(){
        arnoldClan.addCarToStock(electricCar1);
        arnoldClan.addCarToStock(petrolCar);
        arnoldClan.takeInCustomer(customer);
        arnoldClan.sellSomething(customer, electricCar1);
        assertEquals(2999.99, arnoldClan.getTill(), 0.0);
    }

    @Test
    public void can_sell_Part_to_customer_Decreases_Stock(){
        arnoldClan.addSparePartToStock(rotaryEngine);
        arnoldClan.addSparePartToStock(tyre);
        arnoldClan.takeInCustomer(customer);
        arnoldClan.sellSomething(customer, rotaryEngine);
        assertEquals(1, arnoldClan.getNumberOfSparePartsInStock());
    }

    @Test
    public void can_sell_Part_to_customer_Increases_Till(){
        arnoldClan.addSparePartToStock(rotaryEngine);
        arnoldClan.addSparePartToStock(tyre);
        arnoldClan.takeInCustomer(customer);
        arnoldClan.sellSomething(customer, rotaryEngine);
        arnoldClan.sellSomething(customer, tyre);
        assertEquals(399.98, arnoldClan.getTill(), 0.0);
    }

}
