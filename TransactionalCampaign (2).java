package Emailcampaign;

public class TransactionalCampaign extends Campaign {
    private String eventType;
    private int delivered;
    private int bounced;

    public TransactionalCampaign(int campaignId, String name,
            EmailTemplate template, String eventType) {
        super(campaignId, name, template);
        this.eventType = eventType;
        this.delivered = 0;
        this.bounced = 0;
    }

    public String getEventType() {
        return eventType;
    }

    public int getDelivered() {
        return delivered;
    }

    public int getBounced() {
        return bounced;
    }

    public void recordDelivery(int deliveredCount, int bouncedCount) {
        delivered += deliveredCount;
        bounced += bouncedCount;
    }

    @Override
    public void report() {
        System.out.println("[TRANSACTIONAL] " + getName()
                + " | Event: " + eventType);
        System.out.println("  Sent: " + getSent()
                + " | Delivered: " + delivered
                + " | Bounced: " + bounced
                + " | Delivery Rate: " + String.format("%.1f", percent(delivered, getSent())) + "%");
    }
}
