#include <bits/stdc++.h>
using namespace std;
struct List {
    int val;
    List* next, * pre;
    List(int val)/*创建一个节点(带值)*/:val(val), next(NULL), pre(NULL) {}
    List() /*创建一个节点(全空)*/ :val(0), next(NULL),pre(NULL){}//
    void insertpre(List* temp) {//插到前面
        temp->next = this;
        temp->pre = pre;
        pre->next = temp;
        pre = temp;
    }
    void insertnext(List* temp) {//插到后面
        temp->pre = this;
        temp->next = this->next;
        next->pre = temp;
        this->next = temp;
    }
    int del() {
        pre->next = next;
        next->pre = pre;
        int v=this->val;
        delete(this);
        return v;
    }
};
int listins(List* head, int pos, int val){//在pos前插入元素
    if (pos<1 || pos>head->val)return -1;
    head->val++;
    int i = 1;
    auto t = new List(0);
    t->val = val;
    for (auto tp = head->next; i <= pos; i++, tp = tp->next) {
        if (i == pos) {
            tp->insertpre(t);
        }
    }
    return 1;
}
int listdel(List* head, int pos) {//删除第i个元素
    if (pos<1 || pos>head->val)return -1;
    head->val--;
    int i = 1, ans;
    for (auto tp = head->next; i <= pos; i++, tp = tp->next) {
        if (i == pos) {
            tp->del();
            ans = tp->val;
        }
    }
    return ans;
}
int listupdate(List* head, int pos, int val) {//把第i个元素更新
    if (pos<1 || pos>head->val)return -1;
    int i = 1;
    for (auto tp = head->next; i <= pos; i++, tp = tp->next) {
        if (i == pos) {
            tp->val = val;
        }
    }
    return 1;
}
void create(List* head, int len) {//创建长度为len的链表
    head->next=new List;
    head->next->pre=head;
    head->val = len;
    head->pre = NULL;
    List* p = head;
    for (int i = 1; i <= len; i++) {
        int tp;
        cout<<"请输入第"<<i<<"个元素的值\n";
        cin>>tp;
        List* temp = new List(tp);
        p->next = temp;
        temp->pre = p;
        p = p->next;
        p->next = NULL;//永远让下一位是null
    }
}
List* init(int len) {//创建长度为len的链表
    auto head = new List;
    head->next=new List;
    head->next->pre=head;
    head->val = len;
    head->pre = NULL;
    List* p = head;
    for (int i = 1; i <= len; i++) {
        List* temp = new List(i);
        p->next = temp;
        temp->pre = p;
        p = p->next;
        p->next = NULL;
    }
    return head;
}
void outlist(List* head) {//输出链表
    for (List* tp = head->next; tp; tp = tp->next) {
        cout << tp->val << " ";
    }
    cout << endl;
}
int listget(List* head, int pos) {//获取第pos个元素
    if (pos<1 || pos>head->val)return -1;
    int i = 1;
    for (auto tp = head->next; i <= pos; i++, tp = tp->next) {
        if (i == pos) {
            return (tp->val);
        }
    }
    return 1;
}
int getmax(List* head) {
    int ans = INT32_MIN, pos = 1,i=1;
    for (auto tp = head->next; tp;  tp = tp->next,i++) {
        if (tp->val>ans) {
            pos = i;
            ans = tp->val;
        }
    }
    return pos;

}
void listsort(List* start, List* end = NULL)
{
    if (start == NULL || start == end) return;
    auto p1 = start, p2 = start->next;
    while (p2 != end) {
        if (p2->val < start->val) {
            p1 = p1->next;
            swap(p1->val, p2->val);
        }
        p2 = p2->next;
    }
    swap(p1->val, start->val);
    listsort(start, p1);
    listsort(p1->next, end);
}
int delrange(List* head, int l, int r) {//删除(l,r)的值
    int ans = 0;
    for (auto tp = head->next; tp; tp = tp->next) {
        if (tp->val > l&&tp->val<r) {
            auto temp = tp->pre;
            tp->del();
            ans++;
            tp = temp;
        }
    }
    return ans;
}
void rm_rf(List* head) {
    for (auto tp = head->next; tp; tp = tp->next) {
        auto temp = tp->next;
        tp->del();
        tp = temp;

    }
    head->next = NULL;

}
void listrev(List *head){
    for(auto tp=head->next;tp;){
        auto temp=tp->next;
        if(tp->next) {
            tp->pre->next = tp->next;
            tp->next->pre = tp->pre;//暂时删除此节点
        }
        else{//防止空指针
            tp->pre->next=tp->next;
        }
        head->insertnext(tp);
        tp=temp;
    }
}
int cmp(List a,List b){
    return a.val<b.val;
}
void shabicaihuiyongdemaopaopaixu(List *head){
    for(auto i=head->next;i;i=i->next){
        for(auto j=head->next;j!=i;j=j->next){
            if(cmp(*i,*j))swap(i->val,j->val);
        }
    }
}
string listfind(List *head,int val){
    string ans="";
    int i=1;
    for (auto tp = head->next; tp; tp = tp->next,i++) {
        if (tp->val==val) {
            ans.push_back(i+'0');
            ans.push_back(',');
        }
    }
    ans.erase(ans.length()-1,1);
    return ans;
}
string menu="1.创建链表\n2.输出链表\n3.插入节点\n4.删除节点\n5.查找元素\n0.退出\n";
int main() {
    auto head = new List;
    loop:;
    cout<<menu;
    int n;
    cin>>n;
    switch (n){
        case 1:{
            int len;
            cout<<"输入长度\n";
            cin>>len;
            create(head,len);
            break;
        }
        case 2:{outlist(head);break;}
        case 3:{
            int temp,pos;
            cout<<"输入插入的值(插前面)\n";
            cin>>temp;
            cout<<"输入位置\n";
            cin>>pos;
            listins(head,pos,temp);
            break;
        }
        case 4:{
            int pos;
            cout<<"输入删除位置\n";
            cin>>pos;
            listdel(head,pos);
            break;
        }
        case 5:{
            int val;
            cout<<"输入查找元素\n";
            cin>>val;
            cout<<"位置是："<<listfind(head,val)<<endl;
            break;
        }
        case 0:return 0;
        default: ;//do nothing

    }
    goto loop;
    return 0;

}