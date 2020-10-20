package questions;

import java.util.HashSet;

/**
 * Likert is a type of question that is answered on a 5 point scale.  An example of such a scale
 * would be Strongly Agree, Agree, Neither Agree nor Disagree, Disagree, Strongly Disagree.
 */
public class Likert implements Question {

  private final String question;
  private static final HashSet<String> answer = new HashSet<>();

  /**
   * Build a Likert instance.
   *
   * @param question A question to be answered.
   * @throws IllegalArgumentException if the question is an empty string.
   */
  public Likert(String question) {
    // Are we missing the question?
    if (question == null || question.length() == 0) {
      throw new IllegalArgumentException("Please provide a question.");
    }

    this.question = question;

    // Add the scale.
    answer.add("1");
    answer.add("2");
    answer.add("3");
    answer.add("4");
    answer.add("5");
  }

  @Override
  public String getText() {
    return this.question;
  }

  @Override
  public String answer(String answer) {
    if (!this.answer.contains(answer)) {
      return "Incorrect";
    }
    return "Correct";
  }

  @Override
  public int compareTo(Question o) {
    if (!(o instanceof Likert)) {
      return 1;
    }
    Likert q2 = (Likert) (o);
    return this.getText().compareTo(q2.getText());
  }

  @Override
  public String toString() {
    return "Likert question";
  }
}
