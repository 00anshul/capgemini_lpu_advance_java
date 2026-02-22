package crm.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sales_employee")
public class SalesEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;

    @OneToMany(mappedBy = "employee")
    private List<Lead> leads = new ArrayList<>();

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public List<Lead> getLeads() { return leads; }
    public void setLeads(List<Lead> leads) { this.leads = leads; }

    @Override
    public String toString() {
        return "SalesEmployee [id=" + id + ", name=" + name + 
               ", department=" + department + "]";
    }
}