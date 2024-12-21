
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PhoneBookTest { // main class
    public static void main(String[] args) {
        // System.out.println(new PhoneNumber("010-1234-5678"));
        // System.out.println(new PhoneNumber("01012345678"));
        //2. make person Object, call method for making others PhoneNumber object
        // Person person = new Person("Emma");
        // person.addPhoneNumber(new PhoneNumber("01012345678"));
        // person.addPhoneNumber(new PhoneNumber("010-1234-5678"));
        // person.addPhoneNumber(new PhoneNumber("010 1234 5678"));
        // System.out.println(person);
        //3. check person has this phone number
        // person.addPhoneNumber(new PhoneNumber("010 1234 5678"));
        // System.out.println(person.hasPhoneNumber(new PhoneNumber("01012345678")));// useing same number but different type
        
        
        
    }
}

class PhoneNumber{ // phonenumber class
    public final String phoneNumber;

    public PhoneNumber(String rawPhoneNumber) {
        this.phoneNumber = rawPhoneNumber.replaceAll("[^0-9]", "");
    }
    // show status of object
    @Override
    public String toString() {
        return "PhoneNumber{phoneNumber = "+ phoneNumber+"}";
    }

/*
    numbers에 PhoneNumber 가 있는가를 contains로 확인하는데 numbers 는 PhoneNumber 타입의리스트.
    따라서 해당 클래스에서 equals메소드를를 재정의해야함
    -> contains메소드가 equals()를 사용해서 객체를 비교하는데 객체를 다르게 main에서 생성해서 호출했기 때문
    -> 사실 이부분 잘 이해 안감...
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof PhoneNumber)) return false;
        return phoneNumber.equals(((PhoneNumber) obj).phoneNumber);
    }

    
}

class Person{ // person class

    // make unchanged global vaiable
    public final String name;
    private final List<PhoneNumber> numbers; 

    public Person(String name) {
        this.name = name;
        numbers = new ArrayList<>(); // why doesn't written this? because this vaiable is private final 
    }
    // for add phoneNumber to person's phonebook(list type).
    public void addPhoneNumber(PhoneNumber number){
        numbers.add(number);
    }
    // for check persons' phonenumber list
    @Override
    public String toString() {
        return "PersonClass { name = '" +name+ '\''+", numbers = "+numbers + '}';
    }
    
    public boolean hasPhoneNumber(PhoneNumber number){
        return numbers.contains(number); // check to use contains method
    }
}

class PhoneBook{ // phonebook class - list of phone number
    private final Set<Person> people; // for remove duplication
    private  PhoneBook(){
        people = new HashSet<>();
    }
    public void addPerson(Person person){
        people.add(person);
    }

    @Override
    public String toString() {
        return "PhoneBookClass{ people = " + people+"}";
    }

    public Person search(PhoneNumber number){
        return people.stream()
                .filter(p->p.hasPhoneNumber(number))
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {

            //4. make lots of person obj
            Person person1 = new Person("Emma");
            person1.addPhoneNumber(new PhoneNumber("010-1234-5678"));
            Person person2 = new Person("Cris");
            person1.addPhoneNumber(new PhoneNumber("010-2456-7845"));
            Person person3 = new Person("Tom");
            person1.addPhoneNumber(new PhoneNumber("010-4356-1234"));
    
            PhoneBook phoneBook = new PhoneBook();
            phoneBook.addPerson(person1);
            phoneBook.addPerson(person2);
            phoneBook.addPerson(person3);
            System.out.println(phoneBook.search(new PhoneNumber("01012345678")));        
            System.out.println(phoneBook.search(new PhoneNumber("01023456743")));        
            System.out.println(phoneBook.search(new PhoneNumber("01000000000")));        
    }

}