import java.util.ArrayList;

public class DTLearn {
  // Main method
  public static void main (String [] args) {
    Node tree;

    // Check arguments
    if (!UTIL.checkArgs(2, args)) System.exit(0);
    
    // Instantiate scheme object
    Scheme attrib = new Scheme(args[0]);
    //System.out.println(attrib.toString());

    // Instantiate sample object
    Sample sample = new Sample();
    sample.readSampleFile(args[1], attrib);
    //System.out.println(sample.toString());
    
    // Create decision tree and output into Node
    System.out.println("Learning starts:");
    tree = learnDecisionTree(sample, attrib, "");
    
    // Echo the tree
    System.out.println("\nDecision Tree (" + Integer.toString(tree.getNumNodesInTree() + 1) + " nodes):");
    tree.printSimple(0);
  }
  
  // Learn decision tree method
  public static Node learnDecisionTree (Sample g, Scheme attrib, String majority) {
    Node node = null;
    Node tr = null;
    
    // If g is empty, return a node labelles majority
    if (g.getNumExamples() == 0) {
      node = new Node(majority);
      return node;
    }
    
    // If all examples in g have class q, return a Node labelled q
    int result = g.hasAllSameFunc();
    if (result != -1) {
      node = new Node(attrib.getFunction().getValueAt(result));
      return node;
    }
    
    // If attrib is empty, return a node with the majority value of g
    if (attrib.getNumActiveAttributes() == 0) {
      node = new Node(attrib.getFunction().getValueAt(g.majorityValue()));
      return node;
    }
    
    // Choose attribute of best fit
    Attribute b = g.getAttribute(attrib);
    int attribIndex = attrib.getIndexOfAttribute(b);
    System.out.println("             Select attribute " + b.getName());
    
    // Create node (tr) with the chosen attribute as the root
    tr = new Node(b.getName());
    
    
    String m = attrib.getFunction().getValueAt(g.majorityValue());
    // Loop through each possible value of the chosen attribute
    for (int i = 0; i < b.getNumValues(); i++) {
      // Examples in g with b = v
      Sample subg = new Sample();
      for (int j = 0; j < g.getNumExamples(); j++) {
        if (g.getExampleAt(j).getValueAt(attribIndex) == i) {
          subg.addExample(g.getExampleAt(j));
        }
      }
      
      // Recursively call learn decision tree
      attrib.getAttributeAt(attribIndex).setActive(false);
      Node subtr = learnDecisionTree(subg, attrib, m);
      
      // Link node b to the root of subtr and label link by v
      subtr.setParent(tr);
      subtr.setLinkLabel(attrib.getAttributeAt(attribIndex).getValueAt(i));
      tr.addChild(subtr);
    }
    
    return tr;
  }
}