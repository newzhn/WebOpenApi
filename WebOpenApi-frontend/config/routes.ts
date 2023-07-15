export default [
  {
    path: '/user',
    layout: false,
    routes: [
      { name: '登录', path: '/user/login', component: './User/Login' },
      { name: '注册', path: '/user/register', component: './User/Register' },
    ],
  },
  { path: '/index', name: '接口商店', icon: 'smile', component: './Index' },
  { path: '/interface/me', name: '我的接口', icon: 'smile', component: './Interface/Me' },
  { path: '/interface/:id', name: '接口在线调用', icon: 'smile', component: './Interface', hideInMenu: true },
  {
    path: '/admin',
    name: '管理页',
    icon: 'crown',
    access: 'canAdmin',
    routes: [
      { name: '接口管理', icon: 'table', path: '/admin/interface', component: './Admin/Interface' },
      { name: '接口统计', icon: 'analysis', path: '/admin/interface_statistic', component: './Admin/InterfaceStatistic' },
    ],
  },
  { path: '/', redirect: '/index' },
  { path: '*', layout: false, component: './404' },
];
