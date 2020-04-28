package exceptions;

public class NotYourTurnException extends Exception
{
	public NotYourTurnException()
	{
		super();
	}
	public NotYourTurnException(String message)
	{
		super(message);
	}
}
