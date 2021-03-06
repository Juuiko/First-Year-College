module Serial_Twos_Comp (output y, input [7: 0] data, input load, shift_control, Clock, reset_b);
reg [7:0] shift_reg;
wire [7:0] nextShift;
reg nextBit, f_out;
wire f_in;

always@(posedge Clock or negedge reset_b) begin
	if(reset_b==0) begin
		shift_reg <= 0;
		f_out <=0;
		nextBit <=0;
	end
	else if (load) begin
		shift_reg <=data;
	end
	else if (shift_control) begin
		shift_reg = nextShift;
		nextBit <= shift_reg[0];
		f_out <= f_in;
	end
end

assign f_in = nextBit || f_out;
assign y = f_out ^ nextBit;
assign nextShift[6:0] = shift_reg[7:1];
assign nextShift[7] = y;

endmodule
