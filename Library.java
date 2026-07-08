import java.util.*;
import java.io.*;
public class Library implements Serializable {
 private static Library library;
 private Catalog catalog;
 private MemberList memberlist;
 private MemberIdServer IdServer;
// The singleton Library instance
public static Library instance() {
 if (library == null) {
 return library = new Library();
 } else {
 return library;
 }
}
private Library(){
 if (catalog == null) catalog = Catalog.instance();
 if (memberlist == null) memberlist = MemberList.instance();
 if (IdServer == null) IdServer = MemberIdServer.instance();
}
 // The Adding New Books process
 public Book addBook (String title, String author, String bID) {
Book book = new Book(title, author, bID);
if (catalog.insertBook(book))
return (book);
return null;
 }
 // The Adding New Member process
 public Member addMember (String name, String address, String phone) {
Member member = new Member(name, address, phone, IdServer.getId());
if (memberlist.insertMember(member))
return member;
return null;
 } 
 // Book Checkout or Issue Book process. 
 // check the validity of the user. 
 public Member searchMembership (int memberId) {
return memberlist.search(memberId);
 }
 // Book Checkout or Issue. 
 public Book issueBook(int memberId, String bookId) {
Book book = catalog.search(bookId);
if (book == null) { return(null); }
if (book.getBorrower() != null) { return(null); }
Member member = memberlist.search(memberId);
if (member == null) { return(null); }
if (!(book.issue(member) && member.issue(book))) { return null; }
return (book);
 }
 //Printing a member’s transactions
 public ArrayList<Transaction> getTransactions(int memberId, String 
date){
Member member = searchMembership (memberId);
if (member == null) { return (null); }
System.out.println("Member name: " + member.getName());
return member.getTransactions(date); }
 // Place a Hold on Book process. 
 public int placeHold(int memberID, String bookID, int 
duration) {
Member member = memberlist.search(memberID);
if (member == null ) { //invalid memberId
return 1 ; }
Book book = catalog.search(bookID);
if (book == null ) { //invalid bookId
return 2 ; }
Member borrower = book.getBorrower();
if (borrower == null) { 
//Rule 6: Holds can be placed only on books that are currently checked out
//not issued, can borrow
return 3; }
Hold hold = new Hold(member, book, duration);
if (hold != null) { 
 book.placeHold(hold); 
 member.placeHold(hold); 
}
//successfully place hole
return 4; 
 }
 // Inform to customer that Hold Book is ready to collect process
 //When a book with a hold is returned, the appropriate member will be notified
 public Member processHold(String bookId) { 
Book book = catalog.search(bookId);
if (book == null ) { //invalid book
return null ; }
Hold hold = book.getNextHold();
if (hold == null) { //no valid hold exist
return null; }
Member member = hold.getMember();
if (member == null ) { //member invalid
return null ; }
if( book.removeHold(member.getMemberID()) && member.removeHold(bookId)) 
{
 return member; 
}
return null;
 }
 // After specified time, if customer does not come to collect 
 // then Hold on Book is removed process. 
 public int removeHold (int memberId, String bookId){
Book book = catalog.search(bookId);
if (book == null ) { //invalid book
return 1 ; }
Member member = memberlist.search(memberId);
if (member == null ) { //invalid member
return 2 ; }
if( book.removeHold(memberId) && member.removeHold(bookId)) {
 //success remove
return 3; 
}
//no hold exist for given member and book id
return 0;
 }
 // Book return process. 
 public int returnBook (String bookId) {
// 1 – invalid book or book not found
// 2 – book not issued by any member
// 3 – successfully returned 
// 4 – book returned and book has a Hold.
// 0 – book returned unsuccessful
Book book = catalog.search(bookId);
if (book == null) { return ( 1 ); }
Member member = book.returnBook();
if (member == null) { return( 2 ); }
if( member.returnBook(book)) { 
if ( book.hasHold() ) { return ( 4 ); }
return( 3 ); 
}
return (0);
 }
 // Book remove process. 
 public int removeBook (String bookId) {
// 1 – invalid book // 2 – hasHold
// 3 – borrowed. // 4 – successfully removed // 5 – unsuccess removed
Book book = catalog.search(bookId);
if (book == null) { return ( 1 ); }
if ( book.hasHold() ) { return ( 2 ); }
Member member = book.getBorrower();
if (member != null) { return( 3 ); }
if(catalog.remove(book)) { return( 4 );}
return ( 5 );
 }
 // Get all borrowed Books by a member process. 
 public ArrayList<Book> getBooks (int memberId){
Member member = memberlist.search(memberId);
ArrayList<Book> issuedBooks = member.getBooksIssued();
return issuedBooks;
 }
 // Book renew process. 
 public Book renewBook (String bookId, int memberId) {
Member member = memberlist.search(memberId);
Book book = catalog.search(bookId);
member.renew(book);
book.renew(member);
return book;
 }
 //Save the Library object in file
public static boolean save() {
 try {
FileOutputStream file = new FileOutputStream("LibraryData");
ObjectOutputStream output = new ObjectOutputStream(file);
output.writeObject(library);
output.writeObject(IdServer);
return true;
 } catch(IOException ioe) {
ioe.printStackTrace();
return false;
 }
}
public static Library retrieve() {
 try {
FileInputStream file = new FileInputStream("LibraryData");
ObjectInputStream input = new ObjectInputStream(file);
if (library == null) {
library = (Library) input.readObject();
} else {
input.readObject();
}
MemberIdServer.retrieve(input);
return library;
 } catch(IOException ioe) {
ioe.printStackTrace();
return null;
 } catch(ClassNotFoundException cnfe) {
cnfe.printStackTrace();
return null;
 }
}
}