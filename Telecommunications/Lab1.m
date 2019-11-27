x=-2*pi:0.1:2*pi;
y=cos(x);
z=0.5*sin(x);
plot(x,y, 'k'); hold on;
stem(x,z, 'r');

figure;
x=-2*pi:0.01:2*pi;
y=sin(x);
y=y+0.2*randn(size(x));
plot(x,y, 'b');