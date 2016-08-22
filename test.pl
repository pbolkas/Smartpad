word([w,o,m,a,n]).
word([l,e,t,t,e,r]).
word([w,i,n,d,o,w,s]).

one_more([H,H1|T],[H1|T]):-
	word([H1|T]).
one_more(W,CW):-
	append(H,[H1|T],W),
	append(H,T,CW),
	word(CW).
correct(W,CW):-
	append(D,[X|C],W),
	append(A,B,D),
	append(A,[X|B],D1),
	append(D1,C,CW),
	word(CW).
two_more([H,H2,H3|T],[H3|T]):-
	word([H3|T]).
two_more(W,CW):-
	append(A,[B|B1],W),
	append(C,[D|D1],B1),
	append(A,C,CW1),
	append(CW1,D1,CW),
	word(CW).
	
	