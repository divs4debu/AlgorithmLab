#ifndef POLYGON
#define POLYGON

#include <iostream>
#include "Edge.cpp"
#include <vector>
#include "Vertex.cpp"
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

  bool is_inside(Vertex vertex){
    bool inside = true;
    for(int i=0; i< edges.size();i++){
      Edge edge = edges[i];
      int ax = edge.get_start_vertex().get_x(), ay = edge.get_start_vertex().get_y();
      int bx  =edge.get_end_vertex().get_x(), by = edge.get_end_vertex().get_y();
      int cx = vertex.get_x(), cy = vertex.get_y();

      if(((ay < cy)^(by > cy) )&& ((cx < (bx -ax)*(cy -ay)/ (by - ay) + ax ))){
        inside = !inside;
        cout<<"inside"<<endl;
      }
    }
    return inside;

  }
};
#endif
