import java.util.*;
import java.io.*;
public class Catalog implements Serializable {
private static Catalog catalog;
private ArrayList<Book> books; 
public static Catalog instance(){
 if (catalog == null) {
catalog = new Catalog();
 }
 return catalog;
}
private Catalog(){
 books = new ArrayList<>();
}
public boolean insertBook(Book book) {
 return books.add(book);
}
// Search for Book process. 
public Book search (String bookID) {
for (Book book : books){
 if (book.getBookId().equals(bookID)) 
return book;
} //end for
return null;
}
// Book is removed from Catalog process. 
public boolean remove (Book Rbook) {
boolean bookfound = false;
for (Book book : books){
if (book.getBookId().equals(Rbook.getBookId())) { 
bookfound = true; break;
}
}//end for
if(bookfound) books.remove(Rbook);
return bookfound;
}
}