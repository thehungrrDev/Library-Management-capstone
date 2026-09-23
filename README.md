Library Management System

A console-based Java application simulating a library's book catalog and borrowing system, built as a capstone project to consolidate core and intermediate Java concepts — Collections, Generics, Functional Interfaces, Streams, Exception Handling, Optional, and Concurrency — into one working, realistic system.

What It Does
Maintains a catalog of books (id, title, author, total copies, available copies)
Supports adding, finding, borrowing, and deleting books
Prevents duplicate books from being added, using a correctly implemented equals()/hashCode() contract
Persists the catalog to a local file (library.txt) so data survives program restarts
Generates reports (available titles, most in-demand books, total inventory, books grouped by author) using Java Streams
Simulates and resolves a real race condition: multiple threads attempt to borrow the last copy of a book simultaneously, demonstrating both the bug and its fix via synchronized
Concepts Demonstrated
Area	What's used
Collections	HashMap, HashSet, ArrayList — correct equals()/hashCode() for duplicate prevention
OOP	Comparable<T> for natural ordering by title
Generics	Bounded type parameters (<T extends Number>), a generic repository-style method (findFirst)
Functional Interfaces	Predicate, Function, Consumer, Supplier, used directly in stream pipelines
Streams	filter, map, sorted, reduce, collect, groupingBy
Exception Handling	Custom unchecked exceptions (BooksNotFound, NoCopiesAvailable)
Optional	Safe lookups via Optional.ofNullable(...).orElseThrow(...), avoiding null checks
File I/O	try-with-resources for reading/writing the catalog to disk
Concurrency	Thread, Runnable, Thread.join(), and a deliberately reproduced race condition fixed with synchronized
The Race Condition (the interesting part)

Library.borrowBook(int id) is called concurrently by 5 threads attempting to borrow the same book, which has only 1 copy available.

Without synchronized: running this repeatedly shows inconsistent results — sometimes multiple threads incorrectly succeed, and the available copy count can go negative.
With synchronized on borrowBook: exactly one thread succeeds every time, the rest correctly receive a NoCopiesAvailable exception, and the copy count never goes below zero — verified across repeated runs.

This was intentionally built and tested in both states to confirm the difference synchronized makes, rather than just applying it as a known "fix."

Project Structure
src/main/java/com/practice/library/
    KitabKhana.java         — Book entity (id, title, author, copies)
    Library.java            — Core logic: catalog, lookup, borrow, delete, persistence, reporting
    BooksNotFound.java      — Custom exception for missing book lookups
    NoCopiesAvailable.java  — Custom exception for exhausted inventory
pom.xml                     — Maven project configuration
How to Run

Requirements: JDK 17+ and Maven (or an IDE with Maven support, e.g. IntelliJ).

bash
mvn compile

Then run Library.java's main method directly from your IDE, or via Maven exec plugin if configured.

On first run, no library.txt file will exist yet — the program will start with a fresh catalog and save it to library.txt on exit. Subsequent runs will load from that file.

What This Project Is (and Isn't)

This is a learning capstone, not a production system — there's no database, no web layer, and no external API. Its purpose is to demonstrate that the concepts above can work together correctly in one coherent piece of code, as a checkpoint before moving into Spring Boot and real backend development.
