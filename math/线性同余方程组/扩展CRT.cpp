// 用于解决   
//  x%m1≡c1%m1
//  x%m2≡c2%m2
//  ......
// x%mn≡cn%mn
// 的最小解
#include <bits/stdc++.h>
using namespace std;
typedef long long LL;
const int inf = 0x3f3f3f3f;
const int maxn = 15;
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
    int nn;
    scanf("%d", &nn);
    for (int i = 1; i <= nn; ++i)
        scanf("%lld%lld", &m[i], &r[i]);
    long long ans = ex_crt(m, r, nn);
 
    if (ans == -1 || ans == 0 || ans == 1)
        printf("no solve!\n");
    else
        cout<<ans;
    return 0;
}
