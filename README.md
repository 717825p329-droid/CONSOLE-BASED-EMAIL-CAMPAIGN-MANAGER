# Email Campaign Manager

A console-based Email Campaign Manager written in Java using Object-Oriented Programming. It manages subscribers, email templates, campaigns and delivery statistics.

**Course:** 23CSR303 – Object Oriented Programming with Java
**Assignment:** II – Specification 31
**Department:** Computer Science and Engineering, Karpagam College of Engineering

## Features

- Add subscribers with tags and manage their status (ACTIVE / UNSUBSCRIBED)
- Create email templates with `{{name}}` and `{{email}}` placeholders
- Build audiences by tag
- Create promotional and transactional campaigns
- Schedule campaigns now, for later, or with a recurrence
- Send campaigns and track opens, clicks, unsubscribes, conversions and deliveries
- Print campaign performance and subscriber growth reports

## Project Structure

```
src/Emailcampaign/
├── Subscriber.java            # id, name, email, status, tags
├── EmailTemplate.java         # templateId, name, subject, body, placeholders
├── Campaign.java              # base class: campaignId, template, audience, scheduleTime, metrics
├── PromotionalCampaign.java   # extends Campaign (offer code, discount, conversions, revenue)
├── TransactionalCampaign.java # extends Campaign (event type, delivered, bounced)
├── CampaignService.java       # create audiences, schedule/send, track metrics, reports
└── CampaignAppMain.java       # main class to test the application
```

## OOP Concepts Demonstrated

| Concept | Where |
|---|---|
| Inheritance | `PromotionalCampaign` and `TransactionalCampaign` extend `Campaign` |
| Method Overloading | `CampaignService.schedule()` – send now, schedule for later, schedule with recurrence |
| Method Overriding | `report()` shows different KPIs for each campaign type |
| Polymorphism | `List<Campaign>` holds both campaign types and `report()` is resolved at runtime |
| Encapsulation | Private fields; subscriber status and campaign metrics change only through controlled methods |

## How to Run

**Using Eclipse**
1. Create a new Java Project.
2. Create a package named `Emailcampaign` inside `src`.
3. Copy all `.java` files into the package.
4. Right-click `CampaignAppMain.java` → Run As → Java Application.

**Using the command line**
```bash
javac -d out src/Emailcampaign/*.java
java -cp out Emailcampaign.CampaignAppMain
```

Requires JDK 8 or later.

## Sample Output

```
EMAIL CAMPAIGN MANAGER
----------------------

Subscribers added:
  1 - Asha <asha@mail.com> [ACTIVE] Tags: [offers, customers]
  2 - Ravi <ravi@mail.com> [ACTIVE] Tags: [offers]
  3 - Meena <meena@mail.com> [ACTIVE] Tags: [offers, customers]
  4 - Karthik <karthik@mail.com> [ACTIVE] Tags: [customers]

Templates created:
Template 1: Festival Sale | Subject: Big Festival Sale! | Placeholders: [name, email]
Template 2: Order Confirmation | Subject: Your order is confirmed | Placeholders: [name]

Audience sizes -> Promotional: 3, Transactional: 3

Campaign 'Festival Offer' scheduled for 2026-11-01 10:00
Campaign 'Order Receipt' scheduled for 2026-11-01 10:00, repeats daily

Campaign 'Festival Offer' sent to 3 subscribers
Campaign 'Order Receipt' sent to 3 subscribers

===== CAMPAIGN PERFORMANCE REPORT =====
[PROMOTIONAL] Festival Offer | Offer: FEST30 (30.0% off)
  Sent: 3 | Open Rate: 66.7% | CTR: 33.3% | Unsubscribes: 1
  Conversions: 1 | Revenue: Rs.1499.0
[TRANSACTIONAL] Order Receipt | Event: ORDER_PLACED
  Sent: 3 | Delivered: 2 | Bounced: 1 | Delivery Rate: 66.7%

===== SUBSCRIBER GROWTH REPORT =====
Total subscribers ever added: 4
Active: 3 | Unsubscribed: 1
Total emails sent: 6

Email Campaign Manager Completed!
```

## Author

<!-- Add your name and register number here -->
