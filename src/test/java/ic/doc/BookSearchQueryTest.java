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

    bookSearchQueryBuilder.resetQuery();
    bookSearchQueryBuilder = bookSearchQueryBuilder.withLastName("dickens");
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    bookSearchQueryBuilder.resetQuery();
    bookSearchQueryBuilder = bookSearchQueryBuilder.withFirstName("Jane");
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(2));
    assertTrue(books.get(0).matchesAuthor("Austen"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    bookSearchQueryBuilder.resetQuery();
    bookSearchQueryBuilder = bookSearchQueryBuilder.withTitle("Two Cities");
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    bookSearchQueryBuilder.resetQuery();
    bookSearchQueryBuilder = bookSearchQueryBuilder.withPublishedBeforeDate(1700);
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    bookSearchQueryBuilder.resetQuery();
    bookSearchQueryBuilder = bookSearchQueryBuilder.withPublishedAfterDate(1950);
    List<Book> books = bookSearchQueryBuilder.build().execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    List<Book> books = new BookSearchQuery(null, "dickens", null, null, 1840).execute();

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    List<Book> books = new BookSearchQuery(null, null, "of", 1800, 2000).execute();

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
