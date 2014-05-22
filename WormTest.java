package worms.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import worms.util.Util;

/**
 * A class for testing the worms.
 * 
 * @author 	Nicolas Hoppenbrouwers:	Bachelor Ingenieurswetenschappen Computerwetenschappen-Werktuigkunde
 * 			Bram Lust: Bachelor Ingenieurswetenschappen Computerwetenschappen-Elektrotechniek
 * 			We didn't work with Git.
 * @version 1.0
 */
public class WormTest {
	
	private static final double EPS = Util.DEFAULT_EPSILON;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	private Random random;

	private World world;
	

	// X X X X
	// . . . .
	// . . . .
	// X X X X
	private boolean[][] passableMap = new boolean[][] {
			{ false, false, false, false }, { true, true, true, true },
			{ true, true, true, true }, { false, false, false, false } };

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	
	private static Worm testWorm;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setup() {
		world = new World(4.0, 4.0, passableMap, random);
		testWorm = new Worm(world, 0, 2, 0, 1, "Test",null);
	}
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

//	@Test
//	public void test_Constructor_SingleCase(){
//		Worm testWorm = new Worm(world,6.0,6.0,3*Math.PI/2,1.0,"Test");
//		assertEquals(5.0, testWorm.getPositionX(),EPS);
//		assertEquals(5.0, testWorm.getPositionY(),EPS);
//		assertEquals(3*Math.PI/2, testWorm.getDirection(),EPS);
//		assertEquals(1.0, testWorm.getRadius(),EPS);
//		assertEquals("Test", testWorm.getName());
//	}
	
//	@Test
//	public void test_setPositionX_NormalCase(){
//		testWorm.setPositionX(5);
//		assertEquals(5,testWorm.getPositionX(),EPS);	
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Test(expected = IllegalArgumentException.class)
//	public void test_setPositionY_ArgumentException() {
//		Worm worm = new Worm(world,0,0,0,1,"Test",null);
//		worm.setPosition(Double.NaN,0);
//	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setPositionX_ArgumentException() {
		Worm worm = new Worm(world,0,0,0,1,"Test",null);
		worm.setPosition(Double.NaN,0);
	}
	
	@Test
	public void test_isValidPosition_TrueCase(){
		assertTrue(Position.isValidPosition(5));
	}
	
	@Test
	public void test_isValidPosition_FalseCase(){
		assertFalse(Position.isValidPosition(Double.NaN));
	}
	
	@Test
	public void test_setPosition_NormalCase(){
		Worm worm = new Worm(world,0,0,0,1,"Test",null);
		worm.setPosition(2,3);
		assertEquals(worm.getPosition().getX(),2,EPS);
		assertEquals(worm.getPosition().getY(),3,EPS);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setPosition_ArgumentException() {
		Worm worm = new Worm(world,0,0,0,1,"Test",null);
		worm.setPosition(Double.NaN,0);
	}
	
	
	@Test
	public void test_setDirection_NormalCase(){
		Worm worm = new Worm(world,0,0,0,1,"Test",null);
		worm.setDirection(Math.PI);
		assertEquals(Math.PI,worm.getDirection(),EPS);
	}
	
	@Test
	public void test_setDirection_DirectionTooLarge(){
		Worm worm = new Worm(world,0,0,0,1,"Test",null);
		worm.setDirection(9*Math.PI);
		assertEquals(Math.PI,worm.getDirection(),EPS);
	}
	
	@Test
	public void test_setDirection_DirectionTooSmall(){
		Worm worm = new Worm(world,0,0,0,1,"Test",null);
		worm.setDirection(-3.5*Math.PI);
		assertEquals(Math.PI/2,worm.getDirection(),EPS);
	}
	
	@Test
	public void test_isValidDirection_TrueCase(){
		assertTrue(Worm.isValidDirection(Math.PI));
	}
	
	@Test
	public void test_isValidDirection_FalseCase(){
		assertFalse(Worm.isValidDirection(Double.NaN));
	}
	
	@Test
	public void test_setRadius_NormalCase() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		worm.setRadius(0.25);
		assertEquals(0.25,worm.getRadius(),EPS);
		assertEquals(70, worm.getMaximumActionPoints());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setRadius_RadiusTooSmall() {
		Worm worm = new Worm(world,0,0,0,1,"Test",null);
		worm.setRadius(0.2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setRadius_IllegalArgument() {
		Worm worm = new Worm(world,0,0,0,1,"Test",null);
		worm.setRadius(Double.NaN);
	}
	
	@Test
	public void test_isValidRadius_TrueCase(){
		assertTrue(testWorm.isValidRadius(0.25));
	}
	
	@Test
	public void test_isValidDirection_FalseCase_TooSmall(){
		assertFalse(testWorm.isValidRadius(0.2));
	}
	
	@Test
	public void test_isValidDirection_FalseCase_NaN(){
		assertFalse(testWorm.isValidRadius(Double.NaN));
	}
	
	@Test
	public void test_getMinimalRadius_SingleCase(){
		assertEquals(testWorm.getMinimalRadius(),0.25,EPS);
	}
	
	
	@Test
	public void test_getMass_SingleCase() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		assertEquals(4448.495197483147,worm.getMass(),EPS);
	}
	
	@Test
	public void test_getMaximumActionPoints_NormalCase() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		assertEquals(4448,worm.getMaximumActionPoints(),EPS);
	}
	
	@Test
	public void test_getActionPoints_NormalCase() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		assertEquals(4448,worm.getActionPoints(),EPS);
	}
	
	@Test
	public void test_setActionPoints_NormalCase() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		worm.setActionPoints(2000);
		assertEquals(2000,worm.getActionPoints(),EPS);
	}
	
//	@Test
//	public void test_setActionPoints_NegativeCase() {
//		Worm worm = new Worm(world,0, 0, 0, 1, "Test");
//		worm.setActionPoints(-1000);
//		assertEquals(0,worm.getActionPoints(),EPS);
//	}
	
	@Test
	public void test_setActionPoints_TooMany() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		worm.setActionPoints(6000);
		assertEquals(4448,worm.getActionPoints(),EPS);
	}
	
	@Test
	public void test_setName_NormalCase() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		worm.setName("TestNaam");
		assertEquals("TestNaam",worm.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setName_NameTooShort() {
		Worm worm = new Worm(world,0,0,0,1,"Test",null);
		worm.setName("T");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_setName_LowerCaseLetter() {
		Worm worm = new Worm(world,0,0,0,1,"Test",null);
		worm.setName("test");
	}
	
//	@Test(expected = IllegalArgumentException.class)
//	public void test_setName_NonAlphabeticCharacter() {
//		Worm worm = new Worm(world,0,0,0,1,"Test");
//		worm.setName("Test!");
//	}
	
	@Test
	public void isValidName_TrueCase(){
		assertTrue(Worm.isValidName("Test"));
	}
	
	@Test
	public void isValidName_FalseCase_NameTooShort(){
		assertFalse(Worm.isValidName("T"));
	}
	
	@Test
	public void isValidName_FalseCase_LowerCase(){
		assertFalse(Worm.isValidName("test"));
	}
	
//	@Test
//	public void isValidName_FalseCase_InvalidCharacter(){
//		assertFalse(Worm.isValidName("Test="));
//	}
	
	@Test
	public void test_turn_NormalCase() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		worm.turn(Math.PI);
		assertEquals(Math.PI,worm.getDirection(),EPS);
	}
	
	@Test
	public void test_turn_AngleTooBig() {
		Worm worm = new Worm(world,0, 0, 4*Math.PI, 1, "Test",null);
		worm.turn(7*Math.PI);
		assertEquals(Math.PI,worm.getDirection(),EPS);
	}
	
	@Test
	public void test_turn_AngleNegative() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		worm.turn(-3*Math.PI/2);
		assertEquals(Math.PI/2,worm.getDirection(),EPS);
	}
	
	@Test
	public void test_canTurn_TrueCase() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		assertTrue(worm.canTurn(Math.PI));
	}
	
	@Test
	public void test_canTurn_FalseCase_AngleTooBig() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		assertFalse(worm.canTurn(1000*Math.PI));
	}
	
	@Test
	public void test_canTurn_TrueCase_Negative() {
		Worm worm = new Worm(world,0, 0, 0, 1, "Test",null);
		assertTrue(worm.canTurn(-Math.PI));
	}
	
	
//	@Test
//	public void test_move() {
//		Worm worm = new Worm(world,0, 0, 0, 1, "Test");
//		worm.move();
//		assertEquals(1,worm.getPositionX(),EPS);
//		assertEquals(0,worm.getPositionY(),EPS);
//		assertEquals(4444,worm.getActionPoints());
//	}
	
//	@Test(expected = IllegalStateException.class)
//	public void test_move_tooMuchSteps() {
//		Worm worm = new Worm(0,0,0,1,"Test");
//		worm.move(10000);
//	}
	
//	@Test(expected = IllegalArgumentException.class)
//	public void test_move_NegativeStepts() {
//		Worm worm = new Worm(0,0,0,1,"Test");
//		worm.move(-5);
//	}
	
	
//	@Test
//	public void test_canMove_FalseCase() {
//		Worm worm = new Worm(0, 0, 0, 1, "Test");
//		worm.setActionPoints(0);
//		assertTrue(worm.canMove(0));
//	}
	
	
//	@Test
//	public void test_jump() {
//		Worm worm = new Worm(world,0, 0, Math.PI/3, 1, "Test");
//		worm.jump();
//		assertEquals(4.83983922184745,worm.getPositionX(),EPS);
//		assertEquals(0.0,worm.getPositionY(),EPS);
//		assertEquals(0.0,worm.getActionPoints(),EPS);
//	}
	
//	@Test(expected = IllegalStateException.class)
//	public void test_jump_FacingDown() {
//		Worm worm = new Worm(world,0,4.0,1.5*Math.PI,1,"Test");
//		worm.jump();
//	}
	
	@Test
	public void test_canJump_TrueCase() {
		Worm worm = new Worm(world,0,4.0,Math.PI*1.5,1, "Test",null);
		assertTrue(worm.canJump());
	}
	
//	@Test
//	public void test_canJump_FalseCase_NoActionPoints() {
//		Worm worm = new Worm(world,0,4.0, 0, 1, "Test",null);
//		worm.setActionPoints(0);
//		assertFalse(worm.canJump());
//	}
	
	
//	@Test
//	public void test_getJumpTime() {
//		Worm worm = new Worm(world,0, 4.00, Math.PI/3, 1, "Test");
//		assertEquals(1.3075263238687838,worm.getJumpTime(0.001),EPS);
//	}
	
	
	
	
	
	

}