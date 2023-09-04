package de.fhdw.freefeser.app.chatbot.wiki;

public class AppWikiResult implements WikiResult {

    private final String title;
    private final String description;

    public AppWikiResult(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
