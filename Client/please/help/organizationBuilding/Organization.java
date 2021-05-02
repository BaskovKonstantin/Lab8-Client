package please.help.organizationBuilding;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Organization implements Comparable<Organization>, Serializable {

    private static final long serialVersionUID = 20200916L;
    private Long id;
    private LocalDateTime creationDate;

    private final String name;
    private final Coordinates coordinates;
    private final double annualTurnover;
    private final OrganizationType type;
    private final Address officialAddress;
    private String owner;

    public Organization(String name, Coordinates coordinates,
                        double annualTurnover, OrganizationType type, Address officialAddress ){
        this.name = name;
        this.coordinates = coordinates;
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.officialAddress = officialAddress;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }

    public double getAnnualTurnover() {
        return annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public String[] getAllFields(){
        return new String[]{
                getName(), String.valueOf(getCoordinates().getX()),
                String.valueOf(getCoordinates().getY()), String.valueOf(getAnnualTurnover()),
                getType().toString(), getOfficialAddress().getStreet(), getOfficialAddress().getZipCode()
        };
    }

    public int[] getLengthsOfFields(NumberFormat currencyFormat, DateTimeFormatter dateTimeFormatter){
        int[] lengths = new int[10];
        lengths[0] = id.toString().length();
        lengths[1] = name.length();
        lengths[2] = Integer.toString(coordinates.getX()).length();
        lengths[3] = Integer.toString(coordinates.getY()).length();
        lengths[4] = dateTimeFormatter.format(creationDate).length();
        lengths[5] = currencyFormat.format(annualTurnover).length();
        lengths[6] = type.toString().length();
        lengths[7] = officialAddress.getStreet() != null ? officialAddress.getStreet().length() : 4;
        lengths[8] = officialAddress.getZipCode() != null ? officialAddress.getZipCode().length() : 4;
        lengths[9] = owner.length();
        return lengths;
    }

    @Override
    public int compareTo(Organization other) {
        return Double.compare(annualTurnover, other.getAnnualTurnover());
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        Organization otherOrg = (Organization) other;
        return otherOrg.getId().equals(this.id) && otherOrg.getCreationDate().equals(this.creationDate) &&
                otherOrg.getName().equals(this.name) && otherOrg.getCoordinates().equals(this.coordinates) &&
                otherOrg.getAnnualTurnover() == this.annualTurnover && otherOrg.getType().equals(this.type) &&
                otherOrg.getOfficialAddress().equals(this.officialAddress) && otherOrg.getOwner().equals(this.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, name, coordinates.hashCode(), annualTurnover,
                type, officialAddress.hashCode(), owner);
    }

    @Override
    public String toString() {
        return "Organization{ " + "id = " + id + "; creationDate = " + creationDate
                + "; name = " + name + "; coordinates = " + coordinates
                + "; annualTurnover = " + annualTurnover + "; type = " + type
                + "; officialAddress = " + officialAddress + " }";
    }
}
