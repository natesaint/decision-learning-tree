import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class UTIL {
  // Return an ArrayList of Strings representing each line of file
  public static ArrayList<String> readFile (String filename) {
    ArrayList<String> list = new ArrayList<String>();
    try {
      File file = new File(filename);
      Scanner s = new Scanner(file);
      
      while (s.hasNextLine()) {
        String line = s.nextLine();
        list.add(line);
      }
      
      s.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(0);
    }
    return list;
  }
  
  // Print contents of an ArrayList of Strings
  public static void printStringList (ArrayList<String> list) {
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
  }
  
  // Check number of arguments in args
  public static boolean checkArgs (int n, String [] args) {
    if (args.length == n)
      return true;
    else {
      System.out.println("invalid number of arguments");
      return false;
    }
  }

  // Check if a string is made of whitespaces and newlines
  public static boolean isBlank (String str) {
    if (str.length() == 0) return true;
    for (int i = 0; i < str.length(); i++) {
      if (!(str.charAt(i) == ' ' || str.charAt(i) == '\n' || str.charAt(i) == '\0' || str.charAt(i) == '\r' || str.charAt(i) == '\t'))
        return false;
    }
    return true;
  }
}