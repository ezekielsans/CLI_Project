package controller;

import booking.CarBooking;
import booking.CarBookingService;
import car.CarModel;
import car.CarService;
import user.UserModel;
import user.UserService;

import java.util.Scanner;
import java.util.UUID;

public class AppController {

    //call all services

    UserService userService = new UserService();
    CarBookingService carBookingService = new CarBookingService();
    Scanner scan = new Scanner(System.in);

    public void init() {
        while (true) {

            System.out.println("\n1️⃣ - Book Car" + "\n2️⃣ - View All User Booked Cars" + "\n3️⃣ - View All Bookings" + "\n4️⃣ - View Available Cars" + "\n5️⃣ - View Available Electric Cars" + "\n6️⃣ - View all users" + "\n7\uFE0F⃣ - Exit\n" + "\n");


            int input = scan.nextInt();
            if (input == 1) {
                bookCar(userService, carBookingService, scan);
            } else if (input == 2) {
                viewAllUserBookedCars(userService, carBookingService, scan);
            } else if (input == 3) {
                viewAllBookings(carBookingService);
            } else if (input == 4) {
                viewAllAvailableCars(carBookingService, false);

            } else if (input == 5) {
                viewAllAvailableCars(carBookingService, true);
            } else if (input == 6) {
                viewAllUsers(userService);
            } else if (input == 0) {
                System.out.println(" not a valid option ❌");

            } else {
                System.out.println("Thank you!");
                break;
            }


        }
    }

    public void bookCar(UserService userService, CarBookingService carBookingService, Scanner scanner) {
        viewAllAvailableCars(carBookingService, false);
        System.out.println("➡️ select car reg number");
        String regNumber = scanner.nextLine();
        viewAllUsers(userService);
        System.out.println("➡️ select user id");
        String userId = scanner.nextLine();

        try {
            UserModel user = userService.getUserById(UUID.fromString(userId));
            if (user == null) {
                System.out.println("❌ No user found with id " + userId);
            } else {

                UUID bookingId = carBookingService.bookCar(user, regNumber);
                String confirmationMessage = """
                        🎉 Successfully booked car with reg number %s for user %s
                        Booking ref: %s
                        """.formatted(regNumber, user, bookingId);
                System.out.println(confirmationMessage);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }








/*
        carBookingService = new CarBookingService();
        userService = new UserService();
        for (int i = 0; i < carBookingService.getAllCars().length; i++) {
            System.out.println(carBookingService.getAllCars()[i].toString());
        }
        System.out.println("\nPlease Select a Car Reg number: ");
        String regNumInput = scanner.nextLine();
        for (int i = 0; i < userService.getUsers().length; i++) {
            System.out.println(userService.getUsers()[i].toString());
        }
        System.out.println("Select User Id: ");
        String userIdInput = scanner.next();

        //validation
        if (regNumInput != null && userIdInput != null) {
            carBookingService.bookCar(userService.getUserById(UUID.fromString(userIdInput)), userIdInput);
            System.out.println("\nSuccessfully booked car with reg number " + regNumInput + " for user " + userIdInput);
        } else {
            System.out.println("Id selected does not exist");
        }

*/
    }

    public void viewAllUserBookedCars(UserService userService, CarBookingService carBookingService, Scanner scanner) {
        viewAllUsers(userService);
        System.out.println("➡️ select user id");
        String userId = scanner.nextLine();
        UserModel user = userService.getUserById(UUID.fromString(userId));
        if (user == null) {
            System.out.println("❌ No user found with id " + userId);
            return;
        }
        //checking if user has booked cars
        CarModel[] userBookedCars = carBookingService.getUserBookedCars(user.getId());
        if (userBookedCars.length == 0) {
            System.out.printf("❌ user %s has no cars booked", user);
            return;
        }
        for (CarModel userBookedCar : userBookedCars) {
            System.out.println(userBookedCar);
        }

    }

    public void viewAllBookings(CarBookingService carBookingService) {
        CarBooking[] bookings = carBookingService.getBookings();
        if (bookings.length == 0) {

            System.out.println("No bookings available \uD83D\uDE15");
            return;
        }
        for (CarBooking booking : bookings) {
            System.out.println("booking = " + booking);
        }

    }

    public void viewAllAvailableCars(CarBookingService carBookingService, boolean isElectric) {
        CarModel[] availableCars = isElectric ? carBookingService.getAllElectricCars() : carBookingService.getAllCars();

        if (availableCars.length == 0) {

            System.out.println("❌ No cars available for renting");
            return;
        }
        for (CarModel availableCar : availableCars) {
            System.out.println(availableCar);
        }
    }

    public void viewAllElectricCars() {
        carBookingService = new CarBookingService();
        for (int i = 0; i < carBookingService.getAllElectricCars().length; i++) {
            System.out.println(carBookingService.getAllElectricCars()[i].toString());
        }
    }

    public void viewAllUsers(UserService userService) {
        UserModel[] users = userService.getUsers();
        if (users.length == 0) {
            System.out.println("❌ No users in the system");
            return;
        }
        for (UserModel user : users) {
            System.out.println(user);
        }


    }


}
