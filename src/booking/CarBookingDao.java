package booking;


import car.CarModel;
import car.CarService;
import user.UserModel;

import java.util.UUID;

public interface CarBookingDao {


    UUID bookCar(UserModel user, String regNumber);

    CarBooking[] getCarBookings();

    CarModel[] getCar(CarModel[] cars);

    CarBooking[] getBookings();

    void book(CarBooking carBooking);

    void cancelCarBooking(UUID id);
}
