% ------------------- 01 -------------------

isVowel(C) :-
    C = a, !;
    C = e, !;
    C = i, !;
    C = o, !;
    C = u, !;
    C = 'A', !;
    C = 'E', !;
    C = 'I', !;
    C = 'O', !;
    C = 'U'.


% ------------------- 02 -------------------

addComma([], []).
addComma(L1, L2) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    string_concat(H1, ",", H2),
    addComma(T1, T2).


% ------------------- 03 -------------------

htmlListItems([], []).
htmlListItems(L1, L2) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    string_concat("<LI>", H1, S),
    string_concat(S, "</LI>", H2),
    htmlListItems(T1, T2).


% ------------------- 04 -------------------

removeVowels([], []).
removeVowels([H1|T1], L2) :-
    (not(isVowel(H1)) -> L2 = [H1|T2], removeVowels(T1, T2));
    removeVowels(T1, L2).

semVogais(S1, S2) :-
    string_chars(S1, L1),
    removeVowels(L1, L2),
    string_chars(S2, L2).


% ------------------- 05 -------------------

codificacao([], []).
codificacao([H1|T1], L2) :-
    (H1 =:= 32 -> L2 = [H1|T2], codificacao(T1 ,T2));
    L2 = [45|T2],
    codificacao(T1, T2).

codifica(S1, S2) :-
    string_to_list(S1, L1),
    codificacao(L1, L2),
    string_to_list(S2, L2).

% ------------------- 06 -------------------

firstName(S1, S2) :-
    split_string(S1, " ", "", L1),
    L1 = [H1|_],
    S2 = H1.

% ------------------- 07 -------------------

isNumber(C) :-
    C = '1', !;
    C = '2', !;
    C = '3', !;
    C = '4', !;
    C = '5', !;
    C = '6', !;
    C = '7', !;
    C = '8', !;
    C = '9', !;
    C = '0'.

verifyInt([], []).
verifyInt([H1|T1], L2) :-
    (not(isNumber(H1)) -> L2 = [H1|T2], verifyInt(T1, T2));
    verifyInt(T1, L2).

isInt(S1) :-
    string_chars(S1, L1),
    verifyInt(L1, L2),
    string_chars(S2, L2),
    string_length(S2, N),
    N = 0.


% ------------------- 08 -------------------

lastName(S1, S2) :-
    split_string(S1, " ", "", L1),
    last(L1, S2).


% ------------------- 09 -------------------

userName(S1, S2) :-
    firstName(S1, SAUX1),
    string_lower(SAUX1, SAUX2),
    string_chars(SAUX2, L1),
    L1 = [H1|_],
    atom_string(H1, SF),
    lastName(S1, SAUX3),
    string_lower(SAUX3, SAUX4),
    string_concat(SF, SAUX4, S2).


% ------------------- 10 -------------------

vowelChanger(C1, C2) :-
    C1 = a, C2 = '0', !;
    C1 = e, C2 = '1', !;
    C1 = i, C2 = '2', !;
    C1 = o, C2 = '3', !;
    C1 = u, C2 = '4', !;
    C1 = 'A', C2 = '0', !;
    C1 = 'E', C2 = '1', !;
    C1 = 'I', C2 = '2', !;
    C1 = 'O', C2 = '3', !;
    C1 = 'U', C2 = '4'.

encoder([], []).
encoder([H1|T1], L2) :-
    (isVowel(H1) -> vowelChanger(H1, C), L2 = [C|T2], encoder(T1, T2));
    L2 = [H1|T2],
    encoder(T1, T2).

encodeName(S1, S2) :-
    string_chars(S1, L1),
    encoder(L1, L2),
    string_chars(S2, L2).


% ------------------- 11 -------------------

isVowel_(C) :-
    C = a, !;
    C = e, !;
    C = i, !;
    C = o, !;
    C = 'A', !;
    C = 'E', !;
    C = 'I', !;
    C = 'O'.

betterVowelChanger(C1, C2) :-
    C1 = a, C2 = '4', !;
    C1 = e, C2 = '3', !;
    C1 = i, C2 = '1', !;
    C1 = o, C2 = '0', !;
    C1 = 'A', C2 = '4', !;
    C1 = 'E', C2 = '3', !;
    C1 = 'I', C2 = '1', !;
    C1 = 'O', C2 = '0'.

encoder_([], []).
encoder_([H1|T1], L2) :-
    (isVowel_(H1) -> betterVowelChanger(H1, C), L2 = [C|T2], encoder_(T1, T2));
    L2 = [H1|T2],
    encoder_(T1, T2).

encoderu([_], []).
encoderu([H1|T1], L2) :-
    (T1 \= [] -> string_concat(H1, "00", S), L2 = [S|T2], encoderu(T1, T2)).

godIHateWorkingWithStringInProlog(S1, S2, H1) :-
    split_string(S1, H1, "", L1),
    encoderu(L1, L2),
    last(L1, X),
    Y = [X],
    append(L2,Y,L3),
    atomic_list_concat(L3, A),
    atom_string(A, S2), !.

betterEncodeName(S1, S2) :-
    string_chars(S1, L1),
    encoder_(L1, L2),
    string_chars(SAUX, L2),
    godIHateWorkingWithStringInProlog(SAUX, SAUX2, "u"),
    godIHateWorkingWithStringInProlog(SAUX2, S2, "U").
    

    % ------------------- 12 -------------------

funcAdd([], [], 0).
funcAdd(L1, L2, X) :-
    X < 10, !,
    append(L1, ['.'], LAUX),
    Y is X + 1,
    funcAdd(LAUX, L2, Y);
    L2 = L1.

take(N, _, Xs) :- 
    N =< 0, !,
    N =:= 0,
    Xs = [].
take(_, [], []).
take(N, [X|Xs], [X|Ys]) :- 
    M is N-1,
    take(M, Xs, Ys).

func(S1, S2) :-
    string_length(S1, X),
    string_chars(S1, L1),
    X < 10, !,
    funcAdd(L1, L2, X), 
    string_chars(S2, L2);
    string_chars(S1, L1),
    take(10, L1, L2),
    string_chars(S2, L2).