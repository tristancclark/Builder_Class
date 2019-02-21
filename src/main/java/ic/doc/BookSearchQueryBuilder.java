package ic.doc;

public class BookSearchQueryBuilder {

  private String firstName = null;
  private String lastName = null;
  private String title = null;
  private Integer publishedDateAfter = null;
  private Integer publishedDateBefore = null;

  private BookSearchQueryBuilder() {
  }

  public static BookSearchQueryBuilder createBookSearchQueryBuilder() {
    return new BookSearchQueryBuilder();
  }

  public BookSearchQuery build() {
    return new BookSearchQuery(firstName, lastName, title, publishedDateAfter, publishedDateBefore);
  }


  public BookSearchQueryBuilder withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public BookSearchQueryBuilder withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public BookSearchQueryBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public BookSearchQueryBuilder withPublishedDateAfter(Integer publishedAfterDate) {
    this.publishedDateAfter = publishedAfterDate;
    return this;
  }

  public BookSearchQueryBuilder withPublishedDateBefore(Integer publishedBeforeDate) {
    this.publishedDateBefore = publishedBeforeDate;
    return this;
  }

}
