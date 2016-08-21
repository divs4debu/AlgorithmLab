/*
  This class is the basic vertex class representing the coordinate on a x-y plane.
  This class lets you handle the functionality done on the vertex on the cartisian coordinate system.
*/
#ifndef VERTEX
#define VERTEX
#include <iostream>
using namespace std;

class Vertex{
  private:
     int coordinate_x;
     int coordinate_y;
  public:
    Vertex(void):coordinate_x(0),coordinate_y(0){};

    Vertex(int ext_coordinate_x, int ext_coordinate_y){
      coordinate_x = ext_coordinate_x;
      coordinate_y = ext_coordinate_y;
    };

    int get_x(){
      return coordinate_x;
    };

    int get_y(){
      return coordinate_y;
    };
    friend ostream& operator<< (ostream& stream, const Vertex& vertex){
      cout<<vertex.coordinate_x<<", "<<vertex.coordinate_y;
    }

    bool operator!= (const Vertex& vertex){

      if(coordinate_x != vertex.coordinate_x || coordinate_y != vertex.coordinate_y)
        return true;
      else
        return false;
    }

    bool operator< (const Vertex& vertex) const
    {
      return (coordinate_x < vertex.coordinate_x);
    }
};
#endif
