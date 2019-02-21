package ic.doc;

public class BookSearchQueryBuilder {

  private String firstName = null;
  private String lastName = null;
  private String title = null;
  private Integer publishedAfterDate = null;
  private Integer publishedBeforeDate = null;

  private BookSearchQueryBuilder() {}

  public static BookSearchQueryBuilder aBookSearchQueryBuilder() {
    return new BookSearchQueryBuilder();
  }

  public BookSearchQuery build() {
    return new BookSearchQuery(firstName, lastName, title, publishedAfterDate, publishedBeforeDate);
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

  public BookSearchQueryBuilder withPublishedAfterDate(Integer publishedAfterDate) {
    this.publishedAfterDate = publishedAfterDate;
    return this;
  }

  public BookSearchQueryBuilder withPublishedBeforeDate(Integer publishedBeforeDate) {
    this.publishedBeforeDate = publishedBeforeDate;
    return this;
  }

  public void resetQuery() {
    this.firstName = null;
    this.lastName = null;
    this.title = null;
    this.publishedAfterDate = null;
    this.publishedBeforeDate = null;
  }






}
