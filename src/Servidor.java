import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 6666;
        byte[] buffer = new byte[2048];
        try {
            System.out.println("Servidor arrancando");
            DatagramSocket datagramSocket = new DatagramSocket(puerto);

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(peticion);
            System.out.println("Recibida peticion");

            String msj = new String(peticion.getData(), 0, peticion.getLength());
            System.out.println("msj = " + msj);

            String[] palabras = msj.split(" ");
            String palabraMasLarga = "";
            for (String palabra : palabras) {
                if (palabra.length() > palabraMasLarga.length()) {
                    palabraMasLarga = palabra;
                }
            }

            String respuesta = "Palabra más larga: " + palabraMasLarga + ", Longitud: " + palabraMasLarga.length();
            buffer = respuesta.getBytes();
            InetAddress direccion = peticion.getAddress();
            int puertoCliente = peticion.getPort();
            DatagramPacket respuestaPacket = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
            datagramSocket.send(respuestaPacket);
            System.out.println("Envié respuesta al cliente");
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}