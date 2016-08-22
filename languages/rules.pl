cword(List,Theword):-
	word(List),
	List=Theword.
diff(X,Y) :-
	X\=Y.
swapletters(Word,Correctword) :-
	append(A,[X,Y|Z],Word),
	append(A,[Y,X|Z],Correctword),
	word(Correctword).
exchangeletters(Word,Correctword) :-
	append(A,[X|BC],Word),
	append(Y,[Z|Z1],BC),
	append(A,[Z|NBC],Correctword),
	append(Y,[X|Z1],NBC),
	word(Correctword).
correct(Word,Correctword) :-
	append(X,[_|Z],Word),
	append(X,[_|Z],Correctword),
	word(Correctword).
char_after(W,CW):-
	append(D,[X|C],W),
	append(A,B,D),
	append(A,[X|B],D1),
	append(D1,C,CW),
	word(CW).
one_more([_,H1|T],[H1|T]):-
	word([H1|T]).
one_more(W,CW):-
	append(H,[_|T],W),
	append(H,T,CW),
	word(CW).
two_more([_,_,H3|T],[H3|T]):-
	word([H3|T]).
two_more(W,CW):-
	append(A,[_|B1],W),
	append(C,[_|D1],B1),
	append(A,C,CW1),
	append(CW1,D1,CW),
	word(CW).
adding(Phrase,Path):-
	append(Path),nl,
	write(Phrase),
	told.