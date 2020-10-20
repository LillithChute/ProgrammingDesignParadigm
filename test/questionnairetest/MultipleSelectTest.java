package questionnairetest;

import org.junit.Before;
import org.junit.Test;

import questions.MultipleSelect;
import questions.Question;

import static org.junit.Assert.assertEquals;

/**
 * Test file for multiple select class.
 */
public class MultipleSelectTest {

  private Question multipleSelect;

  @Before
  public void setup() {
    multipleSelect = new MultipleSelect(
            "test multiple select",
            "1 2",
            "1", "2", "3");
  }

  @Test
  public void ConstructorToString() {
    String expected = "Multiple select question";
    assertEquals(expected, multipleSelect.toString());
  }

  // Question related tests.
  @Test (expected = IllegalArgumentException.class)
  public void invalidQuestionTest() {
    Question test = new MultipleSelect(
            "",
            "1",
            "1", "2", "3");
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullQuestionTest() {
    Question test = new MultipleSelect(
            null,
            "1",
            "1", "2", "3");
  }

  // Answer related tests.
  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerZeroTest() {
    Question test = new MultipleSelect(
            "My test question.",
            "0 1 3",
            "1", "2", "3"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerNineTest() {
    Question test = new MultipleSelect(
            "tMy test question.est",
            "1 9",
            "1", "2", "3"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerNullTest() {
    Question test = new MultipleSelect(
            "My test question.",
            null,
            "1", "2", "3"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerEmptyTest() {
    Question test = new MultipleSelect(
            "My test question.",
            " ",
            "1", "2", "3"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerZeroLengthTest() {
    Question test = new MultipleSelect(
            "My test question.",
            "",
            "1", "2", "3"
    );
  }

  @Test
  public void answerInDifferentOrder() {
    Question test = new MultipleSelect(
            "My test question.",
            "1 3",
            "sad", "happy", "odd"
    );
    assertEquals("Correct", test.answer("3 1"));
    test = new MultipleSelect(
            "My test question.",
            "1 2 3",
            "sad", "happy", "odd"
    );
    assertEquals("Correct", test.answer("3 1 2"));
  }

  @Test (expected = IllegalArgumentException.class)
  public void answerNotUnique() {
    Question test = new MultipleSelect(
            "My test question.",
            "1 1 2",
            "sad", "happy", "odd"
    );
  }

  // Option tests.
  @Test (expected = IllegalArgumentException.class)
  public void invalidOptionNullTest() {
    Question test = new MultipleSelect(
            "My test question.",
            "1",
            "sad", "happy", null
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidNotEnoughOptionsTest() {
    Question test = new MultipleSelect(
            "My test question.",
            "1",
            "sad", "happy"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidWayTooManyOptionsTest() {
    Question test = new MultipleSelect(
            "My test question.",
            "1",
            "sad", "happy", "great", "terrible", "angry", "emotional", "hollow", "tiny", "vitalis"
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidNotUniqueOptionsTest() {
    Question test = new MultipleSelect(
            "My test question.",
            "1",
            "sad", "sad", "happy"
    );
  }

  @Test
  public void getText() {
    String expected = "test multiple select";
    assertEquals(expected, multipleSelect.getText());
  }

  @Test
  public void answerTest() {
    assertEquals("Correct", multipleSelect.answer("1 2"));
  }

  @Test
  public void invalidAnswerTest() {
    String expected = "Incorrect";
    assertEquals(expected, multipleSelect.answer(null));
    assertEquals(expected, multipleSelect.answer("01"));
    assertEquals(expected, multipleSelect.answer("02"));
    assertEquals(expected, multipleSelect.answer("03"));
    assertEquals(expected, multipleSelect.answer("04"));
    assertEquals(expected, multipleSelect.answer("05"));
    assertEquals(expected, multipleSelect.answer("0"));
    assertEquals(expected, multipleSelect.answer("6 5"));
    assertEquals(expected, multipleSelect.answer(""));
  }
}
