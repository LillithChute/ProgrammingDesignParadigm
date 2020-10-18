package questionnaire;

import java.util.Collections;
import java.util.HashSet;

/**
 * This kind of question is created via the text of the question, the correct answer, as well as
 * the options to select.
 */
public class MultipleSelect implements Question {

  private final String question;
  private final HashSet<String> answer = new HashSet<>();
  private final HashSet<String> options = new HashSet<>();
  private static final HashSet<String> viable = new HashSet<>();

  /**
   * Construct a MultipleSelect instance.
   *
   * @param questionText the question.
   * @param answer the correct answer which is passed in.
   * @param options the available options that can be used.
   * @throws IllegalArgumentException if the question, answer, or options are missing
   *                                  , or the correct answers or options are invalid.
   */
  public MultipleSelect(String questionText, String answer, String... options) {
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
        throw new IllegalArgumentException("Missing option.");
      }
    }

    // Make sure we don't have duplicate options.
    Collections.addAll(this.options, options);

    if (this.options.size() != options.length) {
      throw new IllegalArgumentException("Duplicate options are not allowed.");
    }

    Collections.addAll(this.answer, answer.split(" "));

    // missing an answer.
    if (this.answer.size() == 0) {
      throw new IllegalArgumentException("An answer must be provided.");
    }

    // Check for a correct answer that is available.
    for (String item : this.answer) {
      if (!viable.contains(item)) {
        throw new IllegalArgumentException("No valid correct answer.");
      }
    }

    // No duplicate answers allowed.
    if (this.answer.size() != answer.split(" ").length) {
      throw new IllegalArgumentException("Correct answers must be unique.");
    }

    this.question = questionText;
  }

  @Override
  public String getText() {
    return this.question;
  }

  @Override
  public String answer(String answer) {
    // No answer provided then you are wrong.
    if (answer == null || answer.length() == 0) {
      return "Incorrect";
    }

    // Create a collection of answers.
    HashSet<String> answerHash = new HashSet<>();
    Collections.addAll(answerHash, answer.split(" "));

    // If the number of correct answers do not match, you are wrong.
    if (answerHash.size() != answer.split(" ").length) {
      return "Incorrect";
    }

    // If the number of correct answers do not match, you are wrong.
    if (this.answer.size() != answerHash.size()) {
      return "Incorrect";
    }


    // If the answer chosen is not correct, then obviously you are wrong.
    for (String correctAnswer : this.answer) {
      if (!answerHash.contains(correctAnswer)) {
        return "Incorrect";
      }
    }

    // Otherwise, good job!
    return "Correct";
  }

  @Override
  public int compareTo(Question o) {
    if (o instanceof TrueFalse) {
      return 1;
    } else if (o instanceof MultipleChoice) {
      return 1;
    } else if (o instanceof Likert) {
      return -1;
    }

    MultipleSelect question = (MultipleSelect) (o);

    return this.getText().compareTo(question.getText());
  }

  @Override
  public String toString() {
    return "Multiple select question";
  }
}
