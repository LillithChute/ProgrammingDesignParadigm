package questionnaire;

/**
 * This provides the operations to be used on a questionnaire.
 */
public interface Question {

  /**
   * Gets the text of a question.
   *
   * @return The question.
   */
  String getText();

  /**
   * Takes an answer and figures out of it is correct.
   *
   * @return Whether the answer is correct or not.
   */
  String answer(String answer);
}
