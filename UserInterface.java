import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.Date; 
import java.util.*;
import java.io.*;
public class UserInterface {
 public static UserInterface userInterface ;
 public static final int ADD_MEMBER = 1 ;
 public static final int ADD_BOOKS = 2;
 public static final int ISSUE_BOOKS = 3;
 public static final int RETURN_BOOKS =4;
 public static final int RENEW_BOOKS =5 ;
 public static final int REMOVE_BOOKS = 6;
 public static final int PLACE_A_HOLD = 7;
 public static final int REMOVE_HOLD = 8 ;
 public static final int PROCESS_HOLD =9;
 public static final int PRINT_TRANSACTION = 10;
 public static final int SAVE_DATA = 11;
 public static final int RETRIVE_DATA = 12;
 public static final int HELP =13;
 public static final int EXIT = 0;
 Library library;
 // The singleton UI instance
 public static UserInterface instance() {
 if (userInterface == null) {
 return userInterface = new UserInterface();
 } 
else {
 	return userInterface;
 	}
 }
 // The private constructor
 private UserInterface() {
 	try{
		File file = new File("LibraryData");
		if (file.exists() && file.canRead()){if (yesOrNo("Saved data exists. Use it? ")) { RetrieveData();}
		}
 		if (library == null) library = Library.instance();
		} 
	catch(IOException ex) { ex.printStackTrace(); } 
 } 

 // main process
 public static void main(String[] s) 
{
UserInterface.instance().process();
 }
 // The menu process
public void process() {
int command;
help();
try{
 while ((command = getCommand()) != EXIT) {
 switch (command) {
case ADD_MEMBER: addMember();break;
case ADD_BOOKS: addBooks();break;
case ISSUE_BOOKS: issueBooks();break;
case RETURN_BOOKS: returnBooks();break;
case RENEW_BOOKS: renewBooks();break;
case REMOVE_BOOKS: removeBooks();break;
case PLACE_A_HOLD: placeHold();break;
case REMOVE_HOLD: removeHold();break;
case PROCESS_HOLD: processHolds(); break;
case PRINT_TRANSACTION: getTransactions();break;
case SAVE_DATA: SaveData();break;
case RETRIVE_DATA: RetrieveData(); break;
case EXIT: System.exit(1); break;
case HELP: help(); break;
default: System.out.println("Please enter a valid option");
 } // switch end
 }// while end
 } catch (IOException ex) {
ex.printStackTrace(); 
 }
 }
 //YesOrNo 
 public boolean yesOrNo(String msg) throws IOException {
 System.out.println( msg + "(Type: y for yes (or) n for no)"); 
 BufferedReader reader = new BufferedReader(new 
InputStreamReader(System.in)); 
 String response = reader.readLine();
 if ( response.equals("y"))
return true;
 else
return false;
 }
 public String getToken(String msg) throws IOException {
System.out.print(msg); 
 BufferedReader reader = new BufferedReader(new 
InputStreamReader(System.in)); 
return reader.readLine();
 }
 public int getNumber(String msg) throws IOException {
System.out.println(msg);
BufferedReader reader = new BufferedReader(new 
InputStreamReader(System.in)); 
return Integer.parseInt(reader.readLine());
 }
 public String getDate (String msg) throws IOException {
 System.out.println(msg);
BufferedReader reader = new BufferedReader(new 
InputStreamReader(System.in)); 
return reader.readLine();
 }
 public int getCommand() throws IOException {
System.out.println("-------------------------------------");
System.out.println();
System.out.println("Welcome to Library Automation System");
System.out.println("Please Type In the Number to Process");
System.out.println("1. Register New Member");
System.out.println("2. Add New Books");
System.out.println("3. Issue Books");
System.out.println("4. Return Books");
System.out.println("5. Renew Books");
System.out.println("6. Remove Books");
System.out.println("7. Place a Hold");
System.out.println("8. Remove Hold"); 
System.out.println("9. Process Holds");
System.out.println("10. Print Transactions");
System.out.println("11. Save Data"); 
System.out.println("12. Retrieve Data");
System.out.println("13. Help"); 
System.out.println("0. Exit");
 System.out.println("Enter a number indicating the operation. ");
 BufferedReader reader = new BufferedReader(new 
InputStreamReader(System.in)); 
int cmd = Integer.parseInt(reader.readLine());
System.out.println("-------------------------------------");
return cmd;
 }
 public void help(){
System.out.println("-------------------------------------");
System.out.println();
System.out.println("The Library Automation System");
System.out.println("This system is simple text interface system.");
System.out.println("Initially, the system will display a menu.");
System.out.println(" The user can enter a number from 0 through 13 indicating the operation. ");
System.out.println(" The option 0 will be used to exit the system. ");
System.out.println(" The option 13 display the help screen respectively. ");
System.out.println(" Parameters required for the operation will be prompted. ");
System.out.println(" The result of the operation is then displayed. ");
 }
 //Add New Member
 public void addMember(){
System.out.println("1. Register New Member");
Member result;
try{
do {
 String name = getToken("Enter Member Name: ");
 String address = getToken("Enter Address: ");
 String phone = getToken("Enter Phone: ");
 result = library.addMember(name, address, phone);
 if (result != null) {
 System.out.println("New Member Added: " + 
result.toString());
 } else {
 System.out.println("New Member could not be added");
 }
 if (!yesOrNo("Add more new member?")) { break; }
} while (true); 
} catch (IOException ex) {
ex.printStackTrace(); 
}
 }
 
 // The Adding New Books process
 public void addBooks() {
System.out.println("2. Add New Books");
Book result;
try{
do {
 String title = getToken("Enter book title: ");
 String author = getToken("Enter author: ");
 String bookID = getToken("Enter id: ");
 result = library.addBook(title, author, bookID);
 if (result != null) {
 System.out.println("New Book Added: "+result.toString());
 } else {
 System.out.println("New Book could not be added");
 }
 if (!yesOrNo("Add more new books?")) { break; }
} while (true); 
} catch (IOException ex) {
ex.printStackTrace(); 
}
 }
 
// The Book Checkout or Issuing Books process
public void issueBooks() {

	System.out.println("3. Issue Books");
	Book result;
	try{
	int memberID = getNumber("Enter member ID: ");
	if (library.searchMembership(memberID) == null) {
	System.out.println("No such member exist!!!");
	return;
	}
do {
String bookID = getToken("Enter book id: ");
result = library.issueBook(memberID, bookID);
if (result != null){
 System.out.println("Issue Book Title: " + 
result.getTitle() );
 System.out.println("Due Date to return: " + 
result.getDueDate());
} 
else {
 System.out.println("Book could not be issued");
 break;
}
if (!yesOrNo("More issue books?")) { break; }
} while (true); 
 } catch(IOException ex) { ex.printStackTrace(); }
}

//extracts a member’s transactions information and displays it in the preferred format
public void getTransactions() {
System.out.println("10. Print Transactions");
ArrayList<Transaction> result;
try{
int memberID = getNumber("Enter member id: ");
String date = getDate("Please enter the date for transaction as mm/dd/yyyy: ");
result = library.getTransactions(memberID,date);
if (result == null) {
System.out.println("Invalid Member ID");
} else {
 for(Transaction trans : result) {
 System.out.println(trans.toString());
//System.out.println(trans.getType() + " " + trans.getTitle() + "\n");
 }
 System.out.println("\n There are no more transactions \n" ); 
} 
} catch(IOException ex) { ex.printStackTrace(); }
}
// The Book Return process
public void returnBooks() {
System.out.println("4. Return Books");
int result;
try{
 do {
String bookID = getToken("Enter borrowed book id: ");
result = library.returnBook( bookID);
if (result == 1){
 System.out.println("Book ID not found.");
} else if (result == 2) {
 System.out.println("No borrowed information is found.");
} else if (result == 3) {
 System.out.println("Book is returned successfully.");
} else if (result == 4) {
 System.out.println("Book is returned successfully.");
 System.out.println("Book has a hold.");
} else if (result == 0) {
 System.out.println("Book returned unsuccessfully.");
}
if (!yesOrNo("Any more books to return? ")) { break; }
 } while (true); 
} catch(IOException ex) { ex.printStackTrace(); }
}
//We removes only those books that are not checked out and do not have a hold.
public void removeBooks() {
System.out.println("6. Remove Books");
int result;
// 1 – invalid book // 2 – hasHold
// 3 – borrowed. // 4 – successfully removed 
 // 5 – unsuccess removed
try{
 do {
String bookID = getToken("Enter book id to be removed: ");
result = library.removeBook( bookID);
if (result == 1){
 System.out.println(" Book ID not found.");
} else if (result == 2) {
 System.out.println("Book has a hold. Cannot remove");
} else if (result == 3) {
 System.out.println("Book is checked out. Cannot remove.");
}else if (result == 4) {
 System.out.println("Book is removed successfully.");
} else { System.out.println("Book removed fail."); }
if (!yesOrNo("Any more books to remove? ")) { break; }
 } while (true); 
} catch(IOException ex) { ex.printStackTrace(); }
}
//creates an instance of Hold process
public void placeHold() {
System.out.println("7. Place a Hold");
int result;
try{
 int memberID = getNumber("Enter member id: ");
 String bookID = getToken("Enter hold book id: ");
 int duration = getNumber("Please enter the duration for the hold: ");
 result = library.placeHold(memberID, bookID, duration);
 if (result == 1){
 System.out.println(memberID + " Member ID is invalid!Member not found.");
 } else if (result == 2) {
 System.out.println(bookID + " Book ID is invalid! Book not found.");
 } else if (result == 3) {
 System.out.println("Book is not checked out. Can borrow directly.");
 }else if (result == 4) {
System.out.println("Hold on book is placed successfully.");
 } 
} catch(IOException ex) { ex.printStackTrace(); }
}
// processing the holds at the end of each day.
// inform the member to collect the book within the specified time
public void processHolds() {
System.out.println("9. Process Holds");
Member result;
try{
do {
 String bookID = getToken("Enter book id: ");
 result = library.processHold( bookID);
if (result == null){
 System.out.println(bookID + " Process Holds Error ");
} else {
 System.out.println(" Book on Hold is ready to pick up forMember name: " + result.getName());
System.out.println("Member Address: " + result.getAddress());
System.out.println("Member Phone: " + result.getPhone());
}
if (!yesOrNo("Any more holds on books? ")) { break; }
} while (true); 
} catch(IOException ex) { ex.printStackTrace(); }
}
// Remove a hold on a book:
public void removeHold() {
System.out.println("8. Remove Hold");
int result;
try{
 int memberID = getNumber("Enter member id: ");
 String bookID = getToken("Enter removeHold book id: ");
 result = library.removeHold(memberID, bookID);
 if (result == 1){
 System.out.println(bookID + " BookID is invalid! Book not found.");
 } else if (result == 2) {
 System.out.println(memberID + " Member ID is invalid!Member not found.");
 } else if (result == 3) {
 System.out.println("Hold on Book is removed successfully.");
 } else if (result == 0) {
 System.out.println("No Hold is exist for Member and Book.");
 } 
} catch(IOException ex) { ex.printStackTrace(); }
}
//Renew a book
public void renewBooks() {
System.out.println("5. Renew Book");
ArrayList<Book> result = new ArrayList<>();
try{
 int memberID = getNumber("Enter member id: ");
 Member member = library.searchMembership(memberID);
 if (member== null) {
System.out.println("No such member");
return;
 }
 result = library.getBooks(memberID);
 if (result == null) {
 System.out.println("No Books are borrowedby member.");
 } else {
System.out.println("Member Name: " + member.getName() );
System.out.println("Book list borrowedby member are : ");
for(Book book : result) {
 System.out.println("Book Title: " + book.getTitle() + "\n");
 if (yesOrNo("Book to renew?")) { 
 if(library.renewBook(book.getBookId(), memberID)== null) 
System.out.println("Renew book fail!");
else 
System.out.println("Renew book success for title: " + 
book.getTitle()); 
 }
} //for
System.out.println("\n There are no more books to renew. \n" ); 
 }//if
 } catch(IOException ex) { ex.printStackTrace(); } 
}
private void SaveData() {
 if (library.save()) {
System.out.println("The library has been successfully saved" );
 } else {
System.out.println("There has been an error in saving \n" );
 }
}
private void RetrieveData() {
 library = Library.retrieve();
 if (library != null) {
 System.out.println("The library has been successfully retrieved" );
 } else {
System.out.println("There has been an error in retrieving \n" );
 }
}
} 