package za.co.wethinkcode.avaj.simulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private BufferedWriter writer;
    private static Logger logger;

    private Logger(String logFile) throws IOException {
        File f = new File("./simulation.txt");
        f.delete();
        this.writer = new BufferedWriter(new FileWriter(logFile));
    }

    public static Logger getLogger() throws IOException {
        return   logger = logger == null ? (logger = new Logger("simulation.txt")) : logger;
    }

    public void log(String message) throws IOException {
        this.writer.write(message);
    }

    void close() throws IOException{
        this.writer.close();
    }
}
