package entities;

import javax.persistence.*;

@Entity
@Table(name = "contract_employee")
public class ContractEmployee extends Employee {
    @Column(name = "pay_per_hour")
    private double payPerHour;

    public ContractEmployee(){}

    public ContractEmployee(String name, double payPerHour) {
        super(name);
        this.payPerHour = payPerHour;
    }

    public double getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(double payPerHour) {
        this.payPerHour = payPerHour;
    }
}
