package crm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lead")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String source;
    private String contactInfo;
    private String status;   // NEW, ASSIGNED, CONVERTED

    @ManyToOne(cascade = CascadeType.ALL)
    private SalesEmployee employee;

    @ManyToOne
    private Customer customer;   // set when lead is converted

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public SalesEmployee getEmployee() { return employee; }
    public void setEmployee(SalesEmployee employee) { this.employee = employee; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    @Override
    public String toString() {
        return "Lead [id=" + id + ", name=" + name + 
               ", source=" + source + ", status=" + status + "]";
    }
}