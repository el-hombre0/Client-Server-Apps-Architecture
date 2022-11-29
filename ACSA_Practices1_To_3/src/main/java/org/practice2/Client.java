package main.java.org.practice2;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Client {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();

        Enumeration<NameClassPair> e = context.list("rmi://localhost/");
        while (e.hasMoreElements())
            System.out.println(e.nextElement().getName());

        String url = "rmi://localhost/imath";
        IMath iMath = (IMath)context.lookup(url);
        ArrayList<Double> result = iMath.quadratic_equation(2.0, 7.6, 6.0);
        if(result.get(0) == null)
            System.out.println("Discriminant is negative! I can't work with complex numbers(((");
        else if (result.get(0).equals(result.get(1)))
            System.out.println("x = " + result.get(0).toString());
        else {
            System.out.println("x1 = " + result.get(0).toString());
            System.out.println("x2 = " + result.get(1).toString());
        }
    }
}