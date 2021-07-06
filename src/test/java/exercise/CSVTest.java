package exercise;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


import exercise.Person;
import jdk.jfr.Timestamp;

import static exercise.Gender.Female;

public class CSVTest {
    private AddressBookManager manager;
    private ArrayList<Person> list = new ArrayList<>();
    Person p1, p2;

    @Before
    public void setUp() {
        this.manager = new AddressBookManager();

        LocalDate DOB1 = LocalDate.parse("1997-05-17");
        LocalDate DOB2 = LocalDate.parse("2020-09-21");

        p1 = new Person("Foo", Female, DOB1);
        p2 = new Person("Bar", Female, DOB2);
        list.add(p1);
        list.add(p2);
    }

    @Test
    public void testDeserializePersonData() {
        String csvString = "Foo, Female, 2021-08-17";
        Person person1 = this.manager.deserializePersonData(csvString);

        LocalDate DOB = LocalDate.parse("2021-08-17");
        Person person2 = new Person("Foo", Female, DOB);

        assertEquals(person1.getName(), person2.getName());
        assertEquals(person1.getDateOfBirth(), person2.getDateOfBirth());
        assertEquals(person1.getGender(), person2.getGender());
    }

    @Test
    public void testGetNumberOfFemales() {
        assertEquals(this.manager.getNumberOfFemales(list), 2);       
    }

    @Test
    public void testGetOldestPerson() {
        assertEquals(this.manager.getOldestPerson(list), "Foo");
    }

    @Test
    public void testGetOldestPersonForEmptyList() {
        ArrayList<Person> emptyList = new ArrayList<Person>();
        assertEquals(manager.getOldestPerson(emptyList), null);
    }

    @Test
    public void testParseFileName() {
        ArrayList<Person> parsedData = manager.parse("src/testdata/address-book.csv");
        assertFalse(parsedData.isEmpty());
    }

    @Test 
    public void testParseInvalidFileName() {
        assertEquals(manager.parse("person-book.csv"), null);
    }
}