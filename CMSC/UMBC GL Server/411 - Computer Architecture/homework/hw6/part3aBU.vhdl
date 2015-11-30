-- part3a.vhdl   VHDL '93 version using entities from WORK library
--              basic five stage pipeline of MIPS architecture
--              The 411 course pipeline has the same five stages
--              IF Instruction Fetch includes PC and instruction memory
--              ID Instruction Decode and registers
--              EX Execution including the ALU Arithmetic Logic Unit
--              MEM data Memory
--              WB Write Back into registers
--
--              The signal naming convention uses the stage as a prefix
--              WORK library needs entities and architectures for
--              add32  and  bshift.
--
-- This self contained VHDL file defines:
--     a package declaration and body that defines functions and memory
--     a 32 bit and a 5 bit register entity with clock and clear inputs
--     an instruction memory entity and behavioral architecture
--     a data memory entity and behavioral architecture
--     a general register entity and behavioral architecture
--     multiplexer entities and behavioral architectures
--     equal comparator entities and circuit architectures
--     an incomplete ALU entity and schematic architecture

--     a top level entity, part3a, test bench
--     the architecture, schematic layout of the top level entity
--     the signals for interconnecting the entities
--     a clock generator process
--     the entities connected with signals (port maps)
--     a memory read process that reads "part3a.abs"
--     a print process that shows the registers in the pipeline each clock

library IEEE;
use IEEE.std_logic_1164.all;

package util_pkg is
  function to_integer(sig : std_logic_vector) return integer;

  -- main memory, a process reads a file to load memory
  subtype word is std_logic_vector(31 downto 0);
  type mem_array is array(integer range <>) of word;
  shared variable memory: mem_array(0 to 4095);  -- max 12 bit addresses

  -- general register memory
  type reg_mem_type is array (natural range <>) of word;
  shared variable reg_mem : reg_mem_type(0 to 31) := (others =>(others =>'0'));
end package util_pkg;

package body util_pkg is
  function to_integer(sig : std_logic_vector) return integer is
    variable num : integer := 0;  -- descending sig as integer
  begin
    for i in sig'range loop
      if sig(i)='1' then
        num := num*2+1;
      else
        num := num*2;
      end if;
    end loop;  -- i
    return num;
  end function to_integer;
end package body util_pkg;


library IEEE;
use IEEE.std_logic_1164.all;

entity register_32 is
  port(clk    : in  std_logic;
       clear  : in  std_logic;
       input  : in  std_logic_vector (31 downto 0);
       output : out std_logic_vector (31 downto 0) );
end entity register_32;

architecture behavior of register_32 is
begin  -- behavior
  reg_32: process(clk, clear)
          begin
            if clear='1' then  -- only once
              output <= (others=>'0');
            elsif clk'event and clk='1' then
              output <= input after 250 ps;
            end if;
          end process reg_32;
end architecture behavior;  -- of register_32

library IEEE;
use IEEE.std_logic_1164.all;

entity register_5 is
  port(clk    : in  std_logic;
       clear  : in  std_logic;
       input  : in  std_logic_vector (4 downto 0);
       output : out std_logic_vector (4 downto 0) );
end entity register_5;

architecture behavior of register_5 is
begin  -- behavior
  reg_5: process(clk, clear)
         begin
           if clear='1' then  -- only once
             output <= (others=>'0');
           elsif clk'event and clk='1' then
             output <= input after 250 ps;
           end if;
         end process reg_5;
end architecture behavior;  -- of register_5


library IEEE;
use IEEE.std_logic_1164.all;
use WORK.util_pkg.all;

-- begin part3a here

entity instruction_memory is
  port(clear : in  std_logic;
       addr  : in  std_logic_vector (31 downto 0);
       inst  : out std_logic_vector (31 downto 0));
end entity instruction_memory;

architecture behavior of instruction_memory is
begin  -- behavior
  inst_mem: process(addr, clear) 
              variable word_addr : natural;  -- byte addr/4
            begin
              if clear='1' then  -- total machine clear  
                inst <= x"00000000";
              else -- normal operation
                word_addr := to_integer(addr(13 downto 2)); -- crop to 12 bits
                inst <= memory(word_addr) after 250 ps;
              end if;
            end process inst_mem;
end architecture behavior;  -- of instruction_memory


library IEEE;
use IEEE.std_logic_1164.all;
use WORK.util_pkg.all;

entity data_memory is
  port(address      : in  std_logic_vector (31 downto 0);
       write_data   : in  std_logic_vector (31 downto 0);
       read_enable  : in  std_logic;  -- from address
       write_enable : in  std_logic;  -- rising clock and enable
       write_clk    : in  std_logic;  -- required to write
       read_data    : out std_logic_vector (31 downto 0));
end entity data_memory;

architecture behavior of data_memory is
begin  -- behavior
  data_mem: process(address, write_clk)
              variable word_addr : natural;  -- byte addr/4
            begin
              if write_enable='1' and write_clk='1' then
                word_addr := to_integer(address(13 downto 2));  -- 12 bits
                memory(word_addr) := write_data;  -- write main memory
                read_data <= write_data;  -- just something to output
              elsif read_enable='1' then
                word_addr := to_integer(address(13 downto 2));  -- 12 bits
                read_data <= memory(word_addr) after 250 ps;  -- read memory
              else
                read_data <= x"00000000";  -- just to clean up printout
              end if;
            end process data_mem;
end architecture behavior;  -- of data_memory


library IEEE;
use IEEE.std_logic_1164.all;
use WORK.util_pkg.all;

entity registers is
  port(read_reg_1   : in  std_logic_vector (4 downto 0); -- address
       read_reg_2   : in  std_logic_vector (4 downto 0); -- address
       write_reg    : in  std_logic_vector (4 downto 0); -- address
       write_data   : in  std_logic_vector (31 downto 0);
       write_enable : in  std_logic;  -- rising clock and enable
       write_clk    : in  std_logic;  -- required to write
       read_data_1  : out std_logic_vector (31 downto 0);
       read_data_2  : out std_logic_vector (31 downto 0));
end entity registers;

architecture behavior of registers is
begin  -- behavior
  reg: process(read_reg_1, read_reg_2, write_clk)
         variable reg_addr : natural;
       begin
         if write_enable='1' and write_clk'active and write_clk='1' then
           reg_addr := to_integer(write_reg);
           if reg_addr/=0 then           -- can not change register zero
             reg_mem(reg_addr) := write_data;
           end if;
         end if;
         read_data_1 <= reg_mem(to_integer(read_reg_1));
         read_data_2 <= reg_mem(to_integer(read_reg_2));
         -- signals updated after process exits
       end process reg;
end architecture behavior;  -- of registers

library IEEE;
use IEEE.std_logic_1164.all;

entity mux_32 is
  port(in0    : in  std_logic_vector (31 downto 0);
       in1    : in  std_logic_vector (31 downto 0);
       ctl    : in  std_logic;
       result : out std_logic_vector (31 downto 0));
end entity mux_32;

architecture behavior of mux_32 is 
begin  -- behavior -- no process needed with concurrent statements
  result <= in1 when ctl='1' else in0 after 250 ps;
end architecture behavior;  -- of mux_32

library IEEE;
use IEEE.std_logic_1164.all;
entity mux32_3 is
  port(in0    : in  std_logic_vector (31 downto 0);
       in1    : in  std_logic_vector (31 downto 0);
       in2    : in  std_logic_vector (31 downto 0);
       ct1    : in  std_logic;          -- pass in1(has priority)
       ct2    : in  std_logic;          -- pass in2
       result : out std_logic_vector (31 downto 0));
end entity mux32_3;

architecture behavior of mux32_3 is 
begin  -- behavior -- no process needed with concurrent statements
  result <= in1 when ct1='1' else in2 when ct2='1' else in0 after 50 ps;
end architecture behavior;  -- of mux32_3


library IEEE;
use IEEE.std_logic_1164.all;

entity mux_5 is
  port(in0    : in  std_logic_vector (4 downto 0);
       in1    : in  std_logic_vector (4 downto 0);
       ctl    : in  std_logic;
       result : out std_logic_vector (4 downto 0));
end entity mux_5;

architecture behavior of mux_5 is 
begin  -- behavior -- no process needed with concurrent statements
  result <= in1 when ctl='1' else in0 after 250 ps;
end architecture behavior;  -- of mux_5

library IEEE;
use IEEE.std_logic_1164.all;

entity equal32 is --  a 32-bit compare
  port(inst  : in  std_logic_vector(31 downto 0);
       test  : in  std_logic_vector(31 downto 0);
       equal : out std_logic);
end entity equal32;

architecture circuits of equal32 is
  signal temp : std_logic_vector(0 to 32) := (others=>'1');
begin  -- circuits
  t1: for I in 0 to 31 generate
        temp(I+1) <= (inst(I) xnor test(I)) and temp(I);
      end generate t1;
  equal <= temp(32);
end architecture circuits;  -- of equal32

library IEEE;
use IEEE.std_logic_1164.all;

entity equal6 is -- basically a 6-bit op code compare
  port(inst  : in  std_logic_vector(5 downto 0);
       test  : in  std_logic_vector(5 downto 0);
       equal : out std_logic);
end entity equal6;

architecture circuits of equal6 is
  signal temp : std_logic_vector(0 to 6) := (others=>'1');
begin  -- circuits
  t1: for I in 0 to 5 generate
        temp(I+1) <= (inst(I) xnor test(I)) and temp(I);
      end generate t1;
  equal <= temp(6);
end architecture circuits;  -- of equal6

library IEEE;
use IEEE.std_logic_1164.all;

entity equal5 is -- basically a 5-bit register number compare
  port(inst  : in  std_logic_vector(4 downto 0);
       test  : in  std_logic_vector(4 downto 0);
       equal : out std_logic);
end entity equal5;

architecture circuits of equal5 is
  signal temp : std_logic_vector(0 to 5) := (others=>'1');
begin  -- circuits
  t1: for I in 0 to 4 generate
        temp(I+1) <= (inst(I) xnor test(I)) and temp(I);
      end generate t1;
  equal <= temp(5);
end architecture circuits;  -- of equal5


library IEEE;
use IEEE.std_logic_1164.all;

entity alu_32 is
  port(inA    : in  std_logic_vector (31 downto 0);
       inB    : in  std_logic_vector (31 downto 0);
       inst   : in  std_logic_vector (31 downto 0);
       result : out std_logic_vector (31 downto 0));
end entity alu_32;


architecture schematic of alu_32 is 
  signal cin       : std_logic := '0';
  signal cout      : std_logic;
  signal srlop     : std_logic;
  signal cmplop    : std_logic;
  signal andop     : std_logic;
  signal subop     : std_logic;
  signal sllop     : std_logic;
  signal rrop      : std_logic;
  signal rsop      : std_logic;
  signal rsll      : std_logic;
  signal rsrl      : std_logic;
  signal rsub      : std_logic;
  signal rcmp      : std_logic;
  signal rand      : std_logic;
  signal aresult   : std_logic_vector (31 downto 0);
  signal bmuxresult: std_logic_vector (31 downto 0);
  signal subOrcmp  : std_logic;
  signal sllOrsrl  : std_logic;
  signal bresult   : std_logic_vector (31 downto 0);
  signal notInB    : std_logic_vector (31 downto 0);
  signal andResult : std_logic_vector (31 downto 0);
  
begin  -- schematic
  --
  --   REPLACE THIS SECTION FOR PROJECT PART 2a
  --   (add the signals you need above "begin"
  --
  
  mux1: entity WORK.mux32_3 port map(in0 => aresult,
                                     in1 => andResult,
                                     in2 => bresult,
                                     ct1 => andop,
                                     ct2 => sllOrsrl,
                                     result => result);
  
  mux0: entity WORK.mux_32 port map(in0 => inB,
                                   in1 =>  notInB,
                                   ctl => subOrcmp,
                                   result => bmuxresult);

                                   --change bresult
  
  adder: entity WORK.add32 port map(a    => inA,
                                    b    => bmuxresult,
                                    cin  => subop,
                                    sum  => aresult,
                                    cout => cout);
  
  bsh: entity WORK.bshift port map(left => sllop,
                                   logical => '1',
                                   shift => inst(10 downto 6),
                                   input => inB,
                                   output => bresult);

  r1: entity WORK.equal6 port map(inst => inst(31 downto 26),
                                  test => "000000",
                                  equal => rrop);
  r2: entity WORK.equal6 port map(inst => inst(5 downto 0),
                                  test => "100010",
                                  equal => rsub);
  r3: entity WORK.equal6 port map(inst => inst(5 downto 0),
                                  test => "000010",
                                  equal => rsll);
  r4: entity WORK.equal6 port map(inst => inst(5 downto 0),
                                  test => "000011",
                                  equal => rsrl);
  r5: entity WORK.equal6 port map(inst => inst(5 downto 0),
                                  test => "001011",
                                  equal => rcmp);
  r6: entity WORK.equal6 port map(inst => inst(5 downto 0),
                                  test => "001010",
                                  equal => rand);

  a1: subop     <= rrop and rsub;
  a2: sllop     <= rrop and rsll;
  a3: srlop     <= rrop and rsrl;
  a4: cmplop    <= rrop and rcmp;
  a5: andop     <= rrop and rand;

  a6: subOrcmp    <= subop or cmplop;
  a7: sllOrsrl    <= sllop or srlop;
  a8: notInB    <= not inB;
  a9: andResult <= inB and inA;
  
end architecture schematic;  -- of alu_32


entity part3a is  -- test bench
end part3a;

library STD;
use STD.textio.all;
library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_textio.all;
use WORK.util_pkg.all;

architecture schematic of part3a is -- top level connection of entities
  
  -- signals used in top level architecture (the interconnections)
  
  subtype word_32 is std_logic_vector(31 downto 0);  -- data and inst
  subtype word_6 is std_logic_vector(5 downto 0);    -- op codes
  subtype word_5 is std_logic_vector(4 downto 0);    -- register numbers
  
  signal zero_32 : word_32 := (others=>'0');     -- 32 bits of zero
  signal zero    : std_logic := '0';             -- one bit zero
  signal four_32 : word_32 := x"00000004";       -- four
  
  signal lwop    : word_6 := "100011";     -- lw op code
  signal RRop    : word_6 := "000000";     -- RR op code
  signal swop    : word_6 := "101011";     -- sw op code
  signal addi    : word_6 := "000111";     -- addi op code 
  signal jpop    : word_6 := "000010";
  signal bqop    : word_6 := "100111";
  
  signal clear   : std_logic := '1';       -- one shot clear
  signal clk     : std_logic := '1';       -- master clock
  signal clk_bar : std_logic := '0';       -- complement of master clock
  signal counter : integer := 0;           -- master clock counter,raising edge
  signal nc1     : std_logic;              -- a No Connection for unused output
  signal nc2     : std_logic;
  
  signal PC_next        : word_32;            -- next value of PC 
  signal PC             : word_32;            -- Program Counter
  signal inst           : word_32;            -- instruction fetched
  
  signal ID_IR          : word_32;            -- ID Instruction Register
  signal ID_read_data_1 : word_32;            -- ID Register read data 1
  signal ID_read_data_2 : word_32;            -- ID Register read data 2
  signal ID_sign_ext    : word_32;            -- ID sign extension
  signal RegDst         : std_logic := '0';   -- ID register destination ctl
  signal old_ID_rd      : word_5;
  signal ID_rd          : word_5;             -- ID register destination
  alias  ID_addr        : std_logic_vector(15 downto 0) is ID_IR(15 downto 0);
  alias  ID_reg1        : word_5 is ID_IR(25 downto 21);
  alias  ID_reg2        : word_5 is ID_IR(20 downto 16);
  alias  ID_OP          : word_6 is ID_IR(31 downto 26);
  
  signal EX_IR          : word_32;            -- EX Instruction Register
  signal EX_A           : word_32;            -- EX data A
  signal EX_B           : word_32;            -- EX data B
  signal EX_C           : word_32;            -- EX data C
  signal EX_rd          : word_5;             -- EX register destination
  signal EX_aluB        : word_32;            -- EX into ALU B
  signal ALUSrc         : std_logic := '1';   -- EX ALU B side source control
  signal tempALU        : std_logic;
  signal EX_result      : word_32;            -- EX ALU output
  alias  EX_reg1        : word_5 is EX_IR(25 downto 21);
  alias  EX_reg2        : word_5 is EX_IR(20 downto 16);
  alias  EX_OP          : word_6 is EX_IR(31 downto 26);
  
  signal MEM_IR         : word_32;            -- MEM Inst Register
  signal MEM_addr       : word_32;            -- MEM address
  signal MEM_data       : word_32;            -- MEM write data
  signal MEM_read_data  : word_32;            -- MEM read data
  signal MEM_rd         : word_5;             -- MEM register destination
  signal MEMRead        : std_logic := '1';   -- MEM enable read
  signal MEMWrite       : std_logic := '0';   -- MEM enable write
  alias  MEM_OP         : word_6 is MEM_IR(31 downto 26);
  
  signal WB_IR          : word_32;            -- WB Instruction Register
  signal WB_read        : word_32;            -- WB read data
  signal WB_pass        : word_32;            -- WB pass data
  signal WB_rd          : word_5;             -- WB register destination
  signal WB_rd_zero     : std_logic;          -- WB_rd is zero, no value
  signal MemtoReg       : std_logic := '1';   -- WB mux control
  signal WB_result      : word_32;            -- WB mux output
  signal WB_write_enb   : std_logic := '1';   -- WB enable register write
  signal WB_lwop        : std_logic;          -- Have a LW in WB stage
  signal WB_rrop        : std_logic;
  signal WB_addiop      : std_logic;

  signal AFMA1, AFMA1_1, AFMA1_2, AFMA1_3: std_logic;
  signal AFMA2, AFMA2_1, AFMA2_2: std_logic;
  signal AFMA3, AFMA3_1, AFMA3_2, AFMA3_3: std_logic;
  signal AFMA4, AFMA4_1, AFMA4_2: std_logic;
  signal AFMA5, AFMA5_1, AFMA5_2, AFMA5_3: std_logic;
  signal AFMA6, AFMA6_1, AFMA6_2, AFMA6_3: std_logic;
  signal ALUMuxI        : std_logic_vector(31 downto 0);
  signal MuxMuxI        : std_logic_vector(31 downto 0);
  signal orJBS          : std_logic;
  signal D1Mux          : std_logic_vector(31 downto 0);
  signal D2Mux          : std_logic_vector(31 downto 0);
  signal muxEq32        : std_logic;
  signal shifted2       : std_logic_vector(31 downto 0);
  signal jpCmp, bqCmp, swCmp : std_logic;
  signal pcp            : std_logic_vector(31 downto 0);
  signal jump_addr      : std_logic_vector(31 downto 0);
  signal add32out       : std_logic_vector(31 downto 0);
  signal beqAnded       : std_logic;
  signal bqEqOp         : std_logic;
  signal jpEqOp         : std_logic;
  signal pcAddOut       : std_logic_vector(31 downto 0);
  signal muxT2aOut      : std_logic_vector(31 downto 0);

  -- part2b signals
  signal stall          : std_logic;
  signal sclk           : std_logic;
  signal stallEx        : std_logic_vector(31 downto 0);
  
  --stall lw
  signal stall_lw       : std_logic;
  signal slw1           : std_logic;
  signal slw2           : std_logic;
  signal slw3           : std_logic;
  signal slw3a          : std_logic;
  signal slw3b          : std_logic;
  signal slw4           : std_logic;
  signal slw5           : std_logic;
  signal slw6           : std_logic;

  --stall lwlw
  signal stall_lwlw     : std_logic;
  signal slwlw1         : std_logic;
  signal slwlw2         : std_logic;
  signal slwlw3         : std_logic;
  signal slwlw3a        : std_logic;
  signal slwlw3b        : std_logic;
  signal slwlw4         : std_logic;
  
  --stall lwbq
  signal stall_lwbq     : std_logic;
  signal slwbq1         : std_logic;
  signal slwbq2         : std_logic;
  signal slwbq3         : std_logic;
  signal slwbq4         : std_logic;
  signal slwbq4a        : std_logic;
  signal slwbq4b        : std_logic;
  
  --stall opbq
  signal stall_opbq     : std_logic;
  signal sopbq1         : std_logic;
  signal sopbq2         : std_logic;
  signal sopbq3         : std_logic;
  signal sopbq3a        : std_logic;
  signal sopbq3b        : std_logic;
  
  
begin  -- schematic of part2b, top level architecture and test bench
               
  clock_gen: process(clk, clear)  -- clock generator and one shot clear signal
             begin
               if clear='1' then -- happens only once
                 clear <= '0' after 200 ps;
               elsif clear='0' then     -- avoid time zero glitch
                 clk <= not clk after 5 ns;  -- 10 ns period
               end if;
             end process clock_gen;

             clk_bar <= not clk;               -- for split phase registers

-- IF, Instruction Fetch pipeline stage
             -- part2b change clk to sclk
  PC_reg:    entity WORK.register_32 port map(clk => sclk,
                                              clear => clear,
                                              input => PC_next,
                                              output => PC);
  --inA, inB, cin, result, cout
  PC_incr:   entity WORK.add32 port map(a => PC,
                                        b => four_32,
                                        cin => zero,
                                        sum => pcAddOut,
                                        cout => nc1);
  inst_mem:  entity WORK.instruction_memory port map(clear, PC, inst);

-- ID, Instruction Decode and register stack pipeline stage
             -- part2b change clk to sclk
  ID_IR_reg: entity WORK.register_32 port map(sclk, clear, inst, ID_IR);
  ID_regs:   entity WORK.registers port map(
                                read_reg_1   => ID_IR(25 downto 21),
                                read_reg_2   => ID_IR(20 downto 16),
                                write_reg    => WB_rd,
                                write_data   => WB_result,
                                write_enable => WB_write_enb,
                                write_clk    => clk_bar,
                                read_data_1  => ID_read_data_1,
                                read_data_2  => ID_read_data_2);

  regEqual : entity WORK.equal6 port map(ID_IR(31 downto 26), rrop, RegDST);
             -- RegDst <=   must compute ???

  ID_mux_rd: entity WORK.mux_5 port map(in0    => ID_IR(20 downto 16),
                                        in1    => ID_IR(15 downto 11),
                                        ctl    => RegDst,
                                        result => old_ID_rd);
             -- ! changed ID_rd to old_ID_rd
             ID_sign_ext(15 downto 0) <= ID_addr;  -- just wiring
             ID_sign_ext(31 downto 16) <= (others => ID_IR(15));
             
-- EX, Execute pipeline stage
             --part2b change ID_IR to stallEx
  EX_IR_reg: entity WORK.register_32 port map(clk, clear, stallEx, EX_IR);
  EX_A_reg : entity WORK.register_32 port map(clk, clear, ID_read_data_1,EX_A);
  EX_B_reg : entity WORK.register_32 port map(clk, clear, ID_read_data_2,EX_B);
  EX_C_reg : entity WORK.register_32 port map(clk, clear, ID_sign_ext, EX_C);
  EX_rd_reg: entity WORK.register_5  port map(clk, clear, ID_rd, EX_rd);
             
  aluEqual : entity WORK.equal6 port map(EX_IR(31 downto 26), rrop,
                                         tempALU);
             ALUSrc <= not tempALU;
             -- ALUSrc <=   must compute  ???

  EX_mux1  : entity WORK.mux_32 port map(in0    => MuxMuxI,
                                         in1    => EX_C,
                                         ctl    => ALUSrc,
                                         result => EX_aluB );
             -- might need to change this
             --inA => EX_A,     == ALUMuxI
             --inB => EX_aluB   == MuxMuxI
  ALU      : entity WORK.alu_32 port map(inA => ALUMuxI, 
                                         inB => EX_aluB,
                                         inst  => EX_IR,
                                         result=> EX_result);

-- MEM Data Memory pipeline stage
  MEM_IR_reg  : entity WORK.register_32 port map(clk, clear, EX_IR, MEM_IR);
  MEM_addr_reg: entity WORK.register_32 port map(clk, clear, EX_result,
                                                 MEM_addr);
             -- ++++ changed EX_B to MuxMuxI
  MEM_data_reg: entity WORK.register_32 port map(clk, clear,
                                                 MuxMuxI, MEM_data);
  MEM_rd_reg  : entity WORK.register_5  port map(clk, clear, EX_rd, MEM_rd);

  MEM_lw      : entity WORK.equal6 port map(MEM_IR(31 downto 26), lwop,
                                            MEMRead);

  writeMem    : entity WORK.equal6 port map(MEM_IR(31 downto 26),swop,
                                            MEMWrite);
                -- MEMWrite <=     must compute ???
             
  data_mem    : entity WORK.data_memory port map(address     => MEM_addr,
                                                 write_data  => MEM_data,
                                                 read_enable => MEMRead,
                                                 write_enable=> MEMWrite,
                                                 write_clk   => clk_bar,
                                                 read_data   => MEM_read_data);
             
-- WB, Write Back pipeline stage
  WB_IR_reg  : entity WORK.register_32 port map(clk, clear, MEM_IR, WB_IR);
  WB_read_reg: entity WORK.register_32 port map(clk, clear, MEM_read_data,
                                                WB_read);
  WB_pass_reg: entity WORK.register_32 port map(clk, clear, MEM_addr, WB_pass);
  WB_rd_reg  : entity WORK.register_5  port map(clk, clear, MEM_rd, WB_rd);
  decode_mt  : entity WORK.equal6 port map(WB_IR(31 downto 26), lwop, WB_lwop);
             MemtoReg <= WB_lwop;

  WBrrop     : entity WORK.equal6 port map(WB_IR(31 downto 26), rrop, WB_rrop);

  Addop      : entity WORK.equal6 port map(WB_IR(31 downto 26), addi,
                                           WB_addiop);           
             
  compare_rd : entity WORK.equal5 port map(WB_rd, "00000", WB_rd_zero);
               WB_write_enb <= (not WB_rd_zero) and
                               (WB_lwop or WB_rrop or WB_addiop );
             --  or WB_RRop or WB_addiop ???

  WB_mux     : entity WORK.mux_32 port map(in0    => WB_pass,
                                           in1    => WB_read,
                                           ctl    => MemtoReg,
                                           result => WB_result );

    -- my code for part2a

    afma1eq1: entity WORK.equal5 port map(inst => EX_reg1,
                                          test => MEM_rd,
                                          equal => AFMA1_1);

    afma1eq2: entity WORK.equal5 port map(inst => MEM_rd,
                                          test => "00000",
                                          equal => AFMA1_2);
        
    afma1eq3: entity WORK.equal6 port map(inst => MEM_OP,
                                          test => lwop,
                                          equal => AFMA1_3);
             
    mf1: AFMA1 <= AFMA1_1 and not AFMA1_2 and not AFMA1_3;

    afma2eq1: entity WORK.equal5 port map(inst => EX_reg1,
                                          test => WB_rd,
                                          equal => AFMA2_1);
    afma2eq2: entity WORK.equal5 port map(inst => WB_rd,
                                          test => "00000",
                                          equal => AFMA2_2);
    -- a forward wb result
    mf2: AFMA2 <= AFMA2_1 and not AFMA2_2;

    alu_mux :entity WORK.mux32_3 port map(in0 => EX_A,
                                          in1 => MEM_addr,--EX_result,
                                          in2 => WB_result,
                                          ct1 => AFMA1,
                                          ct2 => AFMA2,
                                          result => ALUMuxI);

    afma3eq1: entity WORK.equal5 port map(inst => EX_reg2,
                                          test => MEM_rd,
                                          equal => AFMA3_1);
    afma3eq2: entity WORK.equal5 port map(inst => MEM_rd,
                                          test => "00000",
                                          equal => AFMA3_2);
    afma3eq3: entity WORK.equal6 port map(inst => MEM_op,
                                          test => lwop,
                                          equal => AFMA3_3);
    -- b forward
    mf3: AFMA3 <= AFMA3_1 and not AFMA3_2 and not AFMA3_3;

    afma4eq1: entity WORK.equal5 port map(inst => EX_reg2,
                                          test => WB_rd,
                                          equal => AFMA4_1);
             
    afma4eq2: entity WORK.equal5 port map(inst => WB_rd,
                                          test => "00000",
                                          equal => AFMA4_2);
    -- b forward wb_result
    mf4: AFMA4 <= AFMA4_1 and not AFMA4_2;

    mux_mux: entity WORK.mux32_3 port map(in0 => EX_B,
                                          in1 => MEM_addr, --EX_result,
                                          in2 => WB_result,
                                          ct1 => AFMA3,
                                          ct2 => AFMA4,
                                          result => MuxMuxI);

    afma5eq1: entity WORK.equal5 port map(inst => ID_reg1,
                                          test => MEM_rd,
                                          equal => AFMA5_1);
    afma5eq2: entity WORK.equal5 port map(inst => MEM_rd,
                                          test => "00000",
                                          equal => AFMA5_2);
    afma5eq3: entity WORK.equal6 port map(inst => MEM_op,
                                          test => lwop,
                                          equal => AFMA5_3);
    -- 1 forward
    mf5: AFMA5 <= AFMA5_1 and not AFMA5_2 and not AFMA5_3;

    afma6eq1: entity WORK.equal5 port map(inst => ID_reg2,
                                          test => MEM_rd,
                                          equal => AFMA6_1);
             
    afma6eq2: entity WORK.equal5 port map(inst => MEM_rd,
                                          test => "00000",
                                          equal => AFMA6_2);
             
    afma6eq3: entity WORK.equal6 port map(inst => MEM_op,
                                          test => lwop,
                                          equal => AFMA6_3);

     -- 2 forward
     mf6: AFMA6 <= AFMA6_1 and not AFMA6_2 and not AFMA6_3;

-- 
             
     d1mux32: entity WORK.mux_32 port map(in0 => ID_read_data_1,
                                          in1 => MEM_addr,
                                          ctl => AFMA5,
                                          result => d1Mux);
     d2mux32: entity WORK.mux_32 port map(in0 => ID_read_data_2,
                                          in1 => MEM_addr,
                                          ctl => AFMA6,
                                          result => d2Mux);
     muxComp: entity WORK.equal32 port map(inst => d1Mux,
                                           test => d2Mux,
                                           equal => muxEq32);

    sh2: shifted2 <= ID_sign_ext(29 downto 0) & "00";         

             --jump = "000010"
             --beq  = "100111"
             --sw   = "101011"
    jpEq6: entity WORK.equal6 port map(inst => ID_OP,
                                       test => jpop,
                                       equal => jpCmp);

    bqEq6: entity WORK.equal6 port map(inst => ID_OP,
                                       test => bqop,
                                       equal => bqCmp);

    swEq6: entity WORK.equal6 port map(inst => ID_OP,
                                       test => swop,
                                       equal => swCmp);

             -- part2b change add or stall
    juBeSw: orJBS <= jpCmp or bqCmp or swCmp or stall;
    --
    ex_mux: entity WORK.mux_5 port map(in0    => old_ID_rd,
                                       in1    => "00000",
                                       ctl    =>  orJBS,
                                       result =>  ID_rd);

           -- part2b change clk to sclk
    pcp_reg: entity WORK.register_32 port map(clk => sclk,
                                              clear => clear,
                                              input => pcAddOut,
                                              output => pcp);

    jAddr: jump_addr <= pcp(31 downto 28) & ID_IR(25 downto 0) & "00";

    -- might have to swap pcp and shifted 2
    secAdd32: entity WORK.add32 port map(a => shifted2, --pcp,
                                         b => pcp, --shifted2,
                                         cin => '0',
                                         sum => add32out,
                                         cout => nc2);

    eq6bqoop: entity WORK.equal6 port map (inst => ID_OP,
                                           test => bqop,  --beq op
                                           equal => bqEqOp);
             
    andBqMuxR: beqAnded <= bqEqOp and MuxEq32;
    -- changed ID_OP to 000010
    eq6jpop: entity WORK.equal6 port map(inst => ID_OP,
                                         test => jpop,  --jumpop
                                         equal => jpEqOp);
     
    pcMux32_3: entity WORK.mux32_3 port map(in0 => pcAddOut,
                                            in1 => jump_addr,
                                            in2 => add32Out,
                                            ct1 => jpEqOp,
                                            ct2 => beqAnded,
                                            result =>PC_next);
   

             -- Part2b code begins
     slw1l: entity WORK.equal6 port map(inst => EX_OP,
                                        test => lwop,
                                        equal => slw1);

     slw2l: entity WORK.equal5 port map(inst => EX_rd,
                                        test => "00000",
                                        equal => slw2);
             
     slw3al: entity WORK.equal5 port map(inst => ID_reg1,
                                         test => EX_rd,
                                         equal => slw3a);
     slw3bl: entity WORK.equal5 port map(inst => ID_reg2,
                                         test => EX_rd,
                                         equal => slw3b);
     slw4l: entity WORK.equal6 port map(inst => ID_OP,
                                        test => lwop,
                                        equal => slw4);
     slw5l: entity WORK.equal6 port map(inst => ID_OP,
                                        test => addi,
                                        equal => slw5);
     slw6l: entity WORK.equal6 port map(inst => ID_OP,
                                        test => jpop,
                                        equal => slw6);
                                        
     slw3 <= slw3a or slw3b; 

     stall_lw <= slw1 and not slw2 and slw3
                 and not slw4 and not slw5 and not slw6;

     slwlw1l: entity WORK.equal6 port map(inst => EX_op,
                                          test => lwop,
                                          equal => slwlw1);
     slwlw2l: entity WORK.equal5 port map(inst => EX_rd,
                                          test => "00000",
                                          equal => slwlw2);
     slwlw3al: entity WORK.equal6 port map(inst => ID_OP,
                                           test => lwop,
                                           equal => slwlw3a);
     slwlw3bl: entity WORK.equal6 port map(inst => ID_OP,
                                           test => addi,
                                           equal => slwlw3b);
     slwlw4l: entity WORK.equal5 port map(inst => ID_reg1,
                                         test => EX_rd,
                                         equal => slwlw4);
     slwlw3 <= slwlw3a or slwlw3b;

     stall_lwlw <= slwlw1 and not slwlw2 and slwlw3 and slwlw4;
             
     slwbq1l: entity WORK.equal6 port map(inst => ID_op,
                                          test => bqop,
                                          equal => slwbq1);
     slwbq2l: entity WORK.equal5 port map(inst => MEM_rd,
                                          test => "00000",
                                          equal => slwbq2);
     slwbq3l: entity WORK.equal6 port map(inst => MEM_op,
                                          test => lwop,
                                          equal => slwbq3);
     slwbq4al: entity WORK.equal5 port map(inst => ID_reg1,
                                           test => MEM_rd,
                                           equal => slwbq4a);
     slwbq4bl: entity WORK.equal5 port map(inst => ID_reg2,
                                           test => MEM_rd,
                                           equal => slwbq4b);
     slwbq4 <= slwbq4a or slwbq4b;

     stall_lwbq <= slwbq1 and not slwbq2 and slwbq3 and slwbq4;

     sopbq1l: entity WORK.equal6 port map(inst => ID_op,
                                          test => bqop,
                                          equal => sopbq1);
     sopbq2l: entity WORK.equal5 port map(inst => EX_rd,
                                          test => "00000",
                                          equal => sopbq2);
     sopbq3al: entity WORK.equal5 port map(inst => ID_reg1,
                                           test => EX_rd,
                                           equal => sopbq3a);
     sopbq3bl: entity WORK.equal5 port map(inst => ID_reg2,
                                           test => EX_rd,
                                           equal => sopbq3b);
     sopbq3 <= sopbq3a or sopbq3b;

     stall_opbq <= sopbq1 and not sopbq2 and sopbq3;

     stall <= stall_lw or stall_lwlw or stall_lwbq or stall_opbq;

     sclk <= stall or clk;

     -- mux32
     sMux32: entity WORK.mux_32 port map(in0 => ID_IR,
                                         in1 => x"00000000",--zero_32,
                                         ctl => stall,
                                         result => stallEx);
             
  loadmem: process    -- read part3a.abs into shared memory array
             file my_input : TEXT open READ_MODE is "part3a.abs";  -- hex data
             variable good : boolean := true;
             variable my_line : LINE;
             variable my_input_line : LINE;
             variable loc : std_logic_vector(31 downto 0);  -- read from file
             variable val : std_logic_vector(31 downto 0);  -- read from file
             variable word_addr : natural;  -- byte addr/4
           begin
             write(my_line, string'
                   ("---PC--- --inst--  loadmem process input .abs file"));
             writeline(output, my_line);
             while good loop
               exit when endfile(my_input);
               readline(my_input, my_input_line);
               my_line := new string'(my_input_line.all);  -- for printing
               writeline(output, my_line); -- writing clears my_line
               hread(my_input_line, loc, good);
               exit when not good;
               hread(my_input_line, val, good);
               exit when not good;
               word_addr := to_integer(loc(13 downto 2)); -- crop to 12 bits
               memory(word_addr) := val;  -- write main memory
             end loop;
             write(my_line, string'("loadmem finish. memory loaded"));
             writeline(output, my_line);
             wait; -- run once. do not keep restarting process
           end process loadmem;
           
  printout:  process -- used to show pipeline, registers and memory
               variable my_line : LINE;   -- not part of working circuit
             begin
               wait for 9.5 ns;         -- just before rising clock
               write(my_line, string'("clock "));
               write(my_line, counter);
               write(my_line, string'("  inst="));
               hwrite(my_line, inst);
               write(my_line, string'("  PC   ="));
               hwrite(my_line, PC);
               write(my_line, string'(" PCnext="));
               --huzzah changed PC_next to MuxT2aOut 
               hwrite(my_line, PC_next);
               writeline(output, my_line);
               write(my_line, string'("ID  stage  IR="));
               hwrite(my_line, ID_IR);
               if (WB_write_enb='1') and (WB_rd/="00000") then
                 write(my_line, string'("  write="));
                 hwrite(my_line, WB_result);
                 write(my_line, string'("  into ="));
                 hwrite(my_line, "000000000000000000000000000"&WB_rd);
               else
                 write(my_line, string'("                "));
                 write(my_line, string'("                "));
               end if;
               write(my_line, string'("                "));
               write(my_line, string'(" rd="));
               write(my_line, ID_rd);
               writeline(output, my_line);

               write(my_line, string'("EX  stage  IR="));
               hwrite(my_line, EX_IR);
               write(my_line, string'("  EX_A ="));
               hwrite(my_line, EX_A);
               write(my_line, string'("  EX_B ="));
               hwrite(my_line, EX_B);
               write(my_line, string'("  EX_C ="));
               hwrite(my_line, EX_C);
               write(my_line, string'(" rd="));
               write(my_line, EX_rd);
               writeline(output, my_line);
               write(my_line, string'("EX  stage"));
               write(my_line, string'("             "));
               write(my_line, string'("EX_aluB="));
               hwrite(my_line, EX_aluB);
               write(my_line, string'(" EX_res="));
               hwrite(my_line, EX_result);
               writeline(output, my_line);
               write(my_line, string'("MEM stage  IR="));
               hwrite(my_line, MEM_IR);
               write(my_line, string'("  addr ="));
               hwrite(my_line, MEM_addr);
               write(my_line, string'("  data ="));
               hwrite(my_line, MEM_data);
               if MEMread='1' then
                 write(my_line, string'("  read ="));
                 hwrite(my_line, MEM_read_data);
               elsif MEMWrite='1' then
                 write(my_line, string'("  wrote="));
                 hwrite(my_line, MEM_data);
               else
                 write(my_line, string'("                "));
               end if;
               write(my_line, string'(" rd="));
               write(my_line, MEM_rd);
               writeline(output, my_line);
               write(my_line, string'("WB  stage  IR="));
               hwrite(my_line, WB_IR);
               write(my_line, string'("  read ="));
               hwrite(my_line, WB_read);
               write(my_line, string'("  pass ="));
               hwrite(my_line, WB_pass);
               write(my_line, string'(" result="));
               hwrite(my_line, WB_result);
               write(my_line, string'(" rd="));
               write(my_line, WB_rd);
               writeline(output, my_line);
               write(my_line, string'("control RegDst="));
               write(my_line, RegDst);
               write(my_line, string'("  ALUSrc="));
               write(my_line, ALUSrc);
               write(my_line, string'("  MemtoReg="));
               write(my_line, MemtoReg);
               write(my_line, string'("  MEMRead="));
               write(my_line, MEMRead);
               write(my_line, string'("  MEMWrite="));
               write(my_line, MEMWrite);
               write(my_line, string'("  WB_write_enb="));
               write(my_line, WB_write_enb);
               writeline(output, my_line);

               -- registers
               write(my_line, string'("reg 0-7 "));
               for I in 0 to 7 loop
                 hwrite(my_line, reg_mem(I));
                 write(my_line, string'(" "));            
               end loop;  -- I
               writeline(output, my_line);
               write(my_line, string'("   8-15 "));
               for I in 8 to 15 loop
                 hwrite(my_line, reg_mem(I));
                 write(my_line, string'(" "));            
               end loop;  -- I
               writeline(output, my_line);
               write(my_line, string'("  16-23 "));
               for I in 16 to 23 loop
                 hwrite(my_line, reg_mem(I));
                 write(my_line, string'(" "));            
               end loop;  -- I
               writeline(output, my_line);
                 
               -- RAM memory
               write(my_line, string'("RAM 70- "));
               for I in 28 to 35 loop  -- word at hex 70 byte address
                 hwrite(my_line, memory(I));
                 write(my_line, string'(" "));
               end loop;
               writeline(output, my_line);

               writeline(output, my_line);  -- blank line
               counter <= counter+1;
               wait for 0.5 ns;         -- rest of 10 ns clock period
             end process printout;

end architecture schematic; -- of part3a

