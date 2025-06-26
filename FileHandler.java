import java.io.*;
import java.util.List;

/**
 * Handles saving and loading library data using serialization
 * Manages all file operations with error handling
 */
public class FileHandler {
    private static final String DATA_DIR = "library_data";
    private static final String BOOKS_FILE = DATA_DIR + "/books.ser";
    private static final String USERS_FILE = DATA_DIR + "/users.ser";

    /**
     * Saves current library state to disk
     * Creates data directory if missing
     */
    public static void saveData(Library library) {
        createDataDirectory();
        
        try {
            saveCollection(BOOKS_FILE, library.getBooks());
            saveCollection(USERS_FILE, library.getUsers());
            System.out.println("DATA SAVED SUCCESSFULLY");
        } catch (IOException e) {
            System.out.println("ERROR SAVING DATA: " + e.getMessage());
        }
    }

    /**
     * Loads library data from disk
     * Silently handles missing files
     */
    @SuppressWarnings("unchecked")
    public static void loadData(Library library) {
        try {
            List<Book> books = (List<Book>) loadCollection(BOOKS_FILE);
            List<User> users = (List<User>) loadCollection(USERS_FILE);
            library.setBooks(books);
            library.setUsers(users);
        } catch (Exception e) {
            System.out.println("NO PREVIOUS DATA FOUND OR ERROR LOADING");
        }
    }

    // ----------- HELPER METHODS ----------- //
    private static void createDataDirectory() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    /**
     * Generic method to save any serializable collection
     */
    private static <T> void saveCollection(String filename, List<T> collection) 
        throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream(filename))) {
            oos.writeObject(collection);
        }
    }

    /**
     * Generic method to load any serializable collection
     */
    private static Object loadCollection(String filename) 
        throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream(filename))) {
            return ois.readObject();
        }
    }
}