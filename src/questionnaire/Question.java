package questionnaire;

/**
 * This provides the operations to be used on a questionnaire.
 */
public interface Question extends Comparable<Question> {

  /**
   * Get the text of the question.
   *
   * @return the text of the question.
   */
  String getText();

  /**
   * Enter the answer and return whether it's correct or not.
   *
   * @param answer the answer.
   * @return "Correct" or "Incorrect".
   */
  String answer(String answer);
}