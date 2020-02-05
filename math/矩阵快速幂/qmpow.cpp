//给定fib前两项，满足 xi=a*x(i-1)+b*x(i-2)    此处i值超越long long最大值 需使用char
#pragma GCC optimize(2)
#include <bits/stdc++.h>
using namespace std;
int mod = 1e9 + 7;
struct mat
{
    long long val[2][2];
    mat mul(mat x)
    {
        mat tp = {0};
        for (int i = 0; i < 2; ++i)
        {
            for (int j = 0; j < 2; ++j)
            {
                tp.val[i][j] += (val[i][0] * x.val[0][j] + val[i][1] * x.val[1][j]) % mod;
            }
        }
        return tp;
    }
    void me()
    {
        val[0][0] = val[1][1] = 1;
    }
};

inline mat matpow(mat a, int n)
{
    mat ans = {0};
    ans.val[0][0] = ans.val[1][1] = 1;

    while (n)
    {
        if (n & 1)
        {
            ans = ans.mul(a);
        }
        a = a.mul(a);
        n >>= 1;
    }
    return ans;
}
const int man = 1e6 + 7;
char temp[man];
mat niubipow(mat tp, int len)
{
    mat ans = {0};
    ans.val[0][0] = ans.val[1][1] = 1;
    for (register int i = len - 1; i >= 0; --i)
    {
        int tlen = temp[i] - '0';
        if (tlen)
            ans = ans.mul(matpow(tp, tlen));
        tp = matpow(tp, 10);
    }
    return ans;
}
int main()
{
    int a, b, c, d, n;
    scanf("%d%d%d%d%s%d", &a, &b, &c, &d, temp, &mod);
    n = strlen(temp);
    mat tp = {c, 1, d, 0};
    mat ans = niubipow(tp, n);
    mat tps = {b, a};
    tps = tps.mul(ans);
    printf("%lld", tps.val[0][1]);
}


