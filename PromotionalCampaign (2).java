package Emailcampaign;

public class PromotionalCampaign extends Campaign {
    private String offerCode;
    private double discountPercent;
    private int conversions;
    private double revenue;

    public PromotionalCampaign(int campaignId, String name,
            EmailTemplate template, String offerCode,
            double discountPercent) {
        super(campaignId, name, template);
        this.offerCode = offerCode;
        this.discountPercent = discountPercent;
        this.conversions = 0;
        this.revenue = 0;
    }

    public String getOfferCode() {
        return offerCode;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public int getConversions() {
        return conversions;
    }

    public double getRevenue() {
        return revenue;
    }

    public void recordConversion(double amount) {
        conversions++;
        revenue += amount;
    }

    @Override
    public void report() {
        System.out.println("[PROMOTIONAL] " + getName()
                + " | Offer: " + offerCode + " (" + discountPercent + "% off)");
        System.out.println("  Sent: " + getSent()
                + " | Open Rate: " + String.format("%.1f", percent(getOpens(), getSent())) + "%"
                + " | CTR: " + String.format("%.1f", percent(getClicks(), getSent())) + "%"
                + " | Unsubscribes: " + getUnsubscribes());
        System.out.println("  Conversions: " + conversions
                + " | Revenue: Rs." + revenue);
    }
}
