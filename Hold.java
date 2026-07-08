import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class Hold implements Serializable {
private Book book;
private Member member;
private String expiredate;
public Hold(Member member, Book book, int duration) {
this.book = book;
this.member = member;
this.expiredate = getExpiredate(duration);
}
public Member getMember() { return member; }
public Book getBook() { return book; }
public String getDate() { return expiredate; }
public boolean isValid() {
 try{
Date cdate = new Date(); 
SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
Date exdate = formatter.parse(this.expiredate); 
return (cdate.before(exdate));
 } catch(ParseException ex) { ex.printStackTrace(); return 
false;}
}
private String getExpiredate(int duration){
 Calendar obj = Calendar.getInstance();
obj.add(Calendar.DATE, duration);
SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
return formatter.format(obj.getTime());
}
}