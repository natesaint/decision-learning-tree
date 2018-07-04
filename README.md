# decision-learning-tree

Implementation of a decision learning tree done in Java.

Sources: S. Russell and P. Norvig, Artificial Intelligence: A Modern Approach, (3rd Ed.), Prentice Hall, 2010


## Compile
```
    javac DTLearn.java
```

## Run

### Input files
Sample Scheme and Data files can be found in /assets.

#### Scheme file
Defines information for the columns in the data file

It must follow the following format:
```
<Integer representing number of columns (n)>

<Column name 1>
<Integer representing number of possible values(N)>
<Value 1> <Value 2> <Value 3> <...> <Value N>

...

<Column name n>
<Integer representing number of possible values(N)>
<Value 1> <Value 2> <Value 3> <...> <Value N>
```

#### Data file
Training set for the decision learning tree

It must follow the following format(defined by corresponding Scheme file):
```
<Column name 1> <Column name 2> <Column name 3> <...> <Column name n>
<Value 1.1> <Value 1.2> <Value 1.3> <...> <Value 1.n>
<Value 2.1> <Value 2.2> <Value 2.3> <...> <Value 2.n>
...
<Value k.1> <Value k.2> <Value k.3> <...> <Value k.n>
```

### Run
```
    java DTLearn <Scheme file> <Data file>
```
