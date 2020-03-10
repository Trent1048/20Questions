public class QuestionNode {

	private String text;
	private boolean isQuestion;
	private QuestionNode yesNode;
	private QuestionNode noNode;

	// creates a node for a question that points at
	// 2 other nodes (1 for yes, 1 for no)
	public QuestionNode(String question, QuestionNode yesNode,
		QuestionNode noNode) {

		text = question;
		this.yesNode = yesNode;
		this.noNode = noNode;
		isQuestion = true;
	}

	// creates a node that stores an answer
	// and points at no other nodes
	public QuestionNode(String answer) {
		text = answer;
		yesNode = null;
		noNode = null;
		isQuestion = false;
	}

	public QuestionNode getYesNode() {
		return yesNode;
	}

	public QuestionNode getNoNode() {
		return noNode;
	}

	public String getText() {
		return text;
	}
}
