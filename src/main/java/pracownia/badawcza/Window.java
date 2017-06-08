package pracownia.badawcza;

import java.util.LinkedList;

public class Window {

	private final int size;
	private LinkedList<Integer> elements;

	public Window(int size) {
		this.size = size;
		elements = new LinkedList();
	}

	public void add(int value){
		elements.add(value);
		if(elements.size() > size) {
			elements.removeFirst();
		}
	}

	public int getTotal() {
		return elements.stream().mapToInt(Integer::intValue).sum();
	}


}
