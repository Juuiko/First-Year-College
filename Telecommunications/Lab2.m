%%%%%%%%%%%%%    QUESTION 1    %%%%%%%%%%%%%

clear variables;
clear all;
t=-1:0.01:1;                            %X-axis range and all values for X
y1=square(2*pi*t);                      %plot square wave to compare with

y2=4/pi*sin(2*pi*t);                    %Fourier series of a square wave
subplot(3,2,1);                         %3x2 grid and plot in the 1st square
plot(t,y1, 'r'); hold on;               %plot the square wave
plot(t,y2, 'b');                        %plot the square wave approximation
y2=0;                                   %reset y2 variable
ylim([-2 2])                            %Y-axis range

for k=1:2:5                             %SUM(k=1,k=odd,kMAX=5(3 functions))
   yLoop=(4/pi)*(sin(2*pi*k*t))/k;      %Fourier series of a square wave
   y2=y2+yLoop;                         %add the prev funtions to the latest one
end
subplot(3,2,2);                         %3x2 grid and plot in the 2nd square
plot(t,y1, 'r'); hold on;               %plot the square wave
plot(t,y2, 'b');                        %plot the square wave approximation
y2=0;                                   %reset y2 variable
ylim([-2 2])                            %Y-axis range

for k=1:2:9
   yLoop=(4/pi)*(sin(2*pi*k*t))/k;
   y2=y2+yLoop;
end
subplot(3,2,3);
plot(t,y1, 'r'); hold on;
plot(t,y2, 'b');
y2=0;
ylim([-2 2])

for k=1:2:19
   yLoop=(4/pi)*(sin(2*pi*k*t))/k;
   y2=y2+yLoop;
end
subplot(3,2,4);
plot(t,y1, 'r'); hold on;
plot(t,y2, 'b');
y2=0;
ylim([-2 2])

for k=1:2:199
   yLoop=(4/pi)*(sin(2*pi*k*t))/k;
   y2=y2+yLoop;
end
subplot(3,2,5);
plot(t,y1, 'r'); hold on;
plot(t,y2, 'b');
y2=0;
ylim([-2 2])

for k=1:2:1999
   yLoop=(4/pi)*(sin(2*pi*k*t))/k;
   y2=y2+yLoop;
end
subplot(3,2,6);
plot(t,y1, 'r'); hold on;
plot(t,y2, 'b');
y2=0;
ylim([-2 2])

%%%%%%%%%%%%%    QUESTION 2    %%%%%%%%%%%%%

clear variables;
clear all;
figure;
t=-1:0.01:1;                            %X-axis range and all values for X
y1=sawtooth(2*pi*(t+0.25),0.5);         %plot sawtooth wave to compare with

k=0;
y2=8/pi^2*(-1)^k*(sin(2*pi*(2*k+1)*t))/(2*k+1)^2;
subplot(3,2,1);
plot(t,y1, 'r'); hold on;
plot(t,y2, 'b');
y2=0;

for k=0:1:1                             %SUM(k=0,kMAX=1(2 functions))
    yLoop=8/pi^2*(-1)^k*(sin(2*pi*(2*k+1)*t))/(2*k+1)^2;    %Fourier series of a square wave
    y2=y2+yLoop;                        %add the prev funtions to the latest one
end
subplot(3,2,2);                         %3x2 grid and plot in the 2nd square
plot(t,y1, 'r'); hold on;               %plot the sawtooth wave
plot(t,y2, 'b');                        %plot the sawtooth wave approximation
y2=0;                                   %reset looping variable

for k=0:1:2
    yLoop=8/pi^2*(-1)^k*(sin(2*pi*(2*k+1)*t))/(2*k+1)^2;
    y2=y2+yLoop;
end
subplot(3,2,3);
plot(t,y1, 'r'); hold on;
plot(t,y2, 'b');
y2=0;

for k=0:1:4
    yLoop=8/pi^2*(-1)^k*(sin(2*pi*(2*k+1)*t))/(2*k+1)^2;
    y2=y2+yLoop;
end
subplot(3,2,4);
plot(t,y1, 'r'); hold on;
plot(t,y2, 'b');
y2=0;

for k=0:1:9
    yLoop=8/pi^2*(-1)^k*(sin(2*pi*(2*k+1)*t))/(2*k+1)^2;
    y2=y2+yLoop;
end
subplot(3,2,5);
plot(t,y1, 'r'); hold on;
plot(t,y2, 'b');
y2=0;

for k=0:1:49
    yLoop=8/pi^2*(-1)^k*(sin(2*pi*(2*k+1)*t))/(2*k+1)^2;
    y2=y2+yLoop;
end
subplot(3,2,6);
plot(t,y1, 'r'); hold on;
plot(t,y2, 'b');
y2=0;

%%%%%%%%%%%%%    QUESTION 3    %%%%%%%%%%%%%

clear variables;
clear all;
figure;                                                 
x=1;                                                      
y=(4/(1*pi));
subplot(3,2,1);
stem(x,y);
x=0;

for k=1:2:5                             %SUM(k=1,k=odd,kMAX=5(3 functions))
   y=(4/(k*pi));                        %amplitude modified by k
   x=x+1;                               %go to next function
   subplot(3,2,2);                      %3x2 grid and plot in the 2nd square
   stem(x,y, 'b'); hold on;             %plot the current function and dno't discard the prev function amplitudes
end
x=0;                                    %reset x for next loop

for k=1:2:9
   y=(4/(k*pi));
   x=x+1;
   subplot(3,2,3);
   stem(x,y, 'b'); hold on;
end
x=0;

for k=1:2:19
   y=(4/(k*pi));
   x=x+1;
   subplot(3,2,4);
   stem(x,y, 'b'); hold on;
end
x=0;

for k=1:2:99
   y=(4/(k*pi));
   x=x+1;
   subplot(3,2,5);
   stem(x,y, 'b'); hold on;
end
x=0;

for k=1:2:999
   y=(4/(k*pi));
   x=x+1;
   subplot(3,2,6);
   stem(x,y, 'b'); hold on;
end