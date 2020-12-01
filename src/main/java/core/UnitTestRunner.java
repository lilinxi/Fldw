package core;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class UnitTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(UnitTest.class);
        for (Failure failure : result.getFailures()) {
            System.err.println(failure.getDescription());
        }
        System.err.println(result.wasSuccessful());
    }
}
