package ir.siavash.helloworld.gateway;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPPublisher implements Publisher{
    private static Socket socket = null;

    public TCPPublisher(String address, int port) throws IOException {
        connect(address, port);
        clientIntroduction();
        startHeartBeat(address, port);
    }

    private static void sendAction(String action, Long recipientId) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            String message = recipientId + "," + action + "," + System.currentTimeMillis() + "\n";
            outputStream.write(message.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("couldn't send action message");
        }
    }

    private void clientIntroduction() throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("3001\n".getBytes());
        outputStream.flush();
    }

    private void startHeartBeat(String address, int port) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            while (true) {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write("h\n".getBytes());
                    outputStream.flush();
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                    connect(address, port);
                    clientIntroduction();
                }
            }
        });
    }

    private static void connect(String address, int port) {
        try {
            socket = new Socket(address, port);
        } catch (IOException e) {
            System.out.println("cant connect to server :" + e.getMessage());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            connect(address, port);
        }
    }

    @Override
    public void publishDirectCommand(String message) throws IOException {
        sendAction(message, 2001l);
    }

    @Override
    public void publishSpeedCommand(String message) throws IOException {
        sendAction(message, 2001l);
    }
}