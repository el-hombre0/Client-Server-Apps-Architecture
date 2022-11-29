package main.java.org.practice1;
//Используя материалы данной практической работы необходимо
//        написать многопоточную программу, в которой два потока записывают
//        строку в стандартный вывод, по образцу PING PONG PING PONG PING
//        PONG. Программа должна работать следующим образом:
//        ∙ 1-й поток печатает «Ping» и переходит в состояние ожидания. ∙ 2-й поток выходит из состояния ожидания,
//        печатает «Pоng», уведомляет 1-й поток, возвращается в состояние ожидания. ∙ 1-й поток выходит из состояния
//        ожидания, печатает «Pшng», уведомляет 2-й поток, возвращается в состояние ожидания. ∙ Шаги 2 и 3 повторяются
//        и печатают «Ping Pong». ∙ Программа должна быть реализована только с использованием Wait
//        Notify, либо ReentrantLock

class CommonResource implements Runnable {
    private String word;
    private Object connector;
    private Boolean isActive;

    void disable(){
        isActive = false;
    }
    public CommonResource(String word, Object connector) {
        this.isActive = true;
        this.word = word;
        this.connector = connector;
    }

    @Override
    public void run() {
        synchronized (connector) {
            while(isActive) {
                System.out.println(word);
                try {Thread.sleep(1000);}
                catch (InterruptedException e) {}

                connector.notify();

                try {connector.wait();}
                catch (InterruptedException e) {}
            }
        }
    }
}
public class Main {
    public static void main(String[] args){
        Object connector = new Object();
        Thread t1 = new Thread(new CommonResource("PING", connector));
        Thread t2 = new Thread(new CommonResource("PONG", connector));

        t1.start();
        t2.start();
    }
}

