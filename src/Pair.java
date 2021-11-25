/**
 * A class which represents a pair of two objects
 *
 * @param <X> The type of the first object
 * @param <Y> The type of the second object
 */
public class Pair<X, Y> {

  private X first;
  private Y second;

  /**
   * Constructs a new {@code Pair} object with the two given inputs
   *
   * @param first  The first object of the pair
   * @param second The second object of the pair
   * @throws IllegalArgumentException if either object is null
   */
  public Pair(X first, Y second) throws IllegalArgumentException {
    this.first = InputValidation.ensureNotNull(first);
    this.second = InputValidation.ensureNotNull(second);
  }

  /**
   * Returns the first value of this pair
   * @return The first value of this pair
   */
  public X getFirst() {
    return this.first;
  }

  /**
   * Returns the second value of this pair
   * @return The second value of this pair
   */
  public Y getSecond() {
    return this.second;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", this.first, this.second);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pair<?, ?>)) {
      // i am unsure of the use of the wildcard here, but it was used for testing purposes and
      // test pass sooo
      return false;
    }

    Pair<X, Y> other = (Pair<X, Y>) o;
    return this.first.equals(other.getFirst()) && this.second.equals(other.getSecond());
  }
}
