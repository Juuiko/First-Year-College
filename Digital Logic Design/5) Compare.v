module Compare (A,B,Y);
	output reg [5:0]	Y;
	input [0:3]	A, B;

	always @ (*)
		begin
		if({A} == {B}) Y[5]=1;
		else Y[5]=0;
		if({A} != {B}) Y[4]=1;
		else Y[4]=0;
		if({A} >  {B}) Y[3]=1;
		else Y[3]=0;
		if({A} <  {B}) Y[2]=1;
		else Y[2]=0;
		if({A} >= {B}) Y[1]=1;
		else Y[1]=0;
		if({A} <= {B}) Y[0]=1;
		else Y[0]=0;
		end
endmodule
