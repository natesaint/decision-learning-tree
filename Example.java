import java.util.ArrayList;

public class Example {
  ArrayList<Integer> attributeValues;

  // Constructor
  public Example () {
    attributeValues = new ArrayList<Integer>();
  }

  // Add a value to the list
  public void addValue (int value) {
    attributeValues.add(value);
  }

  // Get the value at an index
  public int getValueAt (int i) {
    return this.attributeValues.get(i);
  }
  
  // Get the func value for example, a.k.a. the value in last index of ArrayList
  public int getFuncValue () {
    return this.attributeValues.get(this.getNumValues() - 1);
  }
  
  // Get the number of values
  public int getNumValues () {
    return this.attributeValues.size();
  }
  
  // Get an ArrayList of all the values
  public ArrayList<Integer> getAllValues () {
    return this.attributeValues;
  }
  
  // Return example as a string
  public String toString () {
    String str = "";
    
    for (int i = 0; i < this.getNumValues(); i++) {
      if (i + 1 == this.getNumValues())
        str += Integer.toString(this.getValueAt(i));
      else
        str += Integer.toString(this.getValueAt(i)) + " ";
    }
    
    str += "\n";
    
    return str;
  }
}