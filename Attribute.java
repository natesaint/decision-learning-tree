import java.util.ArrayList;

public class Attribute {
  private String name;
  private ArrayList<String> values;
  private boolean active;
  
  // Constructor
  public Attribute (String name) {
    this.name = name;
    this.values = new ArrayList<String>();
    this.active = true;
  }
  
  // Setter for active, represents whether Attribute "exists"
  public void setActive (boolean b) {
    this.active = b;
  }
  
  // Get active value (whether attribute is active)
  public boolean isActive () {
    return this.active;
  }
  
  // Setter for name
  public void setName (String name) {
    this.name = name;
  }

  // Getter for name
  public String getName () {
    return this.name;
  }

  // Add a value to ArrayList of values
  public void addValue (String value) {
    this.values.add(value);
  }

  // Get a value at an index form ArrayList of values
  public String getValueAt (int i) {
    return this.values.get(i);
  }

  // Set all values in ArrayList of values
  public void setAllValues (ArrayList<String> values) {
    this.values = new ArrayList<String>(values);
  }

  // Get all values from ArrayList of values
  public ArrayList<String> getAllValues () {
    return this.values;
  }
  
  // Get the number of values in ArrayList
  public int getNumValues () {
    return this.values.size();
  }
  
  // Get the index of a value in ArrayList of values
  public int getIndexOfValue (String value) {
    for (int i = 0; i < this.getNumValues(); i++) {
      if (this.getValueAt(i).equals(value))
        return i;
    }
    return -1;
  }
  
  // Equals method
  public boolean equals (Attribute attribute) {
    if (!this.getName().equals(attribute.getName()))
      return false;
    
    if (this.getNumValues() != attribute.getNumValues())
      return false;
    
    for (int i = 0; i < this.getNumValues(); i++) {
      if (!this.getValueAt(i).equals(attribute.getValueAt(i))) {
        return false;
      }
    }
    
    return true;
  }
  
  // Convert attribute to a String
  public String toString () {
    String str = "";
    
    str += "name: " + this.getName() + "\npossible values:";
    
    for (int i = 0; i < this.getNumValues(); i++) {
      str += this.getValueAt(i) + ",";
    }
    str += "\n\n";
    
    return str;
  }
}