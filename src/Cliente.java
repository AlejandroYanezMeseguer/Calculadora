import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    public static void main(String[] args) {
        int puerto_servidor = 6666;
        byte[] bufferEnvio = new byte[2048];
        byte[] bufferRecepcion = new byte[2048];
        Scanner sc = new Scanner(System.in);
        try {
            InetAddress direccionServidor = InetAddress.getByName("localhost");

            DatagramSocket datagramSocket = new DatagramSocket();
            String msj;
            System.out.println("Introduce el mensaje para el servidor");
            msj = sc.nextLine();
            bufferEnvio = msj.getBytes();
            DatagramPacket pregunta = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccionServidor, puerto_servidor);
            datagramSocket.send(pregunta);

            DatagramPacket respuesta = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);
            datagramSocket.receive(respuesta);
            System.out.println("Recibo respuesta del servidor");

            String msjServidor = new String(respuesta.getData(), 0, respuesta.getLength());
            System.out.println("msjServidor = " + msjServidor);
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}