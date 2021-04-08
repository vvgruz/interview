import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonExtractor {
    private List<Person> persons;
    private String source;

    public void extractPersons(String source) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(source)));
        while (reader.readLine() != null) {
            String[] person = reader.readLine().split(";");
            persons.add(new Person(person[0], person[1], Integer.parseInt(person[3])));
        }
    }

    public synchronized void removePerson(Person person) {
        for (Person value : persons) {
            if (value == person) {
                persons.remove(person);
            }
        }
    }

    public synchronized void addPerson(Person person) {
        persons.add(person);
    }

    public List<Person> getPersonsByCompany(List<Employee> employees, String name) {
        List result = new ArrayList();
        for (Employee employee : employees) {
            for (String company : employee.getCompanies()) {
                if (company == name) {
                    result.add(employee);
                }
            }
        }
        return result;
    }
}

class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person() {

    }
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return age;
    }
}

class Employee extends Person {
    private List<String> companies;

    public Employee(List<String> companies) {
        this.companies = companies;
    }

    public List<String> getCompanies() {
        return companies;
    }
}
