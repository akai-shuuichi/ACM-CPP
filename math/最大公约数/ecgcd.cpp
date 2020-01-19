//三个愿望 一次满足
#include<bits/stdc++.h>
using namespace std;
int Exgcd(int a, int b, int &x, int &y) {//计算ax+by=gcd(a,b)的特解
  if (!b) {
    x = 1;
    y = 0;
    return a;
  }
  int d = Exgcd(b, a % b, x, y);
  int t = x;
  x = y;
  y = t - (a / b) * y;
  return d;
}
int main(){
int a,b,x,y;
cin>>a>>b;
//cout<<__gcd(a,b);
cout<<Exgcd(a,b,x,y);
cout<<x<<" "<<y;
}