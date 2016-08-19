#ifndef EDGE
#define EDGE

#include <iostream>
#include "Vertex.cpp"
using namespace std;

class Edge{

private:
  Vertex start;
  Vertex end;
public:
  Edge(Vertex v1, Vertex v2){
    start = v1;
    end = v2;
  }

  Vertex get_start_vertex(){
    return start;
  }

  Vertex get_end_vertex(){
    return end;
  }


};
#endif
