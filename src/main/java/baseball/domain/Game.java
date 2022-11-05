package baseball.domain;

import java.util.List;

import javax.swing.text.View;

import baseball.view.GameView;
import baseball.view.inputView;
import baseball.view.printView;

public class Game {
	private final Computer computer;
	private User user;
	private int ball;
	private int strike;

	public Game() {
		computer = new Computer();
	}

	public void play() {
		printView.printStartMessage();
		computer.createAnswer();
		tryCorrectAnswer();
	}

	private void tryCorrectAnswer() {
		initBallAndStrike();
		user = new User(inputView.inputNum());

		compareNumber(computer.getAnswer(), user.getNumber());
		printView.printHint(ball, strike);

		if (isCorrectAnswer()) {
			endGame();
		} else {
			tryCorrectAnswer();
		}
	}

	private void endGame() {
		printView.printEndGame();

		if(inputView.inputRestartOrEnd().equals(inputView.RESTART_NUM)){
			play();
		}
	}

	private boolean isCorrectAnswer() {
		if (strike == Computer.ANSWER_LENGTH) {
			return true;
		} else {
			return false;
		}
	}

	private void initBallAndStrike() {
		ball = 0;
		strike = 0;
	}

	private void compareNumber(List<Integer> answer, List<Integer> number) {
		for (int index = 0; index < number.size(); index++) {
			countBallAndStrike(answer, number, index);
		}
	}

	private void countBallAndStrike(List<Integer> answer, List<Integer> number, int index) {
		if (answer.get(index).equals(number.get(index))) {
			strike++;
		} else if (answer.contains(number.get(index))) {
			ball++;
		}
	}

}
