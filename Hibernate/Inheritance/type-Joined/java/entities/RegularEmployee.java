package entities;

import javax.persistence.*;

@Entity
@Table(name = "ragular_employee")
public class RegularEmployee extends Employee {
    @Column
    private double salary;
    @Column
    private double bonus;

    public RegularEmployee() {
    }

    public RegularEmployee(String name, double salary, double bonus) {
        super(name);
        this.salary = salary;
        this.bonus = bonus;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
