package worms.model;

import java.util.Random;

import worms.model.program.Program;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random = new Random();
		
		boolean[][] passableMap = new boolean[][] {
				{ false, false, false, false }, { true, true, true, true },
				{ true, true, true, true }, { false, false, false, false } };
		
		World world = new World(4.0, 4.0, passableMap, random);
		
		Worm worm = new Worm(world, 0, 2, 0, 1, "Test");
		
		Program program = new Program();
		
		
		
		worm.jump();
	}

}
