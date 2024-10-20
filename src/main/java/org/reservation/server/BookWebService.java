package org.reservation.server;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.reservation.models.Book;

import java.util.ArrayList;
import java.util.List;

@WebService(name = "Books")
public class BookWebService {
    public static ArrayList<Book> booksList = new ArrayList<>();

    @WebMethod(operationName = "getBook")
    public Book getBook(@WebParam(name = "Book") Integer id) {
        return booksList.stream().filter((b) -> b.getId() == id).findFirst().get();
    }

    @WebMethod
    public List<Book> getAllBooks() {
//        this.booksList.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald", "1925"));
        return this.booksList;
    }

    // create method to add a book
    @WebMethod
    public Book addBook(@WebParam(name = "Book") Book book) {
        this.booksList.add(book);
        return book;
    }

    // update book
    @WebMethod
    public Book updateBook(@WebParam(name = "Book") Book book) {
        Book bookToUpdate = this.booksList.stream().filter((b) -> b.getId() == book.getId()).findFirst().get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setPublished(book.getPublished());
        return bookToUpdate;
    }

    // delete book
    @WebMethod
    public boolean deleteBook(@WebParam(name = "Book") Integer id) {
        return this.booksList.removeIf((b) -> b.getId() == id);
    }
}
