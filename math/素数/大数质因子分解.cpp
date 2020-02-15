#include<iostream>
#include<ctime>
#include<algorithm>
#include<map>
using namespace std;
typedef long long ll;
map<ll, int>m;
const int mod = 10000019;
const int times = 50;//测试50次
ll mul(ll a, ll b, ll m)
//求a*b%m
{
    ll ans = 0;
    a %= m;
    while(b)
    {
        if(b & 1)ans = (ans + a) % m;
        b /= 2;
        a = (a + a) % m;
    }
    return ans;
}
ll pow(ll a, ll b, ll m)
//a^b % m
{
    ll ans = 1;
    a %= m;
    while(b)
    {
        if(b & 1)ans = mul(a, ans, m);
        b /= 2;
        a = mul(a, a, m);
    }
    ans %= m;
    return ans;
}
bool Miller_Rabin(ll n, int repeat)//n是测试的大数，repeat是测试重复次数
{
    if(n == 2 || n == 3)return true;//特判
    if(n % 2 == 0 || n == 1)return false;//偶数和1

    //将n-1分解成2^s*d
    ll d = n - 1;
    int s = 0;
    while(!(d & 1)) ++s, d >>= 1;
    //srand((unsigned)time(NULL));在最开始调用即可
    for(int i = 0; i < repeat; i++)//重复repeat次
    {
        ll a = rand() % (n - 3) + 2;//取一个随机数,[2,n-1)
        ll x = pow(a, d, n);
        ll y = 0;
        for(int j = 0; j < s; j++)
        {
            y = mul(x, x, n);
            if(y == 1 && x != 1 && x != (n - 1))return false;
            x = y;
        }
        if(y != 1)return false;//费马小定理
    }
    return true;
}
ll gcd(ll a, ll b)
{
    return b == 0 ? a : gcd(b, a % b);
}
ll pollard_rho(ll n, ll c)//找到n的一个因子
{
    ll x = rand() % (n - 2) + 1;
    ll y = x, i = 1, k = 2;
    while(1)
    {
        i++;
        x = (mul(x, x, n) + c) + n;//不断调整x2
        ll d = gcd(y - x, n);
        if(1 < d && d < n)
            return d;//找到因子
        if(y == x)
            return n;//找到循环，返回n，重新来
        if(i == k)//一个优化
        {
            y = x;
            k <<= 1;
        }
    }
}
void Find(ll n, ll c)
{
    if(n == 1)return;//递归出口

    if(Miller_Rabin(n, times))//如果是素数，就加入
    {
        m[n]++;
        return;
    }

    ll p = n;
    while(p >= n)
        p = pollard_rho(p, c--);//不断找因子，知道找到为止，返回n说明没找到

    Find(p, c);
    Find(n / p, c);
}

int main()
{
    ll n;srand((unsigned)time(NULL));
    while(cin >> n)
    {
        m.clear();
        Find(n, rand() % (n - 1) + 1);//这是自己设置的一个数
        cout<<n<<" = ";
       
        for(map<ll ,int>::iterator it = m.begin(); it != m.end();)
        {
            cout<<it->first<<" ^ "<<it->second;

        if((++it) != m.end())
            cout<<" * ";
    }
        cout<<endl;
    }
    return 0;
}