package exception;

public class ApplicationException extends Exception {

	public ApplicationException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApplicationException(String s)
	{
		super(s);
	}

	public ApplicationException(Exception e)
	{
		super(e);
	}
}
