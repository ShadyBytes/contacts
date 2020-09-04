package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

class phoneRecord {
    private String number;
    private String name;
    final boolean isPerson;
    LocalDateTime dateTime;
    LocalDateTime dateTimeEdited;

    public phoneRecord(String name, String number, boolean isPerson) {
        this.number = number;
        this.name = name;
        this.isPerson = isPerson;
        dateTime = LocalDateTime.now();
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getDateTimeEdited() {
        return dateTimeEdited;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public String getName() {
        return name;
    }

    public void setDateTimeEdited(LocalDateTime dateTimeEdited) {
        this.dateTimeEdited = dateTimeEdited;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void printList(ArrayList<phoneRecord> phoneRecords) {
        for (int i = 0; i < phoneRecords.size(); i++) {
            System.out.println(i + 1 + " " + phoneRecords.get(i).getName());
        }
    }

    public void setNumber(String number) {
        if (number.matches("((\\+\\d\\s)?(\\(\\d{3}\\)|\\d{1,3})[- ]?((\\d{2,3}[- ]?)|([a-zA-Z][- ]?)|\\(\\d{2,3}" +
                "\\)[- ]?)*)" + "|" + "(\\+\\([a-z]+\\))")) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
            this.number = "[no number]";
        }
    }

}

class Person extends phoneRecord {
    private String surname;
    private String birthDate;
    private String gender;

    public Person(String name, String surname, String number, String birthDate, String gender) {
        super(name, number, true);
        this.birthDate = birthDate;
        this.gender = gender;
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(birthDate, formatter);
            birthDate = date.toString();
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
            birthDate = "[no data]";
        }
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public String getSurName() {
        return this.surname;
    }

    public void setSurName(String surname) {
        this.surname = surname;
    }

    public String getNumber() {
        return super.getNumber();
    }
}

class organization extends phoneRecord {


    String address;

    public organization(String name, String number, String address) {
        super(name, number, false);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<phoneRecord> phoneRecords = new ArrayList<>();
        boolean isActive = true;

        while (isActive) {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            switch (scanner.nextLine()) {
                case "add":
                    System.out.println("Enter the type (person, organization): ");
                    String type = scanner.next();
                    if ("person".equals(type)) {
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("Enter the name:");
                        String name = scanner.next();

                        System.out.println("Enter the surname:");
                        String surname = scanner.next();

                        System.out.println("Enter the birth date: ");
                        String birthDate = scanner1.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        try {
                            LocalDate date = LocalDate.parse(birthDate, formatter);
                            birthDate = date.toString();
                        } catch (DateTimeParseException e) {
                            System.out.println("Bad birth date!");
                            birthDate = "[no data]";
                        }

                        System.out.println("Enter the gender (M, F): ");
                        String gender = scanner1.nextLine();
                        if (!"M".equals(gender) && !"F".equals(gender)) {
                            System.out.println("Bad gender!");
                            gender = "[no data]";
                        }

                        System.out.println("Enter the number: ");
                        String number = scanner1.nextLine();
                        if (!number.matches("((\\+\\d\\s)?(\\(\\d{3}\\)|\\d{1,3})[- ]?((\\d{2,3}[- ]?)|([a-zA-Z][- ]?)|\\(\\d{2,3}\\)[- ]?)*)" + "|" + "(\\+\\([a-z]+\\))")) {
                            System.out.println("Wrong number format!");
                            number = "[no number]";
                        }

                        System.out.println("The record added.\n");

                        Person person = new Person(name, surname, number, birthDate, gender);
                        phoneRecords.add(person);
                    } else if ("organization".equals(type)) {
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("Enter the organization name: ");
                        String name = scanner1.nextLine();

                        System.out.println("Enter the address: ");
                        String address = scanner1.nextLine();

                        System.out.println("Enter the number: ");
                        String number = scanner1.nextLine();
                        if (!number.matches("((\\+\\d\\s)?(\\(\\d{3}\\)|\\d{1,3})[- ]?((\\d{2,3}[- ]?)|([a-zA-Z][- ]?)|\\(\\d{2,3}\\)[- ]?)*)" + "|" + "(\\+\\([a-z]+\\))")) {
                            System.out.println("Wrong number format!");
                            number = "[no number]";
                        }

                        System.out.println("The record added.\n");
                        organization organization = new organization(name, number, address);
                        phoneRecords.add(organization);
                    }
                    break;

                case "remove":
                    if (phoneRecords.size() != 0) {
                        int index = scanner.nextInt();
                        phoneRecords.remove(index - 1);
                    } else {
                        System.out.println("No records to remove!");
                    }
                    break;

                case "edit":
                    if (phoneRecords.size() == 0) {
                        System.out.println("No records to edit!");
                    } else {

                        //Person.printList(phoneRecords);
                        organization.printList(phoneRecords);
                        System.out.print("Select a record: ");
                        int index = scanner.nextInt();
                        index = index - 1;
                        Scanner scanner5 = new Scanner(System.in);
                        if (phoneRecords.get(index).isPerson) {
                            Person person = (Person) phoneRecords.get(index);
                            System.out.print("Select a field (name, surname, birth, gender, number): ");

                            String option = scanner.next();
                            switch (option) {
                                case "name":
                                    System.out.print("Enter name: ");
                                    String name = scanner5.nextLine();
                                    phoneRecords.get(index).setName(name);
                                    person.setDateTimeEdited(LocalDateTime.now());
                                    break;
                                case "surname":
                                    System.out.print("Enter surname: ");
                                    String surname = scanner.nextLine();
                                    person.setSurName(surname);
                                    person.setDateTimeEdited(LocalDateTime.now());
                                    break;
                                case "number":
                                    System.out.print("Enter number: ");
                                    String number = scanner5.nextLine();
                                    person.setNumber(number);
                                    person.setDateTimeEdited(LocalDateTime.now());
                                    break;
                                case "birth":
                                    String birthDate = scanner5.nextLine();
                                    person.setBirthDate(birthDate);
                                    person.setDateTimeEdited(LocalDateTime.now());
                                    break;
                                case "gender":
                                    String gender = scanner5.nextLine();
                                    person.setGender(gender);
                                    if (!"M".equals(gender.trim()) && !"F".equals(gender.trim())) {
                                        System.out.println("Bad gender!");
                                        person.setGender("[no data]");
                                    }
                                    person.setDateTimeEdited(LocalDateTime.now());
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            organization organization = (organization) phoneRecords.get(index);
                            System.out.print("Select a field (name, number): ");

                            String option = scanner5.nextLine();
                            switch (option) {
                                case "name":
                                    System.out.print("Enter name: ");
                                    String name = scanner5.nextLine();
                                    phoneRecords.get(index).setName(name);
                                    organization.setDateTimeEdited(LocalDateTime.now());
                                    break;
                                case "address":
                                    System.out.print("Enter address: ");
                                    String address = scanner5.nextLine();
                                    ((organization) phoneRecords.get(index)).setAddress(address);
                                    organization.setDateTimeEdited(LocalDateTime.now());
                                    break;
                                case "number":
                                    System.out.print("Enter number: ");
                                    String number = scanner5.nextLine();
                                    organization.setNumber(number);
                                    organization.setDateTimeEdited(LocalDateTime.now());
                                    break;
                                default:
                                    break;
                            }
                        }

                        System.out.println("The record updated!\n");
                    }
                    break;

                case "info":
                    organization.printList(phoneRecords);
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Select a record: ");
                    int index = scanner2.nextInt();
                    index--;

                    if (phoneRecords.get(index).isPerson) {
                        Person person = (Person) phoneRecords.get(index);
                        System.out.println("Name: " + person.getName());
                        System.out.println("Surname: " + person.getSurName());
                        System.out.println("Birth date: " + person.getBirthDate());
                        System.out.println("Gender: " + person.getGender());
                        System.out.println("Number: " + person.getNumber());
                        System.out.println("Time created: " + person.getDateTime().withNano(0));
                        if (person.getDateTimeEdited() == null) {
                            System.out.println("Time last edit: " + person.getDateTime().withNano(0) + "\n");
                        } else {
                            System.out.println("Time last edit: " + person.getDateTimeEdited().withNano(0) + "\n");
                        }
                    } else {
                        organization organization = (organization) phoneRecords.get(index);
                        System.out.println("Organization name: " + organization.getName());
                        System.out.println("Address: " + organization.getAddress());
                        System.out.println("Number: " + organization.getNumber());
                        System.out.println("Time created: " + organization.getDateTime().withNano(0));
                        if (organization.getDateTimeEdited() == null) {
                            System.out.println("Time last edit: " + organization.getDateTime().withNano(0) + "\n");
                        } else {
                            System.out.println("Time last edit: " + organization.getDateTimeEdited().withNano(0) + "\n");
                        }
                    }
                    break;

                case "count":
                    System.out.println("The Phone Book has " + phoneRecords.size() + " records.");
                    break;

                case "exit":
                    isActive = false;
                    break;
            }
        }
    }
}
