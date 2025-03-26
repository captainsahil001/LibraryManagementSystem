import java.util.*;

// Book class to represent a book object
class Book {
    private String bookID;
    private String title;
    private String author;
    private String genre;
    private String availabilityStatus;

    public Book(String bookID, String title, String author, String genre, String availabilityStatus) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availabilityStatus = availabilityStatus;
    }

    public String getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookID + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Status: " + availabilityStatus;
    }
}

// Library class to manage book operations
class Library {
    private Map<String, Book> books = new HashMap<>();

    public void addBook(Book book) {
        if (books.containsKey(book.getBookID())) {
            System.out.println("Book ID already exists. Cannot add duplicate.");
            return;
        }
        books.put(book.getBookID(), book);
        System.out.println("Book added successfully.");
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        books.values().forEach(System.out::println);
    }

    public void searchBook(String query) {
        for (Book book : books.values()) {
            if (book.getBookID().equalsIgnoreCase(query) || book.getTitle().equalsIgnoreCase(query)) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void updateBook(String bookID, String title, String author, String genre, String availabilityStatus) {
        if (!books.containsKey(bookID)) {
            System.out.println("Book ID not found.");
            return;
        }
        Book book = books.get(bookID);
        if (title != null && !title.isEmpty()) book.setTitle(title);
        if (author != null && !author.isEmpty()) book.setAuthor(author);
        if (genre != null && !genre.isEmpty()) book.setGenre(genre);
        if (availabilityStatus != null && (availabilityStatus.equalsIgnoreCase("Available") || availabilityStatus.equalsIgnoreCase("Checked Out"))) {
            book.setAvailabilityStatus(availabilityStatus);
        }
        System.out.println("Book details updated successfully.");
    }

    public void deleteBook(String bookID) {
        if (books.remove(bookID) != null) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book ID not found.");
        }
    }
}

// Main class to drive the application
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by ID or Title");
            System.out.println("4. Update Book Details");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bookID = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter Availability Status (Available/Checked Out): ");
                    String status = scanner.nextLine();
                    library.addBook(new Book(bookID, title, author, genre, status));
                    break;
                case 2:
                    library.viewAllBooks();
                    break;
                case 3:
                    System.out.print("Enter Book ID or Title: ");
                    String searchQuery = scanner.nextLine();
                    library.searchBook(searchQuery);
                    break;
                case 4:
                    System.out.print("Enter Book ID to update: ");
                    String updateID = scanner.nextLine();
                    System.out.print("Enter new Title (leave blank to skip): ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter new Author (leave blank to skip): ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter new Genre (leave blank to skip): ");
                    String newGenre = scanner.nextLine();
                    System.out.print("Enter new Availability Status (Available/Checked Out, leave blank to skip): ");
                    String newStatus = scanner.nextLine();
                    library.updateBook(updateID, newTitle, newAuthor, newGenre, newStatus);
                    break;
                case 5:
                    System.out.print("Enter Book ID to delete: ");
                    String deleteID = scanner.nextLine();
                    library.deleteBook(deleteID);
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
