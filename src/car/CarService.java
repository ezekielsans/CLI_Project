package car;

public class CarService implements CarDao {


    private static final CarModel[] cars = {
            new CarModel("1223", 30.55, Brands.AUDI, false),
            new CarModel("3232", 1500.55, Brands.BUGATTI, false),
            new CarModel("6969", 320.55, Brands.TOYOTA, true),
            new CarModel("1992", 300.55, Brands.TESLA, true),
            new CarModel("1998", 800.45, Brands.MERCEDES, false),
            new CarModel("7777", 565.45, Brands.PORSCHE, false)};


    @Override
    public CarModel[] getAllCars() {
        return cars;
    }

    @Override
    public CarModel getCar(String regNumber) {
        for (CarModel car : getAllCars()) {
            if (regNumber.equals(car.getRegNumber())) {
                return car;
            }
        }
        throw new IllegalStateException(String.format("Invalid regNumber %s", regNumber));
    }

    @Override
    public CarModel[] getAllElectricCars() {

        //to get the index of the electric car
        int electricCarCount = 0;
        CarModel[] cars = getAllCars();


        if (cars.length == 0) {
            return new CarModel[0];
        }
        //loop the cars to indicate which is the electric car

        for (CarModel car : cars) {
            if (car.getElectric()) {
                electricCarCount++;
            }
        }
        if (electricCarCount == 0) {
            return new CarModel[0];
        }
        //creating a new array for storing detected electric cars
        CarModel[] electricCars = new CarModel[electricCarCount];
        int index = 0;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getElectric()) {

                electricCars[index++] = cars[i];
            }
        }
        return electricCars;
    }
}

