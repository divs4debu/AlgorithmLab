#include "FlipFlop.cpp"
#include <iostream>

using namespace std;

class Counter{
private:
  bool Qa, Qb, Qc, Qd;
  bool prev;

public:
	FlipFlop a, b, c, d;

  Counter(){
    a.J = true;a.K = true;a.clk = false;
    b.J = true;b.K = true;b.clk = a.getQ();
    c.J = true;c.K = true;c.clk = b.getQ();
    d.J = true;d.K = true;d.clk = c.getQ();
    Qa = a.getQ();Qb = b.getQ();Qc = c.getQ();Qd = d.getQ();
  };

  void processOutput(bool Clk){
    a.processNextState(Clk);Qa = a.getQ();
    b.processNextState(Qa);Qb = b.getQ();
    c.processNextState(Qb);Qc = c.getQ();
    d.processNextState(Qc);Qd = d.getQ();

    if(Qd&Qb && (prev & !Clk)){
        a.clear();
        b.clear();
        c.clear();
        d.clear();
        Qa = a.getQ();
    	Qb = b.getQ();
    	Qc = c.getQ();
    	Qd = d.getQ();
    }

    prev = Clk;

  };

  int getA(){
      return Qa;
  }

  int getB(){
		return Qb;
  }

  int getC(){
      return Qc;
  }

  int getD(){
      return Qd;
  }

};
