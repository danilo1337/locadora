package entidade;

public class Genero {

	private int id = 0;
	private String genero = "";
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String toString() {
        return String.format("%s", getGenero());
    }
}
