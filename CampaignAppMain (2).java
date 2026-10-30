package Emailcampaign;

import java.util.List;

public class CampaignAppMain {

    public static void main(String[] args) {
        System.out.println("EMAIL CAMPAIGN MANAGER");
        System.out.println("----------------------");

        CampaignService service = new CampaignService();

        // 1. Add subscribers and tags
        Subscriber s1 = new Subscriber(1, "Asha", "asha@mail.com");
        Subscriber s2 = new Subscriber(2, "Ravi", "ravi@mail.com");
        Subscriber s3 = new Subscriber(3, "Meena", "meena@mail.com");
        Subscriber s4 = new Subscriber(4, "Karthik", "karthik@mail.com");
        s1.addTag("offers");
        s1.addTag("customers");
        s2.addTag("offers");
        s3.addTag("offers");
        s3.addTag("customers");
        s4.addTag("customers");
        service.addSubscriber(s1);
        service.addSubscriber(s2);
        service.addSubscriber(s3);
        service.addSubscriber(s4);

        System.out.println("\nSubscribers added:");
        for (Subscriber s : service.getSubscribers()) {
            System.out.println("  " + s);
        }

        // 2. Build templates
        EmailTemplate t1 = new EmailTemplate(1, "Festival Sale",
                "Big Festival Sale!",
                "Hi {{name}}, enjoy 30% off this week. Sent to {{email}}.");
        EmailTemplate t2 = new EmailTemplate(2, "Order Confirmation",
                "Your order is confirmed",
                "Hi {{name}}, thank you for your order.");
        service.addTemplate(t1);
        service.addTemplate(t2);
        System.out.println("\nTemplates created:");
        t1.display();
        t2.display();

        // 3. Create campaigns
        PromotionalCampaign promo = new PromotionalCampaign(
                101, "Festival Offer", t1, "FEST30", 30);
        TransactionalCampaign trans = new TransactionalCampaign(
                102, "Order Receipt", t2, "ORDER_PLACED");

        promo.setAudience(service.createAudience("offers"));
        trans.setAudience(service.createAudience("customers"));
        service.addCampaign(promo);
        service.addCampaign(trans);

        System.out.println("\nAudience sizes -> Promotional: "
                + promo.getAudience().size() + ", Transactional: "
                + trans.getAudience().size());

        // 4. Schedule (method overloading)
        System.out.println();
        service.schedule(promo, "2026-11-01 10:00");
        service.schedule(trans, "2026-11-01 10:00", "daily");

        // 5. Send campaigns
        System.out.println();
        service.sendNow(promo);
        service.schedule(trans);          // overloaded: send now

        // 6. Update metrics
        service.recordOpen(promo, 2);
        service.recordClick(promo, 1);
        promo.recordConversion(1499.0);
        service.recordUnsubscribe(promo, s2);

        service.recordOpen(trans, 3);
        trans.recordDelivery(2, 1);

        // 7. Reports (polymorphism + overriding)
        service.printCampaignReports();
        service.printSubscriberGrowth();

        System.out.println("\nEmail Campaign Manager Completed!");
    }
}
