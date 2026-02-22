package crm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "support_ticket")
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String issueDescription;
    private String status;   // OPEN, IN_PROGRESS, RESOLVED

    @ManyToOne
    private Order order;

    public Long getId() { return id; }

    public String getIssueDescription() { return issueDescription; }
    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    @Override
    public String toString() {
        return "SupportTicket [id=" + id + ", issue=" + issueDescription + 
               ", status=" + status + "]";
    }
}