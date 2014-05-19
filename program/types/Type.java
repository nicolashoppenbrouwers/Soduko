package worms.model.program.types;

public abstract class Type<T> {

	public Type(T value) {
		this.setValue(value);
	}

	public T getValue() {
		return this.value;
	}

	//Misschien setValue laten overriden in elke subklasse.
	//Met een extra if: (if !(value instanceof Double)) bv.
	// voor als het gegeven argument niet juist zou zijn.
	public void setValue(T value) {
		this.value = value;
	}

	private T value;
}
