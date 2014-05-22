package test;

import worms.model.World;
import java.util.Random;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import worms.model.Facade;


public class WorldTest {
	private Random random;

	
	@Before
	public void setUp() throws Exception {
		random = new Random(23);
	}


	
	@Test(expected = IllegalStateException.class)
	public void test_AddMax_FalseCase_TooManyTeams() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ false, false, false },
				{ true, true, true },
				{ true, true, true },
				{ false, false, false }
		}, random);

		world.addEmptyTeam("TeamOne");
		world.addEmptyTeam("TeamTwo");
		world.addEmptyTeam("TeamThree");
		world.addEmptyTeam("TeamFour");
		world.addEmptyTeam("TeamFive");
		world.addEmptyTeam("TeamSix");
		world.addEmptyTeam("TeamSeven");
		world.addEmptyTeam("TeamEight");
		world.addEmptyTeam("TeamNine");
		world.addEmptyTeam("TeamTen");
		world.addEmptyTeam("TeamEleven");
	}

	@Test
	public void testStartGame() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ false, false, false },
				{ true, true, true },
				{ true, true, true },
				{ false, false, false }
		}, random);
		world.addNewWorm(null);
		world.startGame();
		world.getCurrentWorm().shoot(0);
	}

	
	@Test
	public void testIsGameFinished_trueCase_oneWorm() {
		
		World world = new World(3.0, 4.0, new boolean[][] {
				{ false, false, false },
				{ true, true, true },
				{ true, true, true },
				{ false, false, false }
		}, random);
		world.startGame();
		world.addNewWorm(null);
		assertTrue(world.isGameFinished());

	}
	
	@Test
	public void testAddTeam() {

		World world = new World(3.0, 4.0, new boolean[][] {
				{ false, false, false },
				{ true, true, true },
				{ true, true, true },
				{ false, false, false }
		}, random);
		world.addEmptyTeam("Bram");
		world.addNewWorm(null);
		world.addEmptyTeam("Nicolas");
		world.addNewWorm(null);
		world.startGame();
		assertTrue(world.getCurrentTeam().getName().equals("Bram") );
		world.startNextTurn();
		assertTrue(world.getCurrentTeam().getName().equals("Nicolas") );
	}
	
	@Test
	public void testGetObjectsOfGameWorld() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ false, false, false },
				{ true, true, true },
				{ true, true, true },
				{ false, false, false }
		}, random);
		
		
		world.addEmptyTeam("TeamOne");
		world.addEmptyTeam("TeamTwo");
		world.addNewWorm(null);
		world.addNewFood();
		world.addNewFood();
		world.startGame();
		assertEquals(world.getFood().size(), 2);
		assertEquals(world.getWorms().size(), 1);
		assertEquals(world.getTeams().size(), 1);
	}
	
	@Test
	public void testIsGameFinished_falseCase() {
		
		World world = new World(3.0, 4.0, new boolean[][] {
				{ false, false, false },
				{ true, true, true },
				{ true, true, true },
				{ false, false, false }
		}, random);
		world.startGame();
		world.addNewWorm(null);
		world.addNewWorm(null);
		assertFalse(world.isGameFinished());

	}

	@Test
	public void testIsGameFinished_talseCase_sameTeam() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ false, false, false },
				{ true, true, true },
				{ true, true, true },
				{ false, false, false }
		}, random);
		world.addEmptyTeam("TeamOne");
		world.addNewWorm(null);
		world.addNewWorm(null);
		world.addEmptyTeam("TeamTwo");
		world.startGame();
		assertTrue(world.isGameFinished());
	}

	@Test
	public void testRemove() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ false, false, false },
				{ true, true, true },
				{ true, true, true },
				{ false, false, false }
		}, random);

		world.addNewWorm(null);
		world.addNewWorm(null);
		world.startGame();
		world.getCurrentWorm().terminate();

		assertEquals(world.getWorms().size(), 1);
	}

	@Test
	public void testIsadjacent_trueCase() {

		World world = new World(3.0, 4.0, new boolean[][] {
				{ false, false, false },
				{ true, true, true },
				{ true, true, true },
				{ false, false, false }
		}, random);

		assertTrue(world.isAdjacent(1.5, 1.5, 0.49));
		assertTrue(world.isAdjacent(1.5, 2.5, 0.49));
		assertFalse(world.isAdjacent(1.5, 2,4.9));
		assertFalse(world.isAdjacent(1.5,1,4.9));
	}

	@Test
	public void testGetWinner_Team() {
		World world = new World(3.0, 4.0, new boolean[][] {
				{ false, false, false },
				{ true, true, true },
				{ true, true, true },
				{ false, false, false }
		}, random);
		
		world.addEmptyTeam("TeamOne");
		world.addEmptyTeam("TeamTwo");
		world.addNewWorm(null);
		world.startGame();
		assertTrue(world.getWinner().equals("TeamTwo"));
	}

}