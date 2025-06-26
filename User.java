import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a library user and their borrowed books
 * Implements Serializable for file storage
 */
public class User implements Serializable {
    private final int id;       // Immutable user ID
    private final String name;  // Immutable user name
    private final List<Integer> borrowedBookIds; // Tracks borrowed book IDs
    private static final int MAX_BORROW_LIMIT = 5; // System-wide borrowing limit

    /**
     * Creates a new user with validation
     * @throws IllegalArgumentException if name is empty
     */
    public User(int id, String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("USER NAME CANNOT BE EMPTY");
        }
        
        this.id = id;
        this.name = name.trim();
        this.borrowedBookIds = new ArrayList<>();
    }

    // ----------- CORE FUNCTIONALITY ----------- //
    /**
     * Checks if user can borrow more books
     * @return true if under MAX_BORROW_LIMIT
     */
    public boolean canBorrowMore() {
        return borrowedBookIds.size() < MAX_BORROW_LIMIT;
    }

    /**
     * Adds a book to user's borrowed list
     * @param bookId The ID of book to borrow
     */
    public void borrowBook(int bookId) {
        borrowedBookIds.add(bookId);
    }

    /**
     * Removes a book from user's borrowed list
     * @param bookId The ID of book to return
     */
    public void returnBook(int bookId) {
        borrowedBookIds.remove(Integer.valueOf(bookId));
    }

    // ----------- ACCESSOR METHODS ----------- //
    public int getId() { return id; }
    public String getName() { return name; }
    
    /**
     * @return Unmodifiable list of borrowed book IDs
     */
    public List<Integer> getBorrowedBookIds() {
        return Collections.unmodifiableList(borrowedBookIds);
    }

    /**
     * Custom string representation for console output
     * Formats: ID | NAME | BORROWED: X/5
     */
    @Override
    public String toString() {
        return String.format("ID: %03d | NAME: %-20s | BORROWED: %d/%d",
            id, 
            name.toUpperCase(),
            borrowedBookIds.size(),
            MAX_BORROW_LIMIT);
    }
}