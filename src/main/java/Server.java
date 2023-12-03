package main.java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.start(12345);
    }

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket);

                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private PrintWriter out;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received message from client: " + message);

                    // Проверяем, является ли билет счастливым
                    boolean isLucky = isLuckyTicket(message);

                    // Отправляем результат клиенту
                    out.println(isLucky ? "Lucky ticket!" : "Not a lucky ticket!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean isLuckyTicket(String ticketNumber) {
            // Проверяем, является ли номер билета счастливым.
            // Здесь вы можете использовать свой собственный алгоритм проверки счастливого билета.
            // В этом примере просто сравниваем сумму первых трех цифр с суммой последних трех цифр.
            if (ticketNumber.length() != 6) {
                return false;
            }

            int sumFirstHalf = 0;
            int sumSecondHalf = 0;

            for (int i = 0; i < 6; i++) {
                int digit = Character.getNumericValue(ticketNumber.charAt(i));

                if (i < 3) {
                    sumFirstHalf += digit;
                } else {
                    sumSecondHalf += digit;
                }
            }

            return sumFirstHalf == sumSecondHalf;
        }
    }
}
