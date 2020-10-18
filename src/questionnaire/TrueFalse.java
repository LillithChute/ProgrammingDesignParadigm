package questionnaire;

/**
 * The type of question that can be answered only as "true" or "false".
 */
public class TrueFalse implements Question{

  private final String questionText;
  private final String answer;

  /**
   * This instantiates a truefalse instance.
   *
   * @param questionText The question.
   * @param answer The answer.
   * @throws IllegalArgumentException If the provided question is an empty string or if the answer
   *         is invalid.
   */
  public TrueFalse(String questionText, String answer) {
    if (questionText == null || questionText.length() == 0) {
      throw new IllegalArgumentException("You need to provide a question.");
    }
    if (answer == null
            || (!answer.equalsIgnoreCase("true") && !answer.equalsIgnoreCase("false"))) {
      throw new IllegalArgumentException("Invalid answer.");
    }
    this.questionText = questionText;
    this.answer = answer.toLowerCase();
  }

  @Override
  public String getText() {
    return this.questionText;
  }

  @Override
  public String answer(String answer) {
    if (answer == null) {
      return "Incorrect";
    }
    if (answer.equalsIgnoreCase(answer)) {
      return "Correct";
    }
    return "Incorrect";  }

  @Override
  public int compareTo(Question o) {
    if (!(o instanceof TrueFalse)) {
      return -1;
    }
    TrueFalse question = (TrueFalse) (o);
    return this.getText().compareTo(question.getText());
  }

  @Override
  public String toString() {
    return "True or false question";
  }
}
