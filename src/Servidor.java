import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        while (true) {
            int primerOperando = 0;
            int segundoOperando = 0;
            String operacion = "";
            try {
                InetSocketAddress dir = new InetSocketAddress("localhost", 6666);
                ServerSocket servidor = new ServerSocket();
                servidor.bind(dir);

                System.out.println("Esperando conexiones...");
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado");

                ObjectInputStream lector = new ObjectInputStream(socket.getInputStream());
                String[] mensaje = (String[]) lector.readObject();

                for (int i = 0; i < 3; i++) {
                    switch (i) {
                        case 0:
                            primerOperando = Integer.parseInt(mensaje[i]);
                            break;
                        case 1:
                            segundoOperando = Integer.parseInt(mensaje[i]);
                            break;
                        case 2:
                            operacion = String.valueOf(mensaje[i]);
                            break;
                    }
                }

                OutputStream salida = socket.getOutputStream();
                PrintWriter escritor = new PrintWriter(salida, true);

                if (segundoOperando == 0 && operacion.equals("/")) {
                    escritor.println("Error: División por cero. Inténtalo de nuevo.");
                } else {
                    switch (operacion) {
                        case "+":
                            escritor.println("El resultado es " + Operaciones.Suma(primerOperando, segundoOperando));
                            break;
                        case "-":
                            escritor.println("El resultado es " + Operaciones.Resta(primerOperando, segundoOperando));
                            break;
                        case "*":
                            escritor.println("El resultado es " + Operaciones.Multiplicacion(primerOperando, segundoOperando));
                            break;
                        case "/":
                            escritor.println("El resultado es " + Operaciones.Division(primerOperando, segundoOperando));
                            break;
                        case "^":
                            escritor.println("El resultado es " + Operaciones.Potencia(primerOperando, segundoOperando));
                            break;
                    }
                }

                socket.close();
                servidor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}