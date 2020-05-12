package negocio.Alumno;

public class TRelleno {
	private int idAlumno,idTest,numFallos;
	
	public TRelleno (int idAlumno, int idTest, int numFallos) {
		this.idAlumno = idAlumno;
		this.idTest = idTest;
		this.numFallos = numFallos;
	}
	
	public int getIdAlumno() {
		return idAlumno;
	}
	
	public int getIdTest() {
		return idTest;
	}
	public int getNumFallos() {
		return numFallos;
	}
}
