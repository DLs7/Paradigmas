% ------------------- 01 -------------------

add10toall([], []).
add10toall(L1, L2) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    H2 is H1 + 10,
    add10toall(T1, T2).


% ------------------- 02 -------------------

multN(_, [], []).
multN(N, L1, L2) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    H2 is H1 * N,
    multN(N, T1, T2).


% ------------------- 03 -------------------

applyExpr([], []).
applyExpr(L1, L2) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    H2 is (3 * H1) + 2,
    applyExpr(T1, T2).


% ------------------- 04 -------------------

addSuffix(_, [], []).
addSuffix(S, L1, L2) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    string_concat(H1, S, H2),
    addSuffix(S, T1, T2).


% ------------------- 05 -------------------

selectgt5([], []).
selectgt5([H1|T1], L2) :-
    (H1 > 5 -> L2 = [H1|T2], selectgt5(T1, T2));
    selectgt5(T1, L2).


% ------------------- 06 -------------------

odd(N) :-
    X is mod(N,2),
    X =:= 1.

sumOdds([], 0).
sumOdds([H1|T1], N) :-
    odd(H1),
    sumOdds(T1, M),
    N is M + H1.
sumOdds([H1|T1], N) :-
    not(odd(H1)),
    sumOdds(T1, N).


% ------------------- 07 -------------------

selectExpr([], []).
selectExpr([H1|T1], L2) :-
    (not(odd(H1)), H1 >= 20, H1 =< 50 -> L2 = [H1|T2], selectExpr(T1, T2));
    selectExpr(T1, L2).


% ------------------- 08 -------------------

countShortsList([], []).
countShortsList([H1|T1], L2) :-
    string_length(H1, X),
    (X < 5 -> L2 = [H1|T2], countShortsList(T1, T2));
    countShortsList(T1,L2), !.

countShorts([], 0).
countShorts(L1, N) :-
    countShortsList(L1, L2),
    length(L2, N).

% ------------------- 09 -------------------

calcExpr([], []).
calcExpr([H1|T1], L2) :-
    X is (H1 ** 2) / 2,
    (X > 10 -> L2 = [H1|T2], calcExpr(T1, T2));
    calcExpr(T1, L2), !.


% ------------------- 10 -------------------

changeTrSpaces([], []).
changeTrSpaces([H1|T1], L2) :-
    (H1 =:= 32 -> L2 = [45|T2], changeTrSpaces(T1, T2));
    L2 = [H1|T2],
    changeTrSpaces(T1,T2).

trSpaces(S1, S2) :-
    string_to_list(S1, L1),
    changeTrSpaces(L1, L2),
    string_to_list(S2, L2).