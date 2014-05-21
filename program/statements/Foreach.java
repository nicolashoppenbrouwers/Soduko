package worms.model.program.statements;

import java.util.ArrayList;
import java.util.List;
import worms.model.GameObject;
import worms.model.program.Program;
import worms.model.program.ProgramFactoryImpl.ForeachType;
import worms.model.program.types.Entity;

public class Foreach extends Statement{

	public Foreach(int line, int column,worms.model.programs.ProgramFactory.ForeachType type,String variableName,Statement body ) {
		super(line, column);
		this.type = type;
		this.variableName = variableName;
		this.body = body;
		this.setGameObjects(new ArrayList<GameObject>());
		this.setIndexCurrenGameObject(0);
		this.setTypeChecked(false);
	}
	
	private worms.model.programs.ProgramFactory.ForeachType getType(){
		return this.type;
	}
	
	private final worms.model.programs.ProgramFactory.ForeachType type;
	
	private String getVariableName(){
		return this.variableName;
	}
	
	private final String variableName;
	
	private Statement getBody(){
		return this.body;
	}
	
	private final Statement body;
	
	private void setGameObjects(List<GameObject> gameObjects){
		this.gameObjects = gameObjects;
	}
	
	private List<GameObject> getGameObjects(){
		return this.gameObjects;
	}
	
	private List<GameObject> gameObjects;
	
	private int getIndexCurrentGameObject(){
		return this.indexCurrentGameObject;
	}
	
	private void setIndexCurrenGameObject(int indexCurrentGameObject){
		this.indexCurrentGameObject = indexCurrentGameObject;
	}
	
	private int indexCurrentGameObject;
	
	public boolean isTypeChecked() {
		return this.typeChecked;
	}

	public void setTypeChecked(boolean typeChecked) {
		this.typeChecked = typeChecked;
	}
	
	private boolean typeChecked;

	@Override
	public boolean executeStatement(Program program) {
		
		if (!isTypeChecked()){
			if ((this.getType() == worms.model.programs.ProgramFactory.ForeachType.WORM) || (this.getType() == worms.model.programs.ProgramFactory.ForeachType.ANY)) {
				this.getGameObjects().addAll(program.getWorld().getWorms());
			}
			if ((this.getType() == worms.model.programs.ProgramFactory.ForeachType.FOOD) || (this.getType() == worms.model.programs.ProgramFactory.ForeachType.ANY)){
				this.getGameObjects().addAll(program.getWorld().getFood());
			}
			this.setTypeChecked(true);
		}
		
		while(this.getIndexCurrentGameObject() <= (this.getGameObjects().size()-1)) {
			
			Entity entityGameObject = new Entity(this.getGameObjects().get(this.getIndexCurrentGameObject()));
			program.getGlobals().put(this.getVariableName(),entityGameObject);
			
			if (this.getBody().execute(program)){
				this.setIndexCurrenGameObject(this.getIndexCurrentGameObject()+1);
			}
			else {
				return false;
			}
		}
		this.setGameObjects(new ArrayList <GameObject>());
		this.setIndexCurrenGameObject(0);
		this.setTypeChecked(false);
		return true;
		
	}

	@Override
	public boolean containsActionStatement() {
		return this.getBody().containsActionStatement();
	}

	@Override
	public boolean isWellFormed() {
		return !(this.containsActionStatement());
	}
}
