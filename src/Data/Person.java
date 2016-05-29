package Data;

public class Person {
    public Integer id;
    public String Name, Email, Surname;
    public Integer age, passNumb, passSeries;


    public Person(Integer id, String name, String email, String surname, Integer age, Integer passNumb, Integer passSeries) {
        this.id = id;
        Name = name;
        Email = email;
        Surname = surname;
        this.age = age;
        this.passNumb = passNumb;
        this.passSeries = passSeries;
    }
    public Person(String name, String email, String surname, Integer age, Integer passNumb, Integer passSeries) {
        Name = name;
        Email = email;
        Surname = surname;
        this.age = age;
        this.passNumb = passNumb;
        this.passSeries = passSeries;
    }

    public String getSurname() {
        return Surname;
    }
    public void setSurname(String surname) {
        Surname = surname;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getpassNumb() {
        return passNumb;
    }
    public void setpassNumb(Integer passNumb) {
        this.passNumb = passNumb;
    }

    public Integer getpassSeries() {
        return passSeries;
    }
    public void setpassSeries(Integer passSeries) {
        this.passSeries = passSeries;
    }

    @Override
    public String toString() {
        return "(" + "'" + Name + "','" + Surname + "','" + Email + "','" + age + "','" + passSeries + "','" + passNumb + "')";
    }
}
