#include <iostream>

using namespace std;

class Nand{
public:
  bool a;
  bool b;

  Nand():a(false), b(false){};

  Nand (bool input1, bool input2){
    a = input1;
    b = input2;
  };

  bool output(){return !(a & b);};

};
