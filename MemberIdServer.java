import java.util.*;
import java.io.*;
class MemberIdServer implements Serializable {
private static MemberIdServer server;
private int idCounter;
private MemberIdServer() {
idCounter = 1;
}
public static MemberIdServer instance() {
if (server == null) {
 return (server = new MemberIdServer());
} else {
 return server;
}
}
public int getId() {
return idCounter++;
}
public static void retrieve(ObjectInputStream input){
 try{
if (server == null){
 server = (MemberIdServer) input.readObject();
} else {
 input.readObject();
}
 } catch(IOException ioe) {
ioe.printStackTrace();
 } catch(ClassNotFoundException cnfe) {
cnfe.printStackTrace();
 } 
}
}