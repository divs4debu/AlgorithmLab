#include <iostream>
#include "Polygon.cpp"
#include "Edge.cpp"
#include "Vertex.cpp"
#include <vector>
#include <limits>

using namespace std;
//get the leftmost vertex in the list of the vertices
//this functions checks the x coordinates of list of vertex and return the vertex with the minimum x coordinate

//Working Fine Tested....
Vertex get_leftmost_vertex(vector<Vertex> *v){
  int min =numeric_limits<int>::max();
  Vertex minimum;
  for (int i=0; i<v->size();i++){
    int temp = v->at(i).get_x();
    if(temp < min){
      min = temp;
      minimum = (v->at(i));
    }
  }
  return minimum;
}


int main(){
  Vertex v1(10,10);
  Vertex v2(10,5);
  Vertex v3(5,10);
  Vertex v4(0,10);
  Vertex v5(0,10);

  vector<Vertex> v;
  v.push_back(v4);
  v.push_back(v2);
  v.push_back(v5);
  v.push_back(v3);
  v.push_back(v1);
  /*for(int i=0;i<v.size();i++){
    cout<<v.at(i)<<endl;
  }*/

  Vertex min;
  min = get_leftmost_vertex(&v);
  cout<<min<<endl;
}
