#include <bits/stdc++.h>
using namespace std;
int main()
{
    vector<int> a,b;
    int n;
    cin >> n;
    for (int i = 1; i * i <= n; i++)
    {
        b.push_back(i*i);
    }
    int ans = 0, len = b.size();
    int up = b[len - 1];
    for(int i=0;i<len;i++){

        for(int j=1;j<=a[i];j++){
            if(b[i]%j==0)ans++;
              cout<<1;
        }
      
    }
    
    cout<<ans;
}