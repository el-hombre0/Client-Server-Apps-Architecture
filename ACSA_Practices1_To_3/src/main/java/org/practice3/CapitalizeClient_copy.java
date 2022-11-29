package main.java.org.practice3;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CapitalizeClient_copy {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Give the IP-address as the only command prompt's argument");
            return;
        }
        try (var socket = new Socket(args[0], 59898)) {
            System.out.println("Enter messages, then Ctrl + D or Ctrl + C to exit");
            var scanner = new Scanner(System.in);
            var in = new Scanner(socket.getInputStream());
            var out = new PrintWriter(socket.getOutputStream(), true);
            while (true) {
                if (scanner.hasNextLine())
                    out.println(scanner.nextLine());
                if (in.hasNextLine())
                    System.out.println(in.nextLine());
            }
        }
    }
}
