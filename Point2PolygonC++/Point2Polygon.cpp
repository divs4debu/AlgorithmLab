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
      minimum = v->at(i);
    }
  }
  return minimum;
}

//get the rightmost vertex in the list of the vertices
//this functions checks the x coordinates of list of vertex and return the vertex with the maximum x coordinate

//Working Fine Tested
Vertex get_rightmost_vertex(vector<Vertex> *v){
  int max = numeric_limits<int>::min();
  Vertex maximum;
  for (int i=0; i<v->size();i++){
    if (max < v->at(i).get_x()){
      max = v->at(i).get_x();
      maximum = v->at(i);
    }
  }
  return maximum;
}

//This function tells whether the givem point lies above the given edge or not.
//returns true if lies above else return false

bool is_above(Edge e, Vertex v){
  Vertex start = e.get_start_vertex();
  int ax = start.get_x();
  int ay = start.get_y();

  Vertex end = e.get_end_vertex();
  int bx = end.get_x();
  int by = end.get_y();

  int det = ((ax - v.get_x())*(by - v.get_y())) -((bx - v.get_x()) * (ay - v.get_y()));

  if (det > 0)
    return true;
  else
    return false;
}

//This functions returns the vector of the vertices lying above the given edge
vector<Vertex> get_list_above(Edge e, vector<Vertex> v){
    vector<Vertex> above;
    for (int i =0; i<v.size(); i++){
      if (e.get_start_vertex() != v.at(i) && e.get_end_vertex() != v.at(i) && is_above(e, v.at(i))){
        above.push_back(v.at(i));
      }

      else
        continue;
    }
    return above;
}

vector<Vertex> get_list_below(Edge e, vector<Vertex> v){
  vector<Vertex> below;
  for(int i=0; i<v.size();i++){
    if(e.get_start_vertex() != v.at(i) && e.get_end_vertex() != v.at(i) && !is_above(e,v.at(i)))
      below.push_back(v.at(i));
  }
  return below;
}

//incomplete
Polygon generate_polygon(vector<Vertex> v){
  Vertex left = get_leftmost_vertex(&v);
  Vertex right = get_rightmost_vertex(&v);
  Edge e(left, right);

  vector<Vector> above = get_list_above(e, v);
  vector<Vertex> below = get_list_below(e, v);

}

int main(){
  Vertex v1(10,10);
  Vertex v2(5,7);
  Vertex v3(5,-3);
  Vertex v4(12,3);
  Vertex v5(0,10);
  Vertex v6(-4,3);

  vector<Vertex> v;
  v.push_back(v4);
  v.push_back(v2);
  v.push_back(v5);
  v.push_back(v3);
  v.push_back(v1);
  v.push_back(v6);
  /*for(int i=0;i<v.size();i++){
    cout<<v.at(i)<<endl;
  }*/

  Edge e(v4,v6);
  cout<<"List above"<<endl;
  vector<Vertex> above = get_list_above(e, v);
  for(int i=0;i<above.size();i++)
    cout<<above.at(i)<<endl;
    cout<<"List below"<<endl;
  vector<Vertex> below = get_list_below(e, v);
  for(int i=0;i<below.size();i++)
    cout<<below.at(i)<<endl;
}
