package Emailcampaign;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    private int id;
    private String name;
    private String email;
    private String status;
    private List<String> tags;
    private static int totalSubscribers = 0;

    public Subscriber(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = "ACTIVE";
        this.tags = new ArrayList<>();
        totalSubscribers++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getTags() {
        return new ArrayList<>(tags);
    }

    public static int getTotalSubscribers() {
        return totalSubscribers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
        }
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public boolean isActive() {
        return status.equals("ACTIVE");
    }

    // Controlled update of status (encapsulation)
    public void unsubscribe() {
        if (isActive()) {
            status = "UNSUBSCRIBED";
        }
    }

    public void resubscribe() {
        status = "ACTIVE";
    }

    @Override
    public String toString() {
        return id + " - " + name + " <" + email + "> [" + status
                + "] Tags: " + tags;
    }
}
