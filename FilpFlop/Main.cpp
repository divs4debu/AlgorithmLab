#include <iostream>
#include "Counter.cpp"
//#include "FlipFlop.cpp"

using namespace std;

int main(){
  //FlipFlop Test Equations
  /*FlipFlop a;
  bool inA[] = {false, true};
  bool inB[] = {false, true};
  int i =3;
  cout<<"J"<<" K"<<"\t"<<"Q"<<" Q_bar"<<endl;
  while(i--){
    for(int i = 0; i<2; i++){
      for(int j = 0; j<2; j++){
        a.J = inA[i];
        a.K = inB[j];
        a.processNextState();
        cout<<a.J<<" "<<a.K<<"\t"<<a.getQ()<<" "<<a.getQ_bar()<<endl;
      }
    }
  }*/

  Counter a;
  for(int i = 0; i<8 ; i++){
    bool *z = a.getState();
      cout<<z[0]<<" "<<z[1]<<" "<<z[2]<<endl;
      a.processOutput();
  }

}
