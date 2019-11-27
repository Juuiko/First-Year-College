`timescale 1ns / 100ps

module OddFunction (
	output A,
	input x, y, clk, reset_b);
	wire w1,wD;
	reg state, next_state;
	parameter S0 = 1'b0, S1 = 1'b1;

	always @ ( posedge clk, negedge reset_b )
	if (reset_b == 0) state = S0;
	else
		case (state)
			S0: if (x!=y) state = S1; else state = S0;
			S1: if (x!=y) state = S0; else state = S1;
		endcase
		assign A = state;
endmodule
