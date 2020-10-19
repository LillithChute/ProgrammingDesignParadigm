package questionnaireTest;

import org.junit.Before;
import org.junit.Test;

import questionnaire.Likert;
import questionnaire.Question;
import questionnaire.TrueFalse;

import static org.junit.Assert.assertEquals;

/**
 * Test file for True False class.
 */
public class TrueFalseTest {
  private Question trueFalse;

  @Before
  public void setup() {
    trueFalse = new TrueFalse("This is a test.", "true");
  }

  // constructor
  @Test
  public void constructorToStringTest() {
    String expected = "True or false question";
    assertEquals(expected, trueFalse.toString());
  }

  // Question related tests
  @Test (expected = IllegalArgumentException.class)
  public void invalidQuestionTest() {
    Question test = new Likert("");
  }

  @Test (expected = IllegalArgumentException.class)
  public void nullQuestionTest() {
    Question test = new Likert(null);
  }

  @Test
  public void getText() {
    String expected = "This is a test.";
    assertEquals(expected, trueFalse.getText());
  }

  // Answer related tests.
  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerUpperTest() {
    Question test = new TrueFalse("My test question.", "CORRECT");
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerJunk() {
    Question test = new TrueFalse("My test question.", "kjhsd");
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerEmptyStringTest() {
    Question test = new TrueFalse("My test question.", " ");
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidAnswerNullTest() {
    Question test = new TrueFalse("My test question.", null);
  }

  @Test
  public void answerTest() {
    assertEquals("Correct", trueFalse.answer("true"));
    assertEquals("Incorrect", trueFalse.answer("false"));
  }

  @Test
  public void invalidAnswer() {
    String expected = "Incorrect";
    assertEquals(expected, trueFalse.answer("0"));
    assertEquals(expected, trueFalse.answer("true "));
    assertEquals(expected, trueFalse.answer(""));
    assertEquals(expected, trueFalse.answer(null));
    assertEquals(expected, trueFalse.answer("blarg"));
  }
}
