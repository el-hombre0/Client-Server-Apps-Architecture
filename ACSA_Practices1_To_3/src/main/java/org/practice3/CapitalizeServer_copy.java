package main.java.org.practice3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Executors;
/**Необходимо создать клиент-серверное приложение на языке JAVA с использованием socket, для широковещательного общения
 * пользователей. Приложение может быть как консольным, так и оснащённым полноценным GUI. Клиентское приложение
 * считывает данные из стандартного ввода и отсылает сообщение серверу (с помощью TCP/IP). Сервер, в свою очередь,
 * накапливает сообщения и раз в 5 секунд осуществляет массовую рассылку всем клиентам. Если сообщений за указанный
 * период не поступило, то рассылка не производится. Клиент, получивший сообщение, отображает на экране текст данного
 * сообщения. Структуру и поведение данного клиент-серверного приложения, в том числе, например, в части регистрации
 * конкретного клиента и формата широковещательного сообщения, студент определяет самостоятельно.
*/

public class CapitalizeServer_copy {
    public static void main(String[] args) throws Exception {
        try (var listener = new ServerSocket(59898)) {
            System.out.println("Server is started...");
            var pool = Executors.newFixedThreadPool(20);
            while (true) {
                pool.execute(new Capitalizer(listener.accept()));
            }
        }
    }

    private static class Capitalizer implements Runnable {
        private Socket socket;
        Capitalizer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Connecting: " + socket);
            try {
                var in = new Scanner(socket.getInputStream());
                var out = new PrintWriter(socket.getOutputStream(), true);
                var time = new Date();


                while (true) {
                    if (new Date().getTime() - time.getTime() > 5000) {
                        time = new Date();
                        if (in.hasNextLine())
                            out.println(in.nextLine());
                    }
                }
            }
            catch (Exception e) {
                System.out.println("Error:" + socket);
            }
            finally {
                try {
                    socket.close();
                }
                catch (IOException e) {
                }
                System.out.println("Closed: " + socket);
            }
        }
    }
}

//                while(in.hasNextLine()){
//                    long stamp1 = System.currentTimeMillis();
//                    String message = in.nextLine();
//                    messages.addFirst(message);
//                    long stamp2 = System.currentTimeMillis();
//                    if (stamp2 - stamp1 >= 5000) {
//                        try {
//                            for(String t : messages) {
//                                out.println(t);
//                                messages.pop();
//                            }
//                        }
//                        catch (NoSuchElementException e) {
//                            out.println("No messages.");
//                        }
//                    }
//                }

//            catch (Exception e) {
//                System.out.println("Error:" + socket);
//            }
//            finally {
//                try {
//                    socket.close();
//                }
//                catch (IOException e) {
//                }
//                System.out.println("Closed: " + socket);
//            }
//        }
//    }
//}
