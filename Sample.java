import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;

public class Sample {
  ArrayList<Example> exampleSet;

  // Constructor
  public Sample () {
   exampleSet = new ArrayList<Example>();
  }
  
  // Add an example to ArrayList
  public void addExample (Example example) {
    this.exampleSet.add(example);
  }
  
  // Get example at an index
  public Example getExampleAt (int i) {
    return this.exampleSet.get(i);
  }
  
  // Get an ArrayList of examples
  public ArrayList<Example> getAllExamples () {
    return this.exampleSet;
  }
  
  // Get the number of examples
  public int getNumExamples () {
    return this.exampleSet.size();
  }
  
  // Select the best suited attribute
  public Attribute getAttribute (Scheme attrib) {
    Attribute best = null;
    int k;
    double I, maxGain = -1;
    
    k = attrib.getFunction().getNumValues();
    I = this.infoFmGp(k);
    
    //System.out.println(I);
    
    for (int i = 0; i < attrib.getNumAttributes(); i++) {
      if (attrib.getAttributeAt(i).isActive()) {
        double remainder = this.getRemainder(attrib.getAttributeAt(i), k, attrib);
        //System.out.print("REMAINDER:");
        //System.out.println(remainder);
        double gain = I - remainder;
        
        // Echo information
        System.out.println("   Test " + attrib.getAttributeAt(i).getName() + ": gain = " + Double.toString(gain));
        
        if (gain > maxGain) {
          maxGain = gain;
          best = attrib.getAttributeAt(i);
        }
      }
    }
    
    return best;
  }
  
  // Compute the remainder value
  public double getRemainder (Attribute b, int k, Scheme scheme) {
    int size, m;
    double remainder = 0.0;
    
    // # of examples in g
    size = this.getNumExamples();
    // # of possible values of Attribute, b
    m = b.getNumValues();
    
    // Split g by each value of b into subg[1..m]
    ArrayList<Sample> subg = new ArrayList<Sample>();
    // Get the index of the attribute in Example
    int attribIndex = scheme.getIndexOfAttribute(b);
    for (int i = 0; i < m; i++) {
      Sample temp = new Sample();
      for (int j = 0; j < this.getNumExamples(); j++) {
        if (this.getExampleAt(j).getValueAt(attribIndex) == i) {
          temp.addExample(this.getExampleAt(j));
        }
      }
      subg.add(temp);
    }
    
    // Create subcnt[1..m]
    ArrayList<Integer> subcnt = new ArrayList<Integer>();
    for (int i = 0; i < m; i++) {
      subcnt.add(0);
    }
    
    // Count # of examples at each subg[i]
    for (int i = 0; i < m; i++) {
      subcnt.set(i, subg.get(i).getNumExamples());
    }
    
    for (int i = 0; i < m; i++) {
      double prob = (double)subcnt.get(i)/(double)size;
      double I = subg.get(i).infoFmGp(k);
      remainder = remainder + (prob * I);
    }
    
    return remainder;
  }
  
  // Calculate I
  public double infoFmGp (int k) {
    int size;
    ArrayList<Integer> count = new ArrayList<Integer>();
    double I;
    
    size = this.getNumExamples();
    
    // Create a k element ArrayList<Integer>
    for (int i = 0; i < k; i++) {
      count.add(0);
    }
    
    for (int i = 0; i < k; i++) {
      // # of examples in g with function value v(i)
      count.set(i, this.countNumExamplesWithV(i));
    }

    I = 0.0;
    
    for (int i = 0; i < k; i++) {
      if (count.get(i) != 0) {
        double prob = (double)count.get(i)/(double)size;
        I = I - (prob * (Math.log(prob)/Math.log(2)));
      } else {
        double prob = (double)count.get(i)/(double)size;
        I = I - 0.0;
      }
    }
    
    return I;
  }
  
  // Count the number of examples with function value v
  public int countNumExamplesWithV (int v) {
    int count = 0;
    
    for (int i = 0; i < this.getNumExamples(); i++) {
      if (this.getExampleAt(i).getFuncValue() == v)
        count++;
    }
    
    return count;
  }
  
  // Compute and return the majority value
  public int majorityValue () {
    ArrayList<Integer> list = new ArrayList<Integer>();
    int max = -1, maxCount = -1;
    
    // Add them all to list
    for (int i = 0; i < this.getNumExamples(); i++) {
      list.add(this.getExampleAt(i).getFuncValue());
    }
    
    // Find greatest frequency
    for (int i = 0; i < this.getNumExamples(); i++) {
      int x = Collections.frequency(list, this.getExampleAt(i).getFuncValue());
      if (x > maxCount) {
        maxCount = x;
        max = this.getExampleAt(i).getFuncValue();
      }
    }
    
    return max;
  }
  
  // Return a String if the Sample has all func values for example the same, else return null
  public int hasAllSameFunc () {
    int prev = -1;
    
    for (int i = 0; i < this.getNumExamples(); i++) {
      if (prev != -1) {
        if (this.getExampleAt(i).getFuncValue() != prev)
          return -1;
      }
      prev = this.getExampleAt(i).getFuncValue();
    }

    return prev;
  }

  // Read sample file, compare with the scheme to ensure ordering is correct
  public void readSampleFile (String filename, Scheme scheme) {
    ArrayList<String> list = new ArrayList<String>(UTIL.readFile(filename));
    String [] separated;
    
    // Get list of different attributes
    separated = list.get(0).trim().split("\\s+");
    
    // Check amount of attributes
    if (separated.length != scheme.getNumAttributes() + 1) {
      System.out.println("Error with datafile, incorrect number of attributes based on scheme file.");
      System.exit(0);
    }
    
    // Check order and names of attributes
    for (int i = 0; i < separated.length - 1; i++) {
      if (!separated[i].equals(scheme.getAttributeAt(i).getName())) {
        System.out.println("Error with datafile: '" + separated[i] + "' is not recognized or out of order based on scheme file.");
        System.exit(0);
      }
    }

    // Check function name
    if (!separated[separated.length - 1].equals(scheme.getFunction().getName())) {
        System.out.println("Error with datafile: '" + separated[separated.length - 1] + "' is not recognized or out of order based on scheme file.");
        System.exit(0);
    }
    
    // Loop through each string
    for (int i = 1; i < list.size(); i++) {
      // Skip over blank lines
      if (!UTIL.isBlank(list.get(i))) {
        Example example = new Example();
        separated = list.get(i).trim().split("\\s+");
        
        // Check length of separated
        if (separated.length != scheme.getNumAttributes() + 1) {
          System.out.println("Error with datafile at line " + Integer.toString(i + 1) + ", incorrect number of attributes based on scheme file.");
          System.exit(0);
        }
        
        // Loop through each value and add it to Example
        for (int j = 0; j < separated.length; j++) {
          int index;
          if (j + 1 == separated.length) {
            index = scheme.getFunction().getIndexOfValue(separated[j]);
            if (index == -1) {
              System.out.println("Error with datafile at line " + Integer.toString(i + 1) + ", value does not exist in scheme file: " + separated[j]);
              System.exit(0);
            }
            example.addValue(index);
          } else {
            index = scheme.getAttributeAt(j).getIndexOfValue(separated[j]);
            if (index == -1) {
              System.out.println("Error with datafile at line " + Integer.toString(i + 1) + ", value does not exist in scheme file: " + separated[j]);
              System.exit(0);
            }
            example.addValue(index);
          }
        }
        this.addExample(example);
      }
    }
  }
  
  // Convert Sample to a String
  public String toString () {
    String str = "";
    
    for (int i = 0; i < this.getNumExamples(); i++) {
      str += this.getExampleAt(i).toString();
    }
    
    return str;
  }
}