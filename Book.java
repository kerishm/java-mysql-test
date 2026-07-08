
import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class Book implements Serializable{
private String title, author, bookId;
private String dueDate;
private Member borrowedby;
private ArrayList<Hold> holds;
public Book (String title, String author, String bId) {
this.title = title;
this.author = author;
this.bookId = bId;
holds = new ArrayList<Hold>();
}
public String getTitle() { return this.title; }
public String getAuthor() { return this. author; }
public String getBookId() { return this.bookId; }
@Override
public String toString() { 
 return "BookTitle: " + this.title + ", Author: " + this.author 
+ ", BookID: " + this.bookId;
}
//Book Checkout or Issue records
public Member getBorrower(){
return this.borrowedby;
}
public String getDueDate(){
return this.dueDate;
}
public boolean issue (Member member) {
this.borrowedby = member;
SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
 Calendar obj = Calendar.getInstance();
obj.add(Calendar.MONTH, 1);
this.dueDate = formatter.format(obj.getTime());
return true;
}
//Book return process
public Member returnBook(){
Member member = this.borrowedby;
this.borrowedby = null; // reinitialize the borrower
this.dueDate = null; // reinitialize the dueDate
return member;
}
// Handling Hold on Book process. 
//Placing holds on Book
public void placeHold(Hold hold) {
if (!holds.contains(hold))
holds.add(hold);
}
//check there is a hold for book
public boolean hasHold() { 
return !this.holds.isEmpty();
}
// Get Hold on Book process. 
public Hold getNextHold() {
for (Hold hold : holds) {
 if (hold.isValid()) return hold;
}
return null;
}
// Hold on Book remove process. 
public boolean removeHold (int memId) { 
boolean removed = false;
Hold foundhold = null;
for (Hold hold: holds){
if( hold.getMember().getMemberID() == memId ){ 
foundhold = hold;
}
}//for
if (foundhold != null) {
holds.remove(foundhold); 
removed = true; 
}
return removed; 
}
// Book renew process. 
public boolean renew (Member member) {
//check there is hashold
if (hasHold()) { //holdexit
return false; }
//if no hold then change dueDate and borrowby
return issue(member);
}
} // end of Book Class