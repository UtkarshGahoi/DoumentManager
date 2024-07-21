package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Document {
    private UUID id;
    private String content;
    private User author;
    private List<DocumentVersion> versions;

    public Document(String content, User author) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.author = author;
        this.versions = new ArrayList<>();
        addVersion(content);
    }

    public UUID getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public List<DocumentVersion> getVersions() {
        return versions;
    }

    public void addVersion(String content) {
        int newVersionNumber = versions.size() + 1;
        DocumentVersion newVersion = new DocumentVersion(newVersionNumber, content);
        versions.add(newVersion);
        this.content = content;
    }

    public DocumentVersion getVersion(int versionNumber) {
        return versions.stream()
                .filter(v -> v.getVersionNumber() == versionNumber)
                .findFirst()
                .orElse(null);
    }
    public int getLatestActiveVersion(Document document){
        return document.getVersions().size();
    }

}
