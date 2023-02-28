import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    private Customer customer;
    private ElectricCar electricCar;
    private PetrolCar petrolCar;
    private ArrayList<VehicleType> cars;
    private Part engine;


    @Before
    public void before(){
        customer = new Customer("Victoria", 100000.00);
        electricCar = new ElectricCar("Hyundai", "Orange", 2999.99);
        petrolCar = new PetrolCar("BMW", "Blue", 40000.00);
        cars = new ArrayList<>();
        engine = new Engine("EN123", 349.99);

    }

    @Test
    public void can_increase_number_of_things_bought_Car(){
        customer.addItem(petrolCar);
        assertEquals(1, customer.getNumberOfItemsBought());
    }

    @Test
    public void can_increase_number_of_things_bought_Part(){
        customer.addItem(engine);
    }

    @Test
    public void can_remove_money_from_customer_wallet(){
        customer.buySomething(petrolCar);
        assertEquals(60000, customer.getCash(), 0.0);
    }
}
