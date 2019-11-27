[signal,sampling]=audioread('exercise2_piece.wav');
sound(ammod(signal,30000,sampling), sampling);
%sound(signal, sampling);