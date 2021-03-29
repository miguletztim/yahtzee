import java.io.*;
import java.net.Socket;

// saves name and password
public class User {
    private final String name, password;
    private boolean inGame;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

                                // get-/ set- methods
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
}

                                // saves all the data needed to play a game and handles the connection
class Player {
    private final User USER;
    private final Points points;
    private final Dices dices;

    private final java.net.Socket socket;
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;

    public Player(User user, java.net.Socket socket, BufferedReader bufferedReader, PrintWriter printWriter) {
        this.USER = user;
        this.socket = socket;
        this.points = new Points();
        this.dices = new Dices();

        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;
    }

    public String read() throws IOException {
        return bufferedReader.readLine();
    }

                                    // sends stuff

    public void write(String message) {
        System.out.println("Test "+ message); // DEBUG

        switch (message) {
            case "!startGame":
            case "!isHost":
            case "!isPlayer":
            case "!startTurn":
            case "!endTurn":
            case "!getInput":
            case "!endGame":
                printWriter.println(message);

            default: {
                printWriter.println("!startPrint");
                send();
                printWriter.println(message);
                send();
                printWriter.println("!endPrint");
        }

            send();
        }
    }

    public String getInput() throws IOException {

        printWriter.println("!getInput");
        send();

        return read();
    }

    public void startTurn() {

        printWriter.println("!startTurn");
        send();
    }

    public void endTurn() {

        printWriter.println("!endTurn");
        send();
    }

    public void send() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        printWriter.flush();

    }

                                    // get-/ set- methods
    public User getUSER() {
        return USER;
    }

    public Points getPoints() {
        return points;
    }

    public Dices getDices() {
        return dices;
    }

    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }
}