package org.game;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;

public class LogToFile implements Logging {
    Path file = Paths.get("src/main/resources/log");
    BufferedWriter writer;
    public LogToFile() {
        try {
            Files.deleteIfExists(file);
            Files.createFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void addMassageToLog(String message) {
        LocalTime time = LocalTime.now();
        try{
            writer = new BufferedWriter(new FileWriter(file.toString(),true));
            writer.append(String.format("%02d:%02d:%02d %s\n",time.getHour(), time.getMinute(), time.getSecond(),message));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList <String> getAllMessages() {
        ArrayList<String> log = new ArrayList<>();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file.toString()));
            while ((line = reader.readLine())!=null){
                log.add(line);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return log;
    }
}
