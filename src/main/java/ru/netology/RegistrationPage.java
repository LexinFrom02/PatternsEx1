package ru.netology;

import com.github.javafaker.Faker;

import java.util.Locale;


public class RegistrationPage {
    public RegistrationPage() {
    }

    public static class Registration {
        public Registration() {
        }

        public static RegistrationValues generateByFaker() {
            Faker faker = new Faker(new Locale("ru", "Russia"));
            return new RegistrationValues (
                    faker.address().cityName(),
                    faker.name().name(),
                    faker.phoneNumber().phoneNumber());
        }
    }
}

class RegistrationValues {
    private final String city;
    private final String name;
    private final String phoneNumber;

    public RegistrationValues(String city, String name, String phoneNumber) {
        this.city = city;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
