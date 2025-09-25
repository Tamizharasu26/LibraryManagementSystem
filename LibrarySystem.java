import java.util.*;

class Book {
    private String bookId;
    private String title;
    private boolean isIssued;

    public Book(String bookId, String title) {
        this.bookId = bookId;
        this.title = title;
        this.isIssued = false;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }
}

class User {
    private String userId;
    private String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}



class UserManager {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user.getName());
    }

    public void showAllUsers() {
        for (User user : users) {
            System.out.println(user.getUserId() + ": " + user.getName());
        }
    }

    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}


class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void showAllBooks() {
        for (Book book : books) {
            System.out.println(book.getBookId() + ": " + book.getTitle() +
                    (book.isIssued() ? " (Issued)" : " (Available)"));
        }
    }

    public Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }
}




public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        UserManager userManager = new UserManager();

        while (true) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Show All Books");
            System.out.println("3. Register User");
            System.out.println("4. Show All Users");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    library.addBook(new Book(bookId, bookTitle));
                    break;
                case 2:
                    library.showAllBooks();
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    String userId = scanner.nextLine();
                    System.out.print("Enter user name: ");
                    String userName = scanner.nextLine();
                    userManager.addUser(new User(userId, userName));
                    break;
                case 4:
                    userManager.showAllUsers();
                    break;
                case 5:
                    System.out.print("Enter book ID to issue: ");
                    String issueId = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    String issueUserId = scanner.nextLine();
                    Book issueBook = library.findBookById(issueId);
                    User issueUser = userManager.findUserById(issueUserId);
                    if (issueBook != null && issueUser != null && !issueBook.isIssued()) {
                        issueBook.issue();
                        System.out.println("Book issued to " + issueUser.getName());
                    } else {
                        System.out.println("Book not available, already issued, or user not found.");
                    }
                    break;
                case 6:
                    System.out.print("Enter book ID to return: ");
                    String returnId = scanner.nextLine();
                    Book returnBook = library.findBookById(returnId);
                    if (returnBook != null && returnBook.isIssued()) {
                        returnBook.returnBook();
                        System.out.println("Book returned.");
                    } else {
                        System.out.println("Book not found or not issued.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting Library Management System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
