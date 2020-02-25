#include<bits/stdc++.h>
using namespace std;
typedef long long ll;
const int test_time=32;//出错的概率不大于1/(2^s)
ll qpow(ll a,ll b,ll mod)
{
    ll ans=1,base=a;
    while(b)
    {
        if(b & 1)
        {
        ans=ans*base;
        }
        base=base*base;
        ans%=mod;
        base%=mod;
        b = b >> 1;
    }
    return ans;
}
bool millerRabbin(int n) {//米勒-拉宾(MillerRabbin)素性测试算法
  if (n < 3) return n == 2;
  int a = n - 1, b = 0;
  while (a % 2 == 0) a /= 2, ++b;
  for (int i = 1, j; i <= test_time; ++i) {
    int x = rand() % (n - 2) + 2, v = qpow(x, a, n);
    if (v == 1 || v == n - 1) continue;
    for (j = 0; j < b; ++j) {
      v = (long long)v * v % n;
      if (v == n - 1) break;
    }
    if (j >= b) return 0;
  }
  return 1;
}
int main(){

int n;
cin>>n;
srand((unsigned)time(NULL));
cout<<(millerRabbin(n)?"YES":"NO");

}