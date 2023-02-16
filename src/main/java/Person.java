public class Person {

    private String family;
    private String name;
    private String patronymic;
    private String birthdayDate;
    private Long phone;
    private String gender;

    public Person(String family, String name, String patronymic, String birthdayDate, Long phone, String gender) {
        this.family = family;
        this.name = name;
        this.patronymic = patronymic;
        this.birthdayDate = birthdayDate;
        this.phone = phone;
        this.gender = gender;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getBirthdayDate() {
        return birthdayDate;
    }

    public Long getPhone() {
        return phone;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("<").append(this.family).append(">")
                .append("<").append(this.name).append(">")
                .append("<").append(this.patronymic).append(">")
                .append("<").append(this.birthdayDate).append(">")
                .append("<").append(this.phone).append(">")
                .append("<").append(this.gender).append(">");
        return str.toString();
    }
}
