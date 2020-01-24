//long long级别的素数可能会t
#include<bits/stdc++.h>
using namespace std;
bool isPrime(int a) {
  if (a < 2) return 0;
  for (int i = 2; i * i <= a; ++i)
    if (a % i) return false;
  return true;
}
int main(){
int a;
cin>>a;
cout<<isPrime(a);

}