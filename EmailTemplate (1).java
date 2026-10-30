package Emailcampaign;

import java.util.ArrayList;
import java.util.List;

public class EmailTemplate {
    private int templateId;
    private String name;
    private String subject;
    private String body;
    private List<String> placeholders;

    public EmailTemplate(int templateId, String name, String subject,
            String body) {
        this.templateId = templateId;
        this.name = name;
        this.subject = subject;
        this.body = body;
        this.placeholders = new ArrayList<>();
        findPlaceholders();
    }

    private void findPlaceholders() {
        int start = body.indexOf("{{");
        while (start != -1) {
            int end = body.indexOf("}}", start);
            if (end == -1) {
                break;
            }
            placeholders.add(body.substring(start + 2, end));
            start = body.indexOf("{{", end);
        }
    }

    public int getTemplateId() {
        return templateId;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public List<String> getPlaceholders() {
        return placeholders;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
        placeholders.clear();
        findPlaceholders();
    }

    public String render(Subscriber s) {
        return body.replace("{{name}}", s.getName())
                   .replace("{{email}}", s.getEmail());
    }

    public void display() {
        System.out.println("Template " + templateId + ": " + name
                + " | Subject: " + subject
                + " | Placeholders: " + placeholders);
    }
}
