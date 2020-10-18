package questionnaire;

import java.util.Collections;
import java.util.HashSet;

/**
 * This type of question gives a person multiple options to choose from only one of which is
 * actually correct.
 */
public class MultipleChoice implements Question {

  private final String question;
  private final String answer;
  private final HashSet<String> options = new HashSet<>();
  private static final HashSet<String> viable = new HashSet<>();

  /**
   * Construct a MultipleChoice instance.
   *
   * @param questionText the question.
   * @param answer the correct answer which is passed in.
   * @param options the available options that can be used.
   * @throws IllegalArgumentException if the question, answer, or options are missing
   *                                  , or the correct answers or options are invalid.
   */
  public MultipleChoice(String questionText, String answer, String... options) {
    // Any of the passed in values can't be missing.
    if (questionText == null || questionText.length() == 0
            || answer == null || answer.length() == 0
            || options.length == 0) {
      throw new IllegalArgumentException("Question text, answer, and options can't be null.");
    }

    for (int i = 1; i < 9; i++) {
      viable.add(String.format("%s", i));
    }

    // Make sure we have a valid number of options.
    if (options.length < 3 || options.length > 8) {
      throw new IllegalArgumentException("Incorrect number of options.");
    }

    // Make sure we aren't missing any options.
    for (String item : options) {
      if (item == null || item.length() == 0) {
        throw new IllegalArgumentException("Missing options.");
      }
    }

    // Make sure we don't have duplicate options.
    Collections.addAll(this.options, options);

    if (this.options.size() != options.length) {
      throw new IllegalArgumentException("Duplicate options are not allowed.");
    }

    // Make sure we have a viable correct answer.
    if (!viable.contains(answer)) {
      throw new IllegalArgumentException("No valid correct answer.");
    }

    // Good to go.
    this.question = questionText;
    this.answer = answer;
  }

  @Override
  public String getText() {
    return this.question;
  }

  @Override
  public String answer(String answer) {
    if (this.answer.equals(answer)) {
      return "Correct";
    }
    return "Incorrect";
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof TrueFalse) {
      return 1;
    } else if (o instanceof Likert) {
      return -1;
    } else if (o instanceof MultipleSelect) {
      return -1;
    }
    MultipleChoice question = (MultipleChoice) (o);
    return this.getText().compareTo(question.getText());
  }

  @Override
  public String toString() {
    return "Multiple choice question";
  }
}
