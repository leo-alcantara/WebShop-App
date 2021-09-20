package the.bug.web_shop_system.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Address {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String addressId;
    private String streetNameNumber;
    private String zipCode;
    private String city;
    private String country;

    public Address() {
    }

    public Address(String addressId, String streetNameNumber, String zipCode, String city, String country) {
        this.addressId = addressId;
        this.streetNameNumber = streetNameNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.country = country;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStreetNameNumber() {
        return streetNameNumber;
    }

    public void setStreetNameNumber(String streetNameNumber) {
        this.streetNameNumber = streetNameNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(streetNameNumber, address.streetNameNumber) && Objects.equals(zipCode, address.zipCode) && Objects.equals(city, address.city) && Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetNameNumber, zipCode, city, country);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", streetNameNumber='" + streetNameNumber + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
