% ------------------- 01 --------------------

odd(N) :-
    X is mod(N,2),
    X =:= 1.


% ------------------- 02 --------------------

hasN([],0).
hasN(L,N) :-
    L = [_|T],
    N1 is N - 1,
    hasN(T,N1), !.


% ------------------- 03 --------------------

inc99([],[]).
inc99(L1,L2) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    H2 is H1 + 99,
    inc99(T1,T2).


% ------------------- 04 --------------------

incN([],[],_).
incN(L1,L2,N) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    H2 is H1 + N,
    incN(T1,T2,N).


% ------------------- 05 --------------------

comment([],[]).
comment(L1,L2) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    string_concat("%%",H1,H2),
    comment(T1,T2).


% ------------------- 06 --------------------

onlyEven([],[]).
onlyEven([H|T],LE) :-
    (not(odd(H)) -> LE = [H|TE], onlyEven(T,TE));
    onlyEven(T,LE).


% ------------------- 07 --------------------

countdown(0,[]).
countdown(N,[H|T]) :-
    N > 0,
    H is N,
    N1 is N-1,
    countdown(N1,T).


% ------------------- 08 --------------------

nRandoms(0,[]).
nRandoms(N,[H|T]) :-
    N > 0,
    random_between(0,100,H),
    N1 is N-1,
    nRandoms(N1,T).


% ------------------- 09 --------------------

potN0(-1,[]).
potN0(N,[H|T]) :-
    N > -1,
    H is 2 ^ N,
    N1 is N - 1,
    potN0(N1,T).


% ------------------- 10 --------------------

zipmult([],[],[]).
zipmult(L1,L2,L3) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    L3 = [H3|T3],
    H3 is H1 * H2,
    zipmult(T1,T2,T3).


% ------------------- 11 --------------------

potencias_(NF,NF,[]).
potencias_(NI,NF,[H|T]) :-
    NI < NF,
    H is 2 ^ NI,
    X is NI + 1,
    potencias_(X,NF,T).

potencias(N,L) :-
    potencias_(0,N,L).


% ------------------- 12 --------------------

cedulas(_,[],[]).
cedulas(V,L1,L2) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    H2 is div(V,H1),
    NV is V - H2 * H1,
    cedulas(NV,T1,T2).