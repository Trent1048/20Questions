import java.util.Scanner;
import java.io.PrintStream;

public class QuestionTree {

	private QuestionNode root;
	private UserInterface ui;
	private int gamesPlayed;
	private int wins;

	public QuestionTree(UserInterface ui) {
		root = new QuestionNode("computer");
		this.ui = ui;
	}

	public void play() {
		gamesPlayed++;
		playRound(root);
	}

	private void playRound(QuestionNode node) {
		if (node.isQuestion()) {
			ui.print(node.getText());
			playRound(node.getNode(ui.nextBoolean()));
		} else {
			ui.print("Would your object happen to be " + node.getText() + "?");
			if (ui.nextBoolean()) wins++;
			// add in a new answer and question
		}
	}

	// files saved are in preorder traversal of the tree
	public void save(PrintStream output) {
		return;
	}

	public void load(Scanner input) {
		return;
	}

	public int totalGames() {
		return gamesPlayed;
	}

	public int gamesWon() {
		return wins;
	}
}
