import java.util.*;
import java.util.stream.Collectors;

/**
 * CORE LIBRARY OPERATIONS CLASS
 * Manages all book and user transactions with proper validation
 */
public class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // ----------- DATA ACCESS METHODS ----------- //
    public List<Book> getBooks() { return books; }
    public List<User> getUsers() { return users; }
    public void setBooks(List<Book> books) { this.books = books; }
    public void setUsers(List<User> users) { this.users = users; }

    // ----------- BOOK MANAGEMENT ----------- //
    /**
     * Adds a new book to the library collection
     * Auto-generates ID and validates input
     */
    public void addBook() {
        int id = generateNewId(books.stream().map(Book::getId).toList());
        String title = InputUtil.getNonEmptyString("BOOK TITLE");
        String author = InputUtil.getNonEmptyString("AUTHOR NAME");
        books.add(new Book(id, title, author));
        System.out.println("BOOK ADDED SUCCESSFULLY! ID: " + id);
    }

    /**
     * Displays all books in formatted view
     * Shows special message if no books available
     */
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("\nNO BOOKS AVAILABLE IN LIBRARY");
            return;
        }
        System.out.println("\n=== ALL BOOKS (" + books.size() + ") ===");
        books.forEach(book -> System.out.println(book));
        System.out.println("============================");
    }

    // ----------- USER MANAGEMENT ----------- //
    /**
     * Registers a new library user
     * Auto-generates ID and validates name
     */
    public void registerUser() {
        int id = generateNewId(users.stream().map(User::getId).toList());
        String name = InputUtil.getNonEmptyString("USER NAME");
        users.add(new User(id, name));
        System.out.println("USER REGISTERED SUCCESSFULLY! ID: " + id);
    }

    /**
     * Displays all registered users
     * Shows special message if no users exist
     */
    public void viewUsers() {
        if (users.isEmpty()) {
            System.out.println("\nNO USERS REGISTERED");
            return;
        }
        System.out.println("\n=== ALL USERS (" + users.size() + ") ===");
        users.forEach(user -> System.out.println(user));
        System.out.println("============================");
    }

    // ----------- TRANSACTION PROCESSING ----------- //
    /**
     * Handles book borrowing with complete validation:
     * 1. Verifies user and book exist
     * 2. Checks book availability
     * 3. Enforces 5-book borrowing limit
     */
    public void borrowBook() {
        if (books.isEmpty()) {
            System.out.println("NO BOOKS AVAILABLE FOR BORROWING");
            return;
        }

        int userId = InputUtil.getIntInput("USER ID");
        int bookId = InputUtil.getIntInput("BOOK ID");

        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (user == null) {
            System.out.println("ERROR: USER ID " + userId + " NOT FOUND");
            printAvailableUsers();
            return;
        }
        
        if (book == null) {
            System.out.println("ERROR: BOOK ID " + bookId + " NOT FOUND");
            printAvailableBooks();
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("ERROR: BOOK \"" + book.getTitle() + "\" IS ALREADY BORROWED");
            return;
        }

        if (!user.canBorrowMore()) {
            System.out.println("ERROR: USER \"" + user.getName() + "\" HAS REACHED MAX LIMIT (5 BOOKS)");
            printUserBorrowedBooks(user);
            return;
        }

        user.borrowBook(bookId);
        book.setAvailable(false);
        System.out.println("SUCCESS: BOOK \"" + book.getTitle() + "\" BORROWED BY \"" + user.getName() + "\"");
    }

    /**
     * Handles book returns with validation:
     * 1. Verifies user and book exist
     * 2. Confirms user borrowed the book
     * 3. Updates both records
     */
    public void returnBook() {
        int userId = InputUtil.getIntInput("USER ID");
        int bookId = InputUtil.getIntInput("BOOK ID TO RETURN");

        User user = findUserById(userId);
        Book book = findBookById(bookId);

        if (user == null || book == null) {
            System.out.println("ERROR: INVALID USER OR BOOK ID");
            return;
        }

        if (!user.getBorrowedBookIds().contains(bookId)) {
            System.out.println("ERROR: USER \"" + user.getName() + "\" DID NOT BORROW BOOK ID " + bookId);
            printUserBorrowedBooks(user);
            return;
        }

        user.returnBook(bookId);
        book.setAvailable(true);
        System.out.println("SUCCESS: BOOK \"" + book.getTitle() + "\" RETURNED BY \"" + user.getName() + "\"");
    }

    // ----------- HELPER METHODS ----------- //
    /**
     * Generates new ID (max existing ID + 1)
     */
    private int generateNewId(List<Integer> existingIds) {
        return existingIds.isEmpty() ? 1 : Collections.max(existingIds) + 1;
    }

    /**
     * Finds user by ID using Stream API
     */
    private User findUserById(int id) {
        return users.stream()
                  .filter(u -> u.getId() == id)
                  .findFirst()
                  .orElse(null);
    }

    /**
     * Finds book by ID using Stream API
     */
    private Book findBookById(int id) {
        return books.stream()
                  .filter(b -> b.getId() == id)
                  .findFirst()
                  .orElse(null);
    }

    /**
     * Displays currently available books
     */
    private void printAvailableBooks() {
        if (books.isEmpty()) {
            System.out.println("NO BOOKS AVAILABLE");
            return;
        }
        System.out.println("AVAILABLE BOOK IDs: " + 
            books.stream()
               .filter(Book::isAvailable)
               .map(b -> String.valueOf(b.getId()))
               .collect(Collectors.joining(", ")));
    }

    /**
     * Displays registered users
     */
    private void printAvailableUsers() {
        if (users.isEmpty()) {
            System.out.println("NO USERS REGISTERED");
            return;
        }
        System.out.println("AVAILABLE USER IDs: " + 
            users.stream()
               .map(u -> String.valueOf(u.getId()))
               .collect(Collectors.joining(", ")));
    }

    /**
     * Shows user's currently borrowed books
     */
    private void printUserBorrowedBooks(User user) {
        System.out.println("CURRENTLY BORROWED: " + 
            user.getBorrowedBookIds().stream()
               .map(bId -> findBookById(bId))
               .filter(Objects::nonNull)
               .map(b -> b.getId() + ":" + b.getTitle())
               .collect(Collectors.joining(", ")));
    }
}