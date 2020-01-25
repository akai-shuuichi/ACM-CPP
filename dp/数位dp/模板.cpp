#include <bits/stdc++.h>
using namespace std;
typedef long long ll;
ll l,r;
int a[20];
//dp[i][j]表示高一位为j的i位数满足条件的个数
ll dp[20][20];
//pos:当前枚举数位 pre:上一位数 status:是否有前导零（0为有） limit:是否有上限限制
int check(int i,int pre){
return abs(i-pre)<2;//此处填写条件 比如相邻两位之差小于2就这样写


}
int dfs(int pos,int pre,int status,bool limit){
    if(pos<0){
        return 1;
    }
    if(status && !limit && dp[pos][pre]!=-1){
        return dp[pos][pre];
    }
    //是否有上限限制
    int up=limit?a[pos]:9;
    ll ans=0;
    if(status==0){
        for(int i=0;i<=up;i++){
            ans+=dfs(pos-1,i,i,limit&&(i==up));
        }
    }else{
        for(int i=0;i<=up;i++){
            if(check(i,pre)){
                continue;
            }
            ans+=dfs(pos-1,i,1,limit&&(i==up));
        }
    }
    if(status && !limit){
        dp[pos][pre]=ans;
    }
    return ans;
}
int solve(ll x){
    int len=0;
    while(x){
        a[len++]=x%10;
        x/=10;
    }
    memset(dp,-1,sizeof(dp));
    return dfs(len-1,0,0,true);
}
int main(void){
    scanf("%lld%lld",&l,&r);
    int ans=solve(r)-solve(l-1);
    printf("%d\n",ans);
    return 0;
}
