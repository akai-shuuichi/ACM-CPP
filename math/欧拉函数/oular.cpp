#include<bits/stdc++.h>
using namespace std;
typedef long long ll;
ll eular(ll n)//定义：1~n-1中有多少个数与n互素。 φ(1) = 1
{
    ll ans = n;
    for(int i = 2; i*i <= n; i++)
    {
        if(n % i == 0)
        {
            ans -= ans/i; 
            while(n % i == 0) 
                n /= i;
        }
    }
    if(n > 1)ans -= ans/n; 
    return ans;
}
int main(){
long long n;
cin>>n;
cout<<eular(n);

}