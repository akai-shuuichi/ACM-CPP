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
int listins(List* head, int pos, int val) {//在pos前插入元素
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
        List* temp = new List(i);
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
int main() {
    auto head = new List;
    create(head, 10);//创建一个长度为10的链表
    //listupdate(head, 5, 424);//把第5个更新
    //listdel(head, 3);//删掉第三个
    //listins(head, 4, 666);//在第四个前插入值
    outlist(head);
    delrange(head, 2, 5);//删除大于2小于5的值
    //cout << getmax(head) << endl;;//输出最大值的位置
    //outlist(head);
    rm_rf(head);
    outlist(head);

}