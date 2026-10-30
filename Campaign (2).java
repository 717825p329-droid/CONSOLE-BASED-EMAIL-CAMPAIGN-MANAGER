package Emailcampaign;

import java.util.ArrayList;
import java.util.List;

public class Campaign {
    private int campaignId;
    private String name;
    private EmailTemplate template;
    private List<Subscriber> audience;
    private String scheduleTime;
    private String status;
    private int sent;
    private int opens;
    private int clicks;
    private int unsubscribes;

    public Campaign(int campaignId, String name, EmailTemplate template) {
        this.campaignId = campaignId;
        this.name = name;
        this.template = template;
        this.audience = new ArrayList<>();
        this.scheduleTime = "NOT SCHEDULED";
        this.status = "DRAFT";
    }

    public int getCampaignId() {
        return campaignId;
    }

    public String getName() {
        return name;
    }

    public EmailTemplate getTemplate() {
        return template;
    }

    public List<Subscriber> getAudience() {
        return audience;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public String getStatus() {
        return status;
    }

    public int getSent() {
        return sent;
    }

    public int getOpens() {
        return opens;
    }

    public int getClicks() {
        return clicks;
    }

    public int getUnsubscribes() {
        return unsubscribes;
    }

    public void setAudience(List<Subscriber> audience) {
        this.audience = audience;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Metrics can only be changed through these controlled methods
    public void recordSent(int count) {
        sent += count;
    }

    public void recordOpen() {
        if (opens < sent) {
            opens++;
        }
    }

    public void recordClick() {
        if (clicks < opens) {
            clicks++;
        }
    }

    public void recordUnsubscribe() {
        unsubscribes++;
    }

    protected double percent(int part, int whole) {
        return whole == 0 ? 0 : (part * 100.0) / whole;
    }

    // Overridden in subclasses to show different KPIs
    public void report() {
        System.out.println("Campaign: " + name + " | Status: " + status
                + " | Sent: " + sent + " | Opens: " + opens);
    }
}
