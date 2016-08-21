#ifndef POLYGON
#define POLYGON

#include <iostream>
#include "Edge.cpp"
#include <vector>
using namespace std;

class Polygon{
private:
  vector<Edge> edges;
public:
  Polygon(vector<Edge> e){
    edges = e;
  }

  vector<Edge> get_edges(){
    return edges;
  }

  friend ostream& operator<< (ostream& stream, const Polygon& polygon){
    for(int i=0; i<polygon.edges.size();i++)
      cout<<polygon.edges[i]<<endl;
  }
};
#endif
