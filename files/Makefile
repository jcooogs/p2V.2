###
# This Makefile can be used to make a scanner for the C-- language
# (Yylex.class) and to make a program that tests the scanner (P2.class).
#
# The default makes both the scanner and the test program.
#
# make clean removes all generated files.
#
# Note: P2.java will not compile unless Yylex.class exists.
#
###

# define the java compiler to be used and the flags
JC = javac
FLAGS = -g -cp $(CP) 
CP = ./deps:.

P2.class: P2.java Yylex.class sym.class
	$(JC) $(FLAGS) P2.java

Yylex.class: cminusminus.jlex.java ErrMsg.class sym.class
	$(JC) $(FLAGS) cminusminus.jlex.java

cminusminus.jlex.java: cminusminus.jlex sym.class
	java -cp $(CP) JLex.Main cminusminus.jlex

sym.class: sym.java
	$(JC) $(FLAGS) sym.java

ErrMsg.class: ErrMsg.java
	$(JC) $(FLAGS) ErrMsg.java

	
###
# testing - add more here to run your tester and compare its results
# to expected results
###
test:
	java -cp $(CP) P2 ##2> errors.out 
	diff allTokens.in allTokens.out
	diff Test1_Drew_Expected.out Test1_Drew_Actual.out
	diff Test2_Drew_Expected.out Test2_Drew_Actual.out
	diff Test3_Drew_Expected.out Test3_Drew_Actual.out
	diff Test4_Drew_Expected.out Test4_Drew_Actual.out
	diff Test5_Drew_Expected.out Test5_Drew_Actual.out
	diff Test6_Drew_Expected.out Test5_Drew_Actual.out
	##diff errors.out expected_errors.out
	

###
# clean up
###

clean:
	rm -f *~ *.class cminusminus.jlex.java

cleantest:
	rm -f allTokens.out
	rm -f Test1_Drew_Actual.out
	rm -f Test2_Drew_Actual.out
	rm -f Test3_Drew_Actual.out
	rm -f Test4_Drew_Actual.out
	rm -f Test5_Drew_Actual.out
	rm -f Test6_Drew_Actual.out
	
	
