module BCD_Adder (Sum, Carry_out, Addend, Augend, Carry_in);
	input [3:0]	Addend,Augend;
	input Carry_in;

	output reg [3:0]	Sum;
	output reg Carry_out;
	wire [3:0] wZ,wIn,wSum_out;
	wire wCarry_out,wAnd1,wAnd2,wOuput_carry,wFill;

	fba f1 (wZ, wCarry_out, Addend, Augend, Carry_in);

	and
	 (wAnd1,wZ[3],wZ[2]),
	 (wAnd2,wZ[3],wZ[1]);
	or	(wOuput_carry,wAnd1,wAnd2,wCarry_out);

	assign	wIn[3] = 0;
	assign	wIn[2] = wOuput_carry;
	assign	wIn[1] = wOuput_carry;
	assign	wIn[0] = 0;

	fba f2 (wSum_out,wFill, wIn, wZ,0);

	always @ ( * )
		begin
		{Sum}={wSum_out};
		Carry_out=wOuput_carry;
	end
endmodule


module fba (Sum, Carry_out, A, B, Carry_in);
	input [3:0]	A,B;
	input Carry_in;
	output [3:0]	Sum;
	output Carry_out;

	assign{Carry_out, Sum} = Carry_in + A + B;
endmodule
