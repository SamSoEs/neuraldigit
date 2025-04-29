package neuraldigit.loader.image;

public class LoaderException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	

	public LoaderException(String message) {
		super(message);
	}

	public LoaderException(String message, Throwable cause) {
		super(message, cause);
	}
}
