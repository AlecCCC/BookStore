package com.BookStore.BookStore;

import com.BookStore.BookStore.dao.bookStoreDao;
import com.BookStore.BookStore.entity.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
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

					case 5 -> {
						System.exit(0);
					}
					default -> System.out.println("Invalid option, please try again.");
				}

			}

		};

	}

}
