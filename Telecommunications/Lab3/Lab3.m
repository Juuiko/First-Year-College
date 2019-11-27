%%%%%%%%%%%%%    QUESTION 1    %%%%%%%%%%%%%
clear variables;
fs=10000;
frequency=10;
time=20*(1/frequency);
x=0:1/fs:time-1/fs;                            %X-axis range and all values for X
y=sin(2*pi*frequency*x);
subplotIndex=1;
for N=[64 128 256]
    fourier=fft(y,N);
    F=fftshift(abs(fourier));
    newX=-fs/2:fs/N:(fs/2)-(fs/N);
    subplot(3,1,subplotIndex);
    plot(newX,F); hold on;
    subplotIndex=subplotIndex+1;
end


%%%%%%%%%%%%%    QUESTION 2    %%%%%%%%%%%%%
clear variables;
figure;
load('array.mat');

fs=1000;
N=1024;
fourier=fft(y,N);
F=fftshift(abs(fourier));
newX=-fs/2:fs/N:(fs/2)-(fs/N);
plot(newX,F);
%print("102Hz & 50Hz");


%%%%%%%%%%%%%    QUESTION 3    %%%%%%%%%%%%%
clear variables;
figure;
[notes, fs]=audioread('exercise notes.wav');

note1=notes(1:6600);
note2=notes(6600+1:end);

N=16384;
x=-fs/2:fs/N:(fs/2)-(fs/N);
fourier=fft(note1,N);
F=fftshift(abs(fourier));
subplot(2,1,1);
plot(x,F);
fourier=fft(note2,N);
F=fftshift(abs(fourier));
subplot(2,1,2);
plot(x,F);