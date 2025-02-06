import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // InetSocketAddress nos permite encapsular dirección y puerto en un único punto
            // En caso de que nos sea útil, pero necesitamos una línea más
            // que utilizando directamente el constructor del socket
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

            Socket socket = new Socket();
            socket.connect(dir);
            System.out.println("Conectado al servidor");

            OutputStream salida = socket.getOutputStream();

            PrintWriter escritor = new PrintWriter(salida,true);

            System.out.println("Introduce el primer operando");
            float Op1 = sc.nextFloat();
            sc.nextLine(); // Consumir la nueva línea
            escritor.println(Op1);

            System.out.println("Introduce el segundo operando");
            float Op2 = sc.nextFloat();
            sc.nextLine(); // Consumir la nueva línea
            escritor.println(Op2);

            System.out.println("Introduce el simbolo de la operacion");
            String Operacion = sc.nextLine();
            escritor.println(Operacion);


            // Obtenemos el flujo
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String mensaje = lector.readLine();
            System.out.println("Servidor dice: " + mensaje);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
