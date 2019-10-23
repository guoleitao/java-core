package cn.gltzj.compile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TestTryWithResource {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
