import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejoClientesThread implements Runnable {

    private Socket socket;
    private int primerOperando;
    private int segundoOperando;
    private String operacion;

    public ManejoClientesThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
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
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
