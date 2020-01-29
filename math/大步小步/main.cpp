// 解a^x%n≡b%n的x
#include<bits/stdc++.h>
using namespace std;
typedef long long ll;

ll gcd(ll a, ll b)
{
    return b ? gcd(b, a%b) : a;
}

inline ll fast_exp(ll x, ll n, ll p)
{
    ll z = 1;
    for (ll y = x%p; n; y = y*y%p, n >>= 1)
        if (n & 1)
            z = z*y%p;
    return z;
}

ll extend_bsgs(ll a, ll y, ll p)
{
    a %= p;
    y %= p;
    if (a == 0)
        return y > 1 ? -1 : y == 0 && p > 1;
    ll g, c = 0, q = 1;
    while ((g = gcd(a, p)) != 1) {
        if (y == 1)
            return c;
        if (y % g)
            return -1;
        ++c;
        y /= g;
        p /= g;
        q = a/g*q%p;
    }

    map<ll, ll> x;
    ll m = sqrt(p);
    for (ll i = 1, t = y*a%p; i <= m; ++i, t = t*a%p)
        x[t] = i;
    for (ll i = m, t = fast_exp(a, m, p); i-m < p-1; i += m)
        if (q = q*t%p, x.count(q))
            return i-x[q]+c;
    return -1;
}

int main()
{
    ll a, p, b;
    while (scanf("%lld %lld %lld", &a, &p, &b), p) {
        ll ans = extend_bsgs(a, b, p);
        if (ans == -1)
            puts("No Solution");
        else
            printf("%lld\n", ans);
    }
    return 0;
}