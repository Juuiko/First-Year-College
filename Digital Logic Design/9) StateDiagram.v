module StateDiagram (output reg y_out, input x_in, clk, reset_b);
reg [2: 0] state, next_state;

parameter S0 = 3'b000, S1 = 3'b001, S2 = 3'b010, S3 = 3'b011, S4 = 3'b100, S5 = 3'b101;

always @ ( posedge clk, negedge reset_b)
	if (reset_b == 1'b0) state <= S0;
	else state <= next_state;

always @ (state, x_in)
	case (state)
		S0: if (x_in==1'b1) next_state = S0; else next_state = S1;
		S1: if (x_in==1'b1) next_state = S2; else next_state = S1;
		S2: next_state = S3;
		S3: next_state = S4;
		S4: next_state = S5;
		S5: if (x_in==1'b1) next_state = S0; else next_state = S1;
	endcase

always @ (state, x_in)
	case(state)
		S0: y_out = 1'b0;
		S1: y_out = 1'b0;
		S2: y_out = 1'b1;
		S3: y_out = 1'b1;
		S4: y_out = 1'b1;
		S5: y_out = 1'b0;
	endcase
endmodule
