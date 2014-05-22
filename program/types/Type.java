package worms.model.program.types;

public abstract class Type<T> {

	public Type(T value) {
		this.setValue(value);
	}

	public T getValue() {
		return this.value;
	}


	public void setValue(T value) {
		this.value = value;
	}

	public T value;

	public abstract String toString();
}
