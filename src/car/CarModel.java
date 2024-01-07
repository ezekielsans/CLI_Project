package car;

import java.util.Objects;

public class CarModel {

    private String regNumber;
    private double rentalPricePerDay;
    private Brands brands;
    private Boolean isElectric;


    public CarModel(String regNumber, double rentalPricePerDay, Brands brands, Boolean isElectric) {
        this.regNumber = regNumber;
        this.rentalPricePerDay = rentalPricePerDay;
        this.brands = brands;
        this.isElectric = isElectric;
    }


    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public Brands getBrands() {
        return brands;
    }

    public void setBrands(Brands brands) {
        this.brands = brands;
    }

    public Boolean getElectric() {
        return isElectric;
    }

    public void setElectric(Boolean electric) {
        isElectric = electric;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "regNumber='" + regNumber + '\'' +
                ", rentalPricePerDay=" + rentalPricePerDay +
                ", brands=" + brands +
                ", isElectric=" + isElectric +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return Double.compare(carModel.rentalPricePerDay, rentalPricePerDay) == 0 && Objects.equals(regNumber, carModel.regNumber) && brands == carModel.brands && Objects.equals(isElectric, carModel.isElectric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNumber, rentalPricePerDay, brands, isElectric);
    }
}
