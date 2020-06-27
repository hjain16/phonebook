import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InvalidEmailException extends RuntimeException{

	public InvalidEmailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}


class InvalidMobileNumberException extends RuntimeException{

	public  InvalidMobileNumberException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
}


class Contacts {
	private String name, email, number;// private members

	Contacts() {
	}

	Contacts(String na, String no, String email) {
		setEmail(email);
		setName(na);
		setNumber(no);
	}

	// setters
	public void setName(String na) {
		this.name = na;
	}

	public void setNumber(String no) {
		String reg = "^[0][1-9]\\d{9}$|^[1-9]\\d{9}$";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(no);
		if(!matcher.matches()) {
			throw new InvalidMobileNumberException("Mobile Is Not Valid !! ");
		}
		this.number = no;
	}

	public void setEmail(String email) {
		String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		Pattern pattern;
		Matcher matcher;
		pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(email);
		if(!matcher.matches()) {
			throw new InvalidEmailException("EMail Is Not Valid !! ");
		}
		this.email = email;
	}

	// getters
	public String getName() {
		
		return name;
	}

	public String getNumber() {
		
		return number;
	}

	public String getEmail() {
		
		
		return email;
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format( "%s : %s : %s "  , this.getName() , this.getNumber() , this.getEmail());
	}
}

interface ContactService {

	Contacts[] sim = new Contacts[10];
	Contacts[] phone = new Contacts[10];

	public void insertContact();

	public void updateContact(Contacts contact, String memory);

	public Contacts searchContact(String name, String memory);

	public void copy();

	public void copyAll(String from, String to);

	public Contacts searchContact();

	public void display();

}

class ContactServiceImpl implements ContactService {

	 final int MAX_MEMORY = 10;
	int simMemorySize = 0;
	int phoneMemorySize = 0;

	Scanner input = new Scanner(System.in);

	private boolean isPhoneMemoryFull() {
		return this.phoneMemorySize == MAX_MEMORY;
	}

	private boolean isSimMemoryFull() {
		return this.simMemorySize == MAX_MEMORY;
	}

	private Contacts getInput() {

		Scanner input = new Scanner(System.in);
		System.out.println("You are about to add a new contact to the phone book.");

		System.out.println("Enter Name");
		String name = input.nextLine().trim();
		System.out.println("Enter Number");
		String num = input.nextLine().trim();
		System.out.println("Enter Email");
		
		String email = input.nextLine();

		Contacts c = new Contacts(name, num, email);

		return c;
	}

	private String inputMemory() {

		Scanner input = new Scanner(System.in);
		System.out.println("Choose Memory ");
		String memory = "";
		boolean flag = true;
		while (flag) {

			System.out.println("sim or phone  ");
			memory = input.nextLine().toLowerCase();
			if (memory.equals("sim") || memory.equals("phone")) {
				flag = false;
			} else {
				System.out.println("Choose Correct Memory ");
			}
		}

		return memory;
	}

	private int getSimIndexToInsert() {
		int i = 0;
		for (Contacts c : this.sim) {
			if (c == null)
				return i;

			i++;
		}

		return -1;

	}

	private int getPhoneIndexToInsert() {
		int i = 0;
		for (Contacts c : this.phone) {
			if (c == null)
				return i;

			i++;
		}

		return -1;

	}

	private void insertIntoSim() {
		
		if (!this.isSimMemoryFull()) {
			Contacts contact = this.getInput();
			int index = this.getSimIndexToInsert();
			this.sim[index] = contact;
			
			this.simMemorySize++;
			System.out.println("Done !");
		} else {
			System.out.println("Sim Memory Is Full ....");
		}
	}

	private void insertIntoPhone() {
		if (!this.isSimMemoryFull()) {
			Contacts contact = this.getInput();
			int index = this.getSimIndexToInsert();
			this.phone[index] = contact;
			this.phoneMemorySize ++;
			System.out.println("Done !");
		} else {
			System.out.println("Phone Memory Is Full ....");
		}
	}

	public void insertContact() {
		String memory = this.inputMemory();
		Contacts contact = null;
		if (memory.equals("phone")) {

			this.insertIntoPhone();
		}

		if (memory.equals("sim")) {

			this.insertIntoSim();
		}

	}

	public void updateContact() {
		System.out.println("Updating Contact ");
		System.out.println("Search Contact To Update ");
		Contacts contact  = this.searchContact();
		
		if(contact != null) {
			Contacts temp = this.getInput();
			contact.setEmail(temp.getEmail());
			contact.setName(temp.getName());
			contact.setNumber(temp.getNumber());
			System.out.println("Updated..");
		}else {
			System.out.println("Not Found...");
		}
	}
	
	private Contacts searchByName() {
		Scanner input = new Scanner(System.in);
		System.out.println("ENter Name  : ");
		
		String name = input.nextLine();
		for (Contacts c : this.phone) {
			if (c!=null && c.getName().equalsIgnoreCase(name))
				return c;
		}
		
		for (Contacts c : this.sim) {
			if (c!=null && c.getName().equalsIgnoreCase(name))
				return c;
		}
		input.close();
		return null;
	}
private Contacts searchByNumber() {
	
		Scanner input = new Scanner(System.in);
		System.out.println("ENter Number   : ");
		String number = input.nextLine();
		for (Contacts c : this.phone) {
			if (c!=null && c.getNumber().equalsIgnoreCase(number))
				return c;
		}
		
		for (Contacts c : this.sim) {
			if (c!=null && c.getNumber().equalsIgnoreCase(number))
				return c;
		}
		
		input.close();
		
		return null;
	}

	public Contacts searchContact() {
		System.out.println("You are about to search a contact from the phone book.");
		int number = -1;
		boolean flag = true;
		while(flag) {
			System.out.println("Enter 1 to search by name or 2 by number");
			number = input.nextInt();
			if(number == 1 || number == 2 ) {
				flag = false;
			}
			
			System.out.println(flag);
		}
		if (number == 1) {
			return this.searchByName();
		} else if (number == 2) {
			return this.searchByNumber();
		}	
		
		
		return null;
	}

	public void display() {

		
		System.out.println("=====================");

		System.out.println("In Phone : ");
		for (Contacts c : this.phone) {
			if (c != null)
				System.out.println(c.getName() + " : " + c.getNumber() + " : " + c.getEmail());
		}

		System.out.println("In Sim : ");

		for (Contacts c : this.sim) {
			if (c != null)
				System.out.println(c.getName() + " : " + c.getNumber() + " : " + c.getEmail());
		}
		
		System.out.println("===================");

	}

	@Override
	public void updateContact(Contacts contact, String memory) {
		
	}
	
	private boolean  isInPhone(Contacts contact) {
		for (Contacts c : this.phone) {
			if (c == contact)
				return true;
		}
		
		return false;
	}

	private boolean  isInSim(Contacts contact) {
		for (Contacts c : this.sim) {
			if (c == contact)
				return true;
		}
		
		return false;
	}

	
	@Override
	public Contacts searchContact(String name, String memory) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void copy() {
		System.out.println("Search Contact To Copy : ");
		Contacts contact = this.searchContact();
		if(contact!=null) {
			if(this.isInPhone(contact)) {
				if(this.isSimMemoryFull()) {
					System.out.println("Sim Memory Is Full");
				}else {
					this.sim[this.getSimIndexToInsert()] = contact;
					this.simMemorySize++;
				}
			}else {
				if(this.isPhoneMemoryFull()) {
					System.out.println("Phone Memory Is Full");
				}else {
					this.phone[this.getPhoneIndexToInsert()] = contact;
					this.phoneMemorySize++;	
				}
			}
			
			System.out.println("Copied !");
			
		}else {
			System.out.println("Not Found !!");
		}
	}
	
	
	public void copyAll() {
		String from = this.inputMemory();
		String to = this.inputMemory();
		this.copyAll(from , to );
	}

	@Override
	public void copyAll(String from, String to) {
		// TODO Auto-generated method stub
		if(from.equals("sim") && to.equals("phone") ) {
			for (Contacts c : this.sim) {
				if (c != null)
				{
					if(!this.isPhoneMemoryFull()) {
						this.phone[this.getPhoneIndexToInsert()] = c;
						this.phoneMemorySize++;
					}
				}
			}
		}else {
			for (Contacts c : this.phone) {
				if (c != null)
				{
					if(!this.isSimMemoryFull()) {
						this.sim[this.getSimIndexToInsert()] = c;
						this.simMemorySize++;
					}
				}
			}
		}
		
	}

	
}

public class PhoneBook {
	public static void main(String args[]) {
		Scanner obj = new Scanner(System.in);
		ContactServiceImpl csi = new ContactServiceImpl();
		

		System.out.println("Enter Following keys to perform desired Operations :- ");
		System.out
				.println("1 to Insert\n2 to Update\n3 to Search\n4 to Display\n5 to Copy\n6 to Copy All\n0 to EXIT\n");
		int opt = obj.nextInt();
		while (opt != 0) {
			switch (opt) {
			case 1: {
				csi.insertContact();
				System.out.println("----------------------");
				break;
			}
			case 2: {
				System.out.println("Update");
				csi.updateContact();
				break;
			}
			case 3: {
				System.out.println("Search");
				Contacts contacts = csi.searchContact();
				if(contacts!=null) {
					System.out.println("Found");
					System.out.println(contacts);
				}else {
					System.out.println("Not Found !! ");
				}
				break;
			}
			case 4: {
				System.out.println("Display");
				csi.display();
				break;
			}
			case 5: {
				System.out.println("Copy");
				csi.copy();
				break;
			}
			case 6: {
				System.out.println("CopyAll");
				csi.copyAll();
				break;
			}
			default: {
				System.out.println("Please give valid Input !!!");

			}
			}

			System.out.println(
					"Select keys for operations\n1 to Insert\n2 to Update\n3 to Search\n4 to Display\n5 to Copy\n6 to Copy All\n0 to EXIT\n");
			opt = obj.nextInt();

		}
	}
}