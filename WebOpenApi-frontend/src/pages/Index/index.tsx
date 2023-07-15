import {ActionType, PageContainer, ProColumns, ProTable} from '@ant-design/pro-components';
import React, {useEffect, useRef, useState} from 'react';
import {Button, Empty, Layout, message, Space, Tag} from "antd";
import Search from 'antd/es/input/Search';
import { Content, Header } from 'antd/es/layout/layout';
import {
  applyInterfaceUsingPOST,
  getInterfaceStoreVoByPageUsingPOST
} from "@/services/WebOpenApi-backend/interfaceController";
import { history } from 'umi';

const headerStyle: React.CSSProperties = {
  textAlign: 'center',
  height: '64px',
  paddingInline: '30%',
  lineHeight: '64px',
  color: '#fff',
  background: '#fcfcfc',
};

const contentStyle: React.CSSProperties = {
  minHeight: 120,
  lineHeight: '120px',
};

const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [list, setList] = useState<API.InterfaceStoreVo[]>([]);
  const [total, setTotal] = useState<number>(0);
  const ref = useRef<ActionType>();

  const loadData = async (name= '',current = 1,pageSize = 6) => {
    setLoading(true);
    try {
      const res = await getInterfaceStoreVoByPageUsingPOST({
        name,
        current,
        pageSize,
      })
      setList(res.data.records);
      setTotal(res.data.total);
    }catch (error: any) {
      return [];
    }
    setLoading(false);
  }

  useEffect(() => {
    loadData();
  },[])

  const onSearch = (value: string) => {
    loadData(value);
  };

  /**
   * table 展示的列
   * */
  const columns: ProColumns<API.InterfaceStoreVo>[] = [
    {
      title: 'id',
      dataIndex: 'id',
      valueType: 'index',
      align: 'center',
    },
    {
      title: '接口名称',
      dataIndex: 'name',
      valueType: 'text',
      align: 'center',
    },
    {
      title: '描述',
      dataIndex: 'description',
      valueType: 'textarea',
      align: 'center',
    },
    {
      title: '请求类型',
      dataIndex: 'method',
      valueType: 'text',
      align: 'center',
      render: (_, record) => (
        <Space>
          <Tag color={record.method === 'GET' ? 'success' :
            record.method === 'POST' ? 'processing' :
              record.method === 'PUT' ? 'warning' : 'error'} key={record.method}>
            {record.method}
          </Tag>
        </Space>
      ),
    },
    {
      title: '状态',
      dataIndex: 'status',
      hideInForm: true,
      valueEnum: {
        0: {
          text: '已下线',
          status: 'Default',
        },
        1: {
          text: '在线',
          status: 'Processing',
        },
      },
      align: 'center',
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      valueType: 'dateTime',
      align: 'center',
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => {
        return record.applyFlag ? (
            <Button
              key="applyInterface"
              onClick={async () => {
                const res = await applyInterfaceUsingPOST({
                  interfaceInfoId: Number(record.id),
                  applyNum: record.applyNum
                });
                if (res.code === 200) {
                  message.success('申请成功');
                  // 刷新表格
                  await loadData();
                }
              }}
            >
              开通接口
            </Button>
        ) : (
          <Button
            type="primary"
            key="onlineUse"
            onClick={() => {
              history.push(`/interface/${record.id}`);
            }}
          >
            在线调用
          </Button>
        );
      },
    },
  ];

  return (
    <PageContainer title={"接口商店"}>
      <Header style={headerStyle}>
        <Search
          size={'large'}
          placeholder="请输入查询接口名称"
          onSearch={onSearch}
          enterButton
        />
      </Header>
      {list.length === 0 ? (
        <Empty />
      ) : (
        <Layout>
          <Content style={contentStyle}>
            <ProTable<API.InterfaceStoreVo>
              rowKey="id"
              toolBarRender={false}
              columns={columns}
              dataSource={list}
              loading={loading}
              actionRef={ref}
              pagination={{
                showTotal: (total) => {
                  return '总数：' + total;
                },
                total,
                pageSize: 6,
                onChange: (page, pageSize) => {
                  loadData('',page, pageSize);
                },
              }}
              search={false}
            />
          </Content>
        </Layout>
      )}
    </PageContainer>
  );
};

export default Index;
