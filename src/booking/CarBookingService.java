package booking;

import car.CarDao;
import car.CarModel;
import car.CarService;
import user.UserDao;
import user.UserModel;
import user.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarBookingService implements CarBookingDao, CarDao, UserDao {


    private final CarService carService = new CarService();
    private final UserService userService = new UserService();
    private static CarBooking[] carBookings;

    static {
        carBookings = new CarBooking[10];
    }

    @Override
    public UUID bookCar(UserModel user, String regNumber) {

        CarModel[] availableCars = getAllCars();
        if (availableCars.length == 0) {
            throw new IllegalStateException("No cars available right now");
        }
        for (CarModel availableCar : availableCars) {
            //validate if the car is still available
            if (availableCar.getRegNumber().equals(regNumber)) {

                CarModel car = carService.getCar(regNumber);
                UUID bookingId = UUID.randomUUID();
                book(new CarBooking(bookingId, user, car, LocalDateTime.now()));
                return bookingId;

                //TODO after creating new booking insert into carBookings array
            }
        }

        throw new IllegalStateException("Already booked. car with regNumber " + regNumber);
    }

    //from car service
    @Override
    public CarModel[] getAllCars() {
        return carService.getAllCars();
    }

    @Override
    public CarModel getCar(String regNumber) {
        return carService.getCar(regNumber);
    }

    @Override
    public CarModel[] getCar(CarModel[] cars) {
        if (cars.length == 0) {
            return new CarModel[0];
        }
        CarBooking[] carBookings = getCarBookings();
        if (carBookings.length == 0) {
            return cars;
        }

        //tagging if the car is already booked

        int availableCarsCount = 0;
        for (CarModel car : cars) {
            boolean booked = false;
            for (CarBooking carBooking : carBookings) {
                if (carBooking == null || !carBooking.getCar().equals(car)) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                ++availableCarsCount;
            }
        }


        CarModel[] availableCars = new CarModel[availableCarsCount];
        int index = 0;

        for (CarModel car : cars) {
            boolean booked = false;


            for (CarBooking carBooking : carBookings) {


                if (carBooking == null || !carBooking.getCar().equals(car)) {
                    continue;
                }
                booked = true;
            }
            if (!booked) {
                availableCars[index++] = car;
            }
        }
        return availableCars;
    }


    @Override
    public CarModel[] getAllElectricCars() {
        return getCar(carService.getAllElectricCars());
    }

    @Override
    public UserModel getUserById(UUID id) {
        return userService.getUserById(id);
    }


    public CarModel[] getUserBookedCars(UUID userId) {
        CarBooking[] carBookings = getCarBookings();
        int userBookings = 0;
        for (CarBooking carBooking : carBookings) {

            if (carBooking != null && carBooking.getUser().getId().equals(userId)) {
                ++userBookings;
            }
        }
        if (userBookings == 0) {
            return new CarModel[0];
        }

        CarBooking[] bookings = new CarBooking[userBookings];
        int index = 0;
        for (CarBooking carBooking : carBookings) {
            if (carBooking != null) {

                bookings[index++] = carBooking;
            }
        }
        return new CarModel[0];
    }

    @Override
    public CarBooking[] getBookings() {
        if (carBookings != null) {
            for (int i = 0; i < carBookings.length; i++) {
                System.out.println(carBookings[i].toString());
            }
        } else {
            System.out.println("No Bookings as of now");
        }
        return carBookings;
    }

    @Override
    public void book(CarBooking carBooking) {

    }

    @Override
    public void cancelCarBooking(UUID id) {

    }

    @Override
    public CarBooking[] getCarBookings() {
        return new CarBooking[0];
    }

    @Override
    public UserModel[] getUsers() {
        return userService.getUsers();
    }


}
