package car;

public interface CarDao {

    CarModel[] getAllCars();

    CarModel getCar(String regNumber);

    CarModel[] getAllElectricCars();
}
