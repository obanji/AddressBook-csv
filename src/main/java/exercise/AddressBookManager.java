package exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ValueRange;


public class AddressBookManager {
    public Person deserializePersonData(String data) {
       String[] values = data.split(", ");

       LocalDate date = LocalDate.parse(values[2]);
       Gender gender = Gender.valueOf(values[1]);
       Person person = new Person(values[0], gender, date);
       return person;
    }

    public long getNumberOfFemales(ArrayList<Person> persons) {
        return persons.stream()
        .filter(p -> p.getGender().
        equals(Gender.Female)).count();
    }

    public int computePersonAge(Person person) {
        return Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
    }

    public String getOldestPerson(ArrayList<Person> persons) {
        if (persons.size() == 0) {
            return null;
        }

        Person oldest = null;
        for (Person person : persons) {
            if(oldest == null || computePersonAge(person) > computePersonAge(oldest)) {
                oldest = person;
            }
        }
        return oldest.getName();
    }

    public ArrayList<Person> parse(String fileName) {
        ArrayList<Person> parsedData = new ArrayList<>();
        try 
        {
            BufferedReader addressBookData = new BufferedReader(new FileReader(fileName));
            Scanner scanner = new Scanner(addressBookData);

            while (scanner.hasNextLine()){
                Person person = deserializePersonData(scanner.nextLine());
                parsedData.add(person);
            }
            scanner.close();
            return parsedData;
        } 
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}