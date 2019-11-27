module AdderSub (
	output [0:3]	sum_diff,
	output carry,
	input [0:3]	A,B,
	input select);

	assign{carry, sum_diff} = select ? (A - B) : (A + B);
endmodule
