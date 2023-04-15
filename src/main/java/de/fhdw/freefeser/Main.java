package de.fhdw.freefeser;

public class Main {
    public static void main(String[] args) throws Exception {
        String text = "Ich wohne in Berlin.";
        String location = LocationExtractor.extractLocation(text);
        System.out.println(location);
    }
}