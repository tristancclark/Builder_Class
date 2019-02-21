package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;

public class BookSearchQueryTest {

  BookSearchQueryBuilder bookSearchQueryBuilder = BookSearchQueryBuilder.aBookSearchQueryBuilder();

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    bookSearchQueryBuilder.withLastName("dickens");
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    bookSearchQueryBuilder.withFirstName("Jane");;
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    bookSearchQueryBuilder.withTitle("Two Cities");
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    bookSearchQueryBuilder.withPublishedDateBefore(1700);
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    bookSearchQueryBuilder.withPublishedDateAfter(1950);
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    bookSearchQueryBuilder.withLastName("dickens").withPublishedDateBefore(1840);
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    bookSearchQueryBuilder.withTitle("of").withPublishedDateAfter(1800).withPublishedDateBefore(2000);
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
