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

# Functional programming fundamentals

* Functions have no state and no side effect
* Same input = same output
* Rand takes seed
* Recursion instead of loops

+ Referential transperency
+ The value of an expression is only dependent on the values of the subexpression
+ Advantages
    +  Parallel execution
    +  Lazy evalutation
    +  Testability because of independence
    +  Composability
    +  Memoization, once computed, value can be cached

* Scala optimizes tail recursive functions
* Constant stack space
* The result of the recursive call is the result of the call, therefore, the previous stack is not needed

+ lambdas are literals for creating function objects
+ consist of arguments and function body
+ Type of function can be any interface with a single abstrac method
    + SAM
    + SAM can be functional interfaces
+ Scala can have up to 22 parameters

* Closure is a value storing a function with an environment
* Functions can contain values of free variables (Java)
    * Only final variables allowed
* Functions can have access to storage location (Scala)
    * Either immutable outside of closure and mutable inside = pure closure
    * Or captured on heap and mutable = impure closure

+ Higher order functions take functions as parameters or return functions
+ Java -> use site variance
    + Variance defined in parameter
+ Scala -> no use site variance needeed
    + declaration-site variance used

# Functional data structures

* Immutable
* Algebraic data types
    * allows pattern matching
* Similar properties to value types
    * Operations create new data objects

## Algebraic data types

* Tagged records
* Variants of records / unions
* Immutable
* Tags identify value variants
* No subtyping

__In Scala__
* Case classes implement ADTs with classes and inheritance
* Class name is tag
* Class parameters are fields
* Abstract base type has case classes as subtypes => variants
* Class parameters are public
* equal, hashCode, toString based on class parameters
* no subtypes of case classes
* Enums are short way of defining ADTs

__In Java__
* basically the same but called records

## Pattern matching

* Checking for type
* testing pattern for equality
* binding pattern to bind variables to values

## Basic ADTS

* Tuples
    * Are mathematical tuples
* Option
    * Can have Some value
    * Can have None value
    * Checked using pattern matching

## Functional collections

* List, Set, Maps are immutable
* Operations create new values

## Persistend data structures

* Minimal copying, maximal reuse
* New value reuses old values
* Easy to use, reliable and thread safe
* Causes memory and run time overhead
* Slides IV, p.47, p.50, p.51, p.52

# Functional exception handling

* Option
* Try
    * Success with value
    * Failure with exception
* Optional (Java)

+ Flatmap to chain optionals

* Parameters with call-by-name
* Passed unevaluated
* No function objects

# Functional composition

* Function composition operators take functions as arguments and return functions as result
* Create more complex functions from simpler functions
* Creation of programs by programs
* combine => this(g(x))
* andThen => g(this(x))

# Monoids

* Monoid = (M, +, e)
* M = Set of elements
* + = binary operations
* e = identity element

+ reduction from left: ((((e + a) + b) + c) + d)
+ reduction from right: (a + (b + (c + (d + e))))
+ Parallel reduction: (e + a + b) + (e + c + d)

# Context parameters

* Slide VII, p.8

# Patterns of composition

* Similar or equal patterns of composition
* Functors
* Monads
* Applicatives
* Arrows

