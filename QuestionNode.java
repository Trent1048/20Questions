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

	// Getters

	public QuestionNode getYesNode() {
		return yesNode;
	}

	public QuestionNode getNoNode() {
		return noNode;
	}

	// returns the yesNode if the boolean is true
	// returns the noNode if the boolean is false
	public QuestionNode getNode(boolean side) {
		if (side) {
			getYesNode();
		} else {
			getNoNode();
		}
	}

	public String getText() {
		return text;
	}

	public boolean isQuestion() {
		return question;
	}

	// Setters

	public void setYesNode(QuestionNode newYesNode) {
		yesNode = newYesNode;
	}

	public void setNoNode(QuestionNode newNoNode) {
		noNode = newNoNode;
	}

	public void setNode(boolean side, QuestionNode newNode) {
		if (side) {
			setYesNode(newNode);
		} else {
			setNoNode(newNode);
		}
	}
}
