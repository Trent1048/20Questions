public class QuestionNode {

	private String text;
	private boolean question;
	private QuestionNode yesNode;
	private QuestionNode noNode;

	// creates a node for a question that points at
	// 2 other nodes (1 for yes, 1 for no)
	public QuestionNode(String question, QuestionNode yesNode,
		QuestionNode noNode) {

		text = question;
		this.yesNode = yesNode;
		this.noNode = noNode;
		this.question = true;
	}

	// creates a node that stores an answer
	// and points at no other nodes
	public QuestionNode(String answer) {
		text = answer;
		yesNode = null;
		noNode = null;
		question = false;
	}

	public QuestionNode getYesNode() {
		return yesNode;
	}

	public QuestionNode getNoNode() {
		return noNode;
	}

	// returns the yesNode if the boolean is true
	// returns the noNode if the boolean is false
	public QuestionNode getNode(boolean isYesNode) {
		if (isYesNode) {
			return yesNode;
		} else {
			return noNode;
		}
	}

	public String getText() {
		return text;
	}

	public boolean isQuestion() {
		return question;
	}
}
