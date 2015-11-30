-- pmul8.vhdl parallel multiply 4 bit x 4 bit to get 8 bit unsigned product
--              uses VHDL 'generate' to have less statements
--              see diagram madd.jpg for madd schematic
--              see diagram pmul8.ps for pmul8 schematic

library IEEE;
use IEEE.std_logic_1164.all;

--original madd
entity madd is      -- multiplying full adder stage
  port(c    : in  std_logic;   -- one input, think carry in
       b    : in  std_logic;   -- one input, think previous sum
       m    : in  std_logic;   -- multiplier bit
       a    : in  std_logic;   -- multiplicand bit
       sum  : out std_logic;   -- carry save sum out
       cout : out std_logic);  -- carry save carry out
end entity madd;

architecture circuits of madd is  -- multiplying full adder stage
  signal aa: std_logic;
begin
  aa <= a and m; -- logic could be reduced, yet probably circuit designed
  sum <= (aa and b and c) or (aa and not b and not c) or
         (not aa and b and not c) or (not aa and not b and c) after 1 ns;
  cout <= (aa and b) or (aa and c) or (b and c) after 1 ns;
end architecture circuits; -- of madd

library IEEE;
use IEEE.std_logic_1164.all;

--First copy of  madd  Top Row where c = 0 and b = 0
entity maddTop is      -- multiplying full adder stage
   port(m    : in  std_logic;   -- multiplier bit
        a    : in  std_logic;   -- multiplicand bit
        sum  : out std_logic;   -- carry save sum out
        cout : out std_logic);  -- carry save carry out
   end entity maddTop;

architecture circuits of maddTop is  -- multiplying full adder stage
  signal aa: std_logic;
  signal zero: std_logic := '0';
begin
  aa <= a and m; -- logic could be reduced, yet probably circuit designed
  sum <= aa;-- after 1 ns;
  cout <= zero;
end architecture circuits;  -- of maddTop       

library IEEE;
use IEEE.std_logic_1164.all;

--Second copy of  madd left column c = 0;
entity maddLeft is                       -- multiplying full adder stage
   port(b    : in  std_logic;         -- one input, think previous sum
        m    : in  std_logic;         -- multiplier bit
        a    : in  std_logic;         -- multiplicand bit
        sum  : out std_logic;         -- carry save sum out
        cout : out std_logic);        -- carry save carry out
   end entity maddLeft;

architecture circuits of maddLeft is  -- multiplying full adder stage
    signal aa: std_logic;
begin
  aa <= a and m; -- logic could be reduced, yet probably circuit designed  
  sum <= (aa and not b) or (not aa and b) after 1 ns;
  cout <= (aa and b) after 1 ns;
end architecture circuits;  -- of maddLeft 

library IEEE;
use IEEE.std_logic_1164.all;
--changing (3 downto 0) -> (7 downto 0)
--changing (7 downto 0) -> (15 downto 0)
entity pmul8 is  -- 4 x 4 = 8 bit unsigned product multiplier
  port(a : in  std_logic_vector(7 downto 0);  -- multiplicand
       b : in  std_logic_vector(7 downto 0);  -- multiplier
       p : out std_logic_vector(15 downto 0)); -- product
end pmul8;

architecture circuits of pmul8 is
  constant N  : integer := 7;--3;     -- last row number
  constant NP : integer := N+1;   -- last row plus 1
  constant NM : integer := N-1;   -- last row minus 1
  type arr is array(0 to NP) of std_logic_vector(N downto 0);
  signal s    : arr; -- partial sums
  signal c    : arr; -- partial carries
  signal zero : std_logic := '0';
begin  -- circuits of pmul8
  -- the internal part of the multiplier is nested generate
  -- special case generate is needed for the top row,
  -- the bottom row, the left column and
  -- connecting to the product outputs.
  
  -- center 
  gmaddi: for i in 1 to N generate
    gmaddj: for j in 0 to NM generate
      maddij: entity WORK.madd
              port map(s(i-1)(j+1), c(i-1)(j), b(i), a(j), s(i)(j), c(i)(j));
    end generate gmaddj;  
  end generate gmaddi;  

  -- top row
  gmadd0j: for j in 0 to N generate
    madd0j: entity WORK.maddTop
            --port map(zero, zero, b(0), a(j), s(0)(j), c(0)(j));
            port map(b(0), a(j), s(0)(j), c(0)(j));
  end generate gmadd0j;

  -- left column
  gmaddiN: for i in 1 to N generate
    maddiN: entity WORK.maddLeft
            --port map(zero, c(i-1)(N), b(i), a(N), s(i)(N), c(i)(N));
            port map(c(i-1)(N), b(i), a(N), s(i)(N), c(i)(N)); 
  end generate gmaddiN;

  -- bottom row
  maddNP0: entity WORK.madd
           port map(s(N)(1), c(N)(0), '1', '0', s(NP)(0), c(NP)(0));
  maddNPN: entity WORK.maddLeft--WORK.madd
           --port map(zero, c(N)(N), '1', c(NP)(NM), s(NP)(N), c(NP)(N));
           port map(c(N)(N), '1', c(NP)(NM), s(NP)(N), c(NP)(N));
  gmaddNP: for j in 1 to NM generate
    maddNPj: entity WORK.madd
             port map(s(N)(j+1), c(N)(j), '1', c(NP)(j-1), s(NP)(j), c(NP)(j));
  end generate gmaddNP;
  
  
  -- connect outputs
  gp0i: for i in 0 to N generate
    p0i: p(i) <= s(i)(0);
    pNi: p(i+NP) <= s(NP)(i);
  end generate gp0i;
  
end architecture circuits; -- of pmul8
