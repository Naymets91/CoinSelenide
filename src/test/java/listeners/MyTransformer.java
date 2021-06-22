package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

/**
 * Created by mycola on 21.07.2017.
 */
public class MyTransformer implements IAnnotationTransformer {

    // Do not worry about calling this method as testNG calls it behind the scenes before EVERY method (or test).
    // It will disable single tests, not the entire suite like SkipException

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod){

        String enabled = null;
        if (annotation.getGroups()[0].equals("HomePage")) {
            enabled = System.getProperty("testHomePage");
            System.out.println("testHomePage = " + enabled);
        }
        if (annotation.getGroups()[0].equals("Step1")) {
            enabled = System.getProperty("testStep1");
            System.out.println("testStep1 = " + enabled);
        }
        if (annotation.getGroups()[0].equals("Step2")) {
            enabled = System.getProperty("testStep2");
            System.out.println("testStep2 = " + enabled);
        }
        if (annotation.getGroups()[0].equals("Step3")) {
            enabled = System.getProperty("testStep3");
            System.out.println("testStep3 = " + enabled);
        }
        if (annotation.getGroups()[0].equals("Step4")) {
            enabled = System.getProperty("testStep4");
            System.out.println("testStep4 = " + enabled);
        }
        if (annotation.getGroups()[0].equals("SignIn")) {
            enabled = System.getProperty("testLogin");
            System.out.println("testLogin = " + enabled);
        }
        if (annotation.getGroups()[0].equals("Logout")) {
            enabled = System.getProperty("testLogout");
            System.out.println("testLogout = " + enabled);
        }
        if (annotation.getGroups()[0].equals("EnterpriseUser")) {
            enabled = System.getProperty("testEnterpriseUser");
            System.out.println("testEnterpriseUser = " + enabled);
        }
        if (annotation.getGroups()[0].equals("EnterpriseAdmin")) {
            enabled = System.getProperty("testEnterpriseAdmin");
            System.out.println("testEnterpriseAdmin = " + enabled);
        }
        if (annotation.getGroups()[0].equals("CDAdmin")) {
            enabled = System.getProperty("testCDAdmin");
            System.out.println("testCDAdmin = " + enabled);
        }
        if (annotation.getGroups()[0].equals("ClientUser")) {
            enabled = System.getProperty("testClientUser");
            System.out.println("testClientUser = " + enabled);
        }
        if (enabled!=null) {
            annotation.setEnabled(enabled.equals("true"));
        }
    }
}
