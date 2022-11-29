package main.java.org.practice2;

import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Server {
    public static void main(String[] args) throws RemoteException, NamingException, InterruptedException, MalformedURLException, AlreadyBoundException {
        IMath iMath = new IMathImp();
        Naming.bind("imath", iMath);
//        Context context = new InitialContext();
//        context.bind("rmi:imath", iMath);
        //context.bind("rmi://localhost:1099/imath", iMath);
    }
}

interface IMath extends Remote {
    ArrayList<Double> quadratic_equation(Double a, Double b, Double c) throws RemoteException;
}

class IMathImp extends UnicastRemoteObject implements IMath {

    public IMathImp() throws RemoteException {

    }

    @Override
    public ArrayList<Double> quadratic_equation(Double a, Double b, Double c) throws RemoteException {
        ArrayList<Double> answer = new ArrayList<Double>();

        Double disc = pow(b, 2) - 4 * a * c;
        if (disc < 0) {
            answer.add(null);
        }
        else{
            Double x1 = (-b + Math.sqrt(disc)) / (2 * a);

            Double x2 = (-b - Math.sqrt(disc)) / (2 * a);

            answer.add(x1);
            answer.add(x2);
        }

        return answer;
    }
}