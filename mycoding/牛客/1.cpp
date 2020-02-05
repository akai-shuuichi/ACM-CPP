#include <bits/stdc++.h>
using namespace std;
 void ex_gcd(LL a, LL b, LL &d, LL &x, LL &y)
{
    if (!b)
    {
        d = a, x = 1, y = 0;
    }
    else
    {
        ex_gcd(b, a % b, d, y, x);
        y -= x * (a / b);
    }
}
LL ex_crt(LL *m, LL *r, int n)
{
    LL M = m[1], R = r[1], x, y, d;
    for (int i = 2; i <= n; ++i)
    {
        ex_gcd(M, m[i], d, x, y);
        if ((r[i] - R) % d)
            return -1;
        x = (r[i] - R) / d * x % (m[i] / d);
        R += x * M;
        M = M / d * m[i];
        R %= M;
    }
    return R > 0 ? R : R + M;
}
LL m[maxn], r[maxn]; 
int main()
{
    int n,m;
    cin>>n>>m;
    string str;
    cin>>str;
    vector<int> Hash(2,0);
    int res = 0, maxNum = 0;
    for(int i=0,j=0;j<str.size();j++){
        Hash[str[j]-'0']++;
        maxNum = max(maxNum, Hash[str[j]-'0']);
        while(j-i+1-maxNum>m)
            Hash[str[i++]-'0']--;
        res = max(res,j-i+1);
    }
    cout<<res<<endl;
}