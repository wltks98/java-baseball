package baseball.domain;

import java.util.List;

import baseball.view.GameView;

public class Game {
	private final Computer computer;
	private User user;
	private int ball;
	private int strike;

	public Game() {
		computer = new Computer();
	}

	public void play() {
		GameView.printStartMessage();
		computer.createAnswer();
		user = new User(GameView.inputNum());

		initBallAndStrike();
		compareNumber(computer.getAnswer(), user.getNumber());

		if (isCorrectAnswer()) {
			GameView.printEndGame();

			if(GameView.inputRestartOrEnd().equals(GameView.RESTART_NUM)){
				this.play();
			}
		} else {
			GameView.printHint(ball, strike);
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
