package commons;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataHelper {

    private Locale locale = new Locale("en");
    private Faker faker = new Faker(locale);

    public static DataHelper getData(){
        return  new DataHelper();
    }

    public String getEmail(){
        return faker.internet().emailAddress();
    }

    public String getPhoneNumber(){
        return faker.phoneNumber().subscriberNumber(10);
    }
}
