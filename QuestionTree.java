/*
Trent Bultsma
3/11/2020
CS 145

This class stores a root node to a binary tree that is used
for playing the game 20 questions. It can store and load
previous games from a file using recursion.
*/

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
		playRound(null, true);
	}

	// inputting null for parentNode will make the node equal to root
	private void playRound(QuestionNode parentNode, boolean side) {
		// the node being checked
		QuestionNode node;

		if (parentNode != null) {
			node = parentNode.getNode(side);
		} else {
			node = root;
		}

		// ask the question if it's a question,
		// otherwise ask if it is the correct answer
		if (node.isQuestion()) {
			ui.print(node.getText());
			playRound(node, ui.nextBoolean());
		} else {
			ui.print("Would your object happen to be " + node.getText() + "?");
			if (ui.nextBoolean()) {
				wins++;
			} else {
				// add in a new answer and question

				ui.print("I lose. What is your object? ");
				String realAnswer = ui.nextLine();

				ui.print("Type a yes/no question to distinguish your " +
					"item from " + node.getText() + ": ");
				String newQuestion = ui.nextLine();

				ui.print("And what is the answer for your object? ");
				boolean answerForObject = ui.nextBoolean();

				QuestionNode newAnswerNode = new QuestionNode(realAnswer);

				// set up the answer nodes for going right or left
				QuestionNode yesNode;
				QuestionNode noNode;
				if (answerForObject) {
					yesNode = newAnswerNode;
					noNode = node;
				} else {
					yesNode = node;
					noNode = newAnswerNode;
				}

				QuestionNode newQuestionNode = new QuestionNode(newQuestion,
					yesNode, noNode);

				// inserts the question node into the tree
				if (node == root) {
					root = newQuestionNode;
				} else {
					parentNode.setNode(side, newQuestionNode);
				}
			}
		}
	}

	// files saved are in preorder traversal of the tree
	public void save(PrintStream output) {
		StringBuilder savedNodes = new StringBuilder();
		saveNode(root, savedNodes);
		output.print(savedNodes);
	}

	public void load(Scanner input) {
		root = loadNode(input);
	}

	// recursive methods for saving and loading

	private void saveNode(QuestionNode node, StringBuilder savedNodes) {
		// don't try to add the null child of an answer node
		if (node == null) {
			return;
		}

		// sets it up as a question or answer
		if (node.isQuestion()) {
			savedNodes.append("Q:");
		} else {
			savedNodes.append("A:");
		}

		// saves the current node, then the yes one, then the no one
		savedNodes.append(node.getText() + "\n");
		saveNode(node.getYesNode(), savedNodes);
		saveNode(node.getNoNode(), savedNodes);
	}

	private QuestionNode loadNode(Scanner input) {
		// so it doesn't break when the file is empty
		if (!input.hasNextLine()) return null;

		// figures out if the node is a question or answer and saves it's text
		QuestionNode node;
		String currentText = input.nextLine();
		boolean isQuestion = currentText.substring(0, 2).equals("Q:");
		String nodeText = currentText.substring(2);

		if (isQuestion) {
			node = new QuestionNode(nodeText, loadNode(input), loadNode(input));
		} else {
			node = new QuestionNode(nodeText);
		}

		return node;
	}

	public int totalGames() {
		return gamesPlayed;
	}

	public int gamesWon() {
		return wins;
	}
}
