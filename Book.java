import java.io.Serializable;

/**
 * Represents a book in the library system
 * Implements Serializable for file storage
 */
public class Book implements Serializable {
    private final int id;       // Immutable unique identifier
    private final String title; // Immutable title
    private final String author; // Immutable author
    private boolean isAvailable; // Mutable availability status

    /**
     * Creates a new book with validation
     * @throws IllegalArgumentException if title/author is empty
     */
    public Book(int id, String title, String author) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("TITLE CANNOT BE EMPTY");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("AUTHOR CANNOT BE EMPTY");
        }
        
        this.id = id;
        this.title = title.trim();
        this.author = author.trim();
        this.isAvailable = true; // New books are available by default
    }

    // ----------- ACCESSOR METHODS ----------- //
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }
    
    /**
     * Sets availability status
     * Protected to only allow modification through Library class
     */
    protected void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    /**
     * Custom string representation for console output
     * Formats: ID | TITLE | AUTHOR | STATUS
     */
    @Override
    public String toString() {
        return String.format("ID: %03d | TITLE: %-30s | AUTHOR: %-20s | STATUS: %s",
            id, 
            title.toUpperCase(), 
            author.toUpperCase(), 
            isAvailable ? "AVAILABLE" : "BORROWED");
    }
}