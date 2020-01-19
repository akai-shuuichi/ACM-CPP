#include<bits/stdc++.h>
#define Mod(a,b) a<b?a:a%b+b						
#define LL long long
#define N 100010
using namespace std;
 
LL n,q,mod,a;
map<LL,LL> mp;
 
LL qpow(LL x,LL n,LL mod)
{
    LL res=1;
    while(n)
    {
        if (n&1) res=Mod(res*x,mod),n--;
        x=Mod(x*x,mod); n>>=1;
    }
    return res;
}
 
LL phi(LL k)
{
    LL i,s=k,x=k;
    if (mp.count(k)) return mp[x];					
    for(i = 2;i * i <= k; i++)
    {
        if(k % i == 0) s = s / i * (i - 1);
        while(k % i == 0) k /= i;
    }
    if(k > 1) s = s / k * (k - 1);
    mp[x]=s; return s;
}
 
LL solve(LL l,LL r,LL mod)
{
    if (l==r||mod==1) return Mod(a,mod);				
    return qpow(a,solve(l+1,r,phi(mod)),mod);		
}
 
int main()
{
    scanf("%lld",&q);
    while(q--)
    {
        LL L,R;
        scanf("%lld%lld%lld",&a,&R,&mod);
        if(mod == 1)
            printf("0\n");
        else if(R== 0 || a == 1)
            printf("1\n");
        else
        printf("%lld\n",solve(1,R,mod)%mod);				

    }
    return 0;
}