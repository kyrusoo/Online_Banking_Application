import java.util.Objects;

public class SuperCustomer extends Customer{
    private String bonus;
    public SuperCustomer(String customerId, String name, String email, String phone, String bonus) {
        super(customerId, name, email, phone);
        setBonus(bonus);
    }

    public SuperCustomer() {
        this.setCustomerId("");
        this.setName("");
        this.setEmail("");
        this.setPhone("");
        this.bonus = "";
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "SuperCustomer{" +
                "customerId='" + getCustomerId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", bonus='" + bonus + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        SuperCustomer that = (SuperCustomer) obj;
        return Objects.equals(bonus, that.bonus);
    }
    @Override
    public int hashCode() {
        return Objects.hash(getCustomerId(), getName(), getEmail(), getPhone(), bonus);
    }
}
