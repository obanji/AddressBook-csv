package exercise;
import java.util.ArrayList;


import exercise.Person;
public class Main {

    public static void main(String[] args) {
        
        AddressBookManager manager = new AddressBookManager();
        ArrayList<Person> persons = manager.parse("src/testdata/address-book.csv");
        
        System.out.println("Number of females inside address book: " + manager.getNumberOfFemales(persons));
        System.out.println("Oldest person inside address book: " + manager.getOldestPerson(persons));
    }
}