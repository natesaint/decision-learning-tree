import java.util.ArrayList;

public class Scheme {
  private Attribute function;
  private ArrayList<Attribute> attributes;
  
  // Constructor
  public Scheme (String filename) {
    this.attributes = new ArrayList<Attribute>();
    this.loadSchemeFile(filename);
  }
  
  // Get the index of an attribute
  public int getIndexOfAttribute (Attribute attribute) {
    for (int i = 0; i < this.getNumAttributes(); i++) {
      if (this.getAttributeAt(i).equals(attribute)) {
        return i;
      }
    }
    return -1;
  }
  
  // Load Scheme file
  private void loadSchemeFile (String filename) {
    int numAttr = 0, curr = 0, numFuncVals = 0, currAttr = 0;
    ArrayList<String> list = new ArrayList<String>(UTIL.readFile(filename));
    Attribute attribute = new Attribute("");

    numAttr = Integer.parseInt(list.get(0).trim());

    // Loop through each line
    for (int i = 1; i < list.size(); i++) {
      if (!UTIL.isBlank(list.get(i))) {
        if (curr == 0) { // Name
          attribute = new Attribute(list.get(i).trim());
          currAttr++;
        } else if (curr == 1) { // Number of possible values
          numFuncVals = Integer.parseInt(list.get(i).trim());
        } else if (curr == 2) { // Values
          String[] strs = list.get(i).trim().split("\\s+");
          for (int j = 0; j < numFuncVals; j++) {
            attribute.addValue(strs[j]);
          }
          if (currAttr == numAttr) this.setFunction(attribute);
          else this.addAttribute(attribute);
        }
        curr++;
      } else {
        curr = 0;
      }
    }
  }
  
  // Setter for function
  public void setFunction (Attribute function) {
    this.function = function;
  }
  
  // Getter for function
  public Attribute getFunction () {
    return this.function;
  }
  
  // Add an attribute
  public void addAttribute (Attribute attribute) {
    this.attributes.add(attribute);
  }
  
  // Get an attribute at specified index
  public Attribute getAttributeAt (int i) {
    return this.attributes.get(i);
  }
  
  // Get a list of all the attributes
  public ArrayList<Attribute> getAllAttributes () {
    return this.attributes;
  }
  
  // Get the number of attributes
  public int getNumAttributes () {
    return this.attributes.size();
  }
  
  // Get the number of active attributes
  public int getNumActiveAttributes () {
    int count = 0;
    for (int i = 0; i < this.getNumAttributes(); i++) {
      if (this.getAttributeAt(i).isActive())
        count++;
    }
    return count;
  }
  
  // Equals method
  public boolean equals (Scheme scheme) {
    if (!this.getFunction().equals(scheme.getFunction()))
      return false;

    if (this.getNumAttributes() != scheme.getNumAttributes())
      return false;

    for (int i = 0; i < this.getNumAttributes(); i++) {
      if (!this.getAttributeAt(i).equals(scheme.getAttributeAt(i)))
        return false;
    }
    
    return true;
  }

  // Convert Scheme to a string for printing
  public String toString () {
    String str = "";

    str += "function:\n" + this.getFunction().toString();
    str += "attributes:\n";
    
    for (int i = 0; i < this.getNumAttributes(); i++) {
      str += this.getAttributeAt(i).toString();
    }

    return str;
  }
}