package com.BookStore.BookStore;

import com.BookStore.BookStore.dao.bookStoreDao;
import com.BookStore.BookStore.entity.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BookStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (bookStoreDao bookstoreDao) {

		return runner -> {
			Scanner scanner = new Scanner(System.in);
			while (true) {

				System.out.println("Welcome to the bookstore, what do you want to do?");
				int input = scanner.nextInt();
				scanner.nextLine();

				switch (input) {
					case 1 -> {
						System.out.println("Enter the Author, Title, Price, and Quantity separated with ,");
						String line = scanner.nextLine();
						String[] parts = line.trim().split("\\s*,\\s*");

						String author = parts[0];
						String title = parts[1];
						double price = Double.parseDouble(parts[2].trim());
						int qty = Integer.parseInt(parts[3].trim());

						System.out.printf("Author: %s, Title: %s, Price: %.2f, Quantity: %d \n", author, title, price, qty);

						Book tempBook = new Book(author, title, price, qty);
						bookstoreDao.createBook(tempBook);
					}

					case 2 -> {
						System.out.println("Grab book by ID");
						int id = scanner.nextInt();
						findbyID(id, bookstoreDao);
					}

					case 3 -> {
						System.out.println("Grabbing All Books");
						findAllBooks(bookstoreDao);
					}

					case 4 -> {
						System.out.println("What book do you want to update (Price Only)?  Enter ID:");
						findAllBooks(bookstoreDao);
						int bookId = scanner.nextInt();

						Book tempbook = bookstoreDao.findById(bookId);
						System.out.print("Enter the new price: ");

						double price = scanner.nextDouble();

						tempbook.setBookPrice(price);

						bookstoreDao.updateBook(tempbook);

					}

					case 5 -> {

						System.out.println("Enter The Author:");

						String author = scanner.nextLine();

						List<Book> books = bookstoreDao.findByAuthor(author);

						for (Book book : books) {
							System.out.println(book);
						}

					}

					case 6 -> {

						findAllBooks(bookstoreDao);
						System.out.println("Enter the ID of the book you want to delete");

						int bookId = scanner.nextInt();

						Book tempBook = bookstoreDao.findById(bookId);
						bookId = tempBook.getId();

						System.out.println("Deleting Book: " + tempBook.getBookTitle() + " by " + tempBook.getAuthorName() );

						bookstoreDao.deleteBook(bookId);

					}

					case 7 -> {
						System.exit(0);
					}
					default -> System.out.println("Invalid option, please try again.");
				}

			}

		};

	}

	private void findbyID(int id, bookStoreDao bookstoredao) {

		Book tempBook = bookstoredao.findById(id);

		if (tempBook != null) {
			System.out.println("Found Book with id of " + id);
			System.out.println(tempBook.getBookTitle());
		} else {
			System.out.println("No book with Id " + id + " found");
		}

	}

	private void findAllBooks(bookStoreDao bookstoreDao) {
		List<Book> books = bookstoreDao.findAllBooks();

		for (Book tempbook: books){
			System.out.println(tempbook);
		}

	}

}
