package de.fhdw.freefeser;

import de.fhdw.freefeser.textanalyzer.TextAnalyzer;

public class Main {
    public static void main(String[] args) throws Exception {
        String text = "Wie ist das Wetter in Berlin?";
        String text2 = "Wie ist das Wetter in deiner Mutter?";
        String text3 = "wikiBot";
        String text4 = "WeatherBot";

        String location = TextAnalyzer.extractLocation(text);
        String location2 = TextAnalyzer.extractLocation(text2);
        System.out.println(location + "\n" + location2 + "\n");

        String bot = TextAnalyzer.extractBot(text3);
        String bot2 = TextAnalyzer.extractBot(text4);
        System.out.println(bot + "\n" + bot2);
    }
}