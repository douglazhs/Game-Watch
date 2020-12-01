package br.com.GameWatch.main;

import java.util.Comparator;

public class CompareNotes implements Comparator<Game> {

	@Override
	public int compare(Game game1, Game game2) {
		if(game1.getAvaliationNote() < game2.getAvaliationNote()) return -1;
		else if(game1.getAvaliationNote() > game2.getAvaliationNote()) return 1;
		else return 0;
	}

}
