public class QuestionFactory {

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
		}

		return null;

	}

}
