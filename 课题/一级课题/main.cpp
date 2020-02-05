#include <bits/stdc++.h>
#include <windows.h>
using namespace std;
int a[6] = {0};
struct node
{
    int count;       /*房间号*/
    int flag;        /*用于确定是否有人存在*/
    int mode;        /*客房种类*/
    int money;       /*预付*/
    char name[6];    /*名字*/
    char idcard[20]; /*身份证号*/
    int timestr;     /*何时入住*/
    int timeend;     /*何时离开*/
    struct node *next;
};
struct node *phead = NULL; /*头结点*/
int charu(int num, node *p)
{
    struct node *s;
    while (p->next != NULL)
    {
        if (p->count == num)
            return 0;
        else if (p->next->count > num)
            break;
        else
            p = p->next;
    }
    if (p->next == NULL)
    {
        struct node *pnew = NULL;
        pnew = (struct node *)malloc(sizeof(struct node));
        pnew->count = num;
        pnew->next = NULL;
        s = pnew;
        p->next = pnew; //通过头指针连接各个结点；
        p = p->next;    //头指针后移，始终保证它在最后一个节点上！
    }
    else
    {
        s = (node *)malloc(sizeof(node)); //生成新节点
        s->count = num;
        s->next = p->next;
        p->next = s; //顺序绝对不变
    }
    printf("输入房间类型编号:\n");
    printf("1.普通单人间\n");
    printf("2.豪华单人间\n");
    printf("3.正常房间\n");
    printf("4.总统套房\n");
    printf("5.电竞无人组\n");
    cin >> s->mode;
    memset(s->idcard, 0, sizeof(s->idcard));
    memset(s->name, 0, sizeof(s->name));
    s->timeend = 0;
    s->money = 0;
    s->timestr = 0;
    printf("创建完成！\n");
    return 1;
}
void menu()
{
    printf("1.录入信息\n");
    printf("2.入住登记\n");
    printf("3.退房结算\n");
    printf("4.浏览客房信息\n");
    printf("5.信息查询\n");
    printf("6.修改信息\n");
    printf("7.保存价格信息\n");
    printf("8.保存客人信息\n");
    printf("9.退出\n");
    printf("请输入选项：");
}
void okiya()
{
start:
    int num;
    struct node *p = phead;
    printf("输入客房信息\n");
    printf("先输个房间号吧!\n");
    cin >> num;
    if (charu(num, p))
    {
        return;
    }
    else
    {
        cout << "房间已存在！" << endl;
        goto start;
    }
}
void message()
{
    cout << "输入要入住的房间" << endl;
    int a;
    cin >> a;
    struct node *p = phead;
    while (p != NULL)
    {
        if (p->count == a)
        {
            cout << "找到房间!" << endl;
            cout << "输入姓名";
            cin >> p->name;
            cout << endl
                 << "输入身份证号:";
            cin >> p->idcard;
            cout << endl
                 << "输入何时入住 格式：四位数:";
            cin >> p->timestr;
            cout << endl
                 << "输入何时离开";
            cin >> p->timeend;
            cout << endl
                 << "输入预付金额";
            cin >> p->money;
            cout << endl;
            p->flag = 1;
            return;
        }
        else
            p = p->next;
    }
    if (p == NULL)
    {
        cout << "妈耶，丢失的鬼屋啊" << endl;
    }
    return message();
}
void pf()
{
    struct node *p = phead->next;
    if (p == NULL)
    {
        cout << "什么也没发生" << endl;
        return;
    }
    cout << "房间编号 是否有人 客房种类 预付的钱 客人姓名 何时入住 何时离开 身份证号" << endl;

    while (p != NULL)
    {
        printf("%8d %8d %8d %8d %8s %8d %8d %s\n", p->count, p->flag, p->mode, p->money, p->name, p->timestr, p->timeend, p->idcard);
        p = p->next;
    }
    return;
}
int overp(int x, int y, int z)
{
    x = x / 100;
    y = y / 100;
    if (y > x)
    {
        return (y - x) * z;
    }
    else
        return (y - x + 24) * z;
}
void jiesuan()
{
    int i, k;
    cout << "是几号房需要结账呢:";
    cin >> i;
    struct node *p = phead->next; /*这是头结点*/
    while (p != NULL)
    {
        if (p->count == i)
        {
            if (p->flag)
            {
                cout << "回复0退房" << endl;
                cin >> k;
                if (k)
                    return;
                cout << "需要支付" << overp(p->timestr, p->timeend, a[p->mode]) - p->money << endl;
                p->flag = 0;
                memset(p->idcard, 0, sizeof(p->idcard));
                memset(p->name, 0, sizeof(p->name));
                /*清空数据*/
                return;
            }
        }
        p = p->next;
    }
    cout << "屋子不存在哦" << endl;
    return;
}
void price()
{
    int i;
    printf("输入房间类型编号:\n");
    printf("1.普通单人间\n");
    printf("2.豪华单人间\n");
    printf("3.正常房间\n");
    printf("4.总统套房\n");
    printf("5.电竞无人组\n");
    cin >> i;
    printf("您要修改的是%d号，为%d，您要修改为:", i, a[i]);
    cin >> a[i];
    cout << "修改成功" << endl;
}
void finding()
{
    cout << "输入你要查找到类型编号" << endl;
    cout << "1.房号" << endl;
    cout << "2.姓名" << endl;
    cout << "3.身份证号" << endl;
    int flag = 0 /*还未找到*/, i /*选项*/;
    struct node *p = phead->next;
    cin >> i;
    switch (i)
    {
    case 1:
    {
        cout << "输入房号" << endl;
        int n;
        cin >> n;
        while (p)
        {
            if (p->count == n)
            {
                flag = 1;
                break;
            }
            p = p->next;
        }
    }
    break;
    case 2:
    {
        cout << "输入姓名" << endl;
        char n[20];
        cin >> n;
        while (p)
        {
            if (!strcmp(p->name, n))
            {
                flag = 1;
                break;
            }
            p = p->next;
        }

        break;
    }
    case 3:
    {
        cout << "输入身份证号" << endl;
        char n[20];
        cin >> n;
        while (p)
        {
            if (!strcmp(p->name, n))
            {
                flag = 1;
                break;
            }
            p = p->next;
        }
        break;
    }
    }
    if (!flag)
    {
        cout << "什么也没找到哦" << endl;
        return;
    }
    cout << "房间编号 是否有人 客房种类 预付的钱 客人姓名 何时入住 何时离开 身份证号" << endl;
    printf("%8d %8d %8d %8d %8s %8d %8d %s\n", p->count, p->flag, p->mode, p->money, p->name, p->timestr, p->timeend, p->idcard);
    return;
}
void charge()
{
    cout << "按什么查找?" << endl;
    cout << "1.房号" << endl;
    cout << "2.姓名" << endl;
    cout << "3.身份证号" << endl;
    int flag = 0 /*还未找到*/, i /*选项*/;
    struct node *p = phead->next, *pre = phead;
    ;
    cin >> i;
    switch (i)
    {
    case 1:
    {
        cout << "输入房号" << endl;
        int n;
        cin >> n;
        while (p)
        {
            if (p->count == n)
            {
                flag = 1;
                break;
            }
            p = p->next;
            pre = pre->next;
        }
    }
    break;
    case 2:
    {
        cout << "输入姓名" << endl;
        char n[20];
        cin >> n;
        while (p)
        {
            if (!strcmp(p->name, n))
            {
                flag = 1;
                break;
            }
            p = p->next;
        }
        break;
    }
    case 3:
    {
        cout << "输入身份证号" << endl;
        char n[20];
        cin >> n;
        while (p)
        {
            if (!strcmp(p->name, n))
            {
                flag = 1;
                break;
            }
            p = p->next;
        }
        break;
    }
    }
    if (!flag)
    {
        cout << "什么也没找到哦" << endl;
        return;
    }
    cout << "房间编号 是否有人 客房种类 预付的钱 客人姓名 何时入住 何时离开 身份证号" << endl;
    printf("%8d %8d %8d %8d %8s %8d %8d %s\n", p->count, p->flag, p->mode, p->money, p->name, p->timestr, p->timeend, p->idcard);
    cout << "输入您要修改的属性编号" << endl;
    cout << "1.姓名" << endl;
    cout << "2.离开时间" << endl;
    cout << "3.预付金额" << endl;
    cout << "4.身份证号" << endl;
    cout << "5.客房种类" << endl;
    cout << "6.单价" << endl;
    cout << "7.删除该房间" << endl;
    int k;
    cin >> k;
    fflush(stdin); /*清空缓冲区*/
    switch (k)
    {
    case 6:
        price();
        break;
    case 1:
        cout << "输入新的姓名" << endl;
        memset(p->name, 0, sizeof(p->name)); /*清空原数据*/
        cin >> p->name;
        break;
    case 2:
        cout << "输入新时间" << endl;
        cin >> p->timeend;
        break;
    case 3:
        cout << "输入新的预付金额" << endl;
        cin >> p->money;
        break;
    case 4:
        cout << "输入新身份证号" << endl;
        memset(p->idcard, 0, sizeof(p->idcard)); /*清空原数据*/
        cin >> p->idcard;
        break;
    case 5:
        cout << "输入新房间种类" << endl;
        printf("输入房间类型编号:\n");
        printf("1.垃圾茅厕\n");
        printf("2.鬼屋\n");
        printf("3.正常房间\n");
        printf("4.总统套房\n");
        printf("5.牛逼哄哄的B416\n");
        cin >> p->mode;
        break;
    case 7:
        cout << "回复0删除" << endl;
        int x;
        cin >> x;
        if (x)
            return;
        if (p->next == NULL)
        {
            pre->next = NULL;
            free(p);
        }
        else
        {
            p->count = p->next->count;
            p->timeend = p->next->timeend;
            p->timestr = p->next->timestr;
            p->money = p->next->money;
            p->flag = p->next->flag;
            p->mode = p->next->mode;
            strcpy(p->next->name, p->name);
            strcpy(p->next->idcard, p->idcard);
            p->next = p->next->next;
            free(p);
        }
    }
    cout << "修改成功,新数据如下：" << endl;
    cout << "房间编号 是否有人 客房种类 预付的钱 客人姓名 何时入住 何时离开 身份证号" << endl;
    printf("%8d %8d %8d %8d %8s %8d %8d %s\n", p->count, p->flag, p->mode, p->money, p->name, p->timestr, p->timeend, p->idcard);
}
void saveprice()
{
    cout << "1.保存" << endl
         << "2.载入" << endl;
    int l;
    cin >> l;
    if (l == 1)
    {
        FILE *fp = fopen("money.txt", "w+"); /*用于保存价格*/
        fprintf(fp, "%d %d %d %d %d", a[1], a[2], a[3], a[4], a[5]);
        fclose(fp);
    }
    else
    {
        FILE *fp = fopen("money.txt", "r");
        fscanf(fp, "%d %d %d %d %d", &a[1], &a[2], &a[3], &a[4], &a[5]);
        fclose(fp);
    }
}
void nomo()
{
    int a, b, c, d, e, f;
    char x[20], y[20];
    struct node *p = phead, *q = phead->next;
    FILE *fp = fopen("message.txt", "r");
    if (fp == NULL)
    {
        cout << "没有数据载入" << endl;
        return;
    }
    while (fscanf(fp, " %d %d %d %d %d %d %s %s", &a, &b, &c, &d, &e, &f, x, y) != EOF)
    {
        q = (node *)malloc(sizeof(node));
        q->count = a, q->flag = b, q->mode = c, q->money = d, q->timestr = e, q->timeend = f;
        strcpy(q->name, x);
        strcpy(q->idcard, y);
        p->next = q;
        p = q;
    }
    fclose(fp);
}
void savemessage()
{
    cout << "1.保存" << endl
         << "2.载入" << endl;
    int l;
    cin >> l;
    struct node *p = phead->next;
    if (l == 1)
    {
        FILE *fp = fopen("message.txt", "w+"); /*用于保存信息*/
        while (p)
        {
            fprintf(fp, " %d %d %d %d %d %d %s %s", p->count, p->flag, p->mode, p->money, p->timestr, p->timeend, p->idcard, p->name);
            p = p->next;
        }
        cout << "保存成功" << endl;
        fclose(fp);
    }
    if (l == 2)
    {
        nomo();
    }
}
int main()
{
    //给头节点分配空间；
    phead = (struct node *)malloc(sizeof(struct node));
    phead->next = NULL; //防止野指针的出现;
    MessageBoxA(0, "欢迎使用！", "房管系统", 0);
    menu();
    int flag = 0;
    int choose;
    while (scanf("%d", &choose))
    {
        system("cls");
        switch (choose)
        {
        case 1:
            okiya();
            break;
        case 2:
            message();
            break;
        case 3:
            jiesuan();
            break;
        case 4:
            pf();
            break;
        case 5:
            finding();
            break;
        case 6:
            charge();
            break;
        case 7:
            saveprice();
            break;
        case 8:
            savemessage();
            break;
        case 9:
            return 0;
        }
        menu();
    }
}
