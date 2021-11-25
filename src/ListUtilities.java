import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * A class built for operations performed on Lists
 */
public class ListUtilities {

  /**
   * Maps the given function onto each element of the input list
   *
   * @param inputList The list to be operated on
   * @param function  The function to be applied at each element in the list
   * @param <X>       The type of the input list
   * @param <Y>       The type of the output list
   * @return The list after having the function applied at each element
   */
  public static <X, Y> List<Y> map(List<X> inputList, Function<X, Y> function) {
    List<Y> outputList = new ArrayList<Y>(inputList.size());
    for (int index = 0; index < inputList.size(); index++) {
      outputList.add(function.apply(inputList.get(index)));
    }
    return outputList;
  }
}
