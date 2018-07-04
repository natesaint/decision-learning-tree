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
NOTE: The program will catch if the Data file is not consistent with the Scheme file. The program will assume the Scheme file is correct.

### Run
```
    java DTLearn <Scheme file> <Data file>
```

## Example Usage
```
\decision-learning-tree> javac DTLearn.java
\decision-learning-tree> java DTLearn assets/Loan_sche.txt assets/Loan.txt

Learning starts:
   Test income: gain = 0.9663236712849368
   Test collateral: gain = 0.20604950908542197
   Test debt: gain = 0.06289889437401786
   Test creditHis: gain = 0.26565428452375395
             Select attribute income
   Test collateral: gain = 0.0
   Test debt: gain = 0.31127812445913283
   Test creditHis: gain = 0.5
             Select attribute creditHis
   Test collateral: gain = 0.0
   Test debt: gain = 1.0
             Select attribute debt
   Test collateral: gain = 0.19087450462110933
             Select attribute collateral

Decision Tree (11 nodes):
income
|
|
|
o--->$35K-->  collateral
|             |
|             |
|             |
|             o---NONE-->  LOW
|             |
|             |
|             |
|             o---ADEQUATE-->  LOW
|
|
|
o---$15-35K-->  creditHis
|             |
|             |
|             |
|             o---UNKNOWN-->  debt
|                         |
|                         |
|                         |
|                         o---LOW-->  MODERATE
|                         |
|                         |
|                         |
|                         o---HIGH-->  HIGH
|             |
|             |
|             |
|             o---GOOD-->  MODERATE
|             |
|             |
|             |
|             o---BAD-->  HIGH
|
|
|
o---<$15K-->  HIGH

```
