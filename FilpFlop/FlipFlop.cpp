#include <iostream>

using namespace std;

class FlipFlop{
private:
  bool Q;

public:
  bool J;
  bool K;
  bool clk;

  FlipFlop():J(true),K(true),Q(false),clk(false){};
  FlipFlop(bool a, bool b){
    J = a;
    K = b;
    Q = false;
    clk = false;
  };

  bool getQ_bar(){
      return (!Q);
  };

  bool getQ(){
    return Q;
  };
  void processNextState(bool Clk){
    if (clk)
      Q = (!(K)&Q)|(!(Q)&J);
  }

};
