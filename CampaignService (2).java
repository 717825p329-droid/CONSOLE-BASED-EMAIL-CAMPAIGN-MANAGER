package Emailcampaign;

import java.util.ArrayList;
import java.util.List;

public class CampaignService {
    private List<Subscriber> subscribers;
    private List<EmailTemplate> templates;
    private List<Campaign> campaigns;   // polymorphic list
    private int totalEmailsSent;

    public CampaignService() {
        this.subscribers = new ArrayList<>();
        this.templates = new ArrayList<>();
        this.campaigns = new ArrayList<>();
        this.totalEmailsSent = 0;
    }

    public void addSubscriber(Subscriber s) {
        subscribers.add(s);
    }

    public void addTemplate(EmailTemplate t) {
        templates.add(t);
    }

    public void addCampaign(Campaign c) {
        campaigns.add(c);
    }

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    public int getTotalEmailsSent() {
        return totalEmailsSent;
    }

    // Build an audience of active subscribers having the given tag
    public List<Subscriber> createAudience(String tag) {
        List<Subscriber> audience = new ArrayList<>();
        for (Subscriber s : subscribers) {
            if (s.isActive() && s.hasTag(tag)) {
                audience.add(s);
            }
        }
        return audience;
    }

    // Overloaded schedule(): send later
    public void schedule(Campaign c, String time) {
        c.setScheduleTime(time);
        c.setStatus("SCHEDULED");
        System.out.println("Campaign '" + c.getName() + "' scheduled for " + time);
    }

    // Overloaded schedule(): with recurrence
    public void schedule(Campaign c, String time, String recurrence) {
        c.setScheduleTime(time + " (repeats " + recurrence + ")");
        c.setStatus("SCHEDULED");
        System.out.println("Campaign '" + c.getName() + "' scheduled for "
                + time + ", repeats " + recurrence);
    }

    // Overloaded schedule(): send now
    public void schedule(Campaign c) {
        sendNow(c);
    }

    public void sendNow(Campaign c) {
        int count = 0;
        for (Subscriber s : c.getAudience()) {
            if (s.isActive()) {
                c.getTemplate().render(s);
                count++;
            }
        }
        c.recordSent(count);
        c.setStatus("SENT");
        totalEmailsSent += count;
        System.out.println("Campaign '" + c.getName() + "' sent to "
                + count + " subscribers");
    }

    public void recordOpen(Campaign c, int times) {
        for (int i = 0; i < times; i++) {
            c.recordOpen();
        }
    }

    public void recordClick(Campaign c, int times) {
        for (int i = 0; i < times; i++) {
            c.recordClick();
        }
    }

    public void recordUnsubscribe(Campaign c, Subscriber s) {
        s.unsubscribe();
        c.recordUnsubscribe();
    }

    // Polymorphism: report() resolved at runtime
    public void printCampaignReports() {
        System.out.println("\n===== CAMPAIGN PERFORMANCE REPORT =====");
        for (Campaign c : campaigns) {
            c.report();
        }
    }

    public void printSubscriberGrowth() {
        int active = 0;
        int unsub = 0;
        for (Subscriber s : subscribers) {
            if (s.isActive()) {
                active++;
            } else {
                unsub++;
            }
        }
        System.out.println("\n===== SUBSCRIBER GROWTH REPORT =====");
        System.out.println("Total subscribers ever added: "
                + Subscriber.getTotalSubscribers());
        System.out.println("Active: " + active + " | Unsubscribed: " + unsub);
        System.out.println("Total emails sent: " + totalEmailsSent);
    }
}
