class RPN
{
	public static void main(String[] arg)
	{
		String s[] = {"5 + ) * ( 2", "()",
					  " 2 + { 2 * ( 10 - 4 ) / [ { 4 * 2 / ( 3 + 4) } + 2 ] - 9 }",
				      " 2 + ( - 3 * 5 ) ",
				      " 2 + } 2 * ( 10 - 4 ) / [ { 4 * 2 / ( 3 + 4) } + 2 ] - 9 { ",
		              "(( 2 + 3 ) * 5 ) * 8 ",
		              "5 * 10 + ( 15 - 20 ) )  - 25",
		              " 5 + ( 5 *  10 + ( 15 - 20 )  - 25 ) * 9"
		             };
		for (int i = 0; i < s.length; i++)
		{

			Arithmetic a = new Arithmetic(s[i]);
			if (a.isBalance())
			{
				System.out.println("Expression " + s[i] + " is balanced\n");
				a.postfixExpression();
				System.out.println("The post fixed expression is " + a.getPostfix());
				a.evaluateRPN();
                                System.out.println();
                                int y = a.getResult();
                                if (y!=0)
                                {
                                System.out.println( "The answer is : "+y);
                                }
                                else 
                                {
                                    System.out.println("Must have been an"
                                            + " invalid statement");
                                }
                                // a.getResult();

				/**
				 * Complete the code so that it expresses the result of the evaluation
				*/
			}
			else
				System.out.println("Expression is not balanced\n");
		}
	}
}
