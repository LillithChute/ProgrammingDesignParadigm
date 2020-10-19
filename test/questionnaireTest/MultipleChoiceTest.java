package questionnaireTest;

import org.junit.Before;
import org.junit.Test;

import questionnaire.MultipleChoice;
import questionnaire.Question;

import static org.junit.Assert.assertEquals;

/**
 * Test file for multiple choice class.
 */
public class MultipleChoiceTest {
  private Question multipleChoice;

  @Before
  public void setup() {
    multipleChoice = new MultipleChoice("test multiple choice",
            "1",
            "1", "2", "3");
  }

  // Test the constructor.
  @Test
  public void constructorToString() {
    String expected = "Multiple choice question";
    assertEquals(expected, multipleChoice.toString());
  }

  // Tests for the question.
  @Test (expected = IllegalArgumentException.class)
  public void missingQuestionTest() {
    Question test = new MultipleChoice(
            "",
            "1",
            "1", "2", "3");
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullQuestionTest() {
    Question test = new MultipleChoice(
            null,
            "1",
            "1", "2", "3");
  }

  // Answer related tests.
  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerZeroTest() {
    Question test = new MultipleChoice(
            "My test question.",
            "0",
            "1", "2", "3"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerNineTest() {
    Question test = new MultipleChoice(
            "My test question.",
            "9",
            "1", "2", "3"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerNullTest() {
    Question test = new MultipleChoice(
            "My test question.",
            null,
            "1", "2", "3"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerEmptyTest() {
    Question test = new MultipleChoice(
            "My test question.",
            " ",
            "1", "2", "3"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerZeroLengthTest() {
    Question test = new MultipleChoice(
            "My test question.",
            "",
            "1", "2", "3"
    );
  }

  // Options parameter tests
  @Test (expected = IllegalArgumentException.class)
  public void invalidOptionsNullTest() {
    Question test = new MultipleChoice(
            "My test question.",
            "1",
            "sad", "happy", null
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidNotEnoughOptionsTest() {
    Question test = new MultipleChoice(
            "My test question.",
            "1",
            "sad", "happy"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidTooManyOptionTest() {
    Question test = new MultipleChoice(
            "My test question.",
            "1",
            "sad", "happy", "great", "terrible", "angry", "emotional", "hollow", "tiny", "vitalis"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidOptionsNotUniqueTest() {
    Question test = new MultipleChoice(
            "My test question.",
            "1",
            "sad", "sad", "happy"
    );
  }

  // Get question.
  @Test
  public void getText() {
    String expected = "test multiple choice";
    assertEquals(expected, multipleChoice.getText());
  }

  // Answer related tests
  @Test
  public void answer() {
    assertEquals("Correct", multipleChoice.answer("1"));
  }

  @Test
  public void invalidAnswer() {
    String expected = "Incorrect";
    assertEquals(expected, multipleChoice.answer("0"));
    assertEquals(expected, multipleChoice.answer("6"));
    assertEquals(expected, multipleChoice.answer(""));
    assertEquals(expected, multipleChoice.answer(null));
    assertEquals(expected, multipleChoice.answer("1 "));
    assertEquals(expected, multipleChoice.answer("2 "));
    assertEquals(expected, multipleChoice.answer("3 "));
    assertEquals(expected, multipleChoice.answer("4 "));
    assertEquals(expected, multipleChoice.answer("5 "));
    assertEquals(expected, multipleChoice.answer("01"));
    assertEquals(expected, multipleChoice.answer("02"));
    assertEquals(expected, multipleChoice.answer("03"));
    assertEquals(expected, multipleChoice.answer("04"));
    assertEquals(expected, multipleChoice.answer("05"));
  }
}
