package questionnairetest;

import org.junit.Before;
import org.junit.Test;

import questions.Likert;
import questions.Question;

import static org.junit.Assert.assertEquals;

/**
 * Likert test class.
 */
public class LikertTest {

  private Question likert;

  @Before
  public void setup() {
    likert = new Likert("test likert");
  }

  // Tests for constructor.
  @Test
  public void constructorToStringTest() {
    // test constructor by testing toString()
    String expected = "Likert question";
    assertEquals(expected, likert.toString());
  }

  // Tests for question text
  @Test (expected = IllegalArgumentException.class)
  public void missingQuestionTest() {
    Question test;
    test = new Likert("");
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullQuestionTest() {
    Question test;
    test = new Likert(null);
  }

  @Test
  public void getQuestionTest() {
    String expected = "test likert";
    assertEquals(expected, likert.getText());
  }

  // Make sure the answer method comes back with correct.
  @Test
  public void answerTest() {
    String expected = "Correct";
    assertEquals(expected, likert.answer("1"));
    assertEquals(expected, likert.answer("2"));
    assertEquals(expected, likert.answer("3"));
    assertEquals(expected, likert.answer("4"));
    assertEquals(expected, likert.answer("5"));
  }

  // Check for the incorrect answer.
  @Test
  public void invalidAnswer() {
    String expected = "Incorrect";
    assertEquals(expected, likert.answer("0"));
    assertEquals(expected, likert.answer("6"));
    assertEquals(expected, likert.answer(""));
    assertEquals(expected, likert.answer(null));
    assertEquals(expected, likert.answer("1 "));
    assertEquals(expected, likert.answer("2 "));
    assertEquals(expected, likert.answer("3 "));
    assertEquals(expected, likert.answer("4 "));
    assertEquals(expected, likert.answer("5 "));
    assertEquals(expected, likert.answer("01"));
    assertEquals(expected, likert.answer("10"));
  }
}
