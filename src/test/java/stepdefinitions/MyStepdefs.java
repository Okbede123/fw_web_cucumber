package stepdefinitions;

import cucumber.api.java.vi.Cho;
import cucumber.api.java.vi.Và;

public class MyStepdefs {
    @Cho("^testso$")
    public void testso() {
        System.out.println("1");
    }

    @Và("^testhai$")
    public void testhai() {
        System.out.println("2");
    }
}
