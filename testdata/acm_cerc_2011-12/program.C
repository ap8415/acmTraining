/*
 * CTU Open Contest 2011
 *
 * Sample solution: program
 * Author: Jan Stoklasa
*/
 
#include <iostream>
 
using namespace std;
 
const bool GenerateExecuteInput = false;
 
const int MaxN = 5;
extern const char* a_equals_b;

int main(void)
{
  int N;
  int f[MaxN][2];
  while (cin >> N && N>0)
  {
    for(int i=0; i<N;i++)
    {
      cin >> f[i][0] >> f[i][1];
    }
    for(int i=0; i<N-1;i++)
    {
      cout << "DUP" << endl;
    }
    cout << "NUM 0" << endl;  //sum
    for(int i=0; i<N;i++)
    {
      cout << "SWP" << endl;
      cout << "NUM " << f[i][0] << endl;
      cout << a_equals_b << endl;
      cout << "NUM " << f[i][1] << endl;
      cout << "MUL" << endl;
      cout << "ADD" << endl;
    }
    cout << "END" << endl;
 
    if (GenerateExecuteInput)
    {
      cout << N << endl;
      for(int i=0; i<N;i++)
      {
	cout << f[i][0] << endl;
      }
    }
    cout << endl;
  }
  if (GenerateExecuteInput) cout << "QUIT" << endl;
}
 
/* [a][b] -> [a or b] 
 
NUM 1
SWP
SUB
SWP
NUM 1
SWP
SUB
MUL
NUM 1
SWP
SUB
*/
 
/* [x] -> [x%2][x/2]
 
DUP
NUM 2
MOD
SWP 
NUM 2
DIV
 */
 
const char* a_equals_b =
"SUB\n"
"DUP\n"
"MUL\n"
"DUP\n"
"NUM 2\n"
"MOD\n"
"SWP\n"
"NUM 2\n"
"DIV\n"
"DUP\n"
"NUM 2\n"
"MOD\n"
"SWP\n"
"NUM 2\n"
"DIV\n"
"DUP\n"
"NUM 2\n"
"MOD\n"
"SWP\n"
"NUM 2\n"
"DIV\n"
"DUP\n"
"NUM 2\n"
"MOD\n"
"SWP\n"
"NUM 2\n"
"DIV\n"
"DUP\n"
"NUM 2\n"
"MOD\n"
"SWP\n"
"NUM 2\n"
"DIV\n"
"DUP\n"
"NUM 2\n"
"MOD\n"
"SWP\n"
"NUM 2\n"
"DIV\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"SWP\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"MUL\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"SWP\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"MUL\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"SWP\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"MUL\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"SWP\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"MUL\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"SWP\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"MUL\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"SWP\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"MUL\n"
"NUM 1\n"
"SWP\n"
"SUB\n"
"NUM 1\n"
"SWP\n"
"SUB";

