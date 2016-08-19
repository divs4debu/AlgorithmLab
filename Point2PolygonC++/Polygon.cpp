#ifndef POLYGON
#define POLYGON

#include <iostream>
#include "Edge.cpp"
#include <vector>
using namespace std;

class Polygon{
private:
  std::vector<Edge> edges;
  std::vector<Vertex> vertices;
public:
  Polygon(std::vector<Edge> e){
    edges = e;
  }

  std::vector<Edge> get_edges(){
    return edges;
  }
};
#endif
