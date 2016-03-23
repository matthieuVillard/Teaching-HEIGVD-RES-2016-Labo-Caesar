package ch.heigvd.res.examples;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by matthieu.villard on 23.03.2016.
 */
public class Client
{
    final static Logger LOG = Logger.getLogger(Client.class.getName());

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private int key;

    public Client(int port) {
        try {
            socket = new Socket(InetAddress.getLocalHost(), port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            getKey();
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void contactServer(String str){
        try {
            LOG.info("Client is sending \"" + str + "\" to server");
            Cesar cesar = new Cesar(key);
            LOG.info(cesar.encrypt(str));
            out.println(cesar.encrypt(str));
            out.flush();

            String response = cesar.decrypt(in.readLine());
            LOG.info("Client received response \"" + response + "\" from server");

            LOG.info("Client is cleaning up resources...");
            socket.close();
            out.close();
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getKey() throws IOException {
        LOG.info("Client is asking for a key");
        out.println("key");
        out.flush();

        String response = in.readLine();
        key = Integer.valueOf(response);
        LOG.info("Client received key \"" + key + "\" to server");
    }
}
