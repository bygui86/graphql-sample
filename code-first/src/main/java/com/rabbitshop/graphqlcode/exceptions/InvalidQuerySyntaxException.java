package com.rabbitshop.graphqlcode.exceptions;

public class InvalidQuerySyntaxException extends RuntimeException {

	private static final long serialVersionUID = 5488084029383344895L;

	public InvalidQuerySyntaxException() {
		
		super();
	}
	
	public InvalidQuerySyntaxException(final String arg0) {
		
		super(arg0);
	}
	
	public InvalidQuerySyntaxException(final String arg0, final Throwable arg1) {
		
		super(arg0, arg1);
	}
	
	public InvalidQuerySyntaxException(final Throwable arg0) {
		
		super(arg0);
	}
	
	protected InvalidQuerySyntaxException(final String arg0, final Throwable arg1, final boolean arg2, final boolean arg3) {
		
		super(arg0, arg1, arg2, arg3);
	}
}
