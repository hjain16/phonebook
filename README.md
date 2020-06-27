# phonebook
Basic Operations of Phonebook for demonstration of OOPs Concept in Java
This application is  the simulation of  the mobile phone contacts. This case study will help to understand the basic programming concepts and OOP. 
Create the application to store the contact details of the person in two memories like SIM memory and Phone memory and the users should be provided with an option to choose the memory (SIM/Phone)  while storing the contact information and will have the options to  display, Insert , update and search the contact details. Also , provide the options to copy the specific contact details/ copy all the contacts  from Phone memory to SIM memory and vice versa . 
	      
Create a Contacts class with the attributes Name, Mobile number and the email id, choose the appropriate data type  for the attributes and have the setter and getters method , the class must to have  parameterised constructor and override toString method to display the contact information 

Create the ContactService class with two arrays with type of Contacts namely SIM and Phone with the array size of 10. 

ContactService Interface and it  should have methods insertContact (Contact contact,String memory) updateContact(Contact contact,String memory) , searchContact(String name,String memory) , searchContact(long mobileNumber,String memory) where the memory represents the Phone memory and SIM memory and the searchContact should return the contact object.

Create ContactServiceImpl class with the  implementation of  the ContactService interface and override the methods.

While inserting the contact details user must enter the 10-digit mobile number and valid email id  otherwise InvalidMobileNumberException and InvalidEmailException should be thrown (make changes accordingly in setter methods of mobile number and emaild)

Create a display method to print the contacts and sort based on the Name (display should print the information from both the memory (SIM and Phone)

Create a Copy and CopyAll method to copy the contact information from one memory to another memory 

Create a MainApp class with the main method to perform the operations on contacts object the main method should provide options to user 
1.Insert , 
2. Update , 
3 Search,
4 Display, 
5 Copy , 
6 Copy All and 0 to exit.

Get the appropriate input from the user and perform the operations. Use the ContactService interface reference variable to store the ContactServiceImpl object to call the methods.
