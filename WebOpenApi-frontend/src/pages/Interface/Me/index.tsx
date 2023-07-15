import { ShareAltOutlined } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-components';
import { history } from '@umijs/max';
import {Badge, Card, Descriptions, Layout, List, Tag, Tooltip} from 'antd';
import Search from 'antd/es/input/Search';
import { Content, Header } from 'antd/es/layout/layout';
import React, { useEffect, useState } from 'react';
import indexStyle from './index.less';
import {
  getInterfaceMeVoListUsingGET
} from "@/services/WebOpenApi-backend/interfaceController";

const headerStyle: React.CSSProperties = {
  textAlign: 'center',
  height: '64px',
  paddingInline: '30%',
  lineHeight: '64px',
  color: '#fff',
  background: 'none',
};

const contentStyle: React.CSSProperties = {
  minHeight: 120,
  lineHeight: '120px',
};

const InterfaceMe: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [list, setList] = useState<API.InterfaceMeVo[]>([]);

  const loadData = async (search = '') => {
    setLoading(true);
    try {
      const res = await getInterfaceMeVoListUsingGET({
        search: search,
      })
      if (res.code === 200) {
        setList(res.data);
      }
    }catch (error: any) {
      return [];
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, []);

  const onSearch = (value: string) => {
    loadData(value);
  };

  const CardInfo: React.FC<{
    surplusNum: React.ReactNode;
    item: API.InterfaceMeVo
  }> = ({ surplusNum,item }) => (
    <div className={indexStyle.cardInfo}>
      <div>
        <Descriptions column={1} >
          <Descriptions.Item label="请求类型"><Tag color={item.method === 'GET' ? 'success' :
            item.method === 'POST' ? 'processing' : item.method === 'PUT' ? 'warning' : 'error'} key={item.method}>
            {item.method}
          </Tag></Descriptions.Item>
          <Descriptions.Item label="接口状态">{item.status ? (
            <Badge status="success" text={'在线'} />
          ) : (
            <Badge status="default" text={'下线'} />
          )}</Descriptions.Item>
          <Descriptions.Item label="接口URI">{item.uri}</Descriptions.Item>
        </Descriptions>
      </div>
      <div>
        <p>剩余调用次数</p>
        <p>{surplusNum}</p>
      </div>
    </div>
  );

  return (
    <PageContainer>
      <Layout>
        {/*搜索框*/}
        <Header style={headerStyle}>
          <Search
            size={'large'}
            placeholder="请输入接口名称"
            onSearch={onSearch}
            enterButton
          />
        </Header>
        {/*展示列表*/}
        <Content style={contentStyle}>
          <List<API.InterfaceMeVo>
            className={indexStyle.filterCardList}
            grid={{ gutter: 24, xxl: 3, xl: 2, lg: 2, md: 2, sm: 2, xs: 1 }}
            dataSource={list || []}
            loading={loading}
            renderItem={(item) => (
              <List.Item>
                <Card
                  hoverable
                  bodyStyle={{ paddingBottom: 5 }}
                  actions={[
                    <Tooltip title={item.description} key="share">
                      详情
                    </Tooltip>,
                    <Tooltip title="在线调用" key="share">
                      <div
                        onClick={() => {
                          history.push('/interface/' + item.id);
                        }}
                      >
                        在线调用
                      </div>
                    </Tooltip>,
                  ]}
                >
                  <Card.Meta title={item.name} className={indexStyle.cardInfo}/>
                  <div>
                    <CardInfo surplusNum={item.surplusNum} item={item}/>
                  </div>
                </Card>
              </List.Item>
            )}
          />
        </Content>
      </Layout>
    </PageContainer>
  );
};

export default InterfaceMe;
