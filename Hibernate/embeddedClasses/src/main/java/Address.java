import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String town;


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }


}
