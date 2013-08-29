package networking.common;
/**
 * sends path of world
 * @author muruphenr
 *
 */
public class FilePathUpdate extends Update {
	private static final long serialVersionUID = 1L;
	String path ;

	public FilePathUpdate(String path) {
		this.path = path ;
	}
	public String getPath() {
		return this.path ;
	}

}
