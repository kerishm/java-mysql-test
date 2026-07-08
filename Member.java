import java.util.*;
import java.io.*;
public class Member implements Serializable {
private String name, address, phone;
private int memberId;
private ArrayList<Book> booksBorrowed;
private ArrayList<Hold> booksOnHold;
private ArrayList<Transaction> transactions;
public Member (String name, String address, String phone, int id) {
this.name = name;
this.address = address;
this.phone = phone;
this. memberId = id;
this.booksBorrowed = new ArrayList<>();
this.booksOnHold = new ArrayList<>();
this.transactions = new ArrayList<>();
}
public String getName() { return this.name; }
public String getAddress() { return this. address; }
public String getPhone() { return this.phone; }
public int getMemberID() { return this.memberId; }
@Override
public String toString(){
return "Member Name: " + this.name + ", Address: " + 
this.address 
+ ", Phone: " + this.phone + ", MemeberID: " + 
this.memberId ;
}
//Book Checkout or Book Issue process
public boolean issue(Book book) {
 // to record the book borrowed, add book in the list 
 if (booksBorrowed.add(book)){
 // Member class is keeping track of all the transactions in the class Transaction.
Transaction trans = new Transaction ("Book issued ", book.getTitle());
transactions.add (trans);
return true;
 }
 return false;
}
//Get transactions of a Member for a given date process
public ArrayList<Transaction> getTransactions(String date) {
ArrayList<Transaction> result = new ArrayList<Transaction>();
for (Transaction trans : transactions ) {
if (trans.onDate(date)) {
 result.add(trans);
}
}
return (result);
}
//Book return process
public boolean returnBook (Book Rbook) {
//removed from booksBorrowed 
boolean bookfound = false;
for (Book book : booksBorrowed ){
if ( book.getBookId().equals(Rbook.getBookId())) {
 bookfound = true;
 //record the returned book transaction
 transactions.add(new Transaction ("Book returned", 
Rbook.getTitle())); 
 break;
} 
} // for end
if(bookfound) booksBorrowed.remove(Rbook);
return bookfound; 
}
//Pace a hold on Book process
public void placeHold(Hold hold) {
transactions.add( new Transaction ("Hold Placed", 
hold.getBook().getTitle())); 
booksOnHold.add(hold);
}
//After the reference to the Hold object has been found in the Book, 
// the hold is removed from the book and from the corresponding member as well. 
// Hold remove process
public boolean removeHold(String bookId) {
boolean removed = false;
Hold foundhold = null;
for (Hold hold : booksOnHold ) {
String id = hold.getBook().getBookId();
if (id.equals(bookId)) {
foundhold = hold;
}
} //for
if (foundhold != null) {
transactions.add(new Transaction ("Hold Removed ",foundhold.getBook().getTitle()));
booksOnHold.remove(foundhold); 
removed = true; 
}
return removed;
}
// Book renew process. 
public boolean renew (Book book) {
transactions.add ( new Transaction ("Book Renew ", 
book.getTitle()));
return true;
}
//get Books borrowed by the member
public ArrayList<Book> getBooksIssued( ) {
return booksBorrowed;
}
}//