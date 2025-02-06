import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            // InetSocketAddress nos permite encapsular dirección y puerto en un único punto
            // En caso de que nos sea útil, pero necesitamos una línea más
            // que utilizando directamente el constructor del socket
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);
            ServerSocket servidor = new ServerSocket();
            servidor.bind(dir);

            System.out.println("Esperando conexiones...");
            Socket socket = servidor.accept();
            System.out.println("Cliente conectado");

            ObjectInputStream lector = new ObjectInputStream(socket.getInputStream());

            // Aquí no se avanzará hasta que NO haya una recepción de mensaje
            String[] mensaje = (String[]) lector.readObject();

            for (int i = 0; i < 3; i++) {

                System.out.println(mensaje[i]);

            }

            OutputStream salida = socket.getOutputStream();
            // AutoFlush = true para que envíe los datos inmediatamente
            // para vaciar el buffer de PrintWriter
            PrintWriter escritor = new PrintWriter(salida, true);
            // Enviamos el mensaje al cliente


            socket.close();
            servidor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}