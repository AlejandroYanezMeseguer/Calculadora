import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);
            ServerSocket servidor = new ServerSocket();
            servidor.bind(dir);

            System.out.println("Esperando conexiones...");

            while (true) {
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado");

                ManejoClientesThread ManejoClientesThread = new ManejoClientesThread(socket);
                Thread hilo = new Thread(ManejoClientesThread);
                hilo.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}