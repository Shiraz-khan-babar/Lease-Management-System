package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class DataBase {
	private List<Person> people;
	
	public DataBase(){
		people = new LinkedList<Person>();
	}
	
	public void addPerson(Person person){
		people.add(person);
		sortPeople(people);
	}
	
	public List<Person> getPeople(){
		return Collections.unmodifiableList(people);
	}
	
	public void saveToFile(File file) throws IOException{
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Person[] persons = people.toArray(new Person[people.size()]);
		oos.writeObject(persons);
		oos.close();
	}
	
	public void loadFromFile(File file) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Person[] persons =(Person[])ois.readObject();
			people.clear();
			int size= persons.length;
			Person person = null;
			if(size>0){
			person = persons[size-1];
			}
			if(person!=null){
			int id =person.getId();
			Person.count = ++id;
			}
			people.addAll(Arrays.asList(persons));
			sortPeople(people);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ois.close();
	}
	
	public void sortPeople(List<Person> people){
		Collections.sort(people,new Comparator<Person>() {

			@Override
			public int compare(Person arg0, Person arg1) {
				return arg0.getName().compareTo(arg1.getName());
			}
		} );
	}

	public void removePerson(int row) {
		people.remove(row);
		
	}
	
}
