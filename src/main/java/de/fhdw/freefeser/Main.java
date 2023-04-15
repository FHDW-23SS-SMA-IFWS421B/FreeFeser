package de.fhdw.freefeser;

public class Main {
    public static void main(String[] args) throws Exception {
        String text = "Wie ist das Wetter in Berlin?";
        String text2 = "Wie ist das Wetter in deiner Mutter?";
        String location = LocationExtractor.extractLocation(text);
        String location2 = LocationExtractor.extractLocation(text2);
        System.out.println(location + "\n" + location2);
    }
}