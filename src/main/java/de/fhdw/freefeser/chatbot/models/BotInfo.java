package de.fhdw.freefeser.chatbot.models;

public class BotInfo {
    private String name;
    private String description;
    private String version;
    private String author;
    private String website;

    public BotInfo(String name, String description, String version, String author, String website) {
        this.name = name;
        this.description = description;
        this.version = version;
        this.author = author;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
