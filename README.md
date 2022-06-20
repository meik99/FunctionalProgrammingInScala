# Functional Programming In Scala
This repository is used to hold exercises and examples for the course "Functional Programming in Java and Scala" of the JKU in the summer semester of 2022

# Functional programming

* Programming with mathematical functions
* Types and values
* Recursive functions
* Higher-order functions
* Function applications
* Function compositions
    
+ No value store
+ No pointers or references
+ No assignments or memory changes
+ No side effects
    
* Aggregate operations
* Functions as parameters
* Functions compose to more complex functions

+ Higher, declarative level of abstraction
+ Less error-prone
+ More robust
+ Verified more easily
+ Parallel execution
+ Concurrency and distributed

# Scala

* Functional language
* Object oriented programming possible
  * Sometimes used for efficiency reasons
  * Imperative logic usually faster than recursive

## Multiple inheritance with traits

* Linearization to avoid conflicts
* Subtypes before supertypes
* Right more specific than left

Example of slide II. p. 47

+ StudentFriend has `with Friend` as right most trait, therefor it is first
+ Then Male because AnyRef is also used by HasGender and therefore too generic for now
+ Then HasGender
+ Then Student, because Person is used by Student and would therefore be too generic for now
+ Then Professional, Person, AnyRef, and Ref

## Generics

* `extends` or `<:` (happy face) for upper type bound
* `super` or `>:` (angry face) for lower type bound
* It is angry because it is lower. In fact it is _super_ angry

+ Upper bounded generics are co-variant
+ Lower bounded generics are contra-variant

* Co-variant have type-safe returns
* Contra-variant have type-safe inputs
