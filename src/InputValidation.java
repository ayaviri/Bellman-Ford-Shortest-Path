/**
 * A class built for the validation of input. Most of these methods throw exceptions if the input
 * does not meet the desired constraints
 */
public class InputValidation {

  /**
   * Ensures that the given {@code input} is not null
   *
   * @param input The object whose state we want to check
   * @param <X>   The type of the object
   * @return The input if it is not null
   * @throws IllegalArgumentException if the object is null
   */
  public static <X> X ensureNotNull(X input) throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("Input cannot be null");
    } else {
      return input;
    }
  }

  /**
   * Ensures that the given value is strictly greater than the lower bound
   *
   * @param value      The value to be checked
   * @param lowerBound The strict lower bound for the given value
   * @param message    The error message to be thrown if the given value is not strictly greater
   *                   than the lower bound
   * @return The given value if it is strictly greater than the lower bound
   * @throws IllegalArgumentException if the given value is not strictly greater than the lower
   *                                  bound
   */
  public static int ensureGreaterThan(int value, int lowerBound, String message)
      throws IllegalArgumentException {
    if (!(value > lowerBound)) {
      throw new IllegalArgumentException(message);
    } else {
      return value;
    }
  }

  /**
   * Ensures that the given value is strictly greater than the lower bound AND strictly less than
   * the upper bound
   *
   * @param value      The value to be checked
   * @param lowerBound The strict lower bound for the given value
   * @param upperBound The strict upper bound for the given value
   * @param message    The error message to be thrown if the given value is not strictly greater
   *                   than the lower bound and strictly less than the upper bound
   * @return The given value if it is strictly greater than the lower bound and strictly less than
   * the upper bound
   * @throws IllegalArgumentException if the given value is not strictly greater than the lower
   *                                  bound and strictly less than the upper bound
   */
  public static int ensureWithin(int value, int lowerBound, int upperBound, String message)
      throws IllegalArgumentException {
    if (value > lowerBound && value < upperBound) {
      return value;
    } else {
      throw new IllegalArgumentException(message);
    }
  }
}
