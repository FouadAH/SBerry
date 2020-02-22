package sberry.QuestionFactory;
import sberry.QuestionTypes.Emails;
import sberry.QuestionTypes.Likert;
import sberry.QuestionTypes.MC;
import sberry.QuestionTypes.TF;
import sberry.QuestionTypes.Text;
import sberry.QuestionTypes.Title;
import sberry.QuestionTypes.Type;
import sberry.SDK.Question;

/**Factory class used to create MC, TF, Likert, Text, Title, Emails and Type objects
 * @author Fouad Abou Harfouche
 *
 */
public class QuestionFactory {

	/**Returns the appropriate object depending of the given questiontype
	 * @param questionType Type of object to create
	 * @return MC, TF, Likert, Text, Title, Emails, Type object, or null
	 */
	public Question getQuestion(String questionType) {
		if (questionType == null) {
			return null;
		}
		if (questionType.equalsIgnoreCase("MC")
				|| questionType.equalsIgnoreCase("MC*")) {
			return new MC();

		} else if (questionType.equalsIgnoreCase("TF")
				|| questionType.equalsIgnoreCase("TF*")) {
			return new TF();

		} else if (questionType.equalsIgnoreCase("LIKERT")
				|| questionType.equalsIgnoreCase("LIKERT*")) {
			return new Likert();
		} else if (questionType.equalsIgnoreCase("TEXT")
				|| questionType.equalsIgnoreCase("TEXT*")) {
			return new Text();
		} else if (questionType.equalsIgnoreCase("EMAILS")) {
			return new Emails();
		}
		else if (questionType.equalsIgnoreCase("TITLE")) {
			return new Title();
		}
		else if (questionType.equalsIgnoreCase("TYPE")) {
			return new Type();
		}

		return null;

	}

}
