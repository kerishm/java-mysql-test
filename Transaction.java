import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
public class Transaction implements Serializable {
private String type; 
private String title; 
private String date;
public Transaction (String type, String title) {
this.type = type; 
this.title = title;
date = getCurrentDate(); // MM/dd/yyyy
}
public boolean onDate(String sdate) {
return (this.date.equals(sdate));
}
public String getType() { return this.type; }
public String getTitle() { return this.title; }
public String getDate() { return this.date; }
public String toString(){ 
return ("Transaction Type: " + type + ", Title: " + title + ",IssuedDate : " + date); 
}
private String getCurrentDate(){
SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
 Calendar obj = Calendar.getInstance();
return formatter.format(obj.getTime());
}
}