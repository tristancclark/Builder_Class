package ic.doc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import ic.doc.catalogues.BritishLibraryCatalogue;
import ic.doc.catalogues.LibraryCatalogue;
import java.util.List;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class BookSearchQueryTest {


  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  LibraryCatalogue libraryCatalogue = context.mock(LibraryCatalogue.class);

  BookSearchQueryBuilder bookSearchQueryBuilder = BookSearchQueryBuilder.aBookSearchQueryBuilder();


  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

    context.checking(new Expectations() {{
      exactly(1).of(libraryCatalogue).searchFor("LASTNAME='dickens' ");
    }});

    bookSearchQueryBuilder.withLastName("dickens").build().execute(libraryCatalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {

    context.checking(new Expectations() {{
      exactly(1).of(libraryCatalogue).searchFor("FIRSTNAME='Jane' ");
    }});

    bookSearchQueryBuilder.withFirstName("Jane").build().execute(libraryCatalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

    bookSearchQueryBuilder.withTitle("Two Cities");
    List<Book> books = bookSearchQueryBuilder.build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    bookSearchQueryBuilder.withPublishedDateBefore(1700);
    List<Book> books = bookSearchQueryBuilder.build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Shakespeare"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    bookSearchQueryBuilder.withPublishedDateAfter(1950);
    List<Book> books = bookSearchQueryBuilder.build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("Golding"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    bookSearchQueryBuilder.withLastName("dickens").withPublishedDateBefore(1840);
    List<Book> books = bookSearchQueryBuilder.build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(1));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    bookSearchQueryBuilder.withTitle("of").withPublishedDateAfter(1800).withPublishedDateBefore(2000);
    List<Book> books = bookSearchQueryBuilder.build().execute(BritishLibraryCatalogue.getInstance());

    assertThat(books.size(), is(3));
    assertTrue(books.get(0).matchesAuthor("charles dickens"));
  }
}
