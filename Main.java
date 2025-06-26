/**
 * Main class to run the Library Management System
 * Handles menu display and routing to library operations
 */
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        FileHandler.loadData(library); // Load existing data

        while (true) {
            // Display menu
            System.out.println("\n======== LIBRARY MANAGEMENT SYSTEM ========");
            System.out.println("| 1. ADD NEW BOOK     || 2. REGISTER USER  |");
            System.out.println("| 3. BORROW A BOOK    || 4. RETURN A BOOK  |");
            System.out.println("| 5. VIEW ALL BOOKS   || 6. VIEW ALL USERS |");
            System.out.println("==================  7. EXIT  ===============");
            
            // Get validated user input
            System.out.print("\nENTER YOUR CHOICE (1-7): ");
            int choice = InputUtil.getIntInRange(1, 7);

            // Route to appropriate operation
            switch (choice) {
                case 1 -> library.addBook();
                case 2 -> library.registerUser();
                case 3 -> library.borrowBook();
                case 4 -> library.returnBook();
                case 5 -> library.viewBooks();
                case 6 -> library.viewUsers();
                case 7 -> {
                    FileHandler.saveData(library);
                    System.out.println("\nTHANK YOU FOR USING THE LIBRARY SYSTEM!");
                    System.exit(0);
                }
            }
        }
    }
}