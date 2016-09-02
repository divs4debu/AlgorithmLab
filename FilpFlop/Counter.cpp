#include "FlipFlop.cpp"
#include <iostream>

using namespace std;

class Counter{
public:
  FlipFlop a, b, c;
  bool Qa, Qb, Qc;

  Counter(){
    a.J = true;a.K = true;a.clk = true;
    b.J = true;b.K = true;b.clk = a.getQ();
    c.J = true;c.K = true;c.clk = (b.getQ()&b.clk);
    Qa = a.getQ();Qb = b.getQ();Qc = c.getQ();
  };

  void processOutput(){
    a.processNextState();b.processNextState();c.processNextState();
    Qa = a.getQ();Qb = b.getQ();Qc = c.getQ();
    b.clk = Qa;
    c.clk = (Qb&b.clk);
  };

  bool* getState(){
    bool state[] = {Qa, Qb, Qc};
      return state;
  };
};
