package ic.doc;

import ic.doc.catalogues.LibraryCatalogue;
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

    context.checking(new Expectations() {{
      exactly(1).of(libraryCatalogue).searchFor("TITLECONTAINS(Two Cities) ");
    }});

    bookSearchQueryBuilder.withTitle("Two Cities").build().execute(libraryCatalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {

    context.checking(new Expectations() {{
      exactly(1).of(libraryCatalogue).searchFor("PUBLISHEDBEFORE(1700) ");
    }});

    bookSearchQueryBuilder.withPublishedDateBefore(1700).build().execute(libraryCatalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {

    context.checking(new Expectations() {{
      exactly(1).of(libraryCatalogue).searchFor("PUBLISHEDAFTER(1950) ");
    }});

    bookSearchQueryBuilder.withPublishedDateAfter(1950).build().execute(libraryCatalogue);
  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

    context.checking(new Expectations() {{
      exactly(1).of(libraryCatalogue)
          .searchFor("LASTNAME='dickens' PUBLISHEDBEFORE(1840) ");
    }});

    bookSearchQueryBuilder.withLastName("dickens").withPublishedDateBefore(1840)
        .build().execute(libraryCatalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfTitleAndOtherParameters() {

    context.checking(new Expectations() {{
      exactly(1).of(libraryCatalogue)
          .searchFor("TITLECONTAINS(of) PUBLISHEDAFTER(1800) PUBLISHEDBEFORE(2000) ");
    }});

    bookSearchQueryBuilder.withTitle("of").withPublishedDateAfter(1800)
        .withPublishedDateBefore(2000).build().execute(libraryCatalogue);

  }
}
