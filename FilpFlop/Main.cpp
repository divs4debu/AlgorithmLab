#include <iostream>
#include "Counter.cpp"
#include <ncurses.h>
#include <unistd.h>
#include <stdlib.h>
//#include "FlipFlop.cpp"

using namespace std;

void drawZero(WINDOW *win, int &startx, int &starty){

  for(int i = 0; i<2; i++){
    mvwaddch(win, starty, startx++, ACS_HLINE);
  }
}

void drawZeroToOne(WINDOW *win, int &startx, int &starty, int height){
  int k = starty;
  mvwaddch(win, starty, startx, ACS_LRCORNER);
  for(int i = 0; i< height; i++){
    mvwaddch(win, --k, startx, ACS_VLINE);
  }
  mvwaddch(win, k, startx, ACS_ULCORNER);
}

void drawOne(WINDOW *win, int &startx, int &starty,int height){
  for(int i = 0 ; i<2; i++){
    mvwaddch(win, starty-height, ++startx, ACS_HLINE);
  }
}

void drawOneToZero(WINDOW *win, int &startx, int &starty, int height){
  int k = starty-height;
  mvwaddch(win, k, ++startx, ACS_URCORNER);;
  for(int i = 0; i<height; i++){
    mvwaddch(win,++k, startx, ACS_VLINE);
  }
  mvwaddch(win, k, startx, ACS_LLCORNER);
  startx++;
}

void drawPulse(WINDOW *win,int &startx, int &starty, int height, bool Clk, bool prev){
  init_pair(1, COLOR_RED, COLOR_BLACK);
  init_pair(2, COLOR_GREEN, COLOR_BLACK);

  if(!prev & !Clk){
    wattrset(win, COLOR_PAIR(1));
    drawZero(win, startx, starty);
  }
  if(!prev & Clk){
    wattrset(win, COLOR_PAIR(1));
    drawZeroToOne(win, startx, starty, height);
  }
  if(prev & Clk){
    wattrset(win, COLOR_PAIR(1));
    drawOne(win, startx, starty,height);
  }
  if(prev & !Clk){
    wattrset(win, COLOR_PAIR(2));
    drawOneToZero(win, startx, starty, height);
  }
  refresh();
}

void drawPulseTrain(WINDOW *win,int &startx, int &starty, int height, bool *clks ,int size){
  int prev = 0;
  for (int i=1; i<size; i++){
    drawPulse(win, startx, starty , height, clks[i] , clks[prev]);
    prev++;
  }
}

void addNewValue(bool *clks, bool value, int &size){
  for(int i = size, j= i-1 ; i>=0; i--,j--){
    clks[i] = clks[j];
  }
  clks[0] = value;
}


int main(){


  int startx_clk = 10;
  int starty_clk = 5;

  int start_af1_x = 10;
  int start_af1_y = 10;

  int start_af2_x = 10;
  int start_af2_y = 15;

  int start_af3_x = 10;
  int start_af3_y = 20;

  int start_af4_x = 10;
  int start_af4_y = 25;



  Counter a;

  bool Clk = false;
  bool prev = false;

  bool Clk_arr[200];
  bool Ff1_arr[200];
  bool Ff2_arr[200];
  bool Ff3_arr[200];
  bool Ff4_arr[200];

  int size = 0;

  int k = 0;
  initscr();
  start_color();
  refresh();
  noecho();
  cbreak();
  init_pair(3, COLOR_WHITE, COLOR_BLACK);
  while(true){

    if (k %2 == 0){
      prev = Clk;
      Clk = !Clk;
    }
    clear();

    wattrset(stdscr, COLOR_PAIR(3));
    mvprintw(starty_clk-1, startx_clk-7, "CLK");  mvprintw(starty_clk-1, startx_clk-3, "%d",(int)Clk);
    mvprintw(start_af1_y-1, start_af1_x-7, "FF1");mvprintw(start_af1_y-1, start_af1_x-3,"%d", (int)a.a.getQ());
    mvprintw(start_af2_y-1, start_af2_x-7, "FF2");mvprintw(start_af2_y-1, start_af2_x-3,"%d", (int)a.b.getQ());
    mvprintw(start_af3_y-1, start_af3_x-7, "FF3");mvprintw(start_af3_y-1, start_af3_x-3,"%d", (int)a.c.getQ());
    mvprintw(start_af4_y-1, start_af4_x-7, "FF4");mvprintw(start_af4_y-1, start_af4_x-3,"%d", (int)a.d.getQ());

    a.processOutput(Clk);
    drawPulseTrain(stdscr, startx_clk, starty_clk, 2 , Clk_arr ,size);
    drawPulseTrain(stdscr, start_af1_x, start_af1_y,2, Ff1_arr, size);
    drawPulseTrain(stdscr, start_af2_x, start_af2_y ,2, Ff2_arr, size);
    drawPulseTrain(stdscr, start_af3_x, start_af3_y ,2, Ff3_arr, size);
    drawPulseTrain(stdscr, start_af4_x, start_af4_y ,2, Ff4_arr, size);



    addNewValue(Clk_arr, Clk , size);
    addNewValue(Ff1_arr, a.a.getQ(),size);
    addNewValue(Ff2_arr, a.b.getQ(), size);
    addNewValue(Ff3_arr, a.c.getQ(), size);
    addNewValue(Ff4_arr, a.d.getQ(), size);


    if (size < 200){
      size++;
    }
    refresh();
    sleep(1);

    startx_clk = 10;
    starty_clk = 5;

    start_af1_x = 10;
    start_af1_y = 10;

    start_af2_x =10;
    start_af2_y =15;

    start_af3_x =10;
    start_af3_y =20;

    start_af4_x =10;
    start_af4_y =25;
    k++;


  }

  /*drawZero(stdscr, startx_clk, starty_clk);
  drawZero(stdscr, startx_clk, starty_clk);
  drawZero(stdscr, startx_clk, starty_clk);
  drawZero(stdscr, startx_clk, starty_clk);
  drawZeroToOne(stdscr, startx_clk, starty_clk, 2);
  drawOne(stdscr, startx_clk, starty_clk,2);
  drawOne(stdscr, startx_clk, starty_clk,2);
  drawOne(stdscr, startx_clk, starty_clk,2);
  drawOne(stdscr, startx_clk, starty_clk,2);
  drawOneToZero(stdscr, startx_clk, starty_clk,2);*/
  getch();
	endwin();

}
