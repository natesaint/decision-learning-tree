import java.util.ArrayList;

public class Node {
  String label;
  String linkLabel;
  Node parent;
  ArrayList<Node> children;
  
  // Constructor
  public Node (String label) {
    this.label = label;
    this.parent = null;
    this.children = new ArrayList<Node>();
    this.linkLabel = "";
  }
  
  // Print tree from this node onwards using simple method
  public void printSimple (int level) {
    if (level == 0) {
      System.out.println(this.getLabel());
    }
    
    for (int i = this.getNumChildren() - 1; i >= 0; i--) {
      printBlank(level);
      System.out.println("|");
      printBlank(level);
      System.out.println("|");
      printBlank(level);
      System.out.println("|");
      printBlank(level);
      System.out.println("o---" + this.getChildAt(i).getLinkLabel() + "-->  " + this.getChildAt(i).getLabel());
      this.getChildAt(i).printSimple(level + 6 + this.getMinLength() + 3);
    }
  }
  
  // Get the minimum link label length of children
  public int getMinLength () {
    int length = 999999;
    for (int i = 0; i < this.getNumChildren(); i++) {
      if (this.getChildAt(i).getLinkLabel().length() < length)
        length = this.getChildAt(i).getLinkLabel().length();
    }
    return length;
  }
  
  // Print blank spaces
  public void printBlank (int level) {
    for (int i = 0; i < level; i++) {
      if (i == 0) System.out.print("|");
      else System.out.print(" ");
    }
  }
  
  // Calculate the number of nodes from this point on
  public int getNumNodesInTree () {
    int count = 0;
    for (int i = 0; i < this.getNumChildren(); i++) {
      count += 1;
      count += this.getChildAt(i).getNumNodesInTree();
    }
    return count;
  }
  
  // Add a child node
  public void addChild (Node node) {
    this.children.add(node);
  }
  
  // Get the number of children
  public int getNumChildren () {
    return this.children.size();
  }
  
  // Get the child node at specified index
  public Node getChildAt (int i) {
    return this.children.get(i);
  }
  
  // Set the value of label
  public void setLabel (String label) {
    this.label = label;
  }
  
  // Get the value of label
  public String getLabel () {
    return this.label;
  }
  
  // Set the value of link label
  public void setLinkLabel (String label) {
    this.linkLabel = label;
  }
  
  // Get the value of link label
  public String getLinkLabel () {
    return this.linkLabel;
  }
  
  // Set the parent
  public void setParent (Node node) {
    this.parent = node;
  }
  
  // Get the parent
  public Node getParent () {
    return this.parent;
  }
}