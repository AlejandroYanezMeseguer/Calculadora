import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ArrayOperacion = new String[3];
        try {
            // InetSocketAddress nos permite encapsular dirección y puerto en un único punto
            // En caso de que nos sea útil, pero necesitamos una línea más
            // que utilizando directamente el constructor del socket
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

            Socket socket = new Socket();
            socket.connect(dir);
            System.out.println("Conectado al servidor");

            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Introduce el primer operando");
            String Op1 = sc.nextLine();
          //  sc.nextLine(); // Consumir la nueva línea
            ArrayOperacion[0] = Op1;

            System.out.println("Introduce el segundo operando");
            String Op2 = sc.nextLine();
           // sc.nextLine(); // Consumir la nueva línea
            ArrayOperacion[1] = Op2;

            System.out.println("Introduce el simbolo de la operacion");
            String Operacion = sc.nextLine();
            ArrayOperacion[2] = Operacion;
            salida.writeObject(ArrayOperacion);

            for (int i = 0; i < 3; i++) {

                System.out.println(ArrayOperacion[i]);

            }


//            // Obtenemos el flujo
//            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            String mensaje = lector.readLine();
//            System.out.println("Servidor dice: " + mensaje);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
