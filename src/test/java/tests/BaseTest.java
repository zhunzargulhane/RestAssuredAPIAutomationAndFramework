package tests;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {
    @BeforeMethod
    public void beforeMethod(Method m){
        System.out.println("STARTING TESTS "+m.getName());
        System.out.println("THREAD ID IS  "+Thread.currentThread().getId());
    }
}
