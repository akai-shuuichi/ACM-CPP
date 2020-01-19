#include<cstdio>
#include<iostream>
using namespace std;
typedef long long ll;
ll pow(ll a,ll b,ll mod)
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
int main()
{
    ll a,b,mod;
    cin>>a>>b>>mod;
    printf("%lld\n",pow(a,b,mod));
}