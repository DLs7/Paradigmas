%    ~ tema de filme noir tocando ao fundo ~
%        ~ essa aqui especificamente ~
% ~ https://www.youtube.com/watch?v=jMbp1YGCoB4 ~



% VÍTIMA DO CASO
% essa é a nossa vítima, anita.
% esse programa apresentará a possível solução para o seu cruel assassinato.
vitima(anita).

% INSANIDADE
% essas pessoas tem alguma relação com a cena do crime e apresentam um histórico de insanidade.
% provavelmente a loucura os levou a cometer tal atentado.
insano(adriano).
insano(maria).

% POBREZA
% essas pessoas tem alguma relação com o cena do crime e apresentam um histórico de pobreza.
% provavelmente a ganância os levou a cometer tal atentado.
pobre(bernardo).
pobre(bia).
pobre(pedro).
pobre(maria).

% RIQUEZA
% essas pessoas tem alguma relação com o cena do crime e apresentam um histórico de riqueza.
% provavelmente a ganância os levou a cometer tal atentado.
rico(caren).
rico(alice).
rico(henrique).
rico(adriano).



% LIGAÇÕES
% através dessas correlações podemos desenvolver um perfil sobre os relacionamentos dos suspeitos entre si e com a vítima.
ligacao(bernardo, anita).
ligacao(anita, bernardo).

ligacao(bernardo, caren).
ligacao(caren, bernardo).

ligacao(anita, pedro).
ligacao(pedro, anita).

ligacao(pedro, alice).
ligacao(alice, pedro).

ligacao(henrique, alice).
ligacao(alice, henrique).

ligacao(henrique, maria).
ligacao(maria, henrique).

ligacao(adriano, maria).
ligacao(maria, adriano).

ligacao(adriano, caren).
ligacao(caren, adriano).



% LOCAL
% a partir desse histórico, traçamos onde os suspeitos estavam durante a semana que o assassinato ocorreu.
% extraímos quem estava, onde essa pessoa estava e que dia ela estava nesse local
estadia(pedro, sma, seg).
estadia(pedro, sma, ter).
estadia(pedro, poa, qua).
estadia(pedro, sma, qui).
estadia(pedro, apt, sex).

estadia(caren, poa, seg).
estadia(caren, poa, ter).
estadia(caren, poa, qua).
estadia(caren, sma, qui).
estadia(caren, apt, sex).

estadia(henrique, apt, seg).
estadia(henrique, poa, ter).
estadia(henrique, apt, qua).
estadia(henrique, apt, qui).
estadia(henrique, apt, sex).

estadia(bia, apt, seg).
estadia(bia, poa, ter).
estadia(bia, poa, qua).
estadia(bia, sma, qui).
estadia(bia, apt, sex).

estadia(adriano, apt, seg).
estadia(adriano, apt, ter).
estadia(adriano, sma, qua).
estadia(adriano, apt, qui).
estadia(adriano, apt, sex).

estadia(alice, apt, seg).
estadia(alice, poa, ter).
estadia(alice, poa, qua).
estadia(alice, apt, qui).
estadia(alice, apt, sex).

estadia(bernardo, sma, seg).
estadia(bernardo, sma, ter).
estadia(bernardo, poa, qua).
estadia(bernardo, sma, qui).
estadia(bernardo, apt, sex).

estadia(maria, apt, seg).
estadia(maria, sma, ter).
estadia(maria, sma, qua).
estadia(maria, sma, qui).
estadia(maria, apt, sex).



% ASSASSINO
% a partir desse predicado, podemos inferir quem estava no apartamento nos dias em que o assassinato possa ter ocorrido.
apartamento(X) :-
    estadia(X, apt, qui), !;
    estadia(X, apt, sex), !.



% ARMA
% com esse predicado, inferimos qual dos suspeitos possa ter roubado o bastão, que é possivelmente uma arma do crime.
bastao(X) :-
    estadia(X, sma, qua);
    estadia(X, poa, qui).

% com esse predicado, inferimos qual dos suspeitos possa ter roubado o martelo, que é possivelmente uma arma do crime.
martelo(X) :-
    estadia(X, apt, qua);
    estadia(X, apt, qui).

%
arma(X) :-
    bastao(X);
    martelo(X).



% CHAVE PARA O APARTAMENTO
% com esse predicado, inferimos qual dos suspeitos possa ter roubado a chave que concedia acesso ao apartamento da vítima.
chave(X) :-
    estadia(X, sma, seg);
    estadia(X, poa, ter).

% bia não necessariamente roubou a chave, mas possui uma cópia dela.
% essa informação pode ser de importância.
chave(bia).


% MOTIVAÇÃO

% CIÚMES
% a partir desse predicado, podemos definir a partir das relações dos nossos suspeitos, qual deles poderiam ter ciúmes de anita.
ciumes(X) :-
    ligacao(anita, Y),
    ligacao(X, Y).

% INSANIDADE
% esse predicado nos dá aqueles com histórico de insanidade, uma das possíveis causas do crime.
insanidade(X) :-
    insano(X).

% DINHEIRO
% a partir desse predicado, inferimos quais pessoas poderiam ter sido motivadas a cometer o crime por inveja do dinheiro de anita.
dinheiro(X) :-
    pobre(X).

% DECISÃO FINAL
motivo(X) :-
    ciumes(X);
    insanidade(X);
    dinheiro(X).



% RESOLUÇÃO
% aqui definimos qual suspeito teve acesso ao apartamento, à arma e à chave no dia do crime.
acesso(X) :-
    apartamento(X),
    arma(X),
    chave(X).

% finalmente, definiremos o assassino, verificando se seus motivos são válidos e se o mesmo tem acesso ao apartamento de anita.
assassino(X) :-
    motivo(X),
    acesso(X).