package com.raminq.abohava.service;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileService {

    public void writeToFile(String text, String sourceFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(sourceFileName))) {
            bw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendToFile(String text, String sourceFileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(sourceFileName, true))) {
            bw.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromFile(String sourceFileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFileName));
             StringWriter stringWriter = new StringWriter()) {
            String line;
            while ((line = br.readLine()) != null) {
                stringWriter.write(line);
            }
            return stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
