package model;

import java.util.Date;

public class DocumentVersion {
    private int versionNumber;
    private String content;
    private Date timestamp;

    public DocumentVersion(int versionNumber, String content) {
        this.versionNumber = versionNumber;
        this.content = content;
        this.timestamp = new Date();
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }


}
