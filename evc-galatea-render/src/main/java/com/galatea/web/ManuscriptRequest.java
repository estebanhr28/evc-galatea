package com.galatea.web;

public class ManuscriptRequest {
    private String[] manuscript;

    public ManuscriptRequest() {}
    public ManuscriptRequest(String[] manuscript) { this.manuscript = manuscript; }

    public String[] getManuscript() { return manuscript; }
    public void setManuscript(String[] manuscript) { this.manuscript = manuscript; }
}
