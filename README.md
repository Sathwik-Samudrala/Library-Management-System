## 🏛️ Java Library Management System
A robust OOP-based console application for managing books, users, and transactions with persistent data storage.

## ✨ Features

## 📚 Core Operations
Book Management: Add/view books with auto-generated IDs

User System: Register users with 5-book borrowing limit

Transactions: Validate borrow/return operations

Search: Find books/users by ID

## 🛡️ Validation System
Input Checks: Non-empty strings, valid IDs

Transaction Rules:

Prevent over-borrowing (max 5 books/user)

Block duplicate borrows/returns

Error Recovery: Clear messages with retry prompts

## 💾 Data Persistence
Automatic save/load to library_data/ directory

Handles missing files gracefully

Uses Java Serialization for secure storage

## 🖥️ Demonstration 

Add Book	Borrow Book	View All
https://Library-Management-SS-1.png	https://Library-Management-SS-3.png	https://Library-Management-SS-5.png
System Start: Auto-loads previous data or initializes fresh

Transaction Flow:

Borrow: Checks availability + user limits

Return: Validates book was borrowed by user

Error Handling: Rejects invalid IDs/inputs with helpful messages

## 🚀 How to Run

# Compile & Execute
javac *.java && java Main

## Sample Workflow:

text
> Enter choice (1-7): 1  
> BOOK TITLE: Atomic Habits  
> AUTHOR: James Clear  
✅ BOOK ADDED! ID: 8  
## 🏗️ Project Structure
markdown
src/
├── Book.java          # Book entity (immutable ID/title/author)
├── User.java          # User profile + borrowed books
├── Library.java       # Core logic (transactions/search)
├── FileHandler.java   # Serialization manager
├── InputUtil.java     # Robust input validation
└── Main.java          # Menu driver

## 📜 OOP Implementation
Concept	Example
Encapsulation	protected setAvailable() in Book
Abstraction	FileHandler hides file operations
Polymorphism	Custom toString() for display
Inheritance	Serializable interface implemented
## 📝 License
MIT License - Free for academic/portfolio use.

🔗 GitHub Repo: [Your Link Here]
📧 Contact: sathwik.samudrala915@gmail.com
