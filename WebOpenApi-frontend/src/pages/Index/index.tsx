import { PageContainer } from '@ant-design/pro-components';
import React, {useEffect, useState} from 'react';
import {List} from "antd";
import {getInterfaceListVoByPageUsingPOST} from "@/services/WebOpenApi-backend/interfaceInfoController";
const pageNum = 10

const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [list, setList] = useState<API.InterfaceInfoVo[]>([]);
  const [total, setTotal] = useState<number>(0);

  const loadData = async (current = 1,pageSize = pageNum) => {
    setLoading(true);
    try {
      const res = await getInterfaceListVoByPageUsingPOST({
        current,
        pageSize
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

  return (
    <PageContainer title={"接口开放平台"}>
      <List
        className="interface-list"
        loading={loading}
        itemLayout="horizontal"
        dataSource={list}
        renderItem={(item) => {
          const routeLink = `/interface_info/${item.id}`
          return <List.Item
            actions={[<a key={item.id} href={routeLink}>查看</a>]}
          >
            <List.Item.Meta
              title={<a href={routeLink}>{item.name}</a>}
              description={item.description}
            />
            <div>content</div>
          </List.Item>
        }
        }
        pagination={
          {
            showTotal(total) {
              return "总数：" + total;
            },
            pageSize:pageNum,
            total,
            onChange(page, pageSize) {
              loadData(page, pageSize);
            }
          }
        }
      />
    </PageContainer>
  );
};

export default Index;
