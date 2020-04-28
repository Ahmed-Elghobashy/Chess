package exceptions;

public class NotValidMoveException extends Exception
{
	public NotValidMoveException()
	{
		super();
	}
	public NotValidMoveException(String message)
	{
		super(message);
	}

}
