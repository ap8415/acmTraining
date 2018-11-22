package Competitive_Programming_Book.Chapter2.ArrayManipulation_1D;

import java.util.*;
import java.lang.*;

public class UVa_230_Borrowers {

    static class Book implements Comparable<Book> {
        String title;
        String author;

        public Book(String name, String author) {
            this.title = name;
            this.author = author;
        }

        @Override
        public int compareTo(Book o) {
            if (title.compareTo(o.title) == 0) return 0;
            else if (!Objects.equals(author, o.author)) return author.compareTo(o.author);
            return title.compareTo(o.title);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Book> booksInLibrary = new ArrayList<>();

        List<String> booksByTitle = new ArrayList<>();

        while (true) {
            String line = sc.nextLine(); if (line.equals("END")) break;
            String title = line.split("\"")[1];
            String author = line.split("\"")[2].substring(4);
            Book book = new Book(title, author);
            booksInLibrary.add(book);
            booksByTitle.add(title);
        }

        Collections.sort(booksInLibrary); Collections.sort(booksByTitle);

        List<String> returnedBooks = new ArrayList<>();

        while (true) {
            String line = sc.nextLine(); if (line.equals("END")) break;
            String[] splitLine = line.split(" \"");
            switch (splitLine[0]) {
                case "BORROW":
                    String title = splitLine[1].substring(0, splitLine[1].length() - 1);
                    booksByTitle.remove(title);
                    break;
                case "RETURN":
                    String returnedTitle = splitLine[1].substring(0, splitLine[1].length() - 1);
                    returnedBooks.add(returnedTitle);
                    break;
                case "SHELVE":
                    for (String returnedBook : returnedBooks) {
                        int indexToGet = Collections.binarySearch(booksInLibrary, new Book(returnedBook, ""));
                        Book book = booksInLibrary.get(indexToGet);

                    }

            }
        }

    }

}
