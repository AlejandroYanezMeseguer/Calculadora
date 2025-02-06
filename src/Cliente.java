import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ArrayOperacion = new String[3];
        try {
            InetSocketAddress dir = new InetSocketAddress("localhost", 6666);

            Socket socket = new Socket();
            socket.connect(dir);
            System.out.println("Conectado al servidor");

            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());

            String Op1;
            while (true) {
                System.out.println("Introduce el primer operando:");
                Op1 = sc.nextLine();
                if (Op1.matches("\\d+")) {
                    break;
                } else {
                    System.out.println("Entrada inválida. Por favor, introduce un número.");
                }
            }
            ArrayOperacion[0] = Op1;

            String Op2;
            while (true) {
                System.out.println("Introduce el segundo operando:");
                Op2 = sc.nextLine();
                if (Op2.matches("\\d+")) {
                    break;
                } else {
                    System.out.println("Entrada inválida. Por favor, introduce un número.");
                }
            }
            ArrayOperacion[1] = Op2;

            String Operacion;
            while (true) {
                System.out.println("Introduce el símbolo de la operación (+, -, *, /, ^):");
                Operacion = sc.nextLine();
                if (Operacion.matches("[+\\-*/^]")) {
                    break;
                } else {
                    System.out.println("Entrada inválida. Por favor, introduce un símbolo de operación válido.");
                }
            }
            ArrayOperacion[2] = Operacion;

            salida.writeObject(ArrayOperacion);


//            // Obtenemos el flujo
            BufferedReader lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String mensaje = lector.readLine();
            System.out.println(mensaje);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
