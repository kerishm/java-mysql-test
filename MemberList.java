import java.util.*;
import java.io.*;
public class MemberList implements Serializable {
private static MemberList memberlist;
private ArrayList<Member> memlst;
private MemberList(){
 memlst = new ArrayList<>();
}
public static MemberList instance(){
 if (memberlist == null){
memberlist = new MemberList();
 }
 return memberlist;
}
public boolean insertMember (Member member) {
 return memlst.add( member ); 
}
// public boolean remove (Member member) {
// ….. }
public Member search (int memberID) {
 for (Member member : memlst){
if (member.getMemberID() == memberID) 
return member;
 }//for end
 return null;
}
} 