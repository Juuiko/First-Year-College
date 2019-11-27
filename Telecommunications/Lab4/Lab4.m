%%%%%%%%%%%%%    QUESTION 1    %%%%%%%%%%%%%
clear variables;
[signal,sampling]=audioread('exercise1_piece.wav');
N=2^17;
newX=-(sampling/2):sampling/N:(sampling/2)-(sampling/N);

fourier=fftshift(abs(fft(signal, N)));
subplot(3,1,1);
plot(newX, fourier);

AMS=ammod(signal, 30000,sampling);
fourier=fftshift(abs(fft(AMS, N)));
subplot(3,1,2);
plot(newX, fourier);

FDS=fmmod(signal, 30000,sampling, 10000);
fourier=fftshift(abs(fft(FDS, N)));
subplot(3,1,3);
plot(newX, fourier);



%%%%%%%%%%%%%    QUESTION 2    %%%%%%%%%%%%%
clear variables;
figure;
[signal,sampling]=audioread('exercise2_piece.wav');
N=2^22;
newX=-(sampling/2):sampling/N:(sampling/2)-(sampling/N);
%sound(signal, sampling);


%%%   Part 1   %%%
AMS=ammod(signal,30000,sampling); %#ok<*NASGU>
subplot(2,1,1);
plot(signal);
subplot(2,1,2);
plot(AMS);
%sound(AMS, sampling);

%noiseAM=(0.1*randn(size(AMS)))+AMS;
%sound(noiseAM, sampling);

%ADMS=amdemod(signal,30000,sampling);
%sound(ADMS, sampling);


% %%%   Part 2   %%%
% FMS=fmmod(signal,30000,sampling,10000);
% %sound(FDS, sampling);
% 
% noiseFM=(0.1*randn(size(FMS)))+FMS;
% %sound(noiseFM, sampling);
% 
% FDMS=fmdemod(signal,30000,sampling,20000);
% %sound(FDS, sampling);
% 
% 
% %%%   Part 3   %%%
% FMS2=fmmod(signal,30000,sampling,50000);
% %sound(FDS2, sampling);
% 
% noiseFM2=(0.1*randn(size(FMS2)))+FMS2;
% %sound(noiseFM2, sampling);
% 
% FDS2=fmdemod(signal,30000,sampling,50000);
% %sound(FDS2, sampling);
% 

% %%%   Part 4   %%%
% fourier=fftshift(abs(fft(AMS, N)));
% subplot(3,1,1);
% plot(newX, fourier);
% 
% fourier=fftshift(abs(fft(FMS, N)));
% subplot(3,1,2);
% plot(newX, fourier);
% 
% fourier=fftshift(abs(fft(FMS2, N)));
% subplot(3,1,3);
% plot(newX, fourier);